package com.example.madt_lab_2

import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.AbstractMap
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class AnalyzerTest {
    private fun extractTextFromTextView(textView: TextView): String {
        return textView.text.toString()
    }

    private fun convertToPairList(entries: List<Map.Entry<String, Int>>): List<Pair<String, Int>> {
        return entries.map { it.key to it.value }
    }

    @Test
    fun analyzerTest_wordInput() {
        val analyzer = Analyzer()
        val text = "This is a analyzer test"

        val resultEntries: List<Map.Entry<String, Int>> = analyzer.analyzeWordFrequency(text, 5)
        val resultPairs: List<Pair<String, Int>> = resultEntries.map { it.key to it.value }

        val expectedResult = listOf(
            "this" to 1,
            "is" to 1,
            "a" to 1,
            "analyzer" to 1,
            "test" to 1
        ).sortedWith(compareBy<Pair<String, Int>> { it.first }.thenBy { it.second }) // Sort by word and then by frequency

        val sortedResultPairs = resultPairs.sortedWith(compareBy<Pair<String, Int>> { it.first }.thenBy { it.second }) // Sort by word and then by frequency

        assertEquals(expectedResult, sortedResultPairs)
    }

    @Test
    fun charAnalyzerTest_input() {
        val analyzer = Analyzer()
        val textView = mock(TextView::class.java)

        `when`(textView.text).thenReturn("This is a char analyzer test")

        val resultEntries: List<Map.Entry<Char, Int>> = analyzer.analyzeCharFrequency(extractTextFromTextView(textView), 10)
        val resultPairs: List<Pair<Char, Int>> = resultEntries.map { it.key to it.value }

        val expectedResult = listOf(
            Pair('a', 4),
            Pair('s', 3),
            Pair('t', 3),
            Pair('r', 2),
            Pair('e', 2),
            Pair('h', 2),
            Pair('i', 2),
            Pair('c', 1),
            Pair('y', 1),
            Pair('z', 1)
        )

        assertEquals(expectedResult, resultPairs)
    }
}
