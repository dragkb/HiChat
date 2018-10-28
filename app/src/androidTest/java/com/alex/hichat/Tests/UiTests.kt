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
class UiTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val validUserNameLala = "lala"
    private val validUserEmailLala = "lala@gmail.com"
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
    fun mainIsMainActivityScreenLaunchesTest() {
        val mainScreen = MainScreen()
        mainScreen.assertHamburgerBtnDisplayedAndClickable()
    }

    @Test
    fun mainLoginBtnPresentAndClickableTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        mainScreen.assertLoginBtnDisplayedAndClickable()
    }

    @Test
    fun mainChannelBtnPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        mainScreen.assertAddChannelBtnDisplayed()
    }

    @Test
    fun mainDefaultAccountImgPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        mainScreen.assertAccountImgDisplayed()
    }

    @Test
    fun userNameTxtFieldHintPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertUserNameTxtHintPresent()
    }

    @Test
    fun userEmailTxtFieldPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertUserEmailTxtHintPresent()
    }

    @Test
    fun userPasswordTxtFieldPresentTest() {
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

    @Test
    fun loggedInUserAvatarPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailVijay)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.assertImagePresentTest()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInUserNamePresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.assertUserNamePresentTest(validUserNameLala)
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInUserEmailPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.assertUserEmailTest(validUserEmailLala)
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInAddChannelBtnPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.assertAddChannelBtnPresentTest()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun channelNameHintTestPresent() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailVijay)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.assertDialogChannelNameHintPresent()
        loggedInScreen.clickOnDialogCancelBtn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun channelDescriptionHintTestPresent() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        val loginScreen = mainScreen.clickOnLoginHeaderBtn()
        loginScreen.typeEmail(validUserEmailBill)
        loginScreen.typePassword(validUserPassword)
        val loggedInScreen = loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.assertDialogChannelDescriptionHintPresent()
        loggedInScreen.clickOnDialogCancelBtn()
        loggedInScreen.clickOnLogoutBtn()
    }
}