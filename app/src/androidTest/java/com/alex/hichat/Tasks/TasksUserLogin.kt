package com.alex.hichat.Tasks

import com.alex.hichat.Model.OldUser
import com.alex.hichat.Screens.LoggedInScreen
import com.alex.hichat.Screens.LoginScreen
import com.alex.hichat.Screens.UserClickValidation

object TasksUserLogin {

    fun validLogin(user: OldUser): LoggedInScreen {
        val loginScreen = LoginScreen()
        loginScreen.typeEmail(user.email)
        loginScreen.typePassword(user.password)
        return loginScreen.clickOnLoginBtn(UserClickValidation.VALID_USER)
            as LoggedInScreen
    }

    fun invalidLogin(user: OldUser): LoginScreen {
        val loginScreen = LoginScreen()
        loginScreen.typeEmail(user.email)
        loginScreen.typePassword(user.password)
        return loginScreen.clickOnLoginBtn(UserClickValidation.INVALID_USER)
            as LoginScreen
    }
}