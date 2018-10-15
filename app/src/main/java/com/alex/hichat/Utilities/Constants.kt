package com.alex.hichat.Utilities

// This BASE_URL comes from your heroku account in settings. Add /v1/ in the end of URL
const val BASE_URL = "https://hismackchat.herokuapp.com/v1/"

// BASE_URL for local host running from your machine locally
// const val BASE_URL = "http://10.0.2.2:3005/v1/"

// BASE_URL + account/register. You can get this info from Postman tool
const val URL_REGISTER = "${BASE_URL}account/register"

// URL for login.
const val URL_LOGIN = "${BASE_URL}account/login"

// URL for create an user
const val URL_CREATE_USER = "${BASE_URL}user/add"

// URL for find a user by email
const val URL_FIND_USER_BY_EMAIL = "${BASE_URL}user/byEmail/"

// Socket URL
const val SOCKET_URL = "https://hismackchat.herokuapp.com/"

// Broadcast const to say another activity that something changed
const val BROADCAST_USER_DATA_CHANGE = "BROADCAST_USER_DATA_CHANGE"