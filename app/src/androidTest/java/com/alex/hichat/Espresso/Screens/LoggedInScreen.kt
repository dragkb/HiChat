package com.alex.hichat.Espresso.Screens

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import android.support.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.alex.hichat.Espresso.Utilities.CustomFailureHandler
import com.alex.hichat.R
import com.alex.hichat.Services.MessageService
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything

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

    private val dialogChannelName: ViewInteraction
        get() = onView(withId(R.id.dialogChannelName))

    private val dialogAddChannelNameTxt: ViewInteraction
        get() = onView(withId(R.id.addChannelNameTxt))

    private val dialogAddChannelDescTxt: ViewInteraction
        get() = onView(withId(R.id.addChannelDescTxt))

    private val dialogCancelBtn: ViewInteraction
        get() = onView(withText("CANCEL"))

    private val dialogAddBtn: ViewInteraction
        get() = onView(withText("ADD"))

    private val channelRoomName: ViewInteraction
        get() = onView(withId(R.id.mainChannelName))

    private val messageTxtField: ViewInteraction
        get() = onView(withId(R.id.messageTxtField))

    private val messageSendBtn: ViewInteraction
        get() = onView(withId(R.id.sendMessageBtn))

    override val uniqueView: ViewInteraction
        get() = logoutBtn

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun clickOnLogoutBtn() {
        logoutBtn.perform(click())
    }

    fun clickOnAddChannelBtn() {
        addChannelBtn.perform(click())
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

    fun assertNewChannelSuccessfullyCreated(newChannelName: String) {
        onData(anything())
            .inAdapterView(withId(R.id.channel_list))
            .atPosition(MessageService.channels.indexOf(MessageService.channels.last()))
            .check(matches(isDisplayed()))
//        onView(withId(R.id.channel_list)).perform(swipeUp())

        onView(
            allOf(
                withText("#$newChannelName"), isDescendantOfA(withId(R.id.channel_list))
            )
        ).check(matches(isDisplayed()))
    }

    fun clickOnDialogCancelBtn() {
        dialogCancelBtn.perform(click())
    }

    fun clickOnDialogAddBtn() {
        dialogAddBtn.perform(click())
    }

    fun typeDialogChannelName(channelName: String) {
        dialogAddChannelNameTxt.perform(typeText(channelName))
    }

    fun typeDialogChannelDescription(channelDesc: String) {
        dialogAddChannelDescTxt.perform(typeText(channelDesc))
    }

    fun assertDialogPoppedUp() {
        dialogChannelName.check(matches(withText("Add Channel")))
    }

    fun assertDialogChannelNameHintPresent() {
        dialogAddChannelNameTxt.check(matches(ViewMatchers.withHint("channel name")))
    }

    fun assertDialogChannelDescriptionHintPresent() {
        dialogAddChannelDescTxt.check(matches(ViewMatchers.withHint("channel description")))
    }

    fun clickOnLastChannelRoom() {
        onData(anything())
            .inAdapterView(withId(R.id.channel_list))
            .atPosition(MessageService.channels.indexOf(MessageService.channels.last()))
            .perform(click())
    }

    fun clickOnChannelRoomByIndex(index: Int) {
        // onData(anything()).inAdapterView(withId(R.id.list_view)).atPosition(0).perform(click());
        onData(anything())
            .inAdapterView(withId(R.id.channel_list))
            .atPosition(MessageService.channels.indexOf(MessageService.channels[index]))
            .perform(click())
    }

    fun assertChannelRoomIsClicked(channel: String) {
        channelRoomName.check(matches(withText("#$channel")))
    }

    fun typeNewMessage(newMessage: String) {
        messageTxtField
            .withFailureHandler(CustomFailureHandler)
            .perform(typeText(newMessage))
    }

    fun clickOnMessageSendBtn() {
        messageSendBtn.perform(click())
    }

    fun assertMessageHasSent(messageText: String) {
        onView(withId(R.id.messageListView))
            .check(
                matches(
                    allOf(
                        hasDescendant(withText(messageText)),
                        hasDescendant(withId(R.id.messageBodyLbl))
                    )
                )
            )
    }
}