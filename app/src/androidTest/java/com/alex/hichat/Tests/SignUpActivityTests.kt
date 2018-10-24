package com.alex.hichat.Tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Screens.LoggedInScreen
import com.alex.hichat.Screens.MainScreen
import com.alex.hichat.Services.IdlingResourceHolder
import com.alex.hichat.Utilities.ToastPopUps
import org.junit.After
import org.junit.Before
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class SignUpActivityTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private var randomPassword = Math.random().toFloat()
    private val newUserName = "zorro"
    private var newUserEmail = "zorro$randomPassword@gmail.com"
    private val newUserPassword = "123456"

    @Before
    fun idlingResourceRegister() {
        IdlingRegistry.getInstance().register(IdlingResourceHolder.networkIdlingResource)
    }

    @After
    fun idlingResourceUnregister() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHolder.networkIdlingResource)
    }

    @Test
    fun createNewUserTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.typeNewName(newUserName)
        signUpScreen.typeNewEmail(newUserEmail)
        signUpScreen.typeNewPassword(newUserPassword)
        Espresso.closeSoftKeyboard()
        signUpScreen.generateNewAvatar(3)
        signUpScreen.generateBackgroundColor(3)
        signUpScreen.clickOnCreateUserBtn()
//        sleep(6000)
        val loggedInScreen = LoggedInScreen()
        loggedInScreen.assertUserEmailTest(newUserEmail)
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun createUserToastEmptyFieldsMessageTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.clickOnCreateUserBtn()
//        sleep(700)
        val toastPopUps = ToastPopUps()
        toastPopUps.assertToastSignUpAllFieldsFilledIn(myActivityTestRule)
    }

    @Test
    fun userNameTxtFieldHintPresent() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertUserNameTxtHintPresent()
    }

    @Test
    fun userEmailTxtFieldPresent() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertUserEmailTxtHintPresent()
    }

    @Test
    fun userPasswordTxtFieldPresent() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertUserPasswordTxtHintPresent()
    }

    @Test
    fun tapToGenerateTextViewPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertTapToGenerateTxtViewPresent()
    }

    @Test
    fun generateAvatarImgPresentAndClickableTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertAvatarImgPresentAndClickable()
    }

    @Test
    fun generateBackgroundColorBtnPresentAndClickableTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertBackgroundColorBtnPresentAndClickable()
    }

    @Test
    fun createUserBtnPresentAndClickableTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertCreateUserBtnPresentAndClickable()
    }
}