package com.alex.hichat.Utilities

    import android.support.test.espresso.Espresso.onView
    import android.support.test.espresso.assertion.ViewAssertions.matches
    import android.support.test.espresso.matcher.RootMatchers.withDecorView
    import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
    import android.support.test.espresso.matcher.ViewMatchers.withText
    import android.support.test.rule.ActivityTestRule
    import com.alex.hichat.Controller.CreateUserActivity
    import com.alex.hichat.Controller.LoginActivity
    import com.alex.hichat.Controller.MainActivity
    import com.alex.hichat.R
    import org.hamcrest.CoreMatchers.`is`
    import org.hamcrest.CoreMatchers.not

class ToastPopUps {

    private val mainActivityTestRule = ActivityTestRule(MainActivity::class.java)
    private val loginActivityTestRule = ActivityTestRule(LoginActivity::class.java)
    private val signUpActivityTestRule = ActivityTestRule(CreateUserActivity::class.java)

    private val toastBothFieldsWrong = onView(withText(R.string.toast_fill_both_email_password_fields))
    private val toastSomethingWrong = onView(withText(R.string.toast_something_went_wrong_message))
    private val toastAllThreeFieldsTyped = onView(withText(R.string.toast_signup_user_empty_fields_passed))


    fun assertToastSomethingWrongPopUpped(myActivityTestRule: Any) {
        when (myActivityTestRule) {
            mainActivityTestRule -> toastSomethingWrong
                    .inRoot(withDecorView(not(`is`(mainActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
            loginActivityTestRule -> toastSomethingWrong
                    .inRoot(withDecorView(not(`is`(loginActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
            signUpActivityTestRule -> toastSomethingWrong
                    .inRoot(withDecorView(not(`is`(loginActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
        }
    }

    fun assertToastBothFieldsWrong(myActivityTestRule: Any) {
        when (myActivityTestRule) {
            mainActivityTestRule -> toastBothFieldsWrong
                    .inRoot(withDecorView(not(`is`(mainActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
            loginActivityTestRule -> toastBothFieldsWrong
                    .inRoot(withDecorView(not(`is`(loginActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
            signUpActivityTestRule -> toastBothFieldsWrong
                    .inRoot(withDecorView(not(`is`(loginActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
        }
    }

    fun assertToastSignUpAllFieldsFilledIn(myActivityTestRule: Any) {
        when (myActivityTestRule) {
            mainActivityTestRule -> toastAllThreeFieldsTyped
                    .inRoot(withDecorView(not(`is`(mainActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
            loginActivityTestRule -> toastAllThreeFieldsTyped
                    .inRoot(withDecorView(not(`is`(loginActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
            signUpActivityTestRule -> toastAllThreeFieldsTyped
                    .inRoot(withDecorView(not(`is`(loginActivityTestRule.activity.window.decorView))))
                    .check(matches(isDisplayed()))
        }
    }
}


