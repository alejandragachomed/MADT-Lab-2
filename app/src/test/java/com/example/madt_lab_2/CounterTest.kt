package com.example.madt_lab_2

import android.widget.TextView
import org.junit.Test
import org.junit.Assert.assertEquals
import org.mockito.Mockito.*


class CounterTest {


    /*String converter test*/
    @Test
    fun convertTest() {
        val counter = Counter()

        // Mock TextView
        val textView = mock(TextView::class.java)
        `when`(textView.text).thenReturn("Test String")

        val result = counter.convert(textView)

        assertEquals("Test String", result)
    }


    /*word counter test*/

    @Test
    fun wordCounterTest_noInput() {
        val counter = Counter()

        // Mock the TextView
        val textView = mock(TextView::class.java)
        `when`(textView.text).thenReturn("")

        val result = counter.countWords(textView)

        assertEquals(0, result)
    }

    @Test
    fun wordCounterTest_SingleWord (){

        val counter = Counter()

        val textView = TextView (null)

        textView.text="hello"

        val result = counter.countWords(textView)

        assertEquals(1,result)

    }

    @Test
    fun wordCountertest_MoreThanOneWord() {
        val counter = Counter()

        // Mock the TextView
        val textView = mock(TextView::class.java)
        `when`(textView.text).thenReturn("This is a test") // Mock the behavior of textView.text

        val result = counter.countWords(textView)

        assertEquals(4, result)
    }

    /* char counter text*/
    @Test
    fun charCounterTest_noInput() {
        val counter = Counter()

        // Mock the TextView behavior to return an empty string instead of null
        val textView = mock(TextView::class.java)
        `when`(textView.text).thenReturn("")

        val result = counter.countChars(textView)

        assertEquals(0, result)
    }

    @Test
    fun charCounterTest_SingleChar() {
        val counter = Counter()

        // Mock the TextView behavior
        val textView = mock(TextView::class.java)
        `when`(textView.text).thenReturn("h")

        val result = counter.countChars(textView)

        assertEquals(1, result)
    }


    @Test
    fun charCounterTest_MoreThanOneCharCounter() {
        val counter = Counter()

        // Mock the TextView behavior
        val textView = mock(TextView::class.java)
        `when`(textView.text).thenReturn("This is a unit test")

        val result = counter.countWords(textView)

        assertEquals(5, result) // The expected result should be 4 based on the input text
    }


}