package com.algorithm.leetcode;

import java.util.*;

/**
 * LeetCode 30. Substring with Concatenation of All Words
 *
 * Given a string s and an array of strings words, return all starting indices of
 * substring(s) in s that is a concatenation of each word in words exactly once,
 * in any order, and without any intervening characters.
 *
 * You can return the answer in any order.
 *
 * Example:
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation:
 * Substring starting at index 0 is "barfoo". It is the concatenation of ["bar","foo"].
 * Substring starting at index 9 is "foobar". It is the concatenation of ["foo","bar"].
 */
public class SubstringWithConcatenationOfAllWords {

    /**
     * Sliding window solution using hash maps
     * Time complexity: O(n * m) where n is length of s, m is number of words
     * Space complexity: O(m)
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        // All words have the same length
        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        // Count frequency of each word in words array
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Try each possible starting position offset (0 to wordLen-1)
        for (int offset = 0; offset < wordLen; offset++) {
            // For each offset, check all possible starting positions
            for (int start = offset; start <= s.length() - totalLen; start += wordLen) {
                Map<String, Integer> seen = new HashMap<>();
                boolean valid = true;

                // Check each word in the current window
                for (int i = 0; i < wordCount; i++) {
                    int wordStart = start + i * wordLen;
                    String word = s.substring(wordStart, wordStart + wordLen);

                    // Word not in our word list
                    if (!wordFreq.containsKey(word)) {
                        valid = false;
                        break;
                    }

                    seen.put(word, seen.getOrDefault(word, 0) + 1);

                    // Too many occurrences of this word
                    if (seen.get(word) > wordFreq.get(word)) {
                        valid = false;
                        break;
                    }
                }

                // All words match the frequency requirements
                if (valid && seen.equals(wordFreq)) {
                    result.add(start);
                }
            }
        }

        return result;
    }

    /**
     * Alternative solution: Check each possible substring
     * Time complexity: O(n^2 * m) - less efficient but easier to understand
     * Space complexity: O(m)
     */
    public List<Integer> findSubstringBruteForce(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;

        // Count frequency of each word
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Check each possible starting position
        for (int i = 0; i <= s.length() - totalLen; i++) {
            // Create frequency map for current substring
            Map<String, Integer> seen = new HashMap<>();
            boolean valid = true;

            // Check each word in the substring
            for (int j = 0; j < words.length; j++) {
                int start = i + j * wordLen;
                String word = s.substring(start, start + wordLen);

                // Word not in our list
                if (!wordFreq.containsKey(word)) {
                    valid = false;
                    break;
                }

                seen.put(word, seen.getOrDefault(word, 0) + 1);

                // Too many occurrences of this word
                if (seen.get(word) > wordFreq.get(word)) {
                    valid = false;
                    break;
                }
            }

            // All words match
            if (valid && seen.equals(wordFreq)) {
                result.add(i);
            }
        }

        return result;
    }

    public void test() {
        System.out.println("=== LeetCode 30. Substring with Concatenation of All Words ===\n");

        // Test cases
        testCase("barfoothefoobarman", new String[]{"foo", "bar"}, Arrays.asList(0, 9),
                "Example 1: barfoothefoobarman with [foo, bar]");
        testCase("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"},
                Arrays.asList(), "Example 2: wordgoodgoodgoodbestword with [word, good, best, word]");
        testCase("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"},
                Arrays.asList(6, 9, 12), "Example 3: barfoofoobarthefoobarman with [bar, foo, the]");
        testCase("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"},
                Arrays.asList(8), "Good example with [word, good, best, good]");
        testCase("aaaaaaaaaaaaaa", new String[]{"aa", "aa"}, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                "Repeated words: aaaaaaaaaaaaaa with [aa, aa]");
        testCase("foobarfoobar", new String[]{"foo", "bar"}, Arrays.asList(0, 3, 6),
                "Simple case: foobarfoobar with [foo, bar]");
        testCase("", new String[]{"foo", "bar"}, Arrays.asList(),
                "Empty string");
        testCase("abc", new String[]{}, Arrays.asList(),
                "Empty words array");
    }

    private void testCase(String s, String[] words, List<Integer> expected, String desc) {
        System.out.println(desc);
        System.out.println("String: \"" + s + "\"");
        System.out.println("Words: " + Arrays.toString(words));

        List<Integer> result = findSubstring(s, words);
        Collections.sort(result); // Sort for consistent comparison

        System.out.println("Result: " + result);
        System.out.println("Expected: " + expected);
        System.out.println("Passed: " + (result.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== Individual Test: Substring with Concatenation of All Words ===\n");
        SubstringWithConcatenationOfAllWords alg = new SubstringWithConcatenationOfAllWords();
        alg.test();
    }
}
