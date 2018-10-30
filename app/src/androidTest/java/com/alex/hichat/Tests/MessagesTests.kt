package com.alex.hichat.Tests

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Model.OldUser
import com.alex.hichat.Screens.MainScreen
import com.alex.hichat.Utilities.IdlingResourceHolder
import com.alex.hichat.Tasks.TasksMainLogin
import com.alex.hichat.Tasks.TasksSendMessage
import com.alex.hichat.Tasks.TasksUserLogin
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class MessagesTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val userVijay = OldUser("vijay@space.com", "123456")
    private val userBill = OldUser("bill@smile.com", "123456")
    private val userLala = OldUser("lala@gmail.com", "123456")
    private val channelNameGenerator = "Test - ${(Math.random() * 100000).toInt()}"
    private val channelDesc = "test"
    private val channelName = "Cool API"

    private val testMessageTxt = "I want to be a plumber."
    private val answerMessageText = "What if I could eat 200 sausages for 5 min??? Hmm..."
    private val respondMessageText = "You would be pretty full. I think :)"
    private val conversationVijay = "What an amazing Demo!!! What do you think, Bill?"
    private val conversationBill = "Yeh, 100% agreed! Alex you are cool! Lala, what's up?"
    private val conversationLala = "I love this demo since day one. " +
        "I think you should hire Alex, he is right next to you!" +
        " Don't miss your chance..."

    @Before
    fun idlingResourceRegister() {
        IdlingRegistry.getInstance().register(IdlingResourceHolder.networkIdlingResource)
    }

    @After
    fun idlingResourceUnregister() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHolder.networkIdlingResource)
    }

    @Test
    fun assertMessageSentTest() {
        val mainScreen = MainScreen()
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userLala)
        loggedInScreen.clickOnChannelRoom(channelName)
        TasksSendMessage.sendMessage(testMessageTxt)
        loggedInScreen.assertMessageHasSent(testMessageTxt)
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun assertMessagesVisibleFromOtherAccount() {
        val mainScreen = MainScreen()
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userLala)
        loggedInScreen.clickOnChannelRoom(channelName)
        TasksSendMessage.sendMessage(answerMessageText)
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.clickOnLoginHeaderBtn()
        TasksUserLogin.validLogin(userBill)
        loggedInScreen.clickOnChannelRoom(channelName)
        loggedInScreen.assertMessageHasSent(answerMessageText)
        TasksSendMessage.sendMessage(respondMessageText)
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun assertMessagesSentToNewChannelTest() {
        val mainScreen = MainScreen()
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userVijay)
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.typeDialogChannelName(channelNameGenerator)
        loggedInScreen.typeDialogChannelDescription(channelDesc)
        loggedInScreen.clickOnDialogAddBtn()
        loggedInScreen.clickOnChannelRoom(channelNameGenerator)
        TasksSendMessage.sendMessage(conversationVijay)
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.clickOnLoginHeaderBtn()
        TasksUserLogin.validLogin(userBill)
        loggedInScreen.clickOnChannelRoom(channelNameGenerator)
        loggedInScreen.assertMessageHasSent(conversationVijay)
        TasksSendMessage.sendMessage(conversationBill)
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.clickOnLoginHeaderBtn()
        TasksUserLogin.validLogin(userLala)
        loggedInScreen.clickOnChannelRoom(channelNameGenerator)
        loggedInScreen.assertMessageHasSent(conversationBill)
        TasksSendMessage.sendMessage(conversationLala)
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
    }
}