package com.alex.hichat.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.alex.hichat.R

class LoginScreen : BaseScreen() {

    private val loginEmailTxtField: ViewInteraction
        get() = onView(withId(R.id.loginEmailTxt))

    private val loginPasswordTxtField: ViewInteraction
        get() = onView(withId(R.id.loginPasswordTxt))

    private val loginLoginBtn: ViewInteraction
        get() = onView(withId(R.id.loginLoginBtn))

    private val loginSignUpBtn: ViewInteraction
        get() = onView(withId(R.id.loginCreateUserBtn))

    override val uniqueView: ViewInteraction
        get() = loginLoginBtn

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun typeEmail(email: String) {
        loginEmailTxtField.perform(typeText(email))
    }

    fun typePassword(password: String) {
        loginPasswordTxtField.perform(typeText(password))
    }

    fun clickOnLoginBtn(): LoggedInScreen {
        loginLoginBtn.perform(click())
        return LoggedInScreen()
    }

    fun clickOnSignUpHereBtn(): SignUpScreen {
        loginSignUpBtn.perform(click())
        return SignUpScreen()
    }
}