package com.alex.hichat.Espresso.Tasks

import com.alex.hichat.Espresso.Screens.LoggedInScreen

object TasksSendMessage {

    fun sendMessage(message: String) {
        val loggedInScreen = LoggedInScreen()
        loggedInScreen.typeNewMessage(message)
        loggedInScreen.clickOnMessageSendBtn()
    }
}