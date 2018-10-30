package com.alex.hichat.Tasks

import com.alex.hichat.Screens.LoginScreen
import com.alex.hichat.Screens.MainScreen

object TasksMainLogin {

    fun loginMain(): LoginScreen {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        return mainScreen.clickOnLoginHeaderBtn()
    }
}