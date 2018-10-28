package com.alex.hichat.Tests

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Screens.LoggedInScreen
import com.alex.hichat.Screens.MainScreen
import com.alex.hichat.Screens.UserClickValidation
import com.alex.hichat.Services.IdlingResourceHolder
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.log

@RunWith(AndroidJUnit4::class)
class MessagesTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val validUserEmailLala = "lala@gmail.com"
    private val validUserEmailVijay = "vijay@space.com"
    private val validUserEmailBill = "bill@smile.com"
    private val validUserPassword = "123456"
    private val channelNameGenerator = "Test - ${Math.random().toFloat()}"
    private val channelDesc = "test"
    private val channelName = "#Cool API"
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
        if(IdlingResourceHolder.networkIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(IdlingResourceHolder.networkIdlingResource)
        }
    }

    @Test
    fun assertMessageSentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.clickOnChannelRoom(channelName)
        loggedInScreen.typeNewMessage(answerMessageText)
        loggedInScreen.clickOnMessageSendBtn()
        loggedInScreen.assertMessageHasSent(answerMessageText)
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun assertMessageVisibleFromOtherAccount() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.clickOnChannelRoom(channelName)
        loggedInScreen.typeNewMessage(answerMessageText)
        loggedInScreen.clickOnMessageSendBtn()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailBill)
        loginScreen.typePassword(validUserPassword)
        loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
        loggedInScreen.clickOnChannelRoom(channelName)
        loggedInScreen.assertMessageHasSent(answerMessageText)
        loggedInScreen.typeNewMessage(respondMessageText)
        loggedInScreen.clickOnMessageSendBtn()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun assertMessageSentToNewChannelJustCreatedTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailVijay)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.typeDialogChannelName(channelNameGenerator)
        loggedInScreen.typeDialogChannelDescription(channelDesc)
        loggedInScreen.clickOnDialogAddBtn()
        loggedInScreen.clickOnChannelRoom("#$channelNameGenerator")
        loggedInScreen.typeNewMessage(conversationVijay)
        loggedInScreen.clickOnMessageSendBtn()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailBill)
        loginScreen.typePassword(validUserPassword)
        loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
        loggedInScreen.clickOnChannelRoom("#$channelNameGenerator")
        loggedInScreen.typeNewMessage(conversationBill)
        loggedInScreen.clickOnMessageSendBtn()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.typePassword(validUserPassword)
        loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
        loggedInScreen.clickOnChannelRoom("#$channelNameGenerator")
        loggedInScreen.typeNewMessage(conversationLala)
        loggedInScreen.clickOnMessageSendBtn()
        loggedInScreen.assertMessageHasSent(conversationVijay)
//        loggedInScreen.assertMessageHasSent(conversationBill)
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
    }
}