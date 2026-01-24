package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

/**
 * LeetCode 28. 实现 strStr()
 *
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回 -1。
 *
 * 解题思路：
 * 1. 使用Java String.indexOf()方法：简单直接
 * 2. 使用KMP算法：高效的字符串匹配算法
 */
public class ImplementStrStr implements AlgorithmTest {

    /**
     * 方法一：使用Java内置的String.indexOf()方法
     * 时间复杂度：O(n*m)，其中n是haystack长度，m是needle长度
     * 空间复杂度：O(1)
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }

        return haystack.indexOf(needle);
    }

    /**
     * 方法二：暴力匹配（朴素字符串匹配）
     * 时间复杂度：O(n*m)，最坏情况下
     * 空间复杂度：O(1)
     */
    public int strStrBruteForce(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }

        int n = haystack.length();
        int m = needle.length();

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            // 检查从位置i开始是否匹配needle
            for (j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            // 如果完全匹配，返回起始位置
            if (j == m) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 方法三：KMP算法
     * 时间复杂度：O(n+m)
     * 空间复杂度：O(m)
     */
    public int strStrKMP(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }

        int n = haystack.length();
        int m = needle.length();

        // 构建next数组
        int[] next = buildNext(needle);

        int i = 0; // 主串指针
        int j = 0; // 模式串指针

        while (i < n && j < m) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                // 失配，根据next数组跳转
                j = next[j];
            }
        }

        // 如果j == m，说明匹配成功
        if (j == m) {
            return i - j;
        }

        return -1;
    }

    /**
     * 构建next数组（前缀表）
     * next[i]表示当模式串第i位失配时，应该跳转到的位置
     */
    private int[] buildNext(String needle) {
        int m = needle.length();
        int[] next = new int[m];

        // 初始化
        next[0] = -1;
        int i = 0;
        int j = -1;

        while (i < m - 1) {
            if (j == -1 || needle.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }

        return next;
    }

    /**
     * KMP算法的另一种实现（更易理解的版本）
     */
    public int strStrKMPSimple(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }

        int n = haystack.length();
        int m = needle.length();

        // 构建前缀表
        int[] prefix = buildPrefixTable(needle);

        int i = 0; // 主串指针
        int j = 0; // 模式串指针

        while (i < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                // 找到匹配
                if (j == m) {
                    return i - j;
                }
            } else {
                // 不匹配，根据前缀表跳转
                if (j > 0) {
                    j = prefix[j - 1];
                } else {
                    i++;
                }
            }
        }

        return -1;
    }

    /**
     * 构建前缀表（另一种实现方式）
     */
    private int[] buildPrefixTable(String needle) {
        int m = needle.length();
        int[] prefix = new int[m];
        int len = 0; // 最长公共前后缀长度
        int i = 1;

        while (i < m) {
            if (needle.charAt(i) == needle.charAt(len)) {
                len++;
                prefix[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = prefix[len - 1];
                } else {
                    prefix[i] = 0;
                    i++;
                }
            }
        }

        return prefix;
    }

    public void testInternal() {
        System.out.println("=== LeetCode 28. 实现 strStr() ===\n");

        testCase("hello", "ll", 2, "示例1：在hello中找ll");
        testCase("aaaaa", "bba", -1, "示例2：在aaaaa中找bba");
        testCase("", "", 0, "空字符串匹配空字符串");
        testCase("", "a", -1, "空字符串中找字符");
        testCase("a", "", 0, "字符串中找空字符串");
        testCase("mississippi", "issip", 4, "较长字符串匹配");
        testCase("aaa", "aaaa", -1, "模式串比主串长");
        testCase("abc", "c", 2, "查找单个字符");
        testCase("abc", "d", -1, "查找不存在的字符");
        testCase("abababab", "abab", 0, "重复模式匹配");
    }

    private void testCase(String haystack, String needle, int expected, String desc) {
        System.out.println(desc);
        System.out.println("主串: \"" + haystack + "\"");
        System.out.println("模式串: \"" + needle + "\"");

        // 测试indexOf方法
        int result1 = strStr(haystack, needle);
        System.out.println("indexOf方法结果: " + result1);

        // 测试暴力方法
        int result2 = strStrBruteForce(haystack, needle);
        System.out.println("暴力方法结果: " + result2);

        // 测试KMP方法
        int result3 = strStrKMP(haystack, needle);
        System.out.println("KMP方法结果: " + result3);

        // 测试简化KMP方法
        int result4 = strStrKMPSimple(haystack, needle);
        System.out.println("简化KMP方法结果: " + result4);

        System.out.println("期望结果: " + expected);

        // 验证结果
        boolean allCorrect = (result1 == expected) && (result2 == expected) &&
                           (result3 == expected) && (result4 == expected);
        System.out.println("验证结果: " + (allCorrect ? "✓ 通过" : "✗ 失败"));
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Implement strStr()";
    }

    @Override
    public String getDifficulty() {
        return "简单";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：实现 strStr() (Implement strStr())");
        System.out.println("LeetCode题号: 28");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：暴力匹配、KMP算法");
        System.out.println("时间复杂度：暴力O(n*m)，KMP O(n+m)");
        System.out.println("空间复杂度：暴力O(1)，KMP O(m)");
        System.out.println("关键点：字符串匹配，前缀表构建，KMP算法优化");
        System.out.println("适用场景：字符串查找，模式匹配，文本搜索");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    public static void main(String[] args) {
        System.out.println("=== 单独测试：实现 strStr() ===\n");
        ImplementStrStr alg = new ImplementStrStr();
        alg.test();
    }
}
