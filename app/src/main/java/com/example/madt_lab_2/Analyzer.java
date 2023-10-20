
package com.example.madt_lab_2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Analyzer {

    public List<Entry<String, Integer>> analyzeWordFrequency(String text, int topN) {
        String[] words = text.split("\\s+"); // Split text into words (adjust the regex pattern as needed)
        Map<String, Integer> wordCountMap = new HashMap<>();

        for (String word : words) {
            String normalizedWord = word.toLowerCase(); // Normalize to lowercase
            int count = wordCountMap.getOrDefault(normalizedWord, 0);
            wordCountMap.put(normalizedWord, count + 1);
        }

        // Convert the word-frequency map to a list of entries and sort it by frequency in descending order
        List<Entry<String, Integer>> sortedWordFrequency = new ArrayList<>(wordCountMap.entrySet());
        sortedWordFrequency.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Return the top N words and their frequencies
        return sortedWordFrequency.subList(0, Math.min(topN, sortedWordFrequency.size()));
    }

    public List<Entry<Character, Integer>> analyzeCharFrequency(String text, int topN) {
        Map<Character, Integer> charCountMap = new HashMap<>();

        for (char c : text.toCharArray()) {
            if (Character.isLetterOrDigit(c)) { // Optional: You can filter for specific characters if needed
                char normalizedChar = Character.toLowerCase(c); // Normalize to lowercase
                int count = charCountMap.getOrDefault(normalizedChar, 0);
                charCountMap.put(normalizedChar, count + 1);
            }
        }

        // Convert the char-frequency map to a list of entries and sort it by frequency in descending order
        List<Entry<Character, Integer>> sortedCharFrequency = new ArrayList<>(charCountMap.entrySet());
        sortedCharFrequency.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Return the top N characters and their frequencies
        return sortedCharFrequency.subList(0, Math.min(topN, sortedCharFrequency.size()));
    }
}
