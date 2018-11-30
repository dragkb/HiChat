package com.alex.hichat.Espresso.Tests

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Espresso.Model.OldUser
import com.alex.hichat.Espresso.Screens.MainScreen
import com.alex.hichat.Utilities.IdlingResourceHolder
import com.alex.hichat.Espresso.Tasks.TasksMainLogin
import com.alex.hichat.Espresso.Tasks.TasksUserLogin
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val userVijay = OldUser("vijay@space.com", "123456")
    private val userBill = OldUser("bill@smile.com", "123456")
    private val userLala = OldUser("lala@gmail.com", "123456")
    private val validUserNameLala = "lala"
    private val validUserEmailLala = "lala@gmail.com"

    @Before
    fun idlingResourceRegister() {
        IdlingRegistry.getInstance().register(IdlingResourceHolder.networkIdlingResource)
    }

    @After
    fun idlingResourceUnregister() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHolder.networkIdlingResource)
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
        val loginScreen = TasksMainLogin.loginMain()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertUserNameTxtHintPresent()
    }

    @Test
    fun userEmailTxtFieldPresentTest() {
        val loginScreen = TasksMainLogin.loginMain()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertUserEmailTxtHintPresent()
    }

    @Test
    fun userPasswordTxtFieldPresentTest() {
        val loginScreen = TasksMainLogin.loginMain()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertUserPasswordTxtHintPresent()
    }

    @Test
    fun tapToGenerateTextViewPresentTest() {
        val loginScreen = TasksMainLogin.loginMain()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertTapToGenerateTxtViewPresent()
    }

    @Test
    fun generateAvatarImgPresentAndClickableTest() {
        val loginScreen = TasksMainLogin.loginMain()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertAvatarImgPresentAndClickable()
    }

    @Test
    fun generateBackgroundColorBtnPresentAndClickableTest() {
        val loginScreen = TasksMainLogin.loginMain()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertBackgroundColorBtnPresentAndClickable()
    }

    @Test
    fun createUserBtnPresentAndClickableTest() {
        val loginScreen = TasksMainLogin.loginMain()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.assertCreateUserBtnPresentAndClickable()
    }

    @Test
    fun loggedInUserAvatarPresentTest() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userVijay)
        loggedInScreen.assertImagePresentTest()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInUserNamePresentTest() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userLala)
        loggedInScreen.assertUserNamePresentTest(validUserNameLala)
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInUserEmailPresentTest() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userLala)
        loggedInScreen.assertUserEmailTest(validUserEmailLala)
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun loggedInAddChannelBtnPresentTest() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userLala)
        loggedInScreen.assertAddChannelBtnPresentTest()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun channelNameHintTestPresent() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userVijay)
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.assertDialogChannelNameHintPresent()
        loggedInScreen.clickOnDialogCancelBtn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun channelDescriptionHintTestPresent() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userBill)
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.assertDialogChannelDescriptionHintPresent()
        loggedInScreen.clickOnDialogCancelBtn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun appNameHeaderPresent() {
        val mainScreen = MainScreen()
        mainScreen.assertAppHeaderNamePresent()
    }
}