package com.alex.hichat.Tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Screens.LoginScreen
import com.alex.hichat.Screens.MainScreen
import com.alex.hichat.Screens.SignUpScreen
import com.alex.hichat.Utilities.ToastPopUps
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class SignUpActivityTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private var randomPassword = Math.random().toFloat()
    private val newUserName = "zorro"
    private var newUserEmail = "zorro$randomPassword@gmail.com"
    private val newUserPassword = "123456"

    private val mainScreen = MainScreen()
    private val loginScreen = LoginScreen()
    private val signUpScreen = SignUpScreen()
    private val toastPopUps = ToastPopUps()

    @Test
    fun createNewUserTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.clickOnSignUpHereBtn()
        signUpScreen.typeNewName(newUserName)
                .typeNewEmail(newUserEmail)
                .typeNewPassword(newUserPassword)
        Espresso.closeSoftKeyboard()
        signUpScreen.generateNewAvatar(3)
                .generateBackgroundColor(3)
                .clickOnCreateUserBtn()
        sleep(6000)
        loginScreen.assertUserEmailTest(newUserEmail)
        mainScreen.clickOnLoginHeaderBtn()
    }

    @Test
    fun emptyCreateUserBtnEmptyFieldsToastMessageTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.clickOnSignUpHereBtn()
        signUpScreen.clickOnCreateUserBtn()
        sleep(700)
        toastPopUps.assertToastSignUpAllFieldsFilledIn(myActivityTestRule)
    }

    @Test
    fun userNameTxtFieldHintPresent() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertUserNameTxtHintPresent()
    }

    @Test
    fun userEmailTxtFieldPresent() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertUserEmailTxtHintPresent()
    }

    @Test
    fun userPasswordTxtFieldPresent() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertUserPasswordTxtHintPresent()
    }

    @Test
    fun tapToGenerateTextViewPresentTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertTapToGenerateTxtViewPresent()
    }

    @Test
    fun generateAvatarImgPresentAndClickableTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertAvatarImgPresentAndClickable()
    }

    @Test
    fun generateBackgroundColorBtnPresentAndClickableTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertBackgroundColorBtnPresentAndClickable()
    }

    @Test
    fun createUserBtnPresentAndClickableTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .clickOnLoginHeaderBtn()
        loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertCreateUserBtnPresentAndClickable()
    }
}