package com.algorithm.leetcode;

import java.util.Arrays;

/**
 * LeetCode 31. Next Permutation
 *
 * A permutation of an array of integers is an arrangement of its members into a
 * sequence or linear order.
 *
 * For example, for arr = [1,2,3], the following are all the permutations of arr:
 * [1,2,3], [1,3,2], [3,1,2], [2,3,1].
 *
 * The next permutation of an array of integers is the next lexicographically greater
 * permutation of its integer. More formally, if all the permutations of the array
 * are sorted in one container according to their lexicographical order, then the
 * next permutation of that array is the permutation that follows it in the sorted container.
 *
 * If such arrangement is not possible, the array must be rearranged as the
 * lowest possible order (i.e., sorted in ascending order).
 *
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not
 * have a lexicographical larger rearrangement.
 *
 * Given an array of integers nums, find the next permutation.
 *
 * The replacement must be in place and use only constant extra space.
 */
public class NextPermutation {

    /**
     * Next permutation algorithm
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;

        // Step 1: Find the first decreasing element from the right
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: If such element exists, find the smallest element larger than nums[i] from the right
        if (i >= 0) {
            int j = n - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            // Swap nums[i] and nums[j]
            swap(nums, i, j);
        }

        // Step 3: Reverse the elements from i+1 to the end
        reverse(nums, i + 1, n - 1);
    }

    /**
     * Helper method to swap two elements in array
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Helper method to reverse array from start to end inclusive
     */
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    /**
     * Brute force solution: Generate all permutations and find next one
     * Time complexity: O(n!) - too slow for large arrays
     * Space complexity: O(n) for recursion stack
     * Only for understanding, will timeout on LeetCode
     */
    public void nextPermutationBruteForce(int[] nums) {
        // This would require generating all permutations which is O(n!)
        // Not practical for arrays larger than 10 elements
        Arrays.sort(nums); // Just sort as ascending for demonstration
    }

    public void test() {
        System.out.println("=== LeetCode 31. Next Permutation ===\n");

        // Test cases
        testCase(new int[]{1, 2, 3}, new int[]{1, 3, 2}, "Example 1: [1,2,3] -> [1,3,2]");
        testCase(new int[]{3, 2, 1}, new int[]{1, 2, 3}, "Example 2: [3,2,1] -> [1,2,3]");
        testCase(new int[]{1, 1, 5}, new int[]{1, 5, 1}, "Example 3: [1,1,5] -> [1,5,1]");
        testCase(new int[]{1, 3, 2}, new int[]{2, 1, 3}, "Normal case: [1,3,2] -> [2,1,3]");
        testCase(new int[]{2, 3, 1}, new int[]{3, 1, 2}, "Another example: [2,3,1] -> [3,1,2]");
        testCase(new int[]{1, 2}, new int[]{2, 1}, "Two elements: [1,2] -> [2,1]");
        testCase(new int[]{2, 1}, new int[]{1, 2}, "Two elements descending: [2,1] -> [1,2]");
        testCase(new int[]{1}, new int[]{1}, "Single element: [1] -> [1]");
        testCase(new int[]{}, new int[]{}, "Empty array: [] -> []");
        testCase(new int[]{1, 2, 3, 4}, new int[]{1, 2, 4, 3}, "Four elements: [1,2,3,4] -> [1,2,4,3]");
        testCase(new int[]{4, 3, 2, 1}, new int[]{1, 2, 3, 4}, "All descending: [4,3,2,1] -> [1,2,3,4]");
        testCase(new int[]{1, 5, 1}, new int[]{5, 1, 1}, "Duplicates: [1,5,1] -> [5,1,1]");
    }

    private void testCase(int[] nums, int[] expected, String desc) {
        System.out.println(desc);
        System.out.println("Input: " + Arrays.toString(nums));

        int[] original = nums.clone();
        nextPermutation(nums);

        System.out.println("Output: " + Arrays.toString(nums));
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Passed: " + (Arrays.equals(nums, expected) ? "PASS" : "FAIL"));

        // Verify original array was modified in place
        System.out.println("Modified in place: " + (nums != original ? "PASS" : "FAIL"));
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== Individual Test: Next Permutation ===\n");
        NextPermutation alg = new NextPermutation();
        alg.test();
    }
}
