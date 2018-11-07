package com.alex.hichat.Espresso.Tests

import android.support.test.espresso.IdlingRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Espresso.Model.OldUser
import com.alex.hichat.Espresso.Screens.CreateUserValidation
import com.alex.hichat.Espresso.Screens.LoginScreen
import com.alex.hichat.Espresso.Screens.SignUpScreen
import com.alex.hichat.Espresso.Screens.UserClickValidation
import com.alex.hichat.Utilities.IdlingResourceHolder
import com.alex.hichat.Espresso.Tasks.TasksMainLogin
import com.alex.hichat.Espresso.Tasks.TasksUserLogin
import com.alex.hichat.Espresso.Utilities.ToastPopUps
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToastTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val validUserEmailLala = "lala@gmail.com"
    private val userLala = OldUser("lala@gmail.com", "123456")
    private val userUnknown = OldUser("abra_kadabra@wow.com", "12345678910")

    @Before
    fun loginAndIdlingResourceRegister() {
        IdlingRegistry.getInstance().register(IdlingResourceHolder.networkIdlingResource)
    }

    @After
    fun idlingResourceUnregister() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHolder.networkIdlingResource)
    }

    @Test
    fun loginToastSomethingWentWrongTest() {
        TasksMainLogin.loginMain()
        TasksUserLogin.invalidLogin(userUnknown)
        val toastsPopUps = ToastPopUps()
        toastsPopUps.assertLoginToastSomethingWrong(myActivityTestRule)
    }
    // TODO do all static
    @Test
    fun loginToastFillBothEmailAndPasswordFieldsTest() {
        TasksMainLogin.loginMain()
        val loginScreen = LoginScreen()
        loginScreen.typeEmail(validUserEmailLala)
        loginScreen.clickOnLoginBtn(UserClickValidation.INVALID_USER)
            as LoginScreen
        val toastsPopUps = ToastPopUps()
        toastsPopUps.assertLoginToastBothFieldsWrong(myActivityTestRule)
    }

    @Test
    fun createUserToastEmptyFieldsMessageTest() {
        TasksMainLogin.loginMain()
        val loginScreen = LoginScreen()
        val signUpScreen = loginScreen.clickOnSignUpHereBtn()
        signUpScreen.clickOnCreateUserBtn(CreateUserValidation.INVALID_USER) as SignUpScreen
        val toastPopUps = ToastPopUps()
        toastPopUps.assertCreateUserToastAllFieldsShouldBeFilled(myActivityTestRule)
    }

    @Test
    fun noChannelCreatedWithEmptyFieldsToastTest() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userLala)
        loggedInScreen.clickOnAddChannelBtn()
        loggedInScreen.clickOnDialogAddBtn()
        val toastPopUps = ToastPopUps()
        toastPopUps.assertDialogToastNoChannelCreatedWithEmptyFields(myActivityTestRule)
        loggedInScreen.clickOnLogoutBtn()
    }
}