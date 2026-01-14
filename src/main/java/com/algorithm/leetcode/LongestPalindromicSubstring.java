package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

/**
 * LeetCode 5. Longest Palindromic Substring
 *
 * Problem Description:
 * Given a string s, find the longest palindromic substring in s.
 * A palindrome is a string that reads the same backward as forward.
 *
 * Examples:
 * Input: s = "babad"
 * Output: "bab" or "aba"
 * Explanation: "aba" is also a valid answer.
 *
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * Solutions:
 * 1. Brute Force: Check all substrings, time complexity O(n³)
 * 2. Dynamic Programming: Use 2D array to track palindromes, time O(n²), space O(n²)
 * 3. Center Expansion: Expand from each character center, time O(n²), space O(1)
 * 4. Manacher Algorithm: Preprocess string, time O(n), space O(n)
 */
public class LongestPalindromicSubstring implements AlgorithmTest {

    /**
     * Solution 1: Brute Force
     * Time Complexity: O(n³)
     * Space Complexity: O(1)
     *
     * Approach: Enumerate all possible substrings and check if each is a palindrome
     */
    public String longestPalindromeBruteForce(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int maxLength = 1;
        int start = 0;

        // Enumerate all substrings
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                // Check if substring s[i..j] is a palindrome
                if (isPalindrome(s, i, j)) {
                    int length = j - i + 1;
                    if (length > maxLength) {
                        maxLength = length;
                        start = i;
                    }
                }
            }
        }

        return s.substring(start, start + maxLength);
    }

    /**
     * Helper method: Check if string s[left..right] is a palindrome
     */
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Solution 2: Dynamic Programming
     * Time Complexity: O(n²)
     * Space Complexity: O(n²)
     *
     * Approach: Use 2D array dp[i][j] to indicate if s[i..j] is a palindrome
     * State transition: dp[i][j] = (s[i] == s[j]) && (j - i <= 2 || dp[i+1][j-1])
     */
    public String longestPalindromeDP(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        // Initialize single characters as palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int maxLength = 1;
        int start = 0;

        // 枚举子串长度
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    if (len <= 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }

                    if (dp[i][j] && len > maxLength) {
                        maxLength = len;
                        start = i;
                    }
                }
            }
        }

        return s.substring(start, start + maxLength);
    }

    /**
     * Solution 3: Center Expansion Method (Recommended)
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     *
     * Approach: Expand from each character as center to find palindromes
     * Need to consider both odd and even length palindromes
     */
    public String longestPalindromeCenterExpansion(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int start = 0;
        int maxLength = 1;

        for (int i = 0; i < s.length(); i++) {
            // Expand with single character as center (odd length)
            int len1 = expandAroundCenter(s, i, i);
            // Expand with gap between two characters as center (even length)
            int len2 = expandAroundCenter(s, i, i + 1);

            int currentMax = Math.max(len1, len2);
            if (currentMax > maxLength) {
                maxLength = currentMax;
                start = i - (currentMax - 1) / 2;
            }
        }

        return s.substring(start, start + maxLength);
    }

    /**
     * Helper method: Expand from center outward, return palindrome length
     */
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * Solution 4: Manacher Algorithm
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Approach: Preprocess string by inserting '#' between characters,
     * use array to record the longest palindrome radius for each center
     */
    public String longestPalindromeManacher(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        // Preprocess string by inserting special characters
        StringBuilder processed = new StringBuilder("^");
        for (char c : s.toCharArray()) {
            processed.append("#").append(c);
        }
        processed.append("#$");

        int n = processed.length();
        int[] p = new int[n]; // p[i] represents the longest palindrome radius centered at i

        int center = 0; // Current rightmost palindrome boundary center
        int right = 0;  // Current rightmost palindrome boundary

        for (int i = 1; i < n - 1; i++) {
            // Initialize p[i] using symmetry
            if (i < right) {
                p[i] = Math.min(p[2 * center - i], right - i);
            }

            // Expand palindrome
            while (processed.charAt(i + p[i] + 1) == processed.charAt(i - p[i] - 1)) {
                p[i]++;
            }

            // Update center and right boundary
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }

        // Find position with maximum palindrome radius
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        // Calculate starting position in original string
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    /**
     * Test method (internal implementation)
     */
    public void testInternal() {
        System.out.println("=== LeetCode 5. Longest Palindromic Substring ===\n");

        // Test case 1: Standard example
        String s1 = "babad";
        System.out.println("Test case 1:");
        System.out.println("Input string: \"" + s1 + "\"");

        System.out.print("Brute force result: \"");
        String result1 = longestPalindromeBruteForce(s1);
        System.out.println(result1 + "\"");

        System.out.print("Dynamic programming result: \"");
        result1 = longestPalindromeDP(s1);
        System.out.println(result1 + "\"");

        System.out.print("Center expansion result: \"");
        result1 = longestPalindromeCenterExpansion(s1);
        System.out.println(result1 + "\"");

        System.out.print("Manacher algorithm result: \"");
        result1 = longestPalindromeManacher(s1);
        System.out.println(result1 + "\"");

        System.out.println();

        // Test case 2: Even length palindrome
        String s2 = "cbbd";
        System.out.println("Test case 2 (even length):");
        System.out.println("Input string: \"" + s2 + "\"");

        System.out.print("Center expansion result: \"");
        String result2 = longestPalindromeCenterExpansion(s2);
        System.out.println(result2 + "\"");

        System.out.println();

        // Test case 3: Single character
        String s3 = "a";
        System.out.println("Test case 3 (single character):");
        System.out.println("Input string: \"" + s3 + "\"");

        System.out.print("Center expansion result: \"");
        String result3 = longestPalindromeCenterExpansion(s3);
        System.out.println(result3 + "\"");

        System.out.println();

        // Test case 4: Empty string
        String s4 = "";
        System.out.println("Test case 4 (empty string):");
        System.out.println("Input string: \"" + s4 + "\"");

        System.out.print("Center expansion result: \"");
        String result4 = longestPalindromeCenterExpansion(s4);
        System.out.println(result4 + "\"");

        System.out.println();

        // Test case 5: Longer string
        String s5 = "abcbaabcba";
        System.out.println("Test case 5 (longer string):");
        System.out.println("Input string: \"" + s5 + "\"");

        System.out.print("Center expansion result: \"");
        String result5 = longestPalindromeCenterExpansion(s5);
        System.out.println(result5 + "\"");

        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Longest Palindromic Substring";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== Algorithm Information ===");
        System.out.println("Problem: Longest Palindromic Substring");
        System.out.println("LeetCode Problem Number: 5");
        System.out.println("Difficulty: " + getDifficulty());
        System.out.println("Solutions:");
        System.out.println("1. Brute Force - Time O(n³), Space O(1)");
        System.out.println("2. Dynamic Programming - Time O(n²), Space O(n²)");
        System.out.println("3. Center Expansion - Time O(n²), Space O(1)");
        System.out.println("4. Manacher Algorithm - Time O(n), Space O(n)");
        System.out.println("Recommended Solution: Center Expansion");
        System.out.println();
    }

    @Override
    public void test() {
        // Call the existing test method
        printAlgorithmInfo();
        testInternal();
    }

    /**
     * Test this algorithm standalone
     * Run command: java -cp target/classes com.algorithm.leetcode.LongestPalindromicSubstring
     */
    public static void main(String[] args) {
        System.out.println("=== Standalone Test: Longest Palindromic Substring ===\n");

        LongestPalindromicSubstring algorithm = new LongestPalindromicSubstring();
        algorithm.test();
    }
}
