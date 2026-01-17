package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ArrayUtils;

/**
 * LeetCode 11. 盛最多水的容器
 *
 * 题目描述：
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 *
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 解法：
 * 使用双指针法，从数组两端开始向中间移动。
 * 面积 = min(height[left], height[right]) * (right - left)
 * 移动较短的那根线，因为移动较长的线不会增大面积。
 */
public class ContainerWithMostWater implements AlgorithmTest {

    /**
     * 解法：双指针法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 思路：
     * 1. 初始化左右指针，指向数组的两端
     * 2. 计算当前面积：min(height[left], height[right]) * (right - left)
     * 3. 更新最大面积
     * 4. 移动较短的那根线，因为移动较长的线不会增大面积
     *    - 如果height[left] < height[right]，移动left指针
     *    - 否则移动right指针
     * 5. 重复直到左右指针相遇
     */
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            // 计算当前面积：面积 = min(高度) * 宽度
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            // 更新最大面积
            maxArea = Math.max(maxArea, currentArea);

            // 移动较短的那根线
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    /**
     * 解法二：暴力解法（仅用于对比学习）
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     *
     * 思路：
     * 枚举所有可能的左右边界组合，计算面积，取最大值
     * 这个方法虽然能得到正确结果，但时间复杂度太高，不推荐使用
     */
    public int maxAreaBruteForce(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int maxArea = 0;
        int n = height.length;

        // 枚举所有可能的左右边界
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // 计算面积
                int area = Math.min(height[i], height[j]) * (j - i);
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 11. 盛最多水的容器 ===\n");

        // 测试用例1：标准示例
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("测试用例1：标准示例");
        System.out.print("高度数组: ");
        ArrayUtils.printArray(height1);
        int result1 = maxArea(height1);
        System.out.println("最大水量: " + result1);
        System.out.println("期望结果: 49");
        System.out.println("测试结果: " + (result1 == 49 ? "✓ 通过" : "✗ 失败"));
        System.out.println();

        // 测试用例2：两个元素
        int[] height2 = {1, 2};
        System.out.println("测试用例2：两个元素");
        System.out.print("高度数组: ");
        ArrayUtils.printArray(height2);
        int result2 = maxArea(height2);
        System.out.println("最大水量: " + result2);
        System.out.println("期望结果: 1");
        System.out.println("测试结果: " + (result2 == 1 ? "✓ 通过" : "✗ 失败"));
        System.out.println();

        // 测试用例3：递增数组
        int[] height3 = {1, 2, 3, 4, 5};
        System.out.println("测试用例3：递增数组");
        System.out.print("高度数组: ");
        ArrayUtils.printArray(height3);
        int result3 = maxArea(height3);
        System.out.println("最大水量: " + result3);
        System.out.println("期望结果: 6");
        System.out.println("测试结果: " + (result3 == 6 ? "✓ 通过" : "✗ 失败"));
        System.out.println();

        // 测试用例4：递减数组
        int[] height4 = {5, 4, 3, 2, 1};
        System.out.println("测试用例4：递减数组");
        System.out.print("高度数组: ");
        ArrayUtils.printArray(height4);
        int result4 = maxArea(height4);
        System.out.println("最大水量: " + result4);
        System.out.println("期望结果: 6");
        System.out.println("测试结果: " + (result4 == 6 ? "✓ 通过" : "✗ 失败"));
        System.out.println();

        // 测试用例5：相同高度
        int[] height5 = {3, 3, 3, 3};
        System.out.println("测试用例5：相同高度");
        System.out.print("高度数组: ");
        ArrayUtils.printArray(height5);
        int result5 = maxArea(height5);
        System.out.println("最大水量: " + result5);
        System.out.println("期望结果: 9");
        System.out.println("测试结果: " + (result5 == 9 ? "✓ 通过" : "✗ 失败"));
        System.out.println();

        // 测试用例6：边界情况 - 空数组
        int[] height6 = {};
        System.out.println("测试用例6：边界情况 - 空数组");
        System.out.print("高度数组: ");
        ArrayUtils.printArray(height6);
        int result6 = maxArea(height6);
        System.out.println("最大水量: " + result6);
        System.out.println("期望结果: 0");
        System.out.println("测试结果: " + (result6 == 0 ? "✓ 通过" : "✗ 失败"));
        System.out.println();

        // 测试用例7：边界情况 - 一个元素
        int[] height7 = {5};
        System.out.println("测试用例7：边界情况 - 一个元素");
        System.out.print("高度数组: ");
        ArrayUtils.printArray(height7);
        int result7 = maxArea(height7);
        System.out.println("最大水量: " + result7);
        System.out.println("期望结果: 0");
        System.out.println("测试结果: " + (result7 == 0 ? "✓ 通过" : "✗ 失败"));
        System.out.println();

        // 性能对比测试
        System.out.println("=== 性能对比测试 ===");
        int[] largeArray = new int[1000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = (int) (Math.random() * 1000) + 1;
        }

        // 测试双指针法
        long startTime = System.nanoTime();
        int resultDP = maxArea(largeArray);
        long endTime = System.nanoTime();
        double timeDP = (endTime - startTime) / 1_000_000.0;

        // 测试暴力法（只测试前100个元素，避免时间太长）
        int[] smallArray = new int[100];
        System.arraycopy(largeArray, 0, smallArray, 0, 100);
        startTime = System.nanoTime();
        int resultBrute = maxAreaBruteForce(smallArray);
        endTime = System.nanoTime();
        double timeBrute = (endTime - startTime) / 1_000_000.0;

        System.out.println("双指针法（1000元素）:");
        System.out.println("  结果: " + resultDP);
        System.out.println("  时间: " + String.format("%.3f", timeDP) + " ms");
        System.out.println("  复杂度: O(n)");
        System.out.println();
        System.out.println("暴力法（100元素）:");
        System.out.println("  结果: " + resultBrute);
        System.out.println("  时间: " + String.format("%.3f", timeBrute) + " ms");
        System.out.println("  复杂度: O(n²)");
        System.out.println("  性能提升: " + String.format("%.1f", timeBrute / timeDP * 10) + "倍");

        // 双指针过程演示
        twoPointersDemo();

        System.out.println("\n=== 测试完成 ===");
    }

    /**
     * 双指针过程详细演示
     */
    private void twoPointersDemo() {
        System.out.println("\n=== 双指针过程详细演示 ===");

        // 使用一个简单的例子演示
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("示例数组: [1, 8, 6, 2, 5, 4, 8, 3, 7]");
        System.out.println("目标：演示双指针如何找到最大水量");
        System.out.println();

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        int step = 1;

        System.out.println("初始状态：");
        System.out.println("  left = " + left + " (height = " + height[left] + ")");
        System.out.println("  right = " + right + " (height = " + height[right] + ")");
        System.out.println();

        while (left < right) {
            // 计算当前面积
            int width = right - left;
            int waterHeight = Math.min(height[left], height[right]);
            int currentArea = waterHeight * width;

            System.out.println("步骤 " + step + ":");
            System.out.println("  位置: left=" + left + "(h=" + height[left] + "), right=" + right + "(h=" + height[right] + ")");
            System.out.println("  宽度: " + width);
            System.out.println("  水位: min(" + height[left] + ", " + height[right] + ") = " + waterHeight);
            System.out.println("  面积: " + waterHeight + " × " + width + " = " + currentArea);

            // 更新最大面积
            if (currentArea > maxArea) {
                maxArea = currentArea;
                System.out.println("  ★ 新最大面积: " + maxArea);
            } else {
                System.out.println("  当前最大面积: " + maxArea);
            }

            // 移动指针
            if (height[left] < height[right]) {
                System.out.println("  移动策略: height[" + left + "](" + height[left] + ") < height[" + right + "](" + height[right] + ")，移动左指针");
                left++;
            } else {
                System.out.println("  移动策略: height[" + left + "](" + height[left] + ") >= height[" + right + "](" + height[right] + ")，移动右指针");
                right--;
            }

            step++;
            System.out.println();
        }

        System.out.println("最终结果：最大水量 = " + maxArea);
        System.out.println();

        // 解释为什么这个策略是正确的
        System.out.println("=== 为什么移动较短的线是正确的？ ===");
        System.out.println("假设我们有两条线 A 和 B，A的高度较小：");
        System.out.println("1. 当前面积 = min(A, B) × 宽度 = A × 宽度");
        System.out.println("2. 如果移动A，无论移到哪里，新高度最多是A（因为A已经是当前的最小高度）");
        System.out.println("3. 但宽度会减小，所以新面积 ≤ A × (宽度-1) < 当前面积");
        System.out.println("4. 如果移动B，可能找到更高的线，从而增大面积");
        System.out.println("5. 因此，总是移动较短的线是最优策略！");
    }

    @Override
    public String getAlgorithmName() {
        return "Container With Most Water";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：盛最多水的容器 (Container With Most Water)");
        System.out.println("LeetCode题号: 11");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：双指针法");
        System.out.println("时间复杂度：O(n)");
        System.out.println("空间复杂度：O(1)");
        System.out.println("核心思路：从两端向中间移动，总是移动较短的线");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    /**
     * 单独测试这个算法
     * 运行命令：java -cp target/classes com.algorithm.leetcode.ContainerWithMostWater
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：盛最多水的容器 ===\n");

        ContainerWithMostWater algorithm = new ContainerWithMostWater();
        algorithm.test();
    }
}
