package com.alex.hichat.Controller

import android.app.Application
import com.alex.hichat.Utilities.SharedPrefs

class App : Application() {

    // Like a singleton but only for this class
    companion object {
        lateinit var sharedPrefs: SharedPrefs
    }

    override fun onCreate() {
        sharedPrefs = SharedPrefs(applicationContext)
        super.onCreate()
    }
}