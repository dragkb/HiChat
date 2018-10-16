package com.alex.hichat.Services

import android.util.Log
import com.alex.hichat.Controller.App
import com.alex.hichat.Model.Channel
import com.alex.hichat.Model.Message
import com.alex.hichat.Utilities.GET_URL_CHANNELS
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONException

object MessageService {

    // ArrayList for channels
    val channels = ArrayList<Channel>()
    // ArrayList for messages
    val messages = ArrayList<Message>()

    // JSON request for find all channels
    fun getChannels(complete: (Boolean) -> Unit) {
        val channelsRequest = object  : JsonArrayRequest(Method.GET, GET_URL_CHANNELS, null, Response.Listener { response ->
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
}