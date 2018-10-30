package com.alex.hichat.Espresso.Tests

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Espresso.Model.OldUser
import com.alex.hichat.Espresso.Screens.MainScreen
import com.alex.hichat.Utilities.IdlingResourceHolder
import com.alex.hichat.Espresso.Tasks.TasksMainLogin
import com.alex.hichat.Espresso.Tasks.TasksUserLogin
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MessagesTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val userVijay = OldUser("vijay@space.com", "123456")
    private val userBill = OldUser("bill@smile.com", "123456")
    private val userLala = OldUser("lala@gmail.com", "123456")
    private val randomNumberGen = (Math.random() * 10000).toInt()
    private val channelNameGenerator = "Test - $randomNumberGen"
    private val channelDesc = "test"
    private val testMessageTxt = "I want to live $randomNumberGen years."
    private val answerMessageText = "What if I could eat $randomNumberGen sausages for 5 min??? Hmm..."
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
    fun sendMessageTest() {
        val mainScreen = MainScreen()
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userLala)
        loggedInScreen.clickOnChannelRoomByIndex(1)
        loggedInScreen.typeNewMessage(testMessageTxt)
        loggedInScreen.clickOnMessageSendBtn()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun assertMessagesVisibleFromOtherAccount() {
        val mainScreen = MainScreen()
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userLala)
        loggedInScreen.clickOnChannelRoomByIndex(0)
        loggedInScreen.typeNewMessage(answerMessageText)
        loggedInScreen.clickOnMessageSendBtn()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.clickOnLoginHeaderBtn()
        TasksUserLogin.validLogin(userBill)
        loggedInScreen.clickOnChannelRoomByIndex(0)
        loggedInScreen.assertMessageHasSent(answerMessageText)
        loggedInScreen.typeNewMessage(respondMessageText)
        loggedInScreen.clickOnMessageSendBtn()
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
        loggedInScreen.clickOnLastChannelRoom()
        loggedInScreen.typeNewMessage(conversationVijay)
        loggedInScreen.clickOnMessageSendBtn()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.clickOnLoginHeaderBtn()
        TasksUserLogin.validLogin(userBill)
        loggedInScreen.clickOnLastChannelRoom()
        loggedInScreen.assertMessageHasSent(conversationVijay)
        loggedInScreen.typeNewMessage(conversationBill)
        loggedInScreen.clickOnMessageSendBtn()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.clickOnLoginHeaderBtn()
        TasksUserLogin.validLogin(userLala)
        loggedInScreen.clickOnLastChannelRoom()
        loggedInScreen.assertMessageHasSent(conversationBill)
        loggedInScreen.typeNewMessage(conversationLala)
        loggedInScreen.clickOnMessageSendBtn()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
    }
}