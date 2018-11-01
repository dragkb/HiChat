package com.alex.hichat.Utilities

import android.support.annotation.VisibleForTesting
import android.support.test.espresso.idling.CountingIdlingResource

object IdlingResourceHolder {
    @VisibleForTesting
    val networkIdlingResource = CountingIdlingResource("API call", true)
    // TODO add to constructor
}