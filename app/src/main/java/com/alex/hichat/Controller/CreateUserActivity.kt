package com.alex.hichat.Controller

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import android.widget.Toast
import com.alex.hichat.R
import com.alex.hichat.Services.AuthService
import com.alex.hichat.Services.UserDataService
import com.alex.hichat.Utilities.BROADCAST_USER_DATA_CHANGE
import kotlinx.android.synthetic.main.activity_create_user.*
import java.util.*

class CreateUserActivity : AppCompatActivity() {

    // userAvatar - default avatar for every user
    var userAvatar = "profileDefault"
    // avatarColor - needs for messaging to iOS or macOS, because they have color bounds from 0-1
    var avatarColor = "[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        // Makes the Spinner invisible
        createSpinner.visibility = View.INVISIBLE
    }

    fun generateUserAvatar(view: View) {
        // Using Random API
        val random = Random()
        // Assign next random int to color from 0 - 1. 2 is the upper bound for random
        val color = random.nextInt(2)
        // HiChat app has 27 images, will generate from 0 - 27 randomly.
        val avatar = random.nextInt(28)

        // Conditional logic to set up a background color
        if (color == 0) {
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
        createAvatarImgView.setBackgroundColor(Color.rgb(r, g, b))

        // Getting values for iOS and macOS from 0-1 by converting to double and / 255 and saved vals to API
        val savedR = r.toDouble() / 255
        val savedG = g.toDouble() / 255
        val savedB = b.toDouble() / 255
        // Combined all generated numbers together and give output for iOS and macOS devices
        avatarColor = "[$savedR, $savedG, $savedB, 1]"
        // println(avatarColor) // check the logcat Info for numbers that sent to the API
    }

    fun createUserBtnClicked(view: View) {

        enableSpinner(true)
        val userName = createUserNameTxt.text.toString()
        val email = createEmailTxt.text.toString()
        val password = createPasswordTxt.text.toString()

        // Fields validation
        if(userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            AuthService.registerUser(this, email, password) { registerSuccess ->
                if (registerSuccess) {
                    AuthService.loginUser(this, email, password) { loginSuccess ->
                        if (loginSuccess) {
                            AuthService.createUser(this, userName, email, userAvatar, avatarColor) { createSuccess ->
                                if (createSuccess) {
                                    // Create an Intent for broadcast
                                    val userDataChanged = Intent(BROADCAST_USER_DATA_CHANGE)
                                    // Creating broadcast which saying that data changed to other activities
                                    LocalBroadcastManager.getInstance(this).sendBroadcast(userDataChanged)
                                    enableSpinner(false)
                                    finish() // For dismiss activity use method finish()
                                } else {
                                    errorToast()
                                }
                            }
                        } else{
                            errorToast()
                        }
                    }
                } else {
                    errorToast()
                }
            }
        } else {
            // Error message if fields are not filled in completely
            Toast.makeText(this, "Make sure user name, email and password are filled in", Toast.LENGTH_SHORT).show()
            enableSpinner(false)
        }

    }
    // Error message to usets
    fun errorToast(){
        Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_LONG).show()
    }

    // This function makes the spinner either visible or not and set other buttons to disable or enabled mode when createUserBtn clicked
    fun enableSpinner(enable: Boolean){
        if (enable) {
            createSpinner.visibility = View.VISIBLE
        } else {
            createSpinner.visibility = View.INVISIBLE
        }
        // !enable = false, enable = true. If if statement equal to true then all elements under are true, and false if opposite
        createAvatarImgView.isEnabled = !enable
        backgroundColorBtn.isEnabled = !enable
        createUserBtn.isEnabled = !enable
    }

}
