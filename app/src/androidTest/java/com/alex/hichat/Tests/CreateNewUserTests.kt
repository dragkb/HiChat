package com.alex.hichat.Tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Screens.CreateUserValidation
import com.alex.hichat.Screens.LoggedInScreen
import com.alex.hichat.Screens.MainScreen
import com.alex.hichat.Screens.SignUpScreen
import com.alex.hichat.Services.IdlingResourceHolder
import com.alex.hichat.Utilities.ToastPopUps
import org.junit.After
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class CreateNewUserTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private var randomPassword = Math.random().toFloat()
    private val newUserName = "Zorro"
    private var newUserEmail = "zorro$randomPassword@gmail.com"
    private val newUserPassword = "123456"

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
        val loggedInScreen = signUpScreen.clickOnCreateUserBtn(CreateUserValidation.VALID_USER)
            as LoggedInScreen
        loggedInScreen.assertUserEmailTest(newUserEmail)
        loggedInScreen.clickOnLogoutBtn()
    }
}