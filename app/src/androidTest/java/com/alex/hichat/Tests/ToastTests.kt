package com.alex.hichat.Tests

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Screens.CreateUserValidation
import com.alex.hichat.Screens.LoggedInScreen
import com.alex.hichat.Screens.LoginScreen
import com.alex.hichat.Screens.MainScreen
import com.alex.hichat.Screens.SignUpScreen
import com.alex.hichat.Screens.UserClickValidation
import com.alex.hichat.Services.IdlingResourceHolder
import com.alex.hichat.Utilities.ToastPopUps
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToastTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val invalidUserEmail = "abra_kadabra@wow.com"
    private val invalidUserPassword = "12345678910"
    private val validUserEmailLala = "lala@gmail.com"
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
    fun loginToastSomethingWentWrongTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(invalidUserEmail)
        loginScreen.typePassword(invalidUserPassword)
        loginScreen.clickOnLoginBtn(UserClickValidation.INVALID_USER)
            as LoginScreen
        val toastsPopUps = ToastPopUps()
        toastsPopUps.assertLoginToastSomethingWrong(myActivityTestRule)
    }

    @Test
    fun loginToastFillBothEmailAndPasswordFieldsTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.clickOnLoginBtn(UserClickValidation.INVALID_USER)
            as LoginScreen
        val toastsPopUps = ToastPopUps()
        toastsPopUps.assertLoginToastBothFieldsWrong(myActivityTestRule)
    }

    @Test
    fun createUserToastEmptyFieldsMessageTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.clickOnCreateUserBtn(CreateUserValidation.INVALID_USER) as SignUpScreen
        val toastPopUps = ToastPopUps()
        toastPopUps.assertCreateUserToastAllFieldsShouldBeFilled(myActivityTestRule)
    }

    @Test
    fun noChannelCreatedWithEmptyFieldsToastTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.clickOnDialogAddBtn()
        val toastPopUps = ToastPopUps()
        toastPopUps.assertDialogToastNoChannelCreatedWithEmptyFields(myActivityTestRule)
        loggedInScreen.clickOnLogoutBtn()
    }
}