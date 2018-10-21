package com.alex.hichat

import android.support.test.espresso.Espresso
import android.support.test.runner.AndroidJUnit4
import android.support.test.rule.ActivityTestRule
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.alex.hichat.Controller.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class MainActivityTests {

    var randomPassGen = Math.random()
    val newUserName = "zorro"
    var newUserEmailGen = "zorro$randomPassGen@gmail.com"
    val newUserPassword = "123456"

    @get:Rule
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun isMainActivityOpenTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(allOf(withText("Please Log In"), isDisplayed())))
        onView(withContentDescription("Open navigation drawer"))
                .check(matches(allOf(isClickable(), isDisplayed())))
    }

    @Test
    fun signUpTest() {
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
        sleep(500)
    }
}