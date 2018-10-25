package com.alex.hichat.Services

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.alex.hichat.Controller.App
import com.alex.hichat.Utilities.*
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject

object AuthService {

    // For volley API we need context, and the body(check Postman body) of our request and
    // completion handler to understand if our request finished successfully or not
    fun registerUser(email: String, password: String, complete: (Boolean) -> Unit) {
        // Increment idling resource
        IdlingResourceHolder.networkIdlingResource.increment()

        // Json object that we are passing
        val jsonBody = JSONObject()
        jsonBody.put("email", email)        // Key-value for email
        jsonBody.put("password", password)  // Key-value for password
        val requestBody = jsonBody.toString()

        // Register user by providing Method - POST, URL for API, Success response and Error response
        val registerRequest = object : StringRequest(
            Method.POST, URL_REGISTER, Response.Listener { response ->
            println(response)
            // If success print success message and set complete to true. This is where we parse the String
            complete(true)
        }, Response.ErrorListener {
            // if error print to logcat DEBUG and set complete to false
            error ->
            Log.d("ERROR", "Could not register user: $error")
        }) {
            // Same as Postman Content-type
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            // Return a String from JSONObject and convert to ByteArray
            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        // Add created request to Volley request Queue
        App.sharedPrefs.requestQueue.add(registerRequest)
    }

    fun loginUser(email: String, password: String, complete: (Boolean) -> Unit) {

        // Increment idling resource
        IdlingResourceHolder.networkIdlingResource.increment()

        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()

        val loginRequest = object : JsonObjectRequest(
            Method.POST, URL_LOGIN, null, Response.Listener { response ->
            // This is where we parse the String
            // getString throws JSONException, so we need try catch
            try {
                // Parse user's email and assigns it to userEmail instance variable
                App.sharedPrefs.userEmail = response.getString("user")
                // Parse Json token and assign it to instance variable authToken
                App.sharedPrefs.authToken = response.getString("token")
                // isLoggedIn = true because we logged in
                App.sharedPrefs.isLoggedIn = true
                complete(true)
            } catch (e: JSONException) {
                // Show the exception in debug logcat if fails
                Log.d("JSON", "EXC: " + e.localizedMessage)
                complete(false)
            }
        }, Response.ErrorListener { error ->
            // This is where we deal with errors
            Log.d("ERROR", "Was not able to login: $error")
            complete(false)
        }) {

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        App.sharedPrefs.requestQueue.add(loginRequest)

        // Decrement Idling resource
//        IdlingResourceHolder.networkIdlingResource.decrement()
    }

    fun createUser(
        name: String,
        email: String,
        avatarName: String,
        avatarColor: String,
        complete: (Boolean) -> Unit
    ) {
        // Increment idling resource
        IdlingResourceHolder.networkIdlingResource.increment()

        // All parameters are referred to the Postman request body
        val jsonBody = JSONObject()
        jsonBody.put("name", name)
        jsonBody.put("email", email)
        jsonBody.put("avatarName", avatarName)
        jsonBody.put("avatarColor", avatarColor)
        val requestBody = jsonBody.toString()

        val createRequest = object : JsonObjectRequest(
            Method.POST, URL_CREATE_USER, null, Response.Listener { response ->

            try {
                UserDataService.name = response.getString("name")
                UserDataService.email = response.getString("email")
                UserDataService.avatarName = response.getString("avatarName")
                UserDataService.avatarColor = response.getString("avatarColor")
                UserDataService.id = response.getString("_id")
                complete(true)
            } catch (e: JSONException) {
                Log.d("JSON", "EXC: " + e.localizedMessage)
                complete(false)
            }
        }, Response.ErrorListener { error ->
            Log.d("ERROR", "Could not add user: $error")
            complete(false)
        }) {

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", "Bearer ${App.sharedPrefs.authToken}")
                return headers
            }
        }

        App.sharedPrefs.requestQueue.add(createRequest)
    }

    // Don't need the body since its only Get request
    fun findUserByEmail(context: Context, complete: (Boolean) -> Unit) {
        // Increment idling resource
        IdlingResourceHolder.networkIdlingResource.increment()

        val findRequest = object : JsonObjectRequest(
            Method.GET, "$URL_FIND_USER_BY_EMAIL${App.sharedPrefs.userEmail}",
            null, Response.Listener { response ->

            try {
                UserDataService.name = response.getString("name")
                UserDataService.email = response.getString("email")
                UserDataService.avatarName = response.getString("avatarName")
                UserDataService.avatarColor = response.getString("avatarColor")
                UserDataService.id = response.getString("_id")

                // Sends broadcast to activities that data has been changed
                val userDataChange = Intent(BROADCAST_USER_DATA_CHANGE)
                LocalBroadcastManager.getInstance(context).sendBroadcast(userDataChange)
                complete(true)
            } catch (e: JSONException) {
                Log.d("JSON", "EXC" + e.localizedMessage)
            }
        }, Response.ErrorListener { error ->
            Log.d("ERROR", "Could not find user by email")
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

        App.sharedPrefs.requestQueue.add(findRequest)
    }
}