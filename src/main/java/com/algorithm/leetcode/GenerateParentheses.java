package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 22. 括号生成
 *
 * 题目描述：
 * 给定 n，生成所有由 n 对括号组成的有效括号组合。
 *
 * 解法：回溯（DFS），跟踪已使用的左/右括号数量，保证随时有效。
 */
public class GenerateParentheses implements AlgorithmTest {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) return res;
        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder path, int open, int close, int max) {
        if (path.length() == max * 2) {
            res.add(path.toString());
            return;
        }

        // 可以添加左括号
        if (open < max) {
            path.append('(');
            backtrack(res, path, open + 1, close, max);
            path.deleteCharAt(path.length() - 1);
        }

        // 只有当右括号数量小于左括号数量时才可以添加右括号
        if (close < open) {
            path.append(')');
            backtrack(res, path, open, close + 1, max);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public void testInternal() {
        System.out.println("=== LeetCode 22. 括号生成 ===\n");

        testCase(1, List.of("()"), "n=1");
        testCase(2, List.of("()()", "(())"), "n=2");
        testCase(3, List.of("((()))","(()())","(())()","()(())","()()()"), "n=3 (示例)");
        testCase(4, null, "n=4 (示例输出较多，手工检查)");
    }

    private void testCase(int n, List<String> expected, String desc) {
        List<String> actual = generateParenthesis(n);
        System.out.println(desc);
        System.out.println(" n = " + n);
        System.out.println(" 实际输出: " + actual);
        if (expected != null) {
            System.out.println(" 期望输出: " + expected);
            boolean ok = actual.size() == expected.size() && actual.containsAll(expected);
            System.out.println(" 测试结果: " + (ok ? "✓ 通过" : "✗ 失败"));
        } else {
            System.out.println(" 期望输出: (手工检查)");
        }
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Generate Parentheses";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：括号生成 (Generate Parentheses)");
        System.out.println("LeetCode题号: 22");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：回溯（DFS），跟踪左/右括号数量");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    public static void main(String[] args) {
        System.out.println("=== 单独测试：括号生成 ===\n");
        GenerateParentheses alg = new GenerateParentheses();
        alg.test();
    }
}


