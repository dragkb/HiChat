package com.alex.hichat.Espresso.Tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.IdlingRegistry
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Espresso.Model.NewUser
import com.alex.hichat.Espresso.Tasks.TasksCreateUser
import com.alex.hichat.Utilities.IdlingResourceHolder
import com.alex.hichat.Espresso.Tasks.TasksMainLogin
import org.junit.After
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class CreateNewUserTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private var newUserEmail = "zorro${(Math.random() * 100000).toInt()}@gmail.com"
    private val newUser = NewUser("Zorro", newUserEmail, "123456", 3, 3)

    @Before
    fun loginAndIdlingResourceRegister() {
        IdlingRegistry.getInstance().register(IdlingResourceHolder.networkIdlingResource)
    }

    @After
    fun logoutAndIdlingResourceUnregister() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHolder.networkIdlingResource)
    }

    @Test
    fun createNewUserTest() {
        val loginScreen = TasksMainLogin.loginMain()
        loginScreen.clickOnSignUpHereBtn()
        val loggedInScreen = TasksCreateUser.createNewUser(newUser)
        loggedInScreen.assertUserEmailTest(newUserEmail)
        loggedInScreen.clickOnLogoutBtn()
    }
}