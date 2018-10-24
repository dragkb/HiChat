package com.alex.hichat.Services

import android.support.test.espresso.idling.CountingIdlingResource

object IdlingResourceHolder {
    val networkIdlingResource = CountingIdlingResource("API call", true)
}