package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * LeetCode 20. 有效的括号
 *
 * 题目描述：
 * 给定一个只包括 '(', ')', '{', '}', '[' 和 ']' 的字符串 s ，判断字符串是否有效。
 *
 * 解法：栈。遇到左括号入栈，遇到右括号时检查栈顶是否为对应的左括号。
 */
public class ValidParentheses implements AlgorithmTest {

    private static final Map<Character, Character> PAIRS = new HashMap<>();
    static {
        PAIRS.put(')', '(');
        PAIRS.put('}', '{');
        PAIRS.put(']', '[');
    }

    /**
     * 使用栈判断有效性
     */
    public boolean isValid(String s) {
        if (s == null) return false;
        Deque<Character> stack = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            if (PAIRS.containsValue(ch)) {
                stack.push(ch);
            } else if (PAIRS.containsKey(ch)) {
                if (stack.isEmpty() || stack.pop() != PAIRS.get(ch)) {
                    return false;
                }
            } else {
                // 非法字符，按题意可以视为无效
                return false;
            }
        }
        return stack.isEmpty();
    }

    public void testInternal() {
        System.out.println("=== LeetCode 20. 有效的括号 ===\n");

        testCase("()", true, "简单对称");
        testCase("()[]{}", true, "多种括号");
        testCase("(]", false, "类型不匹配");
        testCase("([)]", false, "顺序错误");
        testCase("{[]}", true, "嵌套正确");
        testCase("", true, "空串视作有效");
        testCase("]", false, "单个右括号");
        testCase("[", false, "单个左括号");
        testCase(null, false, "null输入");
    }

    private void testCase(String s, boolean expected, String desc) {
        boolean actual = isValid(s);
        System.out.println(desc);
        System.out.println(" 输入: " + s);
        System.out.println(" 实际: " + actual + ", 期望: " + expected);
        System.out.println(" 测试结果: " + (actual == expected ? "✓ 通过" : "✗ 失败"));
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Valid Parentheses";
    }

    @Override
    public String getDifficulty() {
        return "简单";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：有效的括号 (Valid Parentheses)");
        System.out.println("LeetCode题号: 20");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：栈");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    public static void main(String[] args) {
        System.out.println("=== 单独测试：有效的括号 ===\n");
        ValidParentheses alg = new ValidParentheses();
        alg.test();
    }
}


