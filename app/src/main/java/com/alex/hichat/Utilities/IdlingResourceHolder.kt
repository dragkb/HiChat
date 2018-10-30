package com.alex.hichat.Utilities

import android.support.test.espresso.idling.CountingIdlingResource

object IdlingResourceHolder {
    val networkIdlingResource = CountingIdlingResource("API call", true)
}