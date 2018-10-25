package com.alex.hichat.Tests

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Screens.LoggedInScreen
import com.alex.hichat.Screens.LoginScreen
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
class LogInActivityTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

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
    fun loginHappyPathTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(3500)
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
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
//        sleep(2000)
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.assertThatLoggedOut()
    }

    @Test
    fun loginToastSomethingWentWrongTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(invalidUserEmail)
        loginScreen.typePassword(invalidUserPassword)
        loginScreen.clickOnLoginBtn(UserClickValidation.INVALID_USER)
            as LoginScreen
//        sleep(700)
        val toastsPopUps = ToastPopUps()
        toastsPopUps.assertLoginToastSomethingWrongAppeared(myActivityTestRule)
    }

    @Test
    fun loginToastFillBothEmailAndPasswordFieldsTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmail)
        loginScreen.clickOnLoginBtn(UserClickValidation.INVALID_USER)
            as LoginScreen
//        sleep(700)
        val toastsPopUps = ToastPopUps()
        toastsPopUps.assertLoginToastBothFieldsWrongAppeared(myActivityTestRule)
    }
}