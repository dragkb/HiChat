package com.alex.hichat.Tests

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Screens.MainScreen
import com.alex.hichat.Services.IdlingResourceHolder
import com.alex.hichat.Utilities.ToastPopUps
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class AddChannelDialogTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val toastsPopUps = ToastPopUps()

    private val validUserEmail = "l@l.com"
    private val validUserPassword = "123456"
    private val channelName = "Test#${Math.random().toFloat()}"
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
        val loggedInScreen = loginScreen.clickOnLoginBtn()
//        sleep(2000)
        val addChannelScreen = loggedInScreen.clickOnAddChannelBtn()
        addChannelScreen.assertDialogPoppedUp()
        addChannelScreen.clickOnCancelBtn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun createNewChannelTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn()
//        sleep(2000)
        val addChannelScreen = loggedInScreen.clickOnAddChannelBtn()
        addChannelScreen.typeChannelName(channelName)
        addChannelScreen.typeChannelDescription(channelDesc)
        addChannelScreen.clickOnAddBtn()
        // need to add assertion of channel added with isDescendandOf()
        loggedInScreen.clickOnLogoutBtn()
    }
}