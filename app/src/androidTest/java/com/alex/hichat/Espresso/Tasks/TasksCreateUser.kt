package com.alex.hichat.Espresso.Tasks

import com.alex.hichat.Espresso.Model.NewUser
import com.alex.hichat.Espresso.Screens.CreateUserValidation
import com.alex.hichat.Espresso.Screens.LoggedInScreen
import com.alex.hichat.Espresso.Screens.SignUpScreen

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