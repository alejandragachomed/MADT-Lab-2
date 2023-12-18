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
import org.junit.Assert.assertEquals

class MainTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    fun handleWordAnalyzerClick(input: String): String {
        val analyzer= Analyzer()
        val topN = 10
        val wordFrequency = analyzer.analyzeWordFrequency(input, topN)

        return wordFrequency.joinToString("\n") { entry ->
            "${entry.key}: ${entry.value}"
        }
    }

     fun handleCharAnalyzerClick(input: String): String {
        val analyzer= Analyzer()
        val topN = 10
        val charFrequency = analyzer.analyzeCharFrequency(input, topN)

        return charFrequency.joinToString("\n") { entry ->
            "${entry.key}: ${entry.value}"
        }
    }
    @Test
    fun testWordAnalyzerButtonClick() {
        val inputText = "This is a test sentence"


        val result = handleWordAnalyzerClick(inputText)


        val expectedText = "Sentence: 1\na: 1\ntest: 1\nthis: 1\nis: 1"
        val expectedResultLower = expectedText.lowercase()
        val resultLower = result.lowercase()
        assertEquals(expectedResultLower, resultLower)
    }

    @Test
    fun testCharAnalyzerButtonClick() {
        val inputText = "This is a test sentence for char analysis"


        val result = handleCharAnalyzerClick(inputText)


        val expectedText = "s: 6\na: 4\ne: 4\nt: 4\ni: 3\nn: 3\nc: 2\nh: 2\nr: 2\nf: 1"
        assertEquals(expectedText, result)
    }

    @Test
    fun emptyInputDisplaysErrorMessage() {

        onView(withId(R.id.inputText)).perform(clearText())


        onView(withId(R.id.inputText)).check(matches(hasErrorText("Field cannot be empty")))
    }

}