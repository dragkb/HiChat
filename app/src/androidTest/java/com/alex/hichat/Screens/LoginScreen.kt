package com.alex.hichat.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import android.support.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.alex.hichat.R
import org.hamcrest.CoreMatchers.*

class LoginScreen : BaseScreen() {

    private val loginEmailTxtField: ViewInteraction
        get() = onView(withId(R.id.loginEmailTxt))

    private val loginPasswordTxtField: ViewInteraction
        get() = onView(withId(R.id.loginPasswordTxt))

    private val loginLoginBtn: ViewInteraction
        get() = onView(withId(R.id.loginLoginBtn))

    private val loginSignUpBtn: ViewInteraction
        get() = onView(withId(R.id.loginCreateUserBtn))

    private val avatarImage: ViewInteraction
        get() = onView(withId(R.id.userImgNavHeader))

    private val userName: ViewInteraction
        get() = onView(withId(R.id.userNameNavHeader))

    private val userEmail: ViewInteraction
        get() = onView(withId(R.id.userEmailNavHeader))

    private val addChannelBtn: ViewInteraction
        get() = onView(withId(R.id.addChannelBtn))

    fun typeEmail(email: String) : LoginScreen {
        loginEmailTxtField.perform(typeText(email))
        return this
    }

    fun typePassword(password: String) : LoginScreen {
        loginPasswordTxtField.perform(typeText(password))
        return this
    }

    fun clickOnLoginBtn() {
        loginLoginBtn.perform(click())
    }

    fun clickOnSignUpHereBtn() {
        loginSignUpBtn.perform(click())
    }

    fun assertImagePresentTest() {
        avatarImage.check(matches(isDisplayed()))
    }

    fun assertImagePositionTest() {
        avatarImage.check(isCompletelyLeftOf((withId(R.id.userEmailNavHeader))))
    }

    fun assertUserNamePresentTest(nameStr: String) {
        userName.check(matches(withText(nameStr)))
    }

    fun assertUserNamePositionTest() {
        userName.check(isCompletelyAbove((withId(R.id.userEmailNavHeader))))
    }

    fun assertUserEmailTest(emailStr: String) {
        userEmail.check(matches(withText(emailStr)))
    }

    fun assertAddChannelBtnPresentTest() {
        addChannelBtn.check(matches(allOf(isDisplayed(), isClickable())))
    }
}