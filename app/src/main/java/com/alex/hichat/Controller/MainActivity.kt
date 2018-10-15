package com.alex.hichat.Controller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.alex.hichat.Model.Channel
import com.alex.hichat.R
import com.alex.hichat.Services.AuthService
import com.alex.hichat.Services.MessageService
import com.alex.hichat.Services.UserDataService
import com.alex.hichat.Utilities.BROADCAST_USER_DATA_CHANGE
import com.alex.hichat.Utilities.SOCKET_URL
import io.socket.client.IO
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity() {

    // Declaring socket URL. When user connected you can track the connection on heroku API server: "a user connected".
    val socket = IO.socket(SOCKET_URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        socket.connect() // Socket is on and ready to connect
        socket.on("channelCreated", onNewChannel)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(userDataChangeReceiver,
                IntentFilter(BROADCAST_USER_DATA_CHANGE))
        super.onResume()
    }

    override fun onDestroy() {
        socket.disconnect()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(userDataChangeReceiver)
        super.onDestroy()
    }

    private val userDataChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (AuthService.isLoggedIn){
                userNameNavHeader.text = UserDataService.name
                userEmailNavHeader.text = UserDataService.email
                val resourceId = resources.getIdentifier(UserDataService.avatarName, "drawable", packageName)
                userImgNavHeader.setImageResource(resourceId)
                // Changing the background color to the one which was picked by user
                userImgNavHeader.setBackgroundColor(UserDataService.returnAvatarColor(UserDataService.avatarColor))
                loginBtnNavHeader.text = "Logout"
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun loginBtnNavClicked(view: View) {
        // if login then we need to see logout and moved to activity after logout
        if (AuthService.isLoggedIn){
            UserDataService.logout()
            userNameNavHeader.text = ""
            userEmailNavHeader.text = ""
            userImgNavHeader.setImageResource(R.drawable.profiledefault)
            userImgNavHeader.setBackgroundColor(Color.TRANSPARENT)
            loginBtnNavHeader.text = "Login"
        } else {
            // if we are not logged in yet then we need to click on login and moved to activity after logged in
            val loginActivityIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginActivityIntent)
        }

    }

    fun addChannelBtnClicked(view: View) {

        if (AuthService.isLoggedIn) {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.add_channel_dialog, null)

            builder.setView(dialogView)
                    .setPositiveButton("Add") { dialogInterface, i ->
                        // perform some logic when clicked
                        val nameTextField = dialogView.findViewById<TextView>(R.id.addChannelNameTxt)
                        val descTextField = dialogView.findViewById<TextView>(R.id.addChannelDescTxt)
                        val channelName = nameTextField.text.toString()
                        val channelDesc = descTextField.text.toString()

                        // Create a channel with the channel name and description
                        // emit() method sending info from out client to API
                        socket.emit("newChannel", channelName, channelDesc)
                    }
                    .setNegativeButton("Cancel") { dialogInterface, i ->
                        // Cancel and close the dialog
                    }
                    .show()
        }
    }

    private val onNewChannel = Emitter.Listener { args ->
        runOnUiThread {
            val channelName = args[0] as String
            val channelDescription = args[1] as String
            val channelId = args[2] as String

            val newChannel = Channel(channelName, channelDescription, channelId)
            MessageService.channels.add(newChannel)
        }


    }

    fun sendMessageBtnClicked(view: View) {
        hideKeyboard()
    }

    // Hiding soft keyboard
    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputManager.isAcceptingText) {
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}
