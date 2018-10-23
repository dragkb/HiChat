package com.alex.hichat.Tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep
import com.alex.hichat.Screens.LoginScreen
import com.alex.hichat.Screens.MainScreen
import com.alex.hichat.Utilities.ToastPopUps


@RunWith(AndroidJUnit4::class)
class LoginActivityTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val validUserName = "lala"
    private val validUserEmail = "l@l.com"
    private val validUserPassword = "123456"
    private val invalidUserEmail = "abrakadabra@booms.com"
    private val invalidUserPassword = "12345678910"

    private val mainScreen = MainScreen()
    private val loginScreen = LoginScreen()
    private val toastsPopUps = ToastPopUps()


    @Test
    fun loginHappyPathTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
                .typePassword(validUserPassword)
                .clickOnLoginBtn()
        sleep(2000)
        mainScreen.assertThatLoggedIn()
        mainScreen.clickOnLoginHeaderBtn()
    }

    @Test
    fun logoutHappyPathTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
                .typePassword(validUserPassword)
                .clickOnLoginBtn()
        sleep(2000)
        mainScreen.clickOnLoginHeaderBtn()
        mainScreen.assertThatLoggedOut()
    }

    @Test
    fun loginUserAvatarPresentTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
                .typePassword(validUserPassword)
                .clickOnLoginBtn()
        sleep(2000)
        loginScreen.assertImagePresentTest()
        mainScreen.clickOnLoginHeaderBtn()
    }

    @Test
    fun loginUserNamePresentTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
                .typePassword(validUserPassword)
                .clickOnLoginBtn()
        sleep(2000)
        loginScreen.assertUserNamePresentTest(validUserName)
        mainScreen.clickOnLoginHeaderBtn()
    }

    @Test
    fun loginUserEmailPresentTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
                .typePassword(validUserPassword)
                .clickOnLoginBtn()
        sleep(2000)
        loginScreen.assertUserEmailTest(validUserEmail)
        mainScreen.clickOnLoginHeaderBtn()
    }

    @Test
    fun loginAvatarImgLeftFromEmailPositionTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
                .typePassword(validUserPassword)
                .clickOnLoginBtn()
        sleep(2000)
        loginScreen.assertImagePositionTest()
        mainScreen.clickOnLoginHeaderBtn()
    }

    @Test
    fun loginNameAboveEmailPositionTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
                .typePassword(validUserPassword)
                .clickOnLoginBtn()
        sleep(2000)
        loginScreen.assertUserNamePositionTest()
        mainScreen.clickOnLoginHeaderBtn()
    }

    @Test
    fun loginAddChannelBtnPresentTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
                .typePassword(validUserPassword)
                .clickOnLoginBtn()
        sleep(2000)
        loginScreen.assertAddChannelBtnPresentTest()
        mainScreen.clickOnLoginHeaderBtn()
    }
    @Test
    fun loginSomethingWentWrongToastMessageTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.typeEmail(invalidUserEmail)
                .typePassword(invalidUserPassword)
                .clickOnLoginBtn()
        sleep(700)
        toastsPopUps.assertToastSomethingWrongPopUpped(myActivityTestRule)
    }

    @Test
    fun loginFillBothEmailPasswordToastMessageTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
                .clickOnLoginBtn()
        sleep(700)
        toastsPopUps.assertToastBothFieldsWrong(myActivityTestRule)
    }
}