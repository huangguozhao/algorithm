package com.algorithm;

import com.algorithm.sorting.BubbleSort;
import com.algorithm.sorting.QuickSort;
import com.algorithm.searching.BinarySearch;
import com.algorithm.leetcode.TwoSum;
import com.algorithm.leetcode.AddTwoNumbers;
import com.algorithm.leetcode.LongestSubstringWithoutRepeatingCharacters;
import com.algorithm.leetcode.MedianOfTwoSortedArrays;
import com.algorithm.leetcode.RegularExpressionMatching;
import com.algorithm.leetcode.ContainerWithMostWater;
import com.algorithm.leetcode.IntegerToRoman;
import com.algorithm.leetcode.RomanToInteger;
import com.algorithm.utils.AlgorithmTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法练习主类
 * 演示各种算法的使用
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== 算法练习演示 ===\n");

        // 创建算法实例列表
        List<AlgorithmTest> algorithms = createAlgorithmList();

        // 运行所有算法的测试
        for (AlgorithmTest algorithm : algorithms) {
            algorithm.test();
        }
    }

    /**
     * 创建所有算法实例列表
     */
    private static List<AlgorithmTest> createAlgorithmList() {
        List<AlgorithmTest> algorithms = new ArrayList<>();

        // 排序算法
        algorithms.add(new BubbleSort(new int[]{64, 34, 25, 12, 22, 11, 90}));
        algorithms.add(new QuickSort(new int[]{64, 34, 25, 12, 22, 11, 90}));

        // 搜索算法
        int[] searchArray = {1, 3, 5, 7, 9, 11, 13, 15};
        algorithms.add(new BinarySearch(searchArray, 7));

        // LeetCode题目
        algorithms.add(new TwoSum());
        algorithms.add(new AddTwoNumbers());
        algorithms.add(new LongestSubstringWithoutRepeatingCharacters());
        algorithms.add(new MedianOfTwoSortedArrays());
        algorithms.add(new RegularExpressionMatching());
        algorithms.add(new ContainerWithMostWater());
        algorithms.add(new IntegerToRoman());
        algorithms.add(new RomanToInteger());

        return algorithms;
    }
}
