package com.alex.hichat.Tasks

import com.alex.hichat.Model.NewUser
import com.alex.hichat.Screens.CreateUserValidation
import com.alex.hichat.Screens.LoggedInScreen
import com.alex.hichat.Screens.SignUpScreen

object TasksCreateUser {

    fun createNewUser(newUser: NewUser): LoggedInScreen {
        val signUpScreen = SignUpScreen()
        signUpScreen.typeNewName(newUser.userName)
        signUpScreen.typeNewEmail(newUser.userEmail)
        signUpScreen.typeNewPassword(newUser.userPassword)
        signUpScreen.generateNewAvatar(newUser.avatarClicks)
        signUpScreen.generateBackgroundColor(newUser.backgroundClicks)
        return signUpScreen.clickOnCreateUserBtn(CreateUserValidation.VALID_USER)
            as LoggedInScreen
    }
}