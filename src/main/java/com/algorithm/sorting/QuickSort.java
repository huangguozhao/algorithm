package com.algorithm.sorting;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ArrayUtils;

/**
 * 快速排序算法
 * 时间复杂度: O(n log n) 平均情况, O(n²) 最坏情况
 * 空间复杂度: O(log n)
 */
public class QuickSort implements AlgorithmTest {

    private int[] array;

    public QuickSort(int[] array) {
        this.array = ArrayUtils.copyArray(array);
    }

    @Override
    public String getAlgorithmName() {
        return "Quick Sort";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("算法名称: " + getAlgorithmName());
        System.out.println("难度: " + getDifficulty());
        System.out.println("时间复杂度: O(n log n) 平均, O(n²) 最坏");
        System.out.println("空间复杂度: O(log n)");
        System.out.println("描述: 快速排序是一种分治的排序算法。它将一个数组分成两个子数组，" +
                          "然后递归地对子数组进行排序。");
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
        quickSort(sortedArray, 0, sortedArray.length - 1);

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

        quickSort(array, 0, array.length - 1);

        System.out.println("排序后数组:");
        ArrayUtils.printArray(array);
        System.out.println();
    }

    /**
     * 快速排序实现
     */
    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 找到分区点
            int pi = partition(arr, low, high);

            // 递归排序左右子数组
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * 分区函数
     */
    private int partition(int[] arr, int low, int high) {
        // 选择最后一个元素作为基准
        int pivot = arr[high];
        int i = (low - 1); // 较小元素的索引

        for (int j = low; j < high; j++) {
            // 如果当前元素小于或等于基准
            if (arr[j] <= pivot) {
                i++;
                // 交换 arr[i] 和 arr[j]
                ArrayUtils.swap(arr, i, j);
            }
        }

        // 交换 arr[i+1] 和 arr[high] (基准元素)
        ArrayUtils.swap(arr, i + 1, high);

        return i + 1;
    }

    /**
     * 获取排序后的数组
     */
    public int[] getSortedArray() {
        return array;
    }

    /**
     * 单独测试这个算法
     * 运行命令：java -cp target/classes com.algorithm.sorting.QuickSort
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：快速排序 ===\n");

        QuickSort algorithm = new QuickSort(new int[]{64, 34, 25, 12, 22, 11, 90});
        algorithm.test();
    }
}

