package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

/**
 * LeetCode 27. 移除元素
 *
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，
 * 并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 解题思路：
 * 使用双指针法，一个指针从左向右遍历，一个指针记录有效元素的位置。
 * 当遇到不等于val的元素时，就将其移动到有效位置。
 */
public class RemoveElement implements AlgorithmTest {

    /**
     * 双指针法：原地移除指定元素
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 思路：
     * 1. 使用两个指针：慢指针i记录有效元素的位置，快指针j遍历数组
     * 2. 当nums[j] != val时，将该元素放到i位置，i前进
     * 3. 当nums[j] == val时，j继续前进，i不动
     * 4. 返回i作为新长度
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 慢指针i：记录有效元素应该存放的位置
        int i = 0;

        // 快指针j：遍历数组
        for (int j = 0; j < nums.length; j++) {
            // 如果当前元素不等于val，将其放到有效位置
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
            // 如果等于val，j继续前进，i不动，相当于跳过这个元素
        }

        return i;
    }

    /**
     * 优化版：当要删除的元素较少时更高效
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 思路：从两端同时遍历，当遇到等于val的元素时，用数组末尾的元素替换
     */
    public int removeElementOptimized(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;
        int n = nums.length;

        while (i < n) {
            if (nums[i] == val) {
                // 用最后一个元素替换当前元素
                nums[i] = nums[n - 1];
                // 数组长度减1
                n--;
            } else {
                // 当前元素不等于val，指针前进
                i++;
            }
        }

        return n;
    }

    public void testInternal() {
        System.out.println("=== LeetCode 27. 移除元素 ===\n");

        testCase(new int[]{3, 2, 2, 3}, 3, "[2, 2]", 2, "示例1：移除值为3的元素");
        testCase(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2, "[0, 1, 3, 0, 4]", 5, "示例2：移除值为2的元素");
        testCase(new int[]{}, 0, "[]", 0, "空数组");
        testCase(new int[]{1}, 1, "[]", 0, "单元素数组，移除该元素");
        testCase(new int[]{1}, 2, "[1]", 1, "单元素数组，不移除");
        testCase(new int[]{1, 1, 1, 1}, 1, "[]", 0, "全是要移除的元素");
        testCase(new int[]{1, 2, 3, 4, 5}, 6, "[1, 2, 3, 4, 5]", 5, "没有要移除的元素");
        testCase(new int[]{4, 5}, 4, "[5]", 1, "移除第一个元素");
        testCase(new int[]{4, 5}, 5, "[4]", 1, "移除最后一个元素");
    }

    private void testCase(int[] input, int val, String expectedArray, int expectedLength, String desc) {
        System.out.println(desc);
        System.out.println("输入数组: " + java.util.Arrays.toString(input));
        System.out.println("要移除的值: " + val);

        // 复制数组用于测试两种方法
        int[] nums1 = input.clone();
        int[] nums2 = input.clone();

        // 测试标准方法
        int length1 = removeElement(nums1, val);
        System.out.println("标准方法结果长度: " + length1);
        System.out.println("修改后数组前" + length1 + "个元素: " +
                          java.util.Arrays.toString(java.util.Arrays.copyOf(nums1, length1)));

        // 测试优化方法
        int length2 = removeElementOptimized(nums2, val);
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
        return "Remove Element";
    }

    @Override
    public String getDifficulty() {
        return "简单";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：移除元素 (Remove Element)");
        System.out.println("LeetCode题号: 27");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：双指针法");
        System.out.println("时间复杂度：O(n)");
        System.out.println("空间复杂度：O(1)");
        System.out.println("关键点：原地修改，O(1)空间，元素顺序可改变");
        System.out.println("适用场景：数组去重，移除特定元素，空间受限的场景");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    public static void main(String[] args) {
        System.out.println("=== 单独测试：移除元素 ===\n");
        RemoveElement alg = new RemoveElement();
        alg.test();
    }
}
