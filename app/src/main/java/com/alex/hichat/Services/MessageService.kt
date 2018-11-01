package com.alex.hichat.Services

import android.support.annotation.VisibleForTesting
import android.util.Log
import com.alex.hichat.Controller.App
import com.alex.hichat.Model.Channel
import com.alex.hichat.Model.Message
import com.alex.hichat.Utilities.IdlingResourceHolder
import com.alex.hichat.Utilities.URL_GET_CHANNELS
import com.alex.hichat.Utilities.URL_GET_MESSAGES
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONException

object MessageService {

    // ArrayList for channels
    @VisibleForTesting
    val channels = ArrayList<Channel>()
    // ArrayList for messages
    val messages = ArrayList<Message>()

    // JSON request for find all channels
    fun getChannels(complete: (Boolean) -> Unit) {
        // Idling resource increment()
        IdlingResourceHolder.networkIdlingResource.increment()
        //TODO wrap for JsonArrayRequest idling resource
        val channelsRequest = object : JsonArrayRequest(
            Method.GET, URL_GET_CHANNELS, null, Response.Listener { response ->
            // Looping through array of JSON objects to get all channels available
            try {
                for (x in 0 until response.length()) {
                    val channel = response.getJSONObject(x)
                    val name = channel.getString("name")
                    val channelDescr = channel.getString("description")
                    val channelId = channel.getString("_id")

                    val newChannel = Channel(name, channelDescr, channelId)
                    this.channels.add(newChannel) // Got all data and add to channels array
                    complete(true)
                }
            } catch (e: JSONException) {
                Log.d("JSON", "EXC " + e.localizedMessage)
            } finally {
                // Idling resource decrement()
                IdlingResourceHolder.networkIdlingResource.decrement()
            }
        }, Response.ErrorListener { error ->
                    Log.d("ERROR", "Could not retrieve channels")
                    complete(false)
        }) {

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", "Bearer ${App.sharedPrefs.authToken}")
                return headers
            }
        }
        App.sharedPrefs.requestQueue.add(channelsRequest)
    }

    // Downloading messages
    fun getMessages(channelId: String, complete: (Boolean) -> Unit) {
        // Unique URL for specific channel
        val url = "$URL_GET_MESSAGES$channelId"
        // Idling resource increment()
        IdlingResourceHolder.networkIdlingResource.increment()
        val messagesRequest = object : JsonArrayRequest(
            Method.GET, url, null, Response.Listener { response ->
            clearMessages() // Clear messages before type in
            try {
                for (i in 0 until response.length()) {
                    val message = response.getJSONObject(i)
                    val messageBody = message.getString("messageBody")
                    val channelId = message.getString("channelId")
                    val id = message.getString("_id")
                    val userName = message.getString("userName")
                    val userAvatar = message.getString("userAvatar")
                    val userAvatarColor = message.getString("userAvatarColor")
                    val timeStamp = message.getString("timeStamp")

                    val newMessage = Message(
                        messageBody, userName, channelId, userAvatar,
                        userAvatarColor, id, timeStamp)
                    // Add to arrayList new object of message
                    this.messages.add(newMessage)
                }
                complete(true)
            } catch (e: JSONException) {
                Log.d("JSON", "EXC" + e.localizedMessage)
                complete(false)
            } finally {
                // Idling resource decrement()
                IdlingResourceHolder.networkIdlingResource.decrement()
            }
        }, Response.ErrorListener { error ->
                try {
                    Log.d("ERROR", "Could not retrieve message")
                    complete(false)
                } finally {
                    // Idling resource decrement()
                    IdlingResourceHolder.networkIdlingResource.decrement()
                }
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", "Bearer ${App.sharedPrefs.authToken}")
                return headers
            }
        }
        App.sharedPrefs.requestQueue.add(messagesRequest)
    }

    fun clearMessages() {
        messages.clear()
    }

    fun clearChannels() {
        channels.clear()
    }
}