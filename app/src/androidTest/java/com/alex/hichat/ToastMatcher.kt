package com.alex.hichat

import android.support.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import android.view.WindowManager

class ToastMatcher : TypeSafeMatcher<Root>() {

    override fun describeTo(description: Description?) {
        description?.appendText("is toast")
    }

    override fun matchesSafely(root: Root?): Boolean {
        // val type = root?.getWindowLayoutParams()?.get()?.type
        val type = root?.windowLayoutParams?.get()?.type
        if (type == WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY) {
            // val windowToken = root?.getDecorView()?.getWindowToken()
            val windowToken = root.decorView.windowToken
            // val appToken = root?.getDecorView()?.getApplicationWindowToken()
            val appToken = root.decorView.applicationWindowToken
            if (windowToken === appToken) {
                //means this window isn't contained by any other windows.
            }
        }
        return false
    }
}