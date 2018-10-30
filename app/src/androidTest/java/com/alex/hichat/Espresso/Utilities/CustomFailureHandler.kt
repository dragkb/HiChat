package com.alex.hichat.Espresso.Utilities

import android.support.test.espresso.EspressoException
import android.support.test.espresso.FailureHandler
import android.view.View
import org.hamcrest.Matcher
import java.lang.AssertionError

object CustomFailureHandler : FailureHandler {
    override fun handle(error: Throwable, viewMatcher: Matcher<View>) {
        if (error is EspressoException || error is AssertionError) {
            error.stackTrace = Thread.currentThread().stackTrace
                .filter { it.className.startsWith("com.alex.hichat") }
                .drop(1)
                .toTypedArray()
        }
        throw error
    }
}