package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ArrayUtils;

import java.util.*;

/**
 * LeetCode 15. 三数之和
 *
 * 题目描述：
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足
 * i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0。
 * 请你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 解法：
 * 排序 + 双指针法
 */
public class ThreeSum implements AlgorithmTest {

    /**
     * 解法：排序 + 双指针
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1) - 不考虑返回结果的空间
     *
     * 思路：
     * 1. 数组排序
     * 2. 遍历数组，对于每个元素nums[i]，在i+1到末尾之间找两个数使其和为 -nums[i]
     * 3. 使用双指针：left指向i+1，right指向末尾
     * 4. 如果三数和为0，记录结果，并跳过重复元素
     * 5. 如果三数和小于0，left右移；如果大于0，right左移
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // 边界检查
        if (nums == null || nums.length < 3) {
            return result;
        }

        // 排序数组
        Arrays.sort(nums);

        // 遍历每个元素作为第一个数
        for (int i = 0; i < nums.length - 2; i++) {
            // 跳过重复的第一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 如果当前数大于0，由于数组已排序，后面的数都大于0，和不可能为0
            if (nums[i] > 0) {
                break;
            }

            // 在i+1到末尾之间找两个数使其和为 -nums[i]
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    // 找到一组解
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳过重复的第二个数
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳过重复的第三个数
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // 继续寻找下一组解
                    left++;
                    right--;
                } else if (sum < target) {
                    // 和太小，左指针右移
                    left++;
                } else {
                    // 和太大，右指针左移
                    right--;
                }
            }
        }

        return result;
    }

    /**
     * 解法二：暴力枚举（仅用于学习对比）
     * 时间复杂度：O(n³)
     * 空间复杂度：O(1)
     *
     * 思路：
     * 三层循环枚举所有可能的三元组，检查和是否为0
     * 这个方法会超时，仅用于展示为什么需要更高效的算法
     */
    public List<List<Integer>> threeSumBruteForce(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> uniqueResult = new HashSet<>();

        if (nums == null || nums.length < 3) {
            return result;
        }

        // 三层循环枚举所有可能的三元组
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        // 找到一组解，排序后加入集合去重
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(triplet);
                        uniqueResult.add(triplet);
                    }
                }
            }
        }

        result.addAll(uniqueResult);
        return result;
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 15. 三数之和 ===\n");

        // 测试用例1：标准示例
        testCase(new int[]{-1, 0, 1, 2, -1, -4}, Arrays.asList(
                Arrays.asList(-1, -1, 2),
                Arrays.asList(-1, 0, 1)
        ), "标准示例");

        // 测试用例2：无解
        testCase(new int[]{0, 1, 1}, new ArrayList<>(), "无解情况");

        // 测试用例3：全零
        testCase(new int[]{0, 0, 0}, Arrays.asList(
                Arrays.asList(0, 0, 0)
        ), "全零数组");

        // 测试用例4：包含重复元素
        testCase(new int[]{-2, 0, 0, 2, 2}, Arrays.asList(
                Arrays.asList(-2, 0, 2)
        ), "包含重复元素");

        // 测试用例5：边界情况 - 少于3个元素
        testCase(new int[]{1, 2}, new ArrayList<>(), "少于3个元素");

        // 测试用例6：空数组
        testCase(new int[]{}, new ArrayList<>(), "空数组");

        // 测试用例7：多个解
        testCase(new int[]{-4, -2, -1, 0, 1, 2, 3}, Arrays.asList(
                Arrays.asList(-4, 1, 3),
                Arrays.asList(-2, -1, 3),
                Arrays.asList(-2, 0, 2),
                Arrays.asList(-1, 0, 1)
        ), "多个解");

        // 测试用例8：所有负数
        testCase(new int[]{-5, -4, -3, -2, -1}, new ArrayList<>(), "所有负数");

        // 测试用例9：所有正数
        testCase(new int[]{1, 2, 3, 4, 5}, new ArrayList<>(), "所有正数");

        // 性能对比测试
        System.out.println("\n=== 性能对比测试 ===");
        int[] largeArray = new int[100];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = (int) (Math.random() * 20) - 10; // -10到10之间的随机数
        }

        // 测试排序+双指针法
        long startTime = System.nanoTime();
        List<List<Integer>> result1 = threeSum(largeArray);
        long endTime = System.nanoTime();
        double time1 = (endTime - startTime) / 1_000_000.0;

        // 测试暴力法（只测试前20个元素）
        int[] smallArray = new int[20];
        System.arraycopy(largeArray, 0, smallArray, 0, 20);
        startTime = System.nanoTime();
        List<List<Integer>> result2 = threeSumBruteForce(smallArray);
        endTime = System.nanoTime();
        double time2 = (endTime - startTime) / 1_000_000.0;

        System.out.println("排序+双指针法（100元素）:");
        System.out.println("  解的数量: " + result1.size());
        System.out.println("  时间: " + String.format("%.3f", time1) + " ms");
        System.out.println("  复杂度: O(n²)");
        System.out.println();
        System.out.println("暴力法（20元素）:");
        System.out.println("  解的数量: " + result2.size());
        System.out.println("  时间: " + String.format("%.3f", time2) + " ms");
        System.out.println("  复杂度: O(n³)");
        System.out.println("  性能提升: " + String.format("%.1f", time2 / time1 * 5) + "倍");

        // 双指针过程演示
        twoPointersDemo();

        System.out.println("\n=== 测试完成 ===");
    }

    /**
     * 双指针过程详细演示
     */
    private void twoPointersDemo() {
        System.out.println("\n=== 排序+双指针过程详细演示 ===");

        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println("示例数组: " + Arrays.toString(nums));
        System.out.println("目标：找到所有和为0的三元组");
        System.out.println();

        // 排序
        Arrays.sort(nums);
        System.out.println("排序后: " + Arrays.toString(nums));
        System.out.println();

        List<List<Integer>> result = new ArrayList<>();

        // 模拟过程
        for (int i = 0; i < nums.length - 2; i++) {
            // 跳过重复的第一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 如果当前数大于0，停止
            if (nums[i] > 0) {
                System.out.println("当前数 " + nums[i] + " > 0，后面的数都大于0，和不可能为0，停止搜索");
                break;
            }

            System.out.println("选择第一个数: " + nums[i] + " (索引 " + i + ")");
            System.out.println("目标和: " + (-nums[i]) + " (需要在右侧找到两个数相加等于此值)");

            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            System.out.println("双指针初始位置: left=" + left + "(" + nums[left] + "), right=" + right + "(" + nums[right] + ")");

            while (left < right) {
                int sum = nums[left] + nums[right];
                System.out.println("  检查: " + nums[left] + " + " + nums[right] + " = " + sum);

                if (sum == target) {
                    System.out.println("  ✓ 找到解: [" + nums[i] + ", " + nums[left] + ", " + nums[right] + "]");

                    List<Integer> triplet = Arrays.asList(nums[i], nums[left], nums[right]);
                    result.add(triplet);

                    // 跳过重复元素
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                        System.out.println("  跳过重复的第二个数，left移动到: " + left);
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                        System.out.println("  跳过重复的第三个数，right移动到: " + right);
                    }

                    left++;
                    right--;
                    System.out.println("  双指针移动: left=" + left + ", right=" + right);
                } else if (sum < target) {
                    System.out.println("  和太小，left右移");
                    left++;
                } else {
                    System.out.println("  和太大，right左移");
                    right--;
                }
                System.out.println();
            }

            System.out.println("当前第一个数的搜索完成");
            System.out.println();
        }

        System.out.println("最终结果: " + result);
        System.out.println("共找到 " + result.size() + " 组解");
        System.out.println();

        // 解释为什么这个方法正确
        System.out.println("=== 为什么排序+双指针法正确？ ===");
        System.out.println("1. 排序后可以利用双指针在O(n)时间内找到和为目标值的两个数");
        System.out.println("2. 跳过重复元素确保结果不重复");
        System.out.println("3. 如果第一个数大于0，后面的数都大于0，和不可能为0，可以提前停止");
        System.out.println("4. 时间复杂度O(n²)，空间复杂度O(1)");
        System.out.println("5. 相比暴力法的O(n³)，性能提升巨大");
    }

    /**
     * 辅助测试方法
     */
    private void testCase(int[] nums, List<List<Integer>> expected, String description) {
        List<List<Integer>> actual = threeSum(nums);

        System.out.println(description);
        System.out.println("输入数组: " + Arrays.toString(nums));

        // 对结果进行排序，以便比较
        sortResult(actual);
        sortResult(expected);

        System.out.println("实际输出: " + actual);
        System.out.println("期望输出: " + expected);
        System.out.println("测试结果: " + (resultsEqual(actual, expected) ? "✓ 通过" : "✗ 失败"));
        System.out.println();
    }

    /**
     * 对结果进行排序（用于比较）
     */
    private void sortResult(List<List<Integer>> result) {
        for (List<Integer> triplet : result) {
            Collections.sort(triplet);
        }
        // 按第一个元素排序
        result.sort((a, b) -> {
            for (int i = 0; i < 3; i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i) - b.get(i);
                }
            }
            return 0;
        });
    }

    /**
     * 比较两个结果是否相等
     */
    private boolean resultsEqual(List<List<Integer>> result1, List<List<Integer>> result2) {
        if (result1.size() != result2.size()) {
            return false;
        }

        for (int i = 0; i < result1.size(); i++) {
            List<Integer> list1 = result1.get(i);
            List<Integer> list2 = result2.get(i);

            if (list1.size() != list2.size()) {
                return false;
            }

            for (int j = 0; j < list1.size(); j++) {
                if (!list1.get(j).equals(list2.get(j))) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String getAlgorithmName() {
        return "Three Sum";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：三数之和 (Three Sum)");
        System.out.println("LeetCode题号: 15");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：排序 + 双指针");
        System.out.println("时间复杂度：O(n²)");
        System.out.println("空间复杂度：O(1)");
        System.out.println("核心思路：排序后用双指针在每个元素右侧找和为负值的两个数");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    /**
     * 单独测试这个算法
     * 运行命令：java -cp target/classes com.algorithm.leetcode.ThreeSum
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：三数之和 ===\n");

        ThreeSum algorithm = new ThreeSum();
        algorithm.test();
    }
}
