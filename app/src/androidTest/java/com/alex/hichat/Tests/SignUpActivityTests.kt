package com.alex.hichat.Tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.R
import org.hamcrest.CoreMatchers.*
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class SignUpActivityTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private var randomPassGen = Math.random()
    private val newUserName = "zorro"
    private var newUserEmailGen = "zorro$randomPassGen@gmail.com"
    private val newUserPassword = "123456"

    @Test
    fun createNewUserTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginCreateUserBtn))
                .perform(click())
        onView(withId(R.id.createUserNameTxt))
                .perform(typeText(newUserName))
        onView(withId(R.id.createEmailTxt))
                .perform(typeText(newUserEmailGen))
        onView(withId(R.id.createPasswordTxt))
                .perform(typeText(newUserPassword))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.createAvatarImgView))
                .perform(click(), click())
        onView(withId(R.id.backgroundColorBtn))
                .perform(click(), click(), click())
        onView(withId(R.id.createUserBtn))
                .perform(click())
        sleep(6000)
        onView(withText(newUserEmailGen))
                .check(matches(isDisplayed()))
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
    }

    @Test
    fun emptyCreateUserBtnEmptyFieldsToastMessageTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginCreateUserBtn))
                .perform(click())
        onView(withId(R.id.createUserBtn))
                .perform(click())
        sleep(700)
        onView(withText(R.string.toast_create_user_empty_fields_passed))
                .inRoot(withDecorView(not(`is`(myActivityTestRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
    }

    @Test
    fun userNameTxtFieldPresent() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginCreateUserBtn))
                .perform(click())
        onView(withId(R.id.createUserNameTxt))
                .check(matches(allOf(withHint("user name"), isDisplayed())))
    }

    @Test
    fun userEmailTxtFieldPresent() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginCreateUserBtn))
                .perform(click())
        onView(withId(R.id.createEmailTxt))
                .check(matches(allOf(withHint("email"), isDisplayed())))
    }

    @Test
    fun userPasswordTxtFieldPresent() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginCreateUserBtn))
                .perform(click())
        onView(withId(R.id.createPasswordTxt))
                .check(matches(allOf(withHint("password"), isDisplayed())))
    }

    @Test
    fun tapToGenerateTextViewPresentTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginCreateUserBtn))
                .perform(click())
        onView(withText("Tap to generate user avatar"))
                .check(matches(allOf(isDisplayed(), not(isClickable()))))
    }

    @Test
    fun generateAvatarImgPresentTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginCreateUserBtn))
                .perform(click())
        onView(withId(R.id.createAvatarImgView))
                .check(matches(allOf(isClickable(), isDisplayed())))
    }

    @Test
    fun generateBackgroundColorBtnPresentTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginCreateUserBtn))
                .perform(click())
        onView(withId(R.id.backgroundColorBtn))
                .check(matches(allOf(isClickable(), isDisplayed(), withText(R.string.generate_background_color))))
    }

    @Test
    fun createUserBtnPresentTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginCreateUserBtn))
                .perform(click())
        onView(withId(R.id.createUserBtn))
                .check(matches(allOf(isClickable(), isDisplayed(), withText(R.string.create_user))))
    }
}