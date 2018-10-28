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

@RunWith(AndroidJUnit4::class)
class UiPositionTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val validUserEmailVijay = "vijay@space.com"
    private val validUserEmailBill = "bill@smile.com"
    private val validUserPassword = "123456"

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
}