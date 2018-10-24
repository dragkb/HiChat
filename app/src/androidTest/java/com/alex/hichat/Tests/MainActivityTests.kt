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

    @Test
    fun mainIsMainActivityScreenLaunchesTest() {
        val mainScreen = MainScreen()
        mainScreen.assertHamburgerBtnDisplayedAndClickable()
    }

    @Test
    fun mainLoginBtnPresentAndClickableTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        mainScreen.assertLoginBtnDisplayedAndClickable()
    }

    @Test
    fun mainChannelBtnPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        mainScreen.assertAddChannelBtnDisplayed()
    }

    @Test
    fun mainDefaultAccountImgPresentTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        mainScreen.assertAccountImgDisplayed()
    }

    @Test
    fun mainDefaultAccountImgPositionTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        mainScreen.assertPositionAccountImgAboveOfLoginBtn()
    }

    @Test
    fun mainChannelBtnPositionTest() {
        val mainScreen = MainScreen()
        mainScreen.clickOnHamburgerBtnMain()
        mainScreen.assertPositionChannelBtnLeftOfLoginBtn()
    }
}