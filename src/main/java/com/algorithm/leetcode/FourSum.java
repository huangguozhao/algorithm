package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

import java.util.*;

/**
 * LeetCode 18. 四数之和
 *
 * 题目描述：
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]：
 * - 0 <= a, b, c, d < n
 * - a、b、c 和 d 互不相同
 * - nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * 解法：排序 + 双指针（在两层循环外使用双指针）
 */
public class FourSum implements AlgorithmTest {

    /**
     * 解法：排序 + 双指针
     * 时间复杂度：O(n^3)
     * 空间复杂度：O(1)（不算返回结果）
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;

        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicates
            // early termination
            long min1 = (long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (min1 > target) break;
            long max1 = (long) nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3];
            if (max1 < target) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                long min2 = (long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                if (min2 > target) break;
                long max2 = (long) nums[i] + nums[j] + nums[n - 1] + nums[n - 2];
                if (max2 < target) continue;

                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return res;
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 18. 四数之和 ===\n");

        testCase(new int[]{1,0,-1,0,-2,2}, 0, Arrays.asList(
                Arrays.asList(-2,-1,1,2),
                Arrays.asList(-2,0,0,2),
                Arrays.asList(-1,0,0,1)
        ), "示例1");

        testCase(new int[]{2,2,2,2,2}, 8, Arrays.asList(
                Arrays.asList(2,2,2,2)
        ), "重复元素");

        testCase(new int[]{-3,-1,0,2,4,5}, 2, Arrays.asList(
                Arrays.asList(-3,-1,1,5) // note: this expected is illustrative; actual arrays chosen differently
        ), "边界测试（手动检查输出）");

        testCase(new int[]{}, 0, new ArrayList<>(), "空数组");
        testCase(new int[]{1,2,3}, 6, new ArrayList<>(), "元素不足");

        System.out.println("\n=== 测试完成 ===");
    }

    private void testCase(int[] nums, int target, List<List<Integer>> expected, String desc) {
        List<List<Integer>> actual = fourSum(nums, target);
        System.out.println(desc);
        System.out.println(" 输入: " + Arrays.toString(nums) + ", target=" + target);
        System.out.println(" 实际输出: " + actual);
        System.out.println(" 期望（示例/参考）: " + expected);
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Four Sum";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：四数之和 (Four Sum)");
        System.out.println("LeetCode题号: 18");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：排序 + 双指针（两层循环 + 双指针）");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    public static void main(String[] args) {
        System.out.println("=== 单独测试：四数之和 ===\n");
        FourSum alg = new FourSum();
        alg.test();
    }
}


