package com.alex.hichat.Espresso.Tasks

import com.alex.hichat.Espresso.Screens.LoginScreen
import com.alex.hichat.Espresso.Screens.MainScreen

object TasksMainLogin {

    fun loginMain(): LoginScreen {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        return mainScreen.clickOnLoginHeaderBtn()
    }
}