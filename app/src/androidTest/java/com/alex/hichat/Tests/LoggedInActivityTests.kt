package com.alex.hichat.Tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Screens.MainScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class LoggedInActivityTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val validUserName = "lala"
    private val validUserEmail = "l@l.com"
    private val validUserPassword = "123456"
    private val invalidUserEmail = "abrakadabra@booms.com"
    private val invalidUserPassword = "12345678910"

    @Test
    fun loggedInUserAvatarPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn()
        sleep(2000)
        loggedInScreen.assertImagePresentTest()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInUserNamePresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn()
        sleep(2000)
        loggedInScreen.assertUserNamePresentTest(validUserName)
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInUserEmailPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn()
        sleep(2000)
        loggedInScreen.assertUserEmailTest(validUserEmail)
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInAvatarImgLeftFromEmailPositionTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn()
        sleep(2000)
        loggedInScreen.assertImagePositionTest()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInNameAboveEmailPositionTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn()
        sleep(2000)
        loggedInScreen.assertUserNamePositionTest()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInAddChannelBtnPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn()
        sleep(2000)
        loggedInScreen.assertAddChannelBtnPresentTest()
        loggedInScreen.clickOnLogoutBtn()
    }
}