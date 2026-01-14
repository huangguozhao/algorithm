package com.algorithm.sorting;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ArrayUtils;

/**
 * 冒泡排序算法
 * 时间复杂度: O(n²)
 * 空间复杂度: O(1)
 */
public class BubbleSort implements AlgorithmTest {

    private int[] array;

    public BubbleSort(int[] array) {
        this.array = ArrayUtils.copyArray(array);
    }

    @Override
    public String getAlgorithmName() {
        return "Bubble Sort";
    }

    @Override
    public String getDifficulty() {
        return "基础";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("算法名称: " + getAlgorithmName());
        System.out.println("难度: " + getDifficulty());
        System.out.println("时间复杂度: O(n²)");
        System.out.println("空间复杂度: O(1)");
        System.out.println("描述: 冒泡排序是一种简单的排序算法。它重复地遍历要排序的数列，" +
                          "一次比较两个元素，如果他们的顺序错误就把他们交换过来。");
        System.out.println();
    }

    @Override
    public void test() {
        System.out.println("=== " + getAlgorithmName() + " 测试 ===\n");

        // 测试用例1：正常数组
        int[] testArray1 = {64, 34, 25, 12, 22, 11, 90};
        testSorting(testArray1, "正常数组");

        // 测试用例2：已排序数组
        int[] testArray2 = {1, 2, 3, 4, 5};
        testSorting(testArray2, "已排序数组");

        // 测试用例3：逆序数组
        int[] testArray3 = {5, 4, 3, 2, 1};
        testSorting(testArray3, "逆序数组");

        // 测试用例4：包含重复元素
        int[] testArray4 = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        testSorting(testArray4, "包含重复元素");

        // 测试用例5：空数组
        int[] testArray5 = {};
        testSorting(testArray5, "空数组");

        // 测试用例6：单个元素
        int[] testArray6 = {42};
        testSorting(testArray6, "单个元素");

        System.out.println();
    }

    /**
     * 执行排序并验证结果
     */
    private void testSorting(int[] arr, String testName) {
        System.out.println("测试用例: " + testName);
        System.out.print("原始数组: ");
        ArrayUtils.printArray(arr);

        // 复制数组进行排序
        int[] sortedArray = ArrayUtils.copyArray(arr);
        bubbleSort(sortedArray);

        System.out.print("排序结果: ");
        ArrayUtils.printArray(sortedArray);

        // 验证排序结果
        if (ArrayUtils.isSorted(sortedArray)) {
            System.out.println("✅ 排序正确！");
        } else {
            System.out.println("❌ 排序错误！");
        }

        System.out.println();
    }

    /**
     * 执行排序（保留原有接口）
     */
    public void execute() {
        System.out.println("=== " + getAlgorithmName() + " ===");
        System.out.println("原始数组:");
        ArrayUtils.printArray(array);

        bubbleSort(array);

        System.out.println("排序后数组:");
        ArrayUtils.printArray(array);
        System.out.println();
    }

    /**
     * 冒泡排序实现
     */
    private void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换元素
                    ArrayUtils.swap(arr, j, j + 1);
                    swapped = true;
                }
            }

            // 如果没有发生交换，数组已经有序
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * 获取排序后的数组
     */
    public int[] getSortedArray() {
        return array;
    }
}

