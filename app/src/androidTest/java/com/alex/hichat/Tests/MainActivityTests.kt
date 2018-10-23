package com.alex.hichat.Tests

import android.support.test.runner.AndroidJUnit4
import android.support.test.rule.ActivityTestRule
import com.alex.hichat.Controller.MainActivity
import com.alex.hichat.Screens.MainScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTests {

    @get:Rule
    var myActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val mainScreen = MainScreen()

    @Test
    fun mainIsMainActivityScreenLaunchesTest() {
        mainScreen.assertHamburgerBtnDisplayedAndClickable()
    }

    @Test
    fun mainLoginBtnPresentAndClickableTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .assertLoginBtnDisplayedAndClickable()
    }

    @Test
    fun mainChannelBtnPresentTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .assertAddChannelBtnDisplayed()
    }

    @Test
    fun mainDefaultAccountImgPresentTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .assertAccountImgDisplayed()
    }

    @Test
    fun mainDefaultAccountImgPositionTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .assertPositionAccountImgAboveOfLoginBtn()
    }

    @Test
    fun mainChannelBtnPositionTest() {
        mainScreen.clickOnHamburgerBtnMain()
                .assertPositionChannelBtnLeftOfLoginBtn()
    }
}