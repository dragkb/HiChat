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
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import com.alex.hichat.Adapters.MessageAdapter
import com.alex.hichat.Model.Channel
import com.alex.hichat.Model.Message
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
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity() {

    // Declaring socket URL. When user connected you can track the connection on heroku API server: "a user connected".
    val socket = IO.socket(SOCKET_URL)
    lateinit var channelAdapter: ArrayAdapter<Channel>
    lateinit var messageAdapter: MessageAdapter
    // This var is used if we're not logged in then we don't have any selected channels, that's why = null
    // We need this for handling downloaded messages for one channel only, not the for all channels
    var selectedChannel: Channel? = null

    private fun setupAdapters() {
        channelAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, MessageService.channels)
        channel_list.adapter = channelAdapter

        messageAdapter = MessageAdapter(this, MessageService.messages)
        messageListView.adapter = messageAdapter
        val layoutManager = LinearLayoutManager(this)
        messageListView.layoutManager = layoutManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Socket is on and ready to connect
        socket.connect()
        // save it to Data base and output to Log if created
        // Socketio - Send out(emit) message from API to those connected in the room
        socket.on("channelCreated", onNewChannel)
        // For message listening
        socket.on("messageCreated", onNewMessage)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        setupAdapters()
        LocalBroadcastManager.getInstance(this).registerReceiver(userDataChangeReceiver,
                IntentFilter(BROADCAST_USER_DATA_CHANGE))

        // Updates UI for channel name if clicked on the channel #name
        channel_list.setOnItemClickListener { _, _, i, _ ->
            selectedChannel = MessageService.channels[i]
            drawer_layout.closeDrawer(GravityCompat.START)
            updateWithChannel()
        }

        // Saving logging state if Logged in
        if (App.sharedPrefs.isLoggedIn) {
            AuthService.findUserByEmail(this) {}
        }
    }

    override fun onDestroy() {
        // disconnect the socket
        socket.disconnect()
        // Unregister broadcast
        LocalBroadcastManager.getInstance(this).unregisterReceiver(userDataChangeReceiver)
        super.onDestroy()
    }

    private val userDataChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (App.sharedPrefs.isLoggedIn) {
                userNameNavHeader.text = UserDataService.name
                userEmailNavHeader.text = UserDataService.email
                val resourceId = resources.getIdentifier(UserDataService.avatarName, "drawable", packageName)
                userImgNavHeader.setImageResource(resourceId)
                // Changing the background color to the one which was picked by user
                userImgNavHeader.setBackgroundColor(UserDataService.returnAvatarColor(UserDataService.avatarColor))
                loginBtnNavHeader.text = "Logout"

                // Downloading channels here
                MessageService.getChannels { complete ->
                    if (complete) {
                        // If we have selected channel then assign it to selectedChannel
                        if (MessageService.channels.count() > 0) {
                            selectedChannel = MessageService.channels[0]
                            // If no channels no reason to notify that data changed
                            channelAdapter.notifyDataSetChanged()
                            updateWithChannel()
                        }
                    }
                }
            }
        }
    }

    // Update UI(mainChannelName) element with particular channel
    fun updateWithChannel() {
        mainChannelName.text = "#${selectedChannel?.name}"
        // Download messages for channel
        if(selectedChannel != null) {
            MessageService.getMessages(selectedChannel!!.id) { complete ->
                if(complete) {
                    // update messages
                    messageAdapter.notifyDataSetChanged()
                    // if messages more than 1 then scroll to the last one (messageAdapter.itemCount - 1)
                    if (messageAdapter.itemCount > 0) {
                        messageListView.smoothScrollToPosition(messageAdapter.itemCount - 1)
                    }
                }
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
        if (App.sharedPrefs.isLoggedIn) {
            UserDataService.logout()
            //After logout reload data messages and channels
            channelAdapter.notifyDataSetChanged()
            messageAdapter.notifyDataSetChanged()
            userNameNavHeader.text = ""
            userEmailNavHeader.text = ""
            userImgNavHeader.setImageResource(R.drawable.profiledefault)
            userImgNavHeader.setBackgroundColor(Color.TRANSPARENT)
            loginBtnNavHeader.text = "Login"
            mainChannelName.text = "Please log in"
        } else {
            // if we are not logged in yet then we need to click on login and moved to activity after logged in
            val loginActivityIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginActivityIntent)
        }

    }

    fun addChannelBtnClicked(view: View) {
        if (App.sharedPrefs.isLoggedIn) {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.add_channel_dialog, null)

            builder.setView(dialogView)
                    .setPositiveButton("Add") { _, _ ->
                        // perform some logic when clicked
                        val nameTextField = dialogView.findViewById<TextView>(R.id.addChannelNameTxt)
                        val descTextField = dialogView.findViewById<TextView>(R.id.addChannelDescTxt)

                        // channelName needs to create an emit for socket channel
                        val channelName = nameTextField.text.toString()
                        // channelDesc needs to create an emit for socket channel
                        val channelDesc = descTextField.text.toString()

                        // Create a channel with the channel name and description
                        // Socektio - emit() method sending info from out client to API
                        socket.emit("newChannel", channelName, channelDesc)
                    }
                    .setNegativeButton("Cancel") { _, _ ->
                        // Cancel and close the dialog
                    }
                    .show()
        }
    }

    // Listens for emit and store data to Channel class. Saves it to Data base and output to Log if created
    private val onNewChannel = Emitter.Listener { args ->
        if(App.sharedPrefs.isLoggedIn) {
            runOnUiThread {
                // Back from main thread to UiThread and update out listView
                val channelName = args[0] as String
                // args is an Array of type Any so we need to cast it String
                val channelDescription = args[1] as String
                val channelId = args[2] as String

                // Create new instance for our new channel
                val newChannel = Channel(channelName, channelDescription, channelId)
                // Then store new channel instance to an ArrayList of channels
                MessageService.channels.add(newChannel)

                channelAdapter.notifyDataSetChanged() // Immediately updates when channel is created
            }
        }
    }

    // Listens for emit to API.
    private val onNewMessage = Emitter.Listener { args ->
        if(App.sharedPrefs.isLoggedIn) {
            runOnUiThread {
                val channelId = args[2] as String
                if (channelId == selectedChannel?.id) {
                    val msgBody = args[0] as String
                    // val msgId = args[1] as String // We skipped this because it's never used in the code
                    val userName = args[3] as String
                    val userAvatar = args[4] as String
                    val userAvatarColor = args[5] as String
                    val id = args[6] as String
                    val timeStamp = args[7] as String

                    val newMessage = Message(msgBody, userName, channelId, userAvatar, userAvatarColor, id, timeStamp)
                    // Storing message to an ArrayList
                    MessageService.messages.add(newMessage)
                    messageAdapter.notifyDataSetChanged()
                    messageListView.smoothScrollToPosition(messageAdapter.itemCount - 1)
                }
            }
        }
    }

    fun sendMessageBtnClicked(view: View) {
        if (App.sharedPrefs.isLoggedIn && messageTxtField.text.isNotEmpty() && selectedChannel != null) {
            val userId = UserDataService.id
            val channelId = selectedChannel!!.id
            socket.emit("newMessage", messageTxtField.text.toString(), userId, channelId,
                    UserDataService.name, UserDataService.avatarName, UserDataService.avatarColor)
            messageTxtField.text.clear()
            hideKeyboard()
        }
    }

    // Hiding soft keyboard
    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputManager.isAcceptingText) {
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}
