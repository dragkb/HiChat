package com.alex.hichat.Services

import android.graphics.Color
import com.alex.hichat.Controller.App
import java.util.*

object UserDataService {

    var id = ""
    var avatarColor = ""
    var avatarName = ""
    var email = ""
    var name = ""

    fun logout(){
        id = ""
        avatarColor = ""
        avatarName = ""
        email = ""
        name = ""
        App.sharedPrefs.authToken = ""
        App.sharedPrefs.userEmail = ""
        App.sharedPrefs.isLoggedIn = false
    }

    fun returnAvatarColor(components: String) : Int {
        val strippedColor = components
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
        var r = 0
        var g = 0
        var b = 0
        val scanner = Scanner(strippedColor)
        if (scanner.hasNext()){
            r = (scanner.nextDouble() * 255).toInt()
            g = (scanner.nextDouble() * 255).toInt()
            b = (scanner.nextDouble() * 255).toInt()
        }
        return Color.rgb(r,g,b)
    }
}