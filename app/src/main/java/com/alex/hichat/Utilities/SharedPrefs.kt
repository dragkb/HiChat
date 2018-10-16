package com.alex.hichat.Utilities

import android.content.Context
import android.content.SharedPreferences
import com.android.volley.toolbox.Volley

class SharedPrefs(context: Context) {

    val PREFS_FILE_NAME = "prefs"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILE_NAME, 0)

    val IS_lOGGED_IN = "isLoggedIn"
    val AUTH_TOKEN = "authToken"
    val USER_EMAIL = "userEmail"

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(IS_lOGGED_IN, false)
        set(value) = prefs.edit().putBoolean(IS_lOGGED_IN, value).apply()

    var authToken: String
        get() = prefs.getString(AUTH_TOKEN, "")
        set(value) = prefs.edit().putString(AUTH_TOKEN, value).apply()

    var userEmail: String
        get() = prefs.getString(USER_EMAIL, "")
        set(value) = prefs.edit().putString(USER_EMAIL, value).apply()

    // Instead of creating new request we will use this val
    val requestQueue = Volley.newRequestQueue(context)
}