package com.yan.weatherapp

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class WeatherAcceptanceTest {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<WeatherActivity> =
            ActivityTestRule(
                WeatherActivity::class.java, true,
                false)

    @Before
    fun setup() {
        //launch activity using empty intent (no arguments needed for now ...)
        mActivityTestRule.launchActivity(Intent())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun reservationJourney_Test() {
        // make sure text is visible on the screen
        onView(withId(R.id.timeZoneTextView)).check(matches(isDisplayed()))

    }

}