package com.alex.hichat.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.alex.hichat.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

class SignUpScreen {

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

    fun typeNewName(name: String) : SignUpScreen{
        newUserNameTxt.perform(typeText(name))
        return this
    }

    fun typeNewEmail(email: String) : SignUpScreen {
        newUserEmailTxt.perform(typeText(email))
        return this
    }

    fun typeNewPassword(password: String) : SignUpScreen {
        newUserPasswordTxt.perform(typeText(password))
        return this
    }

    fun generateNewAvatar(numberOfClicks: Int) : SignUpScreen{
        for (i in 1..numberOfClicks) {
            generateAvatarImgView.perform(click())
        }
        return this
    }

    fun generateBackgroundColor(numberOfClicks: Int) : SignUpScreen {
        for (i in 1..numberOfClicks){
            generateBackgroundColorBtn.perform(click())
        }
        return this
    }

    fun clickOnCreateUserBtn() {
        createUserBtn.perform(click())
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
                                allOf(isDisplayed(), not(isClickable())
                                )
                        )
                )
    }

    fun assertAvatarImgPresentAndClickable() {
        generateAvatarImgView
                .check(
                        matches(
                                allOf(isClickable(), isDisplayed()
                                )
                        )
                )
    }

    fun assertBackgroundColorBtnPresentAndClickable() {
        generateBackgroundColorBtn
                .check(
                        matches(
                                allOf(isClickable(), isDisplayed(), withText(R.string.generate_background_color)
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