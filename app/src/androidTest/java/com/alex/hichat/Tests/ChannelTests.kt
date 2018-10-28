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

class ChannelTests {

    @RunWith(AndroidJUnit4::class)
    class MessagesTests {

        @get:Rule
        var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

        private val validUserEmailLala = "lala@gmail.com"
        private val validUserEmailBill = "bill@smile.com"
        private val validUserPassword = "123456"
        private val channelNameGenerator = "Test - ${Math.random().toFloat()}"
        private val channelDesc = "test"
        private val channelName = "#Cool API"

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
    }
}