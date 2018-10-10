package com.alex.hichat.Utilities

// This BASE_URL comes from your heroku account in settings. Add /v1/ in the end of URL
const val BASE_URL = "https://hismackchat.herokuapp.com/v1/"
// BASE_URL for local host running from your machine locally
// const val BASE_URL = "http://10.0.2.2:3005/v1/"
// BASE_URL + account/register. You can get this info from Postman tool
const val URL_REGISTER = "${BASE_URL}account/register"