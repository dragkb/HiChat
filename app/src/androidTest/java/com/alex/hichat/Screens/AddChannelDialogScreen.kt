package com.alex.hichat.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withHint
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.alex.hichat.R

class AddChannelDialogScreen {

    private val channelName: ViewInteraction
        get() = onView(withId(R.id.dialogChannelName))

    private val addChannelNameTxt: ViewInteraction
        get() = onView(withId(R.id.addChannelNameTxt))

    private val addChannelDescTxt: ViewInteraction
        get() = onView(withId(R.id.addChannelDescTxt))

    private val dialogCancelBtn: ViewInteraction
        get() = onView(withText("CANCEL"))

    private val dialogAddBtn: ViewInteraction
        get() = onView(withText("ADD"))

    fun clickOnCancelBtn(): LoggedInScreen {
        dialogCancelBtn.perform(click())
        return LoggedInScreen()
    }

    fun clickOnAddBtn(): LoggedInScreen {
        dialogAddBtn.perform(click())
        return LoggedInScreen()
    }

    fun typeChannelName(channelName: String) {
        addChannelNameTxt.perform(typeText(channelName))
    }

    fun typeChannelDescription(channelDesc: String) {
        addChannelDescTxt.perform(typeText(channelDesc))
    }

    fun assertDialogPoppedUp() {
        channelName.check(matches(withText("Add Channel")))
    }

    fun assertChannelNameHintPresent() {
        addChannelNameTxt.check(matches(withHint("channel name")))
    }

    fun assertChannelDescriptionHintPresent() {
        addChannelDescTxt.check(matches(withHint("channel description")))
    }
}