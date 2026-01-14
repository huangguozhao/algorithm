package com.algorithm.searching;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ArrayUtils;

/**
 * 二分搜索算法
 * 时间复杂度: O(log n)
 * 空间复杂度: O(1)
 */
public class BinarySearch implements AlgorithmTest {

    private int[] array;
    private int target;

    public BinarySearch(int[] array, int target) {
        this.array = ArrayUtils.copyArray(array);
        this.target = target;
    }

    @Override
    public String getAlgorithmName() {
        return "Binary Search";
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
        System.out.println("时间复杂度: O(log n)");
        System.out.println("空间复杂度: O(1)");
        System.out.println("描述: 二分搜索是一种在有序数组中查找特定元素的搜索算法。" +
                          "每次都将搜索区间减半，直到找到目标元素或区间为空。");
        System.out.println();
    }

    @Override
    public void test() {
        System.out.println("=== " + getAlgorithmName() + " 测试 ===\n");

        // 测试用例1：元素存在于数组中
        int[] testArray1 = {1, 3, 5, 7, 9, 11, 13, 15};
        testSearch(testArray1, 7, "元素存在于数组中");

        // 测试用例2：元素不存在于数组中
        testSearch(testArray1, 6, "元素不存在于数组中");

        // 测试用例3：查找第一个元素
        testSearch(testArray1, 1, "查找第一个元素");

        // 测试用例4：查找最后一个元素
        testSearch(testArray1, 15, "查找最后一个元素");

        // 测试用例5：空数组
        int[] emptyArray = {};
        testSearch(emptyArray, 5, "空数组");

        // 测试用例6：单个元素数组
        int[] singleArray = {42};
        testSearch(singleArray, 42, "单个元素数组 - 找到");
        testSearch(singleArray, 0, "单个元素数组 - 未找到");

        // 测试用例7：包含重复元素的数组
        int[] duplicateArray = {1, 2, 2, 3, 4, 4, 4, 5};
        testSearch(duplicateArray, 2, "包含重复元素 - 查找重复元素");
        testSearch(duplicateArray, 4, "包含重复元素 - 查找重复元素");
        testSearch(duplicateArray, 6, "包含重复元素 - 查找不存在元素");

        System.out.println();
    }

    /**
     * 执行搜索并验证结果
     */
    private void testSearch(int[] arr, int target, String testName) {
        System.out.println("测试用例: " + testName);
        System.out.print("原始数组: ");
        ArrayUtils.printArray(arr);
        System.out.println("目标值: " + target);

        // 复制数组并排序
        int[] sortedArray = ArrayUtils.copyArray(arr);
        java.util.Arrays.sort(sortedArray);
        System.out.print("排序后数组: ");
        ArrayUtils.printArray(sortedArray);

        int result = binarySearch(sortedArray, target);

        if (result == -1) {
            System.out.println("结果: 目标值 " + target + " 未找到");

            // 验证结果是否正确
            boolean shouldNotFind = true;
            for (int num : sortedArray) {
                if (num == target) {
                    shouldNotFind = false;
                    break;
                }
            }

            if (shouldNotFind) {
                System.out.println("✅ 结果正确！");
            } else {
                System.out.println("❌ 结果错误！");
            }
        } else {
            System.out.println("结果: 目标值 " + target + " 找到在索引 " + result + " 处");

            // 验证结果是否正确
            if (result >= 0 && result < sortedArray.length && sortedArray[result] == target) {
                System.out.println("✅ 结果正确！");
            } else {
                System.out.println("❌ 结果错误！");
            }
        }

        System.out.println();
    }

    /**
     * 执行搜索（保留原有接口）
     */
    public void execute() {
        System.out.println("=== " + getAlgorithmName() + " ===");
        System.out.println("查找数组:");
        ArrayUtils.printArray(array);
        System.out.println("目标值: " + target);

        // 先排序数组（因为二分搜索要求有序数组）
        java.util.Arrays.sort(array);
        System.out.println("排序后数组:");
        ArrayUtils.printArray(array);

        int result = binarySearch(array, target);

        if (result == -1) {
            System.out.println("目标值 " + target + " 未找到");
        } else {
            System.out.println("目标值 " + target + " 找到在索引 " + result + " 处");
        }
        System.out.println();
    }

    /**
     * 二分搜索实现
     */
    private int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 检查中间元素
            if (arr[mid] == target) {
                return mid;
            }

            // 如果目标值大于中间元素，在右半部分查找
            if (arr[mid] < target) {
                left = mid + 1;
            }
            // 如果目标值小于中间元素，在左半部分查找
            else {
                right = mid - 1;
            }
        }

        // 目标值不存在
        return -1;
    }

    /**
     * 递归版本的二分搜索
     */
    private int binarySearchRecursive(int[] arr, int left, int right, int target) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            // 检查中间元素
            if (arr[mid] == target) {
                return mid;
            }

            // 如果目标值大于中间元素，在右半部分查找
            if (arr[mid] < target) {
                return binarySearchRecursive(arr, mid + 1, right, target);
            }

            // 如果目标值小于中间元素，在左半部分查找
            return binarySearchRecursive(arr, left, mid - 1, target);
        }

        // 目标值不存在
        return -1;
    }

    /**
     * 使用递归版本进行搜索
     */
    public int searchRecursive() {
        java.util.Arrays.sort(array);
        return binarySearchRecursive(array, 0, array.length - 1, target);
    }

    /**
     * 单独测试这个算法
     * 运行命令：java -cp target/classes com.algorithm.searching.BinarySearch
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：二分搜索 ===\n");

        int[] searchArray = {1, 3, 5, 7, 9, 11, 13, 15};
        BinarySearch algorithm = new BinarySearch(searchArray, 7);
        algorithm.test();
    }
}

