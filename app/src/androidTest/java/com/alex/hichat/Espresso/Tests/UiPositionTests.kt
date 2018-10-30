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
class UiPositionTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val userVijay = OldUser("vijay@space.com", "123456")
    private val userBill = OldUser("bill@smile.com", "123456")

    @Before
    fun idlingResourceRegister() {
        IdlingRegistry.getInstance().register(IdlingResourceHolder.networkIdlingResource)
    }

    @After
    fun idlingResourceUnregister() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHolder.networkIdlingResource)
    }

    @Test
    fun mainDefaultAccountImgPositionTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        mainScreen.assertPositionAccountImgAboveOfLoginBtn()
    }

    @Test
    fun mainChannelBtnPositionTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        mainScreen.assertPositionChannelBtnLeftOfLoginBtn()
    }

    @Test
    fun loggedInAvatarImgLeftFromEmailPositionTest() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userVijay)
        loggedInScreen.assertImagePositionTest()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInNameAboveEmailPositionTest() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userBill)
        loggedInScreen.assertUserNamePositionTest()
        loggedInScreen.clickOnLogoutBtn()
    }
}