package com.alex.hichat.Screens

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import android.support.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.hasSibling
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withClassName
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.ListView
import com.alex.hichat.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.CoreMatchers.isA

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

    private val channelListView: ViewInteraction
        get() = onView(withId(R.id.channel_list))

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

    private val messageRecyclerView: ViewInteraction
        get() = onView(withId(R.id.messageListView))

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
        onView(
            allOf(
                withText("#$newChannelName"), isDescendantOfA(withId(R.id.channel_list)))
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

    fun clickOnChannelRoom(channel: String) {
        onView(
            allOf(
                withText(channel), isDescendantOfA(withId(R.id.channel_list)))
        ).perform(click())
    }

    fun assertChannelRoomIsClicked(channelRoom: String) {
        channelRoomName.check(matches(withText(channelRoom)))
    }

    fun typeNewMessage(newMessage: String) {
        messageTxtField.perform(typeText(newMessage))
    }

    fun clickOnMessageSendBtn() {
        messageSendBtn.perform(click())
    }

    fun assertMessageHasSent(messageText: String) {
        messageRecyclerView.check(
            matches(
                allOf(
                    (hasDescendant(withText(messageText))), hasDescendant(withId(R.id.messageBodyLbl))
                )
            )
        )
    }
//
//    // Click on one of the rows.
//    onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowContentTextView)).perform(click());
//
//    // Click on the toggle button, in a different row.
//    onRow(TEXT_ITEM_60).onChildView(withId(R.id.rowToggleButton)).perform(click());
//
//    // Check that the activity didn't detect the click on the first column.
//    onView(ViewMatchers.withId(R.id.selection_row_value))
//    .check(matches(withText(TEXT_ITEM_30_SELECTED)));
//}
//
///**
// * Uses {@link Espresso#onData(org.hamcrest.Matcher)} to get a reference to a specific row.
// * <p>
// * Note: A custom matcher can be used to match the content and have more readable code.
// * See the Custom Matcher Sample.
// * </p>
// *
// * @param str the content of the field
// * @return a {@link DataInteraction} referencing the row
// */
//private static DataInteraction onRow(String str) {
//    return onData(hasEntry(equalTo(LongListActivity.ROW_TEXT), is(str)));
//}
}