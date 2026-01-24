package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

/**
 * LeetCode 26. 删除有序数组中的重复项
 *
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，
 * 使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 * 然后返回 nums 中唯一元素的个数。
 *
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 * ● 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。
 *    nums 的其余元素与 nums 的大小不重要。
 * ● 返回 k。
 *
 * 解题思路：
 * 使用双指针法，一个慢指针i记录不重复元素应该存放的位置，一个快指针j遍历数组。
 * 当nums[j] != nums[i]时，说明找到了一个新的不重复元素，将其放到i+1的位置。
 */
public class RemoveDuplicatesFromSortedArray implements AlgorithmTest {

    /**
     * 双指针法：原地移除重复元素
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 思路：
     * 1. 使用两个指针：慢指针i记录不重复元素的位置，快指针j遍历数组
     * 2. 当nums[j] != nums[i]时，说明找到新元素，将其放到i+1位置
     * 3. 返回i+1作为新长度
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 慢指针i：指向当前不重复元素的最后一个位置
        int i = 0;

        // 快指针j：遍历数组
        for (int j = 1; j < nums.length; j++) {
            // 如果找到不重复的元素
            if (nums[j] != nums[i]) {
                i++; // 慢指针前进
                nums[i] = nums[j]; // 将新元素放到正确位置
            }
        }

        // 返回新长度（i+1，因为i是从0开始的索引）
        return i + 1;
    }

    /**
     * 优化版：处理边界情况
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int removeDuplicatesOptimized(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }

    public void testInternal() {
        System.out.println("=== LeetCode 26. 删除有序数组中的重复项 ===\n");

        testCase(new int[]{1, 1, 2}, "[1, 2]", 2, "示例1：两个重复元素");
        testCase(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, "[0, 1, 2, 3, 4]", 5, "示例2：多个重复元素");
        testCase(new int[]{}, "[]", 0, "空数组");
        testCase(new int[]{1}, "[1]", 1, "单元素数组");
        testCase(new int[]{1, 1, 1, 1}, "[1]", 1, "全重复元素");
        testCase(new int[]{1, 2, 3, 4, 5}, "[1, 2, 3, 4, 5]", 5, "无重复元素");
        testCase(new int[]{-1, 0, 0, 0, 0, 3, 3}, "[-1, 0, 3]", 3, "包含负数");
        testCase(new int[]{1, 1, 1, 2, 2, 3}, "[1, 2, 3]", 3, "递增序列");
    }

    private void testCase(int[] input, String expectedArray, int expectedLength, String desc) {
        System.out.println(desc);
        System.out.println("输入数组: " + java.util.Arrays.toString(input));

        // 复制数组用于测试两种方法
        int[] nums1 = input.clone();
        int[] nums2 = input.clone();

        // 测试标准方法
        int length1 = removeDuplicates(nums1);
        System.out.println("标准方法结果长度: " + length1);
        System.out.println("修改后数组前" + length1 + "个元素: " +
                          java.util.Arrays.toString(java.util.Arrays.copyOf(nums1, length1)));

        // 测试优化方法
        int length2 = removeDuplicatesOptimized(nums2);
        System.out.println("优化方法结果长度: " + length2);
        System.out.println("修改后数组前" + length2 + "个元素: " +
                          java.util.Arrays.toString(java.util.Arrays.copyOf(nums2, length2)));

        System.out.println("期望长度: " + expectedLength);
        System.out.println("期望数组: " + expectedArray);

        // 验证结果
        boolean lengthCorrect = (length1 == expectedLength) && (length2 == expectedLength);
        System.out.println("长度验证: " + (lengthCorrect ? "✓ 通过" : "✗ 失败"));
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Remove Duplicates from Sorted Array";
    }

    @Override
    public String getDifficulty() {
        return "简单";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：删除有序数组中的重复项 (Remove Duplicates from Sorted Array)");
        System.out.println("LeetCode题号: 26");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：双指针法（快慢指针）");
        System.out.println("时间复杂度：O(n)");
        System.out.println("空间复杂度：O(1)");
        System.out.println("关键点：原地修改，保持相对顺序，双指针遍历");
        System.out.println("适用场景：有序数组去重，空间受限的场景");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    public static void main(String[] args) {
        System.out.println("=== 单独测试：删除有序数组中的重复项 ===\n");
        RemoveDuplicatesFromSortedArray alg = new RemoveDuplicatesFromSortedArray();
        alg.test();
    }
}
