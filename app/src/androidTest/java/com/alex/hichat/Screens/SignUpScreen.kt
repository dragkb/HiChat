package com.alex.hichat.Screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withHint
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import com.alex.hichat.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

enum class CreateUserValidation {
    VALID_USER,
    INVALID_USER
}

class SignUpScreen : BaseScreen() {

    private val newUserNameTxt: ViewInteraction
        get() = onView(withId(R.id.createUserNameTxt))

    private val newUserEmailTxt: ViewInteraction
        get() = onView(withId(R.id.createEmailTxt))

    private val newUserPasswordTxt: ViewInteraction
        get() = onView(withId(R.id.createPasswordTxt))

    private val tapToGenerateTxtView: ViewInteraction
        get() = onView(withText("Tap to generate user avatar"))

    private val generateAvatarImgView: ViewInteraction
        get() = onView(withId(R.id.createAvatarImgView))

    private val generateBackgroundColorBtn: ViewInteraction
        get() = onView(withId(R.id.backgroundColorBtn))

    private val createUserBtn: ViewInteraction
        get() = onView(withId(R.id.createUserBtn))

    override val uniqueView: ViewInteraction
        get() = createUserBtn

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun typeNewName(name: String) {
        newUserNameTxt.perform(typeText(name))
    }

    fun typeNewEmail(email: String) {
        newUserEmailTxt.perform(typeText(email))
    }

    fun typeNewPassword(password: String) {
        newUserPasswordTxt.perform(typeText(password))
        Espresso.closeSoftKeyboard()
    }

    fun generateNewAvatar(numberOfClicks: Int) {
        for (i in 1..numberOfClicks) {
            generateAvatarImgView.perform(click())
        }
    }

    fun generateBackgroundColor(numberOfClicks: Int) {
        for (i in 1..numberOfClicks) {
            generateBackgroundColorBtn.perform(click())
        }
    }

    fun clickOnCreateUserBtn(user: CreateUserValidation): Any {
        createUserBtn.perform(click())
        when (user) {
            CreateUserValidation.VALID_USER -> return LoggedInScreen()
            CreateUserValidation.INVALID_USER -> return SignUpScreen()
        }
    }

    fun assertUserNameTxtHintPresent() {
        newUserNameTxt
            .check(
                matches(
                    allOf(
                        withHint("user name"), isDisplayed()
                    )
                )
            )
    }

    fun assertUserEmailTxtHintPresent() {
        newUserEmailTxt
            .check(
                matches(
                    allOf(
                        withHint("email"), isDisplayed()
                    )
                )
            )
    }

    fun assertUserPasswordTxtHintPresent() {
        newUserPasswordTxt
            .check(
                matches(
                    allOf(
                        withHint("password"), isDisplayed()
                    )
                )
            )
    }

    fun assertTapToGenerateTxtViewPresent() {
        tapToGenerateTxtView
            .check(
                matches(
                    allOf(
                        isDisplayed(), not(isClickable())
                    )
                )
            )
    }

    fun assertAvatarImgPresentAndClickable() {
        generateAvatarImgView
            .check(
                matches(
                    allOf(
                        isClickable(), isDisplayed()
                    )
                )
            )
    }

    fun assertBackgroundColorBtnPresentAndClickable() {
        generateBackgroundColorBtn
            .check(
                matches(
                    allOf(
                        isClickable(), isDisplayed(), withText(R.string.generate_background_color)
                    )
                )
            )
    }

    fun assertCreateUserBtnPresentAndClickable() {
        createUserBtn
            .check(
                matches(
                    allOf(
                        isClickable(), isDisplayed(), withText(R.string.create_user)
                    )
                )
            )
    }
}