package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1. 两数之和
 *
 * 题目描述：
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target 的那两个整数，
 * 并返回它们的数组下标。你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 示例：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 解法：
 * 1. 暴力解法：两层循环，时间复杂度 O(n²)
 * 2. 哈希表解法：一次遍历，使用HashMap存储数值和索引，时间复杂度 O(n)
 */
public class TwoSum implements AlgorithmTest {

    /**
     * 解法一：暴力解法
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     *
     * 思路：使用两层循环，遍历所有可能的数对，找到和等于target的两个数
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        // 没有找到符合条件的两个数
        return new int[0];
    }

    /**
     * 解法二：哈希表解法（推荐）
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * 思路：使用HashMap存储已遍历的数值和对应的索引。
     * 在遍历过程中，检查target - 当前数值是否已经在HashMap中。
     */
    public int[] twoSumHashMap(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        // 创建HashMap来存储数值和索引的映射
        Map<Integer, Integer> numToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // 计算需要的配对数值
            int complement = target - nums[i];

            // 检查配对数值是否已经在HashMap中
            if (numToIndex.containsKey(complement)) {
                // 找到了配对，返回两个索引
                return new int[]{numToIndex.get(complement), i};
            }

            // 将当前数值和索引存入HashMap
            numToIndex.put(nums[i], i);
        }

        // 没有找到符合条件的两个数
        return new int[0];
    }

    /**
     * 解法二的优化版本：处理重复数值的情况
     * 当数组中可能有重复数值，但题目要求返回不同的索引时使用
     */
    public int[] twoSumHashMapOptimized(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        Map<Integer, Integer> numToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (numToIndex.containsKey(complement) && numToIndex.get(complement) != i) {
                return new int[]{numToIndex.get(complement), i};
            }

            // 注意：这里会覆盖之前的索引，但由于我们是从左到右遍历，
            // 如果有重复值，我们会使用最右边的索引
            numToIndex.put(nums[i], i);
        }

        return new int[0];
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 1. 两数之和 ===\n");

        // 测试用例1：标准示例
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("测试用例1：");
        System.out.print("输入数组: ");
        ArrayUtils.printArray(nums1);
        System.out.println("目标值: " + target1);

        System.out.print("暴力解法结果: ");
        int[] result1 = twoSumBruteForce(nums1, target1);
        if (result1.length == 2) {
            ArrayUtils.printArray(result1);
            System.out.println("验证: nums[" + result1[0] + "] + nums[" + result1[1] + "] = " +
                             nums1[result1[0]] + " + " + nums1[result1[1]] + " = " + target1);
        } else {
            System.out.println("未找到解");
        }

        System.out.print("哈希表解法结果: ");
        result1 = twoSumHashMap(nums1, target1);
        if (result1.length == 2) {
            ArrayUtils.printArray(result1);
            System.out.println("验证: nums[" + result1[0] + "] + nums[" + result1[1] + "] = " +
                             nums1[result1[0]] + " + " + nums1[result1[1]] + " = " + target1);
        } else {
            System.out.println("未找到解");
        }

        System.out.println();

        // 测试用例2：包含负数
        int[] nums2 = {-1, -2, -3, -4, -5};
        int target2 = -8;
        System.out.println("测试用例2（包含负数）:");
        System.out.print("输入数组: ");
        ArrayUtils.printArray(nums2);
        System.out.println("目标值: " + target2);

        System.out.print("哈希表解法结果: ");
        int[] result2 = twoSumHashMap(nums2, target2);
        if (result2.length == 2) {
            ArrayUtils.printArray(result2);
            System.out.println("验证: nums[" + result2[0] + "] + nums[" + result2[1] + "] = " +
                             nums2[result2[0]] + " + " + nums2[result2[1]] + " = " + target2);
        } else {
            System.out.println("未找到解");
        }

        System.out.println();

        // 测试用例3：无解的情况
        int[] nums3 = {1, 2, 3, 4};
        int target3 = 10;
        System.out.println("测试用例3（无解）:");
        System.out.print("输入数组: ");
        ArrayUtils.printArray(nums3);
        System.out.println("目标值: " + target3);

        System.out.print("哈希表解法结果: ");
        int[] result3 = twoSumHashMap(nums3, target3);
        if (result3.length == 2) {
            ArrayUtils.printArray(result3);
        } else {
            System.out.println("未找到解");
        }

        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Two Sum";
    }

    @Override
    public String getDifficulty() {
        return "简单";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：两数之和 (Two Sum)");
        System.out.println("LeetCode题号: 1");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：");
        System.out.println("1. 暴力解法 - 时间复杂度 O(n²), 空间复杂度 O(1)");
        System.out.println("2. 哈希表解法 - 时间复杂度 O(n), 空间复杂度 O(n)");
        System.out.println("推荐解法：哈希表解法");
        System.out.println();
    }

    @Override
    public void test() {
        // 调用原有的测试方法
        printAlgorithmInfo();
        testInternal();
    }

    /**
     * 单独测试这个算法
     * 运行命令：java -cp target/classes com.algorithm.leetcode.TwoSum
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：两数之和 ===\n");

        TwoSum algorithm = new TwoSum();
        algorithm.test();
    }
}

