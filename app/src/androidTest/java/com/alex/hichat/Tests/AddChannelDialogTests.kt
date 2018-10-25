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
class AddChannelDialogTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val validUserEmail = "lala@gmail.com"
    private val validUserPassword = "123456"
    val channelName = "Test - ${Math.random().toFloat()}"
    private val channelDesc = "test"

    @Before
    fun idlingResourceRegister() {
        IdlingRegistry.getInstance().register(IdlingResourceHolder.networkIdlingResource)
    }

    @After
    fun idlingResourceUnregister() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHolder.networkIdlingResource)
    }

    @Test
    fun addChannelBtnInvokeDialogTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
        val addChannelScreen = loggedInScreen.clickOnAddChannelBtn()
        addChannelScreen.assertDialogPoppedUp()
        addChannelScreen.clickOnCancelBtn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun channelNameHintTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
        val addChannelScreen = loggedInScreen.clickOnAddChannelBtn()
        addChannelScreen.assertChannelNameHintPresent()
        addChannelScreen.clickOnCancelBtn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun channelDescriptionHintTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
        val addChannelScreen = loggedInScreen.clickOnAddChannelBtn()
        addChannelScreen.assertChannelDescriptionHintPresent()
        addChannelScreen.clickOnCancelBtn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun dialogCancelBtnPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
        val addChannelScreen = loggedInScreen.clickOnAddChannelBtn()
        addChannelScreen.typeChannelName(channelName)
        addChannelScreen.typeChannelDescription(channelDesc)
        addChannelScreen.clickOnAddBtn()
//        loggedInScreen.assertNewChannelSuccessfullyCreated()
        // need to add assertion of channel added with isDescendantOf()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun createNewChannelTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
        val addChannelScreen = loggedInScreen.clickOnAddChannelBtn()
        addChannelScreen.typeChannelName(channelName)
        addChannelScreen.typeChannelDescription(channelDesc)
        addChannelScreen.clickOnAddBtn()
//        loggedInScreen.assertNewChannelSuccessfullyCreated()
        // need to add assertion of channel added with isDescendantOf()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun noChannelCreatedWithEmptyFieldsToastTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
        val addChannelScreen = loggedInScreen.clickOnAddChannelBtn()
        addChannelScreen.clickOnAddBtn()
        val toastPopUps = ToastPopUps()
        toastPopUps.assertDialogToastNoChannelCreatedWithEmptyFieldsAppeared(myActivityTestRule)
        loggedInScreen.clickOnLogoutBtn()
    }
}