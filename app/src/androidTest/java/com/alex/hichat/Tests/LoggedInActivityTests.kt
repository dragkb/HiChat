package com.alex.hichat.Tests

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Screens.LoggedInScreen
import com.alex.hichat.Screens.MainScreen
import com.alex.hichat.Screens.UserClickValidation
import com.alex.hichat.Services.IdlingResourceHolder
import com.alex.hichat.Utilities.ToastPopUps
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoggedInActivityTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val validUserNameLala = "lala"
    private val validUserEmailLala = "lala@gmail.com"
    private val validUserEmailVijay = "vijay@space.com"
    private val validUserEmailBill = "bill@smile.com"
    private val validUserPassword = "123456"
    private val channelNameGenerator = "Test - ${Math.random().toFloat()}"
    private val channelDesc = "test"
    private val channelName = "#Cool API"
    private val newMessageText = "What if I could eat 200 sausages for 5 min??? Hmm..."
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
    fun loggedInUserAvatarPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailVijay)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.assertImagePresentTest()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInUserNamePresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.assertUserNamePresentTest(validUserNameLala)
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInUserEmailPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.assertUserEmailTest(validUserEmailLala)
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInAvatarImgLeftFromEmailPositionTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailVijay)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.assertImagePositionTest()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInNameAboveEmailPositionTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailBill)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.assertUserNamePositionTest()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInAddChannelBtnPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.assertAddChannelBtnPresentTest()
        loggedInScreen.clickOnLogoutBtn()
    }

    // Dialog tests below
    @Test
    fun addChannelBtnInvokeDialogTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailBill)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.assertDialogPoppedUp()
        loggedInScreen.clickOnDialogCancelBtn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun channelNameHintTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailVijay)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.assertDialogChannelNameHintPresent()
        loggedInScreen.clickOnDialogCancelBtn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun channelDescriptionHintTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailBill)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.assertDialogChannelDescriptionHintPresent()
        loggedInScreen.clickOnDialogCancelBtn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun createNewChannelTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailBill)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.typeDialogChannelName(channelNameGenerator)
        loggedInScreen.typeDialogChannelDescription(channelDesc)
        loggedInScreen.clickOnDialogAddBtn()
        loggedInScreen.assertNewChannelSuccessfullyCreated(channelNameGenerator)
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun noChannelCreatedWithEmptyFieldsToastTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.clickOnDialogAddBtn()
        val toastPopUps = ToastPopUps()
        toastPopUps.assertDialogToastNoChannelCreatedWithEmptyFields(myActivityTestRule)
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun assertThatChannelIsClicked() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.clickOnChannelRoom(channelName)
        loggedInScreen.assertChannelRoomIsClicked(channelName)
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
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
        loggedInScreen.typeNewMessage(newMessageText)
        loggedInScreen.clickOnMessageSendBtn()
        loggedInScreen.assertMessageHasSent(newMessageText)
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
        loggedInScreen.typeNewMessage(newMessageText)
        loggedInScreen.clickOnMessageSendBtn()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailBill)
        loginScreen.typePassword(validUserPassword)
        loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
        loggedInScreen.clickOnChannelRoom(channelName)
        loggedInScreen.assertMessageHasSent(newMessageText)
        loggedInScreen.typeNewMessage("You would be pretty full. I think :)")
        loggedInScreen.clickOnMessageSendBtn()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun assertMessageSentToNewChannelCreatedTest() {
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
        loggedInScreen.assertMessageHasSent(conversationBill)
        loggedInScreen.clickOnMessageSendBtn()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
    }


}