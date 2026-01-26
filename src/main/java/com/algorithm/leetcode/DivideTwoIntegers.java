package com.algorithm.leetcode;

/**
 * LeetCode 29. Divide Two Integers
 *
 * Given two integers dividend and divisor, divide two integers without using multiplication,
 * division, and mod operator.
 *
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * Note: Assume we are dealing with an environment that could only store integers within
 * the 32-bit signed integer range: [-2^31, 2^31 - 1]. In this problem, if the quotient is
 * strictly greater than 2^31 - 1, then return 2^31 - 1, and if the quotient is strictly less
 * than -2^31, then return -2^31.
 */
public class DivideTwoIntegers {

    /**
     * Bit manipulation solution: using bit shifts to implement division
     * Time complexity: O(log n) - n is the dividend
     * Space complexity: O(1)
     */
    public int divide(int dividend, int divisor) {
        // Handle overflow edge case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the result
        boolean negative = (dividend > 0) ^ (divisor > 0);

        // Convert both dividend and divisor to positive for calculation
        long dividendLong = Math.abs((long) dividend);
        long divisorLong = Math.abs((long) divisor);

        long result = 0;

        // Use bit shifts to perform division
        while (dividendLong >= divisorLong) {
            long tempDivisor = divisorLong;
            long multiple = 1;

            // Find the largest tempDivisor * 2^k <= dividendLong
            while (dividendLong >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }

            // Subtract the found multiple
            dividendLong -= tempDivisor;
            result += multiple;
        }

        // Apply the sign
        if (negative) {
            result = -result;
        }

        // Handle overflow cases for 32-bit integers
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int) result;
    }

    /**
     * Brute force solution: repeatedly subtract divisor (will timeout, for understanding only)
     * Time complexity: O(n) - n is the size of quotient
     * Space complexity: O(1)
     */
    public int divideBruteForce(int dividend, int divisor) {
        // Handle overflow edge case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the result
        boolean negative = (dividend > 0) ^ (divisor > 0);

        // Convert both dividend and divisor to positive for calculation
        long dividendLong = Math.abs((long) dividend);
        long divisorLong = Math.abs((long) divisor);

        long result = 0;

        // Repeatedly subtract divisor
        while (dividendLong >= divisorLong) {
            dividendLong -= divisorLong;
            result++;
        }

        // Apply the sign
        if (negative) {
            result = -result;
        }

        // Handle overflow cases for 32-bit integers
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int) result;
    }

    public void test() {
        System.out.println("=== LeetCode 29. Divide Two Integers ===\n");

        // Test cases
        testCase(10, 3, 3, "Normal case: 10 ÷ 3 = 3");
        testCase(7, -3, -2, "Negative divisor: 7 ÷ (-3) = -2");
        testCase(-7, 3, -2, "Negative dividend: -7 ÷ 3 = -2");
        testCase(-7, -3, 2, "Same sign division: -7 ÷ (-3) = 2");
        testCase(1, 1, 1, "Equal numbers: 1 ÷ 1 = 1");
        testCase(0, 1, 0, "Dividend is zero: 0 ÷ 1 = 0");
        testCase(Integer.MAX_VALUE, 1, Integer.MAX_VALUE, "Max value divided by 1");
        testCase(Integer.MIN_VALUE, -1, Integer.MAX_VALUE, "Overflow edge: MIN_VALUE ÷ (-1) = MAX_VALUE");
        testCase(Integer.MIN_VALUE, 1, Integer.MIN_VALUE / 1, "Min value divided by 1");
        testCase(2147483647, 2, 1073741823, "Large number division: 2147483647 ÷ 2 = 1073741823");
    }

    private void testCase(int dividend, int divisor, int expected, String desc) {
        System.out.println(desc);
        System.out.println(" Dividend: " + dividend + ", Divisor: " + divisor);

        int result = divide(dividend, divisor);
        System.out.println(" Result: " + result);
        System.out.println(" Expected: " + expected);
        System.out.println(" Passed: " + (result == expected ? "PASS" : "FAIL"));
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== Individual Test: Divide Two Integers ===\n");
        DivideTwoIntegers alg = new DivideTwoIntegers();
        alg.test();
    }
}
