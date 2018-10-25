package com.alex.hichat.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import android.support.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withChild
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.alex.hichat.R
import com.alex.hichat.Tests.AddChannelDialogTests
import org.hamcrest.CoreMatchers.allOf

class LoggedInScreen : BaseScreen() {

    private val avatarImage: ViewInteraction
        get() = onView(withId(R.id.userImgNavHeader))

    private val userName: ViewInteraction
        get() = onView(withId(R.id.userNameNavHeader))

    private val userEmail: ViewInteraction
        get() = onView(withId(R.id.userEmailNavHeader))

    private val addChannelBtn: ViewInteraction
        get() = onView(withId(R.id.addChannelBtn))

    private val logoutBtn: ViewInteraction
        get() = onView(withText("LOGOUT"))

    override val uniqueView: ViewInteraction
        get() = logoutBtn

    private val channelListView: ViewInteraction
        get() = onView(withId(R.id.channel_list))
//        get() = onData(anything()).inAdapterView(withId(R.id.channel_list))

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun clickOnLogoutBtn() {
        logoutBtn.perform(click())
    }

    fun clickOnAddChannelBtn(): AddChannelDialogScreen {
        addChannelBtn.perform(click())
        return AddChannelDialogScreen()
    }

    fun assertImagePresentTest() {
        avatarImage.check(matches(ViewMatchers.isDisplayed()))
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

    fun assertThatLoggedIn() {
        userName.check(matches(isDisplayed()))
    }

    fun assertNewChannelSuccessfullyCreated() {
        // Getting val channelName from AddChannelDialogTests class which is dynamically changes every test
        val channelNameText = AddChannelDialogTests()
        channelListView.check(matches(withChild(withText(channelNameText.channelName))))
//        channelListView?.check(matches(withChild(withText(channelNameText.channelName))))
    }
}