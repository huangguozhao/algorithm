package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

import java.util.Arrays;

/**
 * LeetCode 16. 最接近的三数之和
 *
 * 题目描述：
 * 给你一个长度为 n 的整数数组 nums 和一个目标值 target。
 * 请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在恰好一个解。
 *
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)。
 *
 * 解法：
 * 排序 + 双指针法
 */
public class ThreeSumClosest implements AlgorithmTest {

    /**
     * 解法：排序 + 双指针
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     *
     * 思路：
     * 1. 对数组进行排序
     * 2. 遍历每个元素作为第一个数
     * 3. 在剩余数组中使用双指针找最接近target的两个数
     * 4. 记录最小的差值对应的三数之和
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("数组长度至少为3");
        }

        // 排序数组
        Arrays.sort(nums);

        int closestSum = nums[0] + nums[1] + nums[2]; // 初始化为前三个数的和
        int minDiff = Math.abs(closestSum - target); // 最小差值

        // 遍历每个元素作为第一个数
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                int currentDiff = Math.abs(currentSum - target);

                // 更新最接近的和
                if (currentDiff < minDiff) {
                    minDiff = currentDiff;
                    closestSum = currentSum;
                }

                // 根据当前和与target的关系移动指针
                if (currentSum < target) {
                    left++; // 和太小，左指针右移增大和
                } else if (currentSum > target) {
                    right--; // 和太大，右指针左移减小和
                } else {
                    // 找到完全相等的和，直接返回
                    return currentSum;
                }
            }
        }

        return closestSum;
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 16. 最接近的三数之和 ===\n");

        // 测试用例1：标准示例
        testCase(new int[]{-1, 2, 1, -4}, 1, 2, "标准示例");

        // 测试用例2：完全匹配
        testCase(new int[]{0, 0, 0}, 1, 0, "完全匹配");

        // 测试用例3：负数target
        testCase(new int[]{-3, -2, -5, 3, -4}, -1, -2, "负数target");

        // 测试用例4：正数target
        testCase(new int[]{1, 1, 1, 0}, 3, 3, "正数target");

        // 测试用例5：包含重复元素
        testCase(new int[]{1, 1, 1, 1}, 3, 3, "包含重复元素");

        // 测试用例6：大数组
        testCase(new int[]{-10, -5, 0, 5, 10, 15, 20}, 8, 10, "大数组");

        // 双指针过程演示
        twoPointersDemo();

        System.out.println("\n=== 测试完成 ===");
    }

    /**
     * 双指针过程详细演示
     */
    private void twoPointersDemo() {
        System.out.println("\n=== 排序+双指针过程详细演示 ===");

        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        System.out.println("示例数组: " + Arrays.toString(nums));
        System.out.println("目标值: " + target);
        System.out.println("目标：找到三个数的和最接近" + target);
        System.out.println();

        // 排序
        Arrays.sort(nums);
        System.out.println("排序后: " + Arrays.toString(nums));
        System.out.println();

        int closestSum = nums[0] + nums[1] + nums[2];
        int minDiff = Math.abs(closestSum - target);

        System.out.println("初始状态:");
        System.out.println("  closestSum = " + closestSum + " (前三个数之和)");
        System.out.println("  minDiff = " + minDiff + " (与目标的差值)");
        System.out.println();

        // 模拟过程
        for (int i = 0; i < nums.length - 2; i++) {
            System.out.println("选择第一个数: " + nums[i] + " (索引 " + i + ")");
            System.out.println("需要在右侧找到两个数，使三个数之和最接近 " + target);

            int left = i + 1;
            int right = nums.length - 1;

            System.out.println("双指针初始位置: left=" + left + "(" + nums[left] + "), right=" + right + "(" + nums[right] + ")");

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                int currentDiff = Math.abs(currentSum - target);

                System.out.println("  当前三元组: [" + nums[i] + ", " + nums[left] + ", " + nums[right] + "]");
                System.out.println("  当前和: " + currentSum);
                System.out.println("  与目标差值: " + currentDiff);

                if (currentDiff < minDiff) {
                    minDiff = currentDiff;
                    closestSum = currentSum;
                    System.out.println("  ★ 更新最接近的和: " + closestSum + " (差值: " + minDiff + ")");
                } else {
                    System.out.println("  当前最接近的和: " + closestSum + " (差值: " + minDiff + ")");
                }

                // 根据当前和与target的关系移动指针
                if (currentSum < target) {
                    System.out.println("  和太小(" + currentSum + " < " + target + ")，左指针右移增大和");
                    left++;
                } else if (currentSum > target) {
                    System.out.println("  和太大(" + currentSum + " > " + target + ")，右指针左移减小和");
                    right--;
                } else {
                    System.out.println("  找到完全相等的和(" + currentSum + " == " + target + ")，直接返回");
                    closestSum = currentSum;
                    break;
                }

                System.out.println("  指针位置更新: left=" + left + ", right=" + right);
                System.out.println();
            }

            System.out.println("当前第一个数的搜索完成");
            System.out.println("当前最接近的和: " + closestSum + " (差值: " + minDiff + ")");
            System.out.println();
        }

        System.out.println("最终结果: 最接近的三数之和 = " + closestSum);
        System.out.println("与目标 " + target + " 的差值: " + Math.abs(closestSum - target));
        System.out.println();

        // 解释为什么这个方法正确
        System.out.println("=== 为什么排序+双指针法正确？ ===");
        System.out.println("1. 排序后可以利用双指针在O(n)时间内找到最接近target的两个数");
        System.out.println("2. 对于每个固定第一个数，双指针总能找到最优的两个数");
        System.out.println("3. 通过比较差值，记录全局最优解");
        System.out.println("4. 如果找到完全相等的和，可以提前返回");
        System.out.println("5. 时间复杂度O(n²)，空间复杂度O(1)");
        System.out.println("6. 相比暴力法的O(n³)，性能提升巨大");
    }

    /**
     * 辅助测试方法
     */
    private void testCase(int[] nums, int target, int expected, String description) {
        int actual = threeSumClosest(nums, target);
        System.out.println(description);
        System.out.println("输入数组: " + Arrays.toString(nums));
        System.out.println("目标值: " + target);
        System.out.println("实际输出: " + actual);
        System.out.println("期望输出: " + expected);
        System.out.println("差值: " + Math.abs(actual - target) + " (期望差值: " + Math.abs(expected - target) + ")");
        System.out.println("测试结果: " + (actual == expected ? "✓ 通过" : "✗ 失败"));
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Three Sum Closest";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：最接近的三数之和 (Three Sum Closest)");
        System.out.println("LeetCode题号: 16");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：排序 + 双指针");
        System.out.println("时间复杂度：O(n²)");
        System.out.println("空间复杂度：O(1)");
        System.out.println("核心思路：排序后对每个元素用双指针找最接近target的两个数");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    /**
     * 单独测试这个算法
     * 运行命令：java -cp target/classes com.algorithm.leetcode.ThreeSumClosest
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：最接近的三数之和 ===\n");

        ThreeSumClosest algorithm = new ThreeSumClosest();
        algorithm.test();
    }
}
