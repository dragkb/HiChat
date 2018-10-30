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
class LogInTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val userLala = OldUser("lala@gmail.com", "123456")

    @Before
    fun loginAndIdlingResourceRegister() {
        IdlingRegistry.getInstance().register(IdlingResourceHolder.networkIdlingResource)
    }

    @After
    fun idlingResourceUnregister() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHolder.networkIdlingResource)
    }

    @Test
    fun loginHappyPathTest() {
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userLala)
        loggedInScreen.assertThatLoggedIn()
        loggedInScreen.clickOnLogoutBtn()
    }

    @Test
    fun logoutHappyPathTest() {
        val mainScreen = MainScreen()
        TasksMainLogin.loginMain()
        val loggedInScreen = TasksUserLogin.validLogin(userLala)
        loggedInScreen.clickOnLogoutBtn()
        mainScreen.assertThatLoggedOut()
    }
}