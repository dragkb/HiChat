package com.alex.hichat.Tasks

import com.alex.hichat.Screens.LoggedInScreen

object TasksSendMessage {

    fun sendMessage(message: String) {
        val loggedInScreen = LoggedInScreen()
        loggedInScreen.typeNewMessage(message)
        loggedInScreen.clickOnMessageSendBtn()
    }
}