package com.alex.hichat

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.PositionAssertions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import org.hamcrest.Matchers.*


@RunWith(AndroidJUnit4::class)
class LoginActivityTests {

    @get:Rule
    var loginActivityTestRule = ActivityTestRule(MainActivity::class.java)

    val validUserName = "lala"
    val validUserEmail = "l@l.com"
    val validUserPassword = "123456"
    val invalidUserEmail = "abrakadabra@booms.com"
    val invalidUserPassword = "12345678910"

    @Test
    fun loginHappyPathTest() {
        onView(withId(R.id.mainChannelName)).check(matches(ViewMatchers.isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginEmailTxt))
                .perform(typeText(validUserEmail))
        onView(withId(R.id.loginPasswordTxt))
                .perform(typeText(validUserPassword))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.loginLoginBtn))
                .perform(click())
        sleep(3500)
        onView(withId(R.id.loginBtnNavHeader))
                .check(matches(allOf(withText("LOGOUT"), isClickable())))
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
    }

    @Test
    fun logoutHappyPathTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginEmailTxt))
                .perform(typeText(validUserEmail))
        onView(withId(R.id.loginPasswordTxt))
                .perform(typeText(validUserPassword))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.loginLoginBtn))
                .perform(click())
        sleep(3500)
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .check(matches(allOf(withText("LOGIN"), isClickable())))
    }

    @Test
    fun loginUserAvatarPresentTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginEmailTxt))
                .perform(typeText(validUserEmail))
        onView(withId(R.id.loginPasswordTxt))
                .perform(typeText(validUserPassword))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.loginLoginBtn))
                .perform(click())
        sleep(3500)
        onView(withId(R.id.userImgNavHeader))
                .check(matches(isDisplayed()))
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
    }

    @Test
    fun loginUserNamePresentTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginEmailTxt))
                .perform(typeText(validUserEmail))
        onView(withId(R.id.loginPasswordTxt))
                .perform(typeText(validUserPassword))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.loginLoginBtn))
                .perform(click())
        sleep(3500)
        onView(withId(R.id.userNameNavHeader))
                .check(matches(withText(validUserName)))
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
    }

    @Test
    fun loginUserEmailPresentTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginEmailTxt))
                .perform(typeText(validUserEmail))
        onView(withId(R.id.loginPasswordTxt))
                .perform(typeText(validUserPassword))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.loginLoginBtn))
                .perform(click())
        sleep(3500)
        onView(withId(R.id.userEmailNavHeader))
                .check(matches(withText(validUserEmail)))
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
    }

    @Test
    fun loginAvatarImgLeftFromEmailPositionTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginEmailTxt))
                .perform(typeText(validUserEmail))
        onView(withId(R.id.loginPasswordTxt))
                .perform(typeText(validUserPassword))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.loginLoginBtn))
                .perform(click())
        sleep(3500)
        onView(withId(R.id.userImgNavHeader))
                .check(isCompletelyLeftOf((withId(R.id.userEmailNavHeader))))
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
    }

    @Test
    fun loginNameAboveEmailPositionTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginEmailTxt))
                .perform(typeText(validUserEmail))
        onView(withId(R.id.loginPasswordTxt))
                .perform(typeText(validUserPassword))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.loginLoginBtn))
                .perform(click())
        sleep(3500)
        onView(withId(R.id.userNameNavHeader))
                .check(isCompletelyAbove((withId(R.id.userEmailNavHeader))))
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
    }

    @Test
    fun loginAddChannelBtnPresentTest() {
        onView(withId(R.id.mainChannelName))
                .check(matches(isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginEmailTxt))
                .perform(typeText(validUserEmail))
        onView(withId(R.id.loginPasswordTxt))
                .perform(typeText(validUserPassword))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.loginLoginBtn))
                .perform(click())
        sleep(3500)
        onView(withId(R.id.addChannelBtn))
                .check(matches(allOf(isDisplayed(), isClickable())))
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
    }
    @Test
    fun loginSomethingWentWrongToastMessageTest() {
        onView(withId(R.id.mainChannelName)).check(matches(ViewMatchers.isDisplayed()))
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginEmailTxt))
                .perform(typeText(invalidUserEmail))
        onView(withId(R.id.loginPasswordTxt))
                .perform(typeText(invalidUserPassword))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.loginLoginBtn))
                .perform(click())
        sleep(700)
        onView(withText(R.string.toast_something_went_wrong_message))
                .inRoot(withDecorView(not(`is`(loginActivityTestRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
        Espresso.pressBack()
    }

    @Test
    fun loginFillBothEmailPasswordToastMessageTest() {
        onView(withContentDescription("Open navigation drawer"))
                .perform(click())
        onView(withId(R.id.loginBtnNavHeader))
                .perform(click())
        onView(withId(R.id.loginEmailTxt))
                .perform(typeText(validUserEmail))
        onView(withId(R.id.loginLoginBtn))
                .perform(click())
        sleep(700)
        onView(withText(R.string.toast_fill_both_email_password_fields))
                .inRoot(withDecorView(not(`is`(loginActivityTestRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
    }
}