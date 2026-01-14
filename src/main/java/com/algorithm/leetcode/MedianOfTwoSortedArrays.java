package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ArrayUtils;

/**
 * LeetCode 4. 寻找两个正序数组的中位数
 *
 * 题目描述：
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出并返回这两个正序数组的中位数。
 * 算法的时间复杂度应该为 O(log (m+n))。
 *
 * 示例：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * 解法：
 * 使用二分查找的方法，在较短的数组上进行二分，找到合适的分割点
 * 使得左半部分的所有元素都小于等于右半部分的所有元素
 */
public class MedianOfTwoSortedArrays implements AlgorithmTest {

    /**
     * 解法：二分查找
     * 时间复杂度：O(log(min(m,n)))
     * 空间复杂度：O(1)
     *
     * 思路：
     * 1. 确保nums1是较短的数组
     * 2. 在nums1上进行二分查找，找到分割点
     * 3. 计算左右两部分的边界值
     * 4. 根据总元素个数的奇偶性返回中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 处理边界情况：两个数组都为空
        if (nums1.length == 0 && nums2.length == 0) {
            throw new IllegalArgumentException("两个数组都为空，无法计算中位数");
        }

        // 确保nums1是较短的数组
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2; // 左半部分应该有的元素个数

        int left = 0;
        int right = m;

        while (left < right) {
            int i = left + (right - left + 1) / 2; // nums1分割点
            int j = totalLeft - i; // nums2分割点

            if (nums1[i - 1] > nums2[j]) {
                // nums1的左半部分最大值 > nums2的右半部分最小值
                // 说明分割点太靠右，需要左移
                right = i - 1;
            } else {
                // 分割点合适或太靠左，继续搜索右半部分
                left = i;
            }
        }

        int i = left;
        int j = totalLeft - i;

        // 获取左半部分的最大值
        int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
        int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
        int leftMax = Math.max(nums1LeftMax, nums2LeftMax);

        // 获取右半部分的最小值
        int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
        int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];
        int rightMin = Math.min(nums1RightMin, nums2RightMin);

        // 根据总元素个数的奇偶性返回结果
        if ((m + n) % 2 == 1) {
            return leftMax;
        } else {
            return (leftMax + rightMin) / 2.0;
        }
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 4. 寻找两个正序数组的中位数 ===\n");

        // 测试用例1：基本示例
        int[] nums1_1 = {1, 3};
        int[] nums2_1 = {2};
        System.out.println("测试用例1：");
        System.out.print("数组1: ");
        ArrayUtils.printArray(nums1_1);
        System.out.print("数组2: ");
        ArrayUtils.printArray(nums2_1);
        double result1 = findMedianSortedArrays(nums1_1, nums2_1);
        System.out.println("实际输出: " + String.format("%.5f", result1));
        System.out.println("期望输出: 2.00000");
        System.out.println("结果: " + (Math.abs(result1 - 2.00000) < 1e-5 ? "✓ 正确" : "✗ 错误"));

        // 测试用例2：两个数组长度相等
        int[] nums1_2 = {1, 2};
        int[] nums2_2 = {3, 4};
        System.out.println("\n测试用例2：");
        System.out.print("数组1: ");
        ArrayUtils.printArray(nums1_2);
        System.out.print("数组2: ");
        ArrayUtils.printArray(nums2_2);
        double result2 = findMedianSortedArrays(nums1_2, nums2_2);
        System.out.println("实际输出: " + String.format("%.5f", result2));
        System.out.println("期望输出: 2.50000");
        System.out.println("结果: " + (Math.abs(result2 - 2.50000) < 1e-5 ? "✓ 正确" : "✗ 错误"));

        // 测试用例3：一个数组为空
        int[] nums1_3 = {};
        int[] nums2_3 = {1};
        System.out.println("\n测试用例3（一个数组为空）：");
        System.out.print("数组1: ");
        ArrayUtils.printArray(nums1_3);
        System.out.print("数组2: ");
        ArrayUtils.printArray(nums2_3);
        double result3 = findMedianSortedArrays(nums1_3, nums2_3);
        System.out.println("实际输出: " + String.format("%.5f", result3));
        System.out.println("期望输出: 1.00000");
        System.out.println("结果: " + (Math.abs(result3 - 1.00000) < 1e-5 ? "✓ 正确" : "✗ 错误"));

        // 测试用例4：奇数个元素
        int[] nums1_4 = {1, 3, 5};
        int[] nums2_4 = {2, 4, 6, 8};
        System.out.println("\n测试用例4（奇数个元素）：");
        System.out.print("数组1: ");
        ArrayUtils.printArray(nums1_4);
        System.out.print("数组2: ");
        ArrayUtils.printArray(nums2_4);
        double result4 = findMedianSortedArrays(nums1_4, nums2_4);
        System.out.println("实际输出: " + String.format("%.5f", result4));
        System.out.println("期望输出: 4.00000");
        System.out.println("结果: " + (Math.abs(result4 - 4.00000) < 1e-5 ? "✓ 正确" : "✗ 错误"));

        // 测试用例5：包含重复元素
        int[] nums1_5 = {1, 2, 2};
        int[] nums2_5 = {2, 3, 4};
        System.out.println("\n测试用例5（包含重复元素）：");
        System.out.print("数组1: ");
        ArrayUtils.printArray(nums1_5);
        System.out.print("数组2: ");
        ArrayUtils.printArray(nums2_5);
        double result5 = findMedianSortedArrays(nums1_5, nums2_5);
        System.out.println("实际输出: " + String.format("%.5f", result5));
        System.out.println("期望输出: 2.00000");
        System.out.println("结果: " + (Math.abs(result5 - 2.00000) < 1e-5 ? "✓ 正确" : "✗ 错误"));

        // 测试用例6：两个数组都为空（边界情况）
        int[] nums1_6 = {};
        int[] nums2_6 = {};
        System.out.println("\n测试用例6（两个数组都为空）：");
        System.out.print("数组1: ");
        ArrayUtils.printArray(nums1_6);
        System.out.print("数组2: ");
        ArrayUtils.printArray(nums2_6);
        try {
            double result6 = findMedianSortedArrays(nums1_6, nums2_6);
            System.out.println("实际输出: " + String.format("%.5f", result6));
            System.out.println("期望输出: 未定义（或抛出异常）");
        } catch (Exception e) {
            System.out.println("抛出异常: " + e.getClass().getSimpleName());
            System.out.println("结果: ✓ 正确（空数组情况应抛出异常）");
        }

        // 测试用例7：大数组测试
        int[] nums1_7 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] nums2_7 = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        System.out.println("\n测试用例7（大数组）：");
        System.out.print("数组1: ");
        ArrayUtils.printArray(nums1_7);
        System.out.print("数组2: ");
        ArrayUtils.printArray(nums2_7);
        double result7 = findMedianSortedArrays(nums1_7, nums2_7);
        System.out.println("实际输出: " + String.format("%.5f", result7));
        System.out.println("期望输出: 10.50000");
        System.out.println("结果: " + (Math.abs(result7 - 10.50000) < 1e-5 ? "✓ 正确" : "✗ 错误"));

        System.out.println("\n=== 测试完成 ===");

        // 测试第二种解法：寻找第K小的数
        System.out.println("\n=== 解法二：寻找第K小的数 ===");
        testKthMethod();
    }

    /**
     * 解法二：寻找第K小的数的方法
     * 时间复杂度：O(log(min(m,n)))
     * 空间复杂度：O(log(min(m,n))) - 递归栈空间
     *
     * 思路：
     * 将中位数问题转化为寻找第K小的数
     * 对于奇数长度：找第 (m+n+1)/2 小的数
     * 对于偶数长度：找第 (m+n)/2 和第 (m+n)/2+1 小的数，取平均
     */
    public double findMedianSortedArraysKth(int[] nums1, int[] nums2) {
        // 处理边界情况
        if (nums1.length == 0 && nums2.length == 0) {
            throw new IllegalArgumentException("两个数组都为空，无法计算中位数");
        }

        int totalLength = nums1.length + nums2.length;

        if (totalLength % 2 == 1) {
            // 奇数个元素，返回第 (totalLength/2 + 1) 小的数
            return findKth(nums1, 0, nums2, 0, totalLength / 2 + 1);
        } else {
            // 偶数个元素，返回第 (totalLength/2) 和第 (totalLength/2 + 1) 小的数的平均值
            double num1 = findKth(nums1, 0, nums2, 0, totalLength / 2);
            double num2 = findKth(nums1, 0, nums2, 0, totalLength / 2 + 1);
            return (num1 + num2) / 2.0;
        }
    }

    /**
     * 在两个有序数组中寻找第K小的数
     * @param nums1 第一个数组
     * @param start1 nums1的起始索引
     * @param nums2 第二个数组
     * @param start2 nums2的起始索引
     * @param k 要寻找第K小的数
     * @return 第K小的数
     */
    private double findKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        // 确保nums1是较短的数组部分
        if (start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }
        if (start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        // 分别在两个数组中找第k/2小的数
        int mid1 = start1 + k / 2 - 1;
        int mid2 = start2 + k / 2 - 1;

        // 处理边界情况
        int val1 = (mid1 >= nums1.length) ? Integer.MAX_VALUE : nums1[mid1];
        int val2 = (mid2 >= nums2.length) ? Integer.MAX_VALUE : nums2[mid2];

        if (val1 < val2) {
            // 排除nums1中前k/2个元素，递归查找第k-k/2小的数
            return findKth(nums1, start1 + k / 2, nums2, start2, k - k / 2);
        } else {
            // 排除nums2中前k/2个元素，递归查找第k-k/2小的数
            return findKth(nums1, start1, nums2, start2 + k / 2, k - k / 2);
        }
    }

    /**
     * 测试寻找第K小的数的方法
     */
    public void testKthMethod() {
        System.out.println("使用寻找第K小的数的方法测试：");

        // 测试用例1：基本示例
        int[] nums1_1 = {1, 3};
        int[] nums2_1 = {2};
        System.out.println("测试用例1：");
        System.out.print("数组1: ");
        ArrayUtils.printArray(nums1_1);
        System.out.print("数组2: ");
        ArrayUtils.printArray(nums2_1);
        double result1 = findMedianSortedArraysKth(nums1_1, nums2_1);
        System.out.println("实际输出: " + String.format("%.5f", result1));
        System.out.println("期望输出: 2.00000");
        System.out.println("结果: " + (Math.abs(result1 - 2.00000) < 1e-5 ? "✓ 正确" : "✗ 错误"));

        // 测试用例2：两个数组长度相等
        int[] nums1_2 = {1, 2};
        int[] nums2_2 = {3, 4};
        System.out.println("\n测试用例2：");
        System.out.print("数组1: ");
        ArrayUtils.printArray(nums1_2);
        System.out.print("数组2: ");
        ArrayUtils.printArray(nums2_2);
        double result2 = findMedianSortedArraysKth(nums1_2, nums2_2);
        System.out.println("实际输出: " + String.format("%.5f", result2));
        System.out.println("期望输出: 2.50000");
        System.out.println("结果: " + (Math.abs(result2 - 2.50000) < 1e-5 ? "✓ 正确" : "✗ 错误"));

        // 测试用例3：奇数个元素
        int[] nums1_3 = {1, 3, 5};
        int[] nums2_3 = {2, 4, 6, 8};
        System.out.println("\n测试用例3：");
        System.out.print("数组1: ");
        ArrayUtils.printArray(nums1_3);
        System.out.print("数组2: ");
        ArrayUtils.printArray(nums2_3);
        double result3 = findMedianSortedArraysKth(nums1_3, nums2_3);
        System.out.println("实际输出: " + String.format("%.5f", result3));
        System.out.println("期望输出: 4.00000");
        System.out.println("结果: " + (Math.abs(result3 - 4.00000) < 1e-5 ? "✓ 正确" : "✗ 错误"));
    }

    @Override
    public String getAlgorithmName() {
        return "Median of Two Sorted Arrays";
    }

    @Override
    public String getDifficulty() {
        return "困难";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：寻找两个正序数组的中位数 (Median of Two Sorted Arrays)");
        System.out.println("LeetCode题号: 4");
        System.out.println("难度：" + getDifficulty());
        System.out.println("时间复杂度要求：O(log(m+n))");
        System.out.println("解法：二分查找");
        System.out.println("核心思路：在较短数组上二分查找合适的分割点");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
        System.out.println("\n" + "=".repeat(50));
    }

    /**
     * 单独测试这个算法
     * 运行命令：java -cp target/classes com.algorithm.leetcode.MedianOfTwoSortedArrays
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：寻找两个正序数组的中位数 ===\n");

        MedianOfTwoSortedArrays algorithm = new MedianOfTwoSortedArrays();
        algorithm.test();
    }
}
