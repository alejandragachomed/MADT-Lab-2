package com.example.madt_lab_2

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class MainTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testWordCountButtonClick() {
        // Type text into EditText
        onView(withId(R.id.inputText)).perform(typeText("Hello world"))

        // Click on the button for word count
        onView(withId(R.id.Wanalyzer)).perform(click())

        // Check if the text view displays the expected word count
        onView(withId(R.id.textView)).check(matches(withText("2"))) // Adjust based on your logic
    }

    @Test
    fun emptyInputDisplaysErrorMessage() {
        // Clear any existing text in EditText
        onView(withId(R.id.inputText)).perform(clearText())

        // Check if the EditText displays the error message
        onView(withId(R.id.inputText)).check(matches(hasErrorText("Field cannot be empty")))
    }

}