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
class LoggedInActivityTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val validUserName = "lala"
    private val validUserEmail = "lala@gmail.com"
    private val validUserPassword = "123456"
    private val invalidUserEmail = "abra_kadabra@wow.com"
    private val invalidUserPassword = "12345678910"

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
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
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
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
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
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
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
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
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
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
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
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
        loggedInScreen.assertAddChannelBtnPresentTest()
        loggedInScreen.clickOnLogoutBtn()
    }
}