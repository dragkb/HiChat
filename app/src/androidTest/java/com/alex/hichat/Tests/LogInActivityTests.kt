package com.alex.hichat.Tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Screens.MainScreen
import com.alex.hichat.Utilities.ToastPopUps
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class LogInActivityTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val validUserEmail = "l@l.com"
    private val validUserPassword = "123456"
    private val invalidUserEmail = "abrakadabra@booms.com"
    private val invalidUserPassword = "12345678910"

    @Test
    fun loginHappyPathTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn()
        sleep(2000)
        loggedInScreen.assertThatLoggedIn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun logoutHappyPathTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn()
        sleep(2000)
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.assertThatLoggedOut()
    }

    @Test
    fun loginSomethingWentWrongToastMessageTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(invalidUserEmail)
        loginScreen.typePassword(invalidUserPassword)
        loginScreen.clickOnLoginBtn()
        sleep(700)
        val toastsPopUps = ToastPopUps()
        toastsPopUps.assertToastSomethingWrongPopUpped(myActivityTestRule)
    }

    @Test
    fun loginFillBothEmailPasswordToastMessageTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.clickOnLoginBtn()
        sleep(700)
        val toastsPopUps = ToastPopUps()
        toastsPopUps.assertToastBothFieldsWrong(myActivityTestRule)
    }
}