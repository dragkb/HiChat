package com.alex.hichat.Espresso.Tests

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Espresso.Model.OldUser
import com.alex.hichat.Espresso.Screens.LoggedInScreen
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
class ChannelTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val userLala = OldUser("lala@gmail.com", "123456")
    private val userBill = OldUser("bill@smile.com", "123456")
    private val channelNameGenerator = "Test - ${(Math.random() * 100000).toInt()}"
    private val channelDesc = "test"
    private val channelName = "Cool API"

    @Before
    fun loginAndIdlingResourceRegister() {
        IdlingRegistry.getInstance().register(IdlingResourceHolder.networkIdlingResource)
    }

    @After
    fun logoutAndIdlingResourceUnregister() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHolder.networkIdlingResource)
    }

    @Test
    fun addChannelBtnInvokeDialogTest() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userLala)
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.assertDialogPoppedUp()
        loggedInScreen.clickOnDialogCancelBtn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun createNewChannelTest() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userBill)
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.typeDialogChannelName(channelNameGenerator)
        loggedInScreen.typeDialogChannelDescription(channelDesc)
        loggedInScreen.clickOnDialogAddBtn()
        loggedInScreen.assertNewChannelSuccessfullyCreated(channelNameGenerator)
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun assertThatChannelIsClicked() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userLala)
        loggedInScreen.clickOnChannelRoomByIndex(0)
        loggedInScreen.assertChannelRoomIsClicked(channelName)
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        loggedInScreen.clickOnLogoutBtn()
    }
}