package com.alex.hichat.Espresso.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import android.support.test.espresso.assertion.PositionAssertions.isCompletelyRightOf
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.alex.hichat.R
import org.hamcrest.CoreMatchers.allOf

class MainScreen : BaseScreen() {

    private val hamburgerBtn: ViewInteraction
        get() = onView(withContentDescription("Open navigation drawer"))

    private val loginBtn: ViewInteraction
        get() = onView(withId(R.id.loginBtnNavHeader))

    private val addChannelBtn: ViewInteraction
        get() = onView(withId(R.id.addChannelBtn))

    private val accountImg: ViewInteraction
        get() = onView(withId(R.id.userImgNavHeader))

    override val uniqueView: ViewInteraction
        get() = hamburgerBtn

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun clickOnHamburgerBtnMain() {
        hamburgerBtn.perform(click())
    }

    fun clickOnLoginHeaderBtn(): LoginScreen {
        loginBtn.perform(click())
        return LoginScreen()
    }

    fun assertHamburgerBtnDisplayedAndClickable() {
        hamburgerBtn
            .check(
                matches(
                    allOf(
                        isClickable(), isDisplayed()
                    )
                )
            )
    }

    fun assertLoginBtnDisplayedAndClickable() {
        loginBtn
            .check(
                matches(
                    allOf(
                        isDisplayed(), withText(R.string.login_login), isClickable()
                    )
                )
            )
    }

    fun assertAddChannelBtnDisplayed() {
        addChannelBtn.check(matches(isDisplayed()))
    }

    fun assertAccountImgDisplayed() {
        accountImg.check(matches(isDisplayed()))
    }

    fun assertPositionAccountImgAboveOfLoginBtn() {
        accountImg.check(isCompletelyAbove(withId(R.id.loginBtnNavHeader)))
    }

    fun assertPositionChannelBtnLeftOfLoginBtn() {
        addChannelBtn.check(isCompletelyRightOf(withId(R.id.loginBtnNavHeader)))
    }

    fun assertThatLoggedOut() {
        loginBtn.check(matches(allOf(withText("LOGIN"), isClickable())))
    }
}