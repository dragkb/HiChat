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
import com.alex.hichat.R
import com.alex.hichat.Services.AuthService
import com.alex.hichat.Services.UserDataService
import com.alex.hichat.Utilities.BROADCAST_USER_DATA_CHANGE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        hideKeyboard() // Hide the soft keyboard

        LocalBroadcastManager.getInstance(this).registerReceiver(userDataChangeReceiver,
                IntentFilter(BROADCAST_USER_DATA_CHANGE))

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
                        // Create channle with the channel name and description
                        hideKeyboard()
                    }
                    .setNegativeButton("Cancel") { dialogInterface, i ->
                        // Cancel and close the dialog
                        hideKeyboard()
                    }
                    .show()
        }
    }

    fun sendMessageBtnClicked(view: View) {

    }

    // Hiding soft keyboard
    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputManager.isAcceptingText) {
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}
