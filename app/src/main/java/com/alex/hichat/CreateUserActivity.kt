package com.alex.hichat

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_create_user.*
import java.util.*

class CreateUserActivity : AppCompatActivity() {

    // userAvatar - default avatar for every user
    var userAvatar = "profiledefault"
    // avatarColor - needs for messaging to iOS or macOS, because they have color bounds from 0-1
    var avatarColor = "[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
    }

    fun generateUserAvatar(view: View) {
        // Using Random API
        val random = Random()
        // Assign next random int to color from 0 - 1. 2 is the upper bound for random
        val color = random.nextInt(2)
        // HiChat app has 27 images, will generate from 0 - 27 randomly.
        val avatar = random.nextInt(28)

        // Conditional logic to set up a background color
        if(color == 0) {
            userAvatar = "light$avatar"
        } else {
            userAvatar = "dark$avatar"
        }

        // Getting Id of the element by implementing resourceId
        val resourceId = resources.getIdentifier(userAvatar, "drawable", packageName)
        // Set the image to the resourceId
        createAvatarImgView.setImageResource(resourceId)
    }

    fun generateColorClicked(view: View) {
        // Random generator
        val random = Random()
        // RGB color r(red), g(green0, b(blue) - bounds from 0-255
        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)
        // Combined all generated numbers together and give output
        createAvatarImgView.setBackgroundColor(Color.rgb(r,g,b))

        // Getting values for iOS and macOS from 0-1 by converting to double and / 255 and saved vals to API
        val savedR = r.toDouble() / 255
        val savedG = r.toDouble() / 255
        val savedB = r.toDouble() / 255
        // Combined all generated numbers together and give output for iOS and macOS devices
        avatarColor = "[$savedR, $savedG, $savedB, 1]"
        // println(avatarColor) // check the logcat Info for numbers that sent to the API
    }

    fun createUserBtnClicked(view: View) {

    }
}
