package com.alex.hichat.Utilities

    import android.support.test.espresso.Espresso.onView
    import android.support.test.espresso.assertion.ViewAssertions.matches
    import android.support.test.espresso.matcher.RootMatchers.withDecorView
    import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
    import android.support.test.espresso.matcher.ViewMatchers.withText
    import android.support.test.rule.ActivityTestRule
    import com.alex.hichat.Controller.LoginActivity
    import com.alex.hichat.Controller.MainActivity
    import com.alex.hichat.R
    import org.hamcrest.CoreMatchers.`is`
    import org.hamcrest.CoreMatchers.not

class ToastPopUps {

    private val mainActivityTestRule = ActivityTestRule(MainActivity::class.java)
    private val loginActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    private val toastBothFieldsWrong = onView(withText(R.string.toast_fill_both_email_password_fields))
    private val toastSomethingWrong = onView(withText(R.string.toast_something_went_wrong_message))


    fun assertToastSomethingWrongPopUpped(myActivityTestRule: Any) {
        if (myActivityTestRule == mainActivityTestRule) {
            toastSomethingWrong
                    .inRoot(withDecorView(not(`is`(mainActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
        } else if (myActivityTestRule == loginActivityTestRule) {
            toastSomethingWrong
                    .inRoot(withDecorView(not(`is`(loginActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
        }
    }

    fun assertToastBothFieldsWrong(myActivityTestRule: Any) {
        if (myActivityTestRule == mainActivityTestRule) {
            toastBothFieldsWrong
                    .inRoot(withDecorView(not(`is`(mainActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
        } else if (myActivityTestRule == loginActivityTestRule) {
            toastBothFieldsWrong
                    .inRoot(withDecorView(not(`is`(loginActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
        }
    }
}


