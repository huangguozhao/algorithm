package com.algorithm.leetcode;

/**
 * Dynamic Programming Solution Demo - Longest Palindromic Substring
 * Demonstrates the step-by-step process of DP approach
 */
public class LongestPalindromicSubstringDemo {

    public static void main(String[] args) {
        String s = "abba";
        demonstrateDP(s);
    }

    public static void demonstrateDP(String s) {
        System.out.println("=== Dynamic Programming Solution Demo ===");
        System.out.println("Input string: \"" + s + "\"");
        System.out.println("String length: " + s.length());
        System.out.println();

        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        // Initialize single characters as palindromes
        System.out.println("Step 1: Initialize single characters (length 1 substrings)");
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            System.out.println("dp[" + i + "][" + i + "] = true (char '" + s.charAt(i) + "')");
        }
        printDPTable(s, dp, 1);
        System.out.println();

        int maxLength = 1;
        int start = 0;

        // Process substrings by length
        for (int len = 2; len <= n; len++) {
            System.out.println("Step " + len + ": Processing substrings of length " + len);

            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                System.out.println("  Checking substring s[" + i + ".." + j + "] = \"" + s.substring(i, j + 1) + "\"");

                if (s.charAt(i) == s.charAt(j)) {
                    System.out.println("    s[" + i + "]='" + s.charAt(i) + "' == s[" + j + "]='" + s.charAt(j) + "' ✓");

                    if (len <= 3) {
                        dp[i][j] = true;
                        System.out.println("    Length <= 3, directly set to true: dp[" + i + "][" + j + "] = true");
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                        System.out.println("    Check inner substring dp[" + (i + 1) + "][" + (j - 1) + "] = " + dp[i + 1][j - 1]);
                        System.out.println("    So dp[" + i + "][" + j + "] = " + dp[i][j]);
                    }

                    if (dp[i][j]) {
                        System.out.println("    ✓ This is a palindrome!");
                        if (len > maxLength) {
                            maxLength = len;
                            start = i;
                            System.out.println("    New record! Current max length: " + maxLength + ", start position: " + start);
                        }
                    } else {
                        System.out.println("    ✗ Inner part is not palindrome, so this is not a palindrome");
                    }
                } else {
                    System.out.println("    s[" + i + "]='" + s.charAt(i) + "' != s[" + j + "]='" + s.charAt(j) + "' ✗");
                    dp[i][j] = false;
                    System.out.println("    dp[" + i + "][" + j + "] = false");
                }
                System.out.println();
            }

            printDPTable(s, dp, len);
            System.out.println();
        }

        System.out.println("Final result:");
        System.out.println("Longest palindromic substring: \"" + s.substring(start, start + maxLength) + "\"");
        System.out.println("Length: " + maxLength);
        System.out.println("Start position: " + start);
    }

    private static void printDPTable(String s, boolean[][] dp, int currentLen) {
        System.out.println("Current DP table status (processed lengths <= " + currentLen + "):");
        System.out.print("   ");
        for (int i = 0; i < s.length(); i++) {
            System.out.print(" " + s.charAt(i) + " ");
        }
        System.out.println();

        for (int i = 0; i < dp.length; i++) {
            System.out.print(s.charAt(i) + " ");
            for (int j = 0; j < dp[i].length; j++) {
                if (i > j) {
                    System.out.print("   ");
                } else if (dp[i][j]) {
                    System.out.print(" ✓ ");
                } else {
                    System.out.print(" ✗ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}