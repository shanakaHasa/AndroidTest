package com.example.myapplication_test_2

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testSubmitButtonWithAnswer() {
        // Enter an answer
        onView(withId(R.id.answerEditText)).perform(typeText("97"), closeSoftKeyboard())

        // Click submit button
        onView(withId(R.id.submitButton)).perform(click())

        // Ensure that question display updates or proceeds to the next question
        // Add appropriate assertions here based on your implementation
    }

    @Test
    fun testResetButton() {
        // Enter an answer
        onView(withId(R.id.answerEditText)).perform(typeText("97"), closeSoftKeyboard())

        // Click reset button
        onView(withId(R.id.resetButton)).perform(click())

        // Ensure that the answer field is cleared and question index is reset to zero
        // Add appropriate assertions here based on your implementation
    }



}

