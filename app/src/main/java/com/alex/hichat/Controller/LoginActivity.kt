package com.alex.hichat.Controller

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.alex.hichat.R
import com.alex.hichat.Services.AuthService
import com.alex.hichat.Services.IdlingResourceHolder
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Make progress spinner invisible
        loginSpinner.visibility = View.INVISIBLE
    }

    fun loginLoginBtnClicked(view: View) {
        enableSpinner(true)
        val email = loginEmailTxt.text.toString()
        val password = loginPasswordTxt.text.toString()
        hideKeyboard()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            AuthService.loginUser(email, password) { loginSuccess ->
                if (loginSuccess) {
                    IdlingResourceHolder.networkIdlingResource.decrement()
                    AuthService.findUserByEmail(this) { findSuccess ->
                        if (findSuccess) {
                            enableSpinner(false)
                            finish()
                            // Idling resource decrement
                            IdlingResourceHolder.networkIdlingResource.decrement()
                        } else {
                            IdlingResourceHolder.networkIdlingResource.decrement()
                            errorToast()
                            enableSpinner(false)
                        }
                    }
                } else {
                    IdlingResourceHolder.networkIdlingResource.decrement()
                    errorToast()
                    enableSpinner(false)
                }
            }
        } else {
            Toast.makeText(
                this, R.string.toast_fill_both_email_password_fields, Toast.LENGTH_LONG)
                .show()
            enableSpinner(false)
        }
    }

    fun loginCreateUserBtnClicked(view: View) {
        val createUserIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createUserIntent)
        // finish() work like Stack returns to previous activity
        finish()
    }

    // Error message to users
    fun errorToast() {
        Toast.makeText(this, R.string.toast_something_went_wrong_message, Toast.LENGTH_LONG).show()
    }

    // This function makes the spinner either visible or not and set other buttons to disable or enabled mode when createUserBtn clicked
    fun enableSpinner(enable: Boolean) {
        if (enable) {
            loginSpinner.visibility = View.VISIBLE
        } else {
            loginSpinner.visibility = View.INVISIBLE
        }
        // !enable = false, enable = true. If if statement equal to true then all elements under are true, and false if opposite
        loginCreateUserBtn.isEnabled = !enable
        loginLoginBtn.isEnabled = !enable
    }

    // Hiding soft keyboard
    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputManager.isAcceptingText) {
            inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}
