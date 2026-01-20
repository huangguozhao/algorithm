package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 17. 电话号码的字母组合
 *
 * 题目描述：
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 解法：回溯（DFS）/回溯生成所有组合
 */
public class LetterCombinations implements AlgorithmTest {

    private static final Map<Character, String> DIGIT_MAP = new HashMap<>();
    static {
        DIGIT_MAP.put('2', "abc");
        DIGIT_MAP.put('3', "def");
        DIGIT_MAP.put('4', "ghi");
        DIGIT_MAP.put('5', "jkl");
        DIGIT_MAP.put('6', "mno");
        DIGIT_MAP.put('7', "pqrs");
        DIGIT_MAP.put('8', "tuv");
        DIGIT_MAP.put('9', "wxyz");
    }

    /**
     * 回溯法生成所有字母组合
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        StringBuilder path = new StringBuilder();
        backtrack(digits, 0, path, result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder path, List<String> result) {
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }

        char digit = digits.charAt(index);
        String letters = DIGIT_MAP.get(digit);
        if (letters == null || letters.isEmpty()) {
            // skip invalid digit (shouldn't happen per problem constraints)
            backtrack(digits, index + 1, path, result);
            return;
        }

        for (char ch : letters.toCharArray()) {
            path.append(ch);
            backtrack(digits, index + 1, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 17. 电话号码的字母组合 ===\n");

        testCase("", new ArrayList<>(), "空输入应返回空列表");
        testCase("2", List.of("a","b","c"), "单个数字2");
        testCase("23", List.of("ad","ae","af","bd","be","bf","cd","ce","cf"), "示例23");
        testCase("79", null, "包含7和9的组合（长度4与4）");
    }

    private void testCase(String digits, List<String> expected, String desc) {
        List<String> actual = letterCombinations(digits);
        System.out.println(desc);
        System.out.println(" 输入: \"" + digits + "\"");
        System.out.println(" 实际输出: " + actual);
        if (expected != null) {
            System.out.println(" 期望输出: " + expected);
            // simple contains check for demo (order not important)
            boolean ok = actual.size() == expected.size() && actual.containsAll(expected);
            System.out.println(" 测试结果: " + (ok ? "✓ 通过" : "✗ 失败"));
        } else {
            System.out.println(" 期望输出: (手工检查)");
        }
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Letter Combinations of a Phone Number";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：电话号码的字母组合 (Letter Combinations of a Phone Number)");
        System.out.println("LeetCode题号: 17");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：回溯（DFS）");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    public static void main(String[] args) {
        System.out.println("=== 单独测试：电话号码的字母组合 ===\n");
        LetterCombinations alg = new LetterCombinations();
        alg.test();
    }
}


