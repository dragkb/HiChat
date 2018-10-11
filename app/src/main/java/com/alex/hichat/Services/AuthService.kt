package com.alex.hichat.Services

import android.content.Context
import android.util.Log
import com.alex.hichat.Utilities.URL_LOGIN
import com.alex.hichat.Utilities.URL_REGISTER
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

object AuthService {

    var isLoggedIn = false
    var userEmail = ""
    var authToken = ""

    // For volley API we need context, then email and password for GET request and
    // completion handler to understand if our request finished successful or not
    fun registerUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit) {

        // Json object that we are passing
        val jsonBody = JSONObject()
        jsonBody.put("email", email)        // Key-value for email
        jsonBody.put("password", password)  // Key-value for password
        val requestBody = jsonBody.toString()

        // Register user by providing Method - POST, URL for API, Success response and Error response
        val registerRequest = object : StringRequest(Method.POST, URL_REGISTER, Response.Listener { response ->
            println(response)
            // If success print success message and set complete to true. This is where we parse the String
            complete(true)
        }, Response.ErrorListener {
            // if error print to logcat DEBUG and set complete to false
            error -> Log.d("ERROR" , "Could not register user: $error")
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
        Volley.newRequestQueue(context).add(registerRequest)
    }

    fun loginUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit){

        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        val requestBody = jsonBody.toString()

        val loginRequest = object : JsonObjectRequest(Method.POST, URL_LOGIN, null, Response.Listener{ response ->
            // This is where we parse the String
            // getString throws JSONException, so we need try catch
            try {
                userEmail = response.getString("user")  // Parse user's email and assigns it to userEmail instance variable
                authToken = response.getString("token") // Parse Json token and assign it to instance variable authToken
                isLoggedIn = true                             // isLoggedIn = true because we logged in
                complete(true)
            } catch (e: JSONException) {
                Log.d("JSON", "EXC: " + e.localizedMessage) // Show the exception in debug logcat if fails
                complete(false)
            }
        }, Response.ErrorListener {
            // This is where we deal with errors
            error -> Log.d("ERROR", "Was not able to login: $error")
            complete(false)
        }) {

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        Volley.newRequestQueue(context).add(loginRequest)
    }
}