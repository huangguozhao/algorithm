package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 3. 无重复字符的最长子串
 *
 * 题目描述：
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 *
 * 示例：
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *
 * 解法：
 * 1. 滑动窗口法 + HashSet：时间复杂度 O(n)，空间复杂度 O(min(m,n))
 * 2. 滑动窗口法 + HashMap：可以记录字符的最新位置，优化窗口收缩
 *
 * 其中 m 是字符集大小，n 是字符串长度
 */
public class LongestSubstringWithoutRepeatingCharacters implements AlgorithmTest {

    /**
     * 解法一：滑动窗口 + HashSet
     * 时间复杂度：O(2n) = O(n)，最坏情况下每个字符被访问两次
     * 空间复杂度：O(min(m,n))，其中m是字符集大小
     *
     * 思路：
     * 使用左右指针维护一个窗口，窗口内的字符都不重复
     * 当右指针遇到重复字符时，左指针向右移动直到重复字符被移除
     */
    /**
     * 解法一：滑动窗口 + HashSet
     * 时间复杂度：O(2n) = O(n)，最坏情况下每个字符被访问两次
     * 空间复杂度：O(min(m,n))，其中m是字符集大小
     *
     * 思路：
     * 使用左右指针维护一个窗口，窗口内的字符都不重复
     * 当右指针遇到重复字符时，左指针向右移动直到重复字符被移除
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 使用HashSet存储窗口内的字符
        Set<Character> charSet = new HashSet<>();
        int maxLength = 0;
        int left = 0;

        // 右指针遍历整个字符串
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 如果当前字符已经在窗口中，收缩左边界
            while (charSet.contains(currentChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }

            // 将当前字符加入窗口
            charSet.add(currentChar);

            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }


    /**
     * 解法二：滑动窗口 + HashMap（优化版本）
     * 时间复杂度：O(n)
     * 空间复杂度：O(min(m,n))
     *
     * 思路：
     * 使用HashMap记录每个字符最后出现的位置
     * 当遇到重复字符时，可以直接跳到重复字符的下一个位置
     * 比解法一更高效，避免了逐步收缩左指针的过程
     */
    public int lengthOfLongestSubstringOptimized(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 使用HashMap存储字符和其最后出现的位置
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 如果当前字符已经在窗口中出现过
            if (charIndexMap.containsKey(currentChar)) {
                // 更新左边界：取当前左边界和重复字符位置+1的较大值
                left = Math.max(left, charIndexMap.get(currentChar) + 1);
            }

            // 更新字符的位置
            charIndexMap.put(currentChar, right);

            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * 解法三：暴力解法（仅用于理解，不推荐）
     * 时间复杂度：O(n³)
     * 空间复杂度：O(min(m,n))
     *
     * 思路：检查所有可能的子串，找出最长的无重复字符子串
     */
    public int lengthOfLongestSubstringBruteForce(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int maxLength = 0;

        // 遍历所有可能的子串起始位置
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                // 检查子串s[i..j]是否包含重复字符
                if (isUnique(s, i, j)) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }

    /**
     * 辅助方法：检查字符串的子串是否包含重复字符
     */
    private boolean isUnique(String s, int start, int end) {
        Set<Character> charSet = new HashSet<>();

        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (charSet.contains(c)) {
                return false;
            }
            charSet.add(c);
        }

        return true;
    }

    /**
     * 获取最长无重复子串的内容（不仅仅是长度）
     * @param s 输入字符串
     * @return 最长无重复子串
     */
    public String getLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int maxStart = 0;  // 最长子串的起始位置
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            if (charIndexMap.containsKey(currentChar)) {
                left = Math.max(left, charIndexMap.get(currentChar) + 1);
            }

            charIndexMap.put(currentChar, right);

            // 如果当前窗口长度更大，更新结果
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                maxStart = left;
            }
        }

        return s.substring(maxStart, maxStart + maxLength);
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 3. 无重复字符的最长子串 ===\n");

        // 测试用例1：标准示例
        testCase("abcabcbb", 3, "\"abc\"");

        // 测试用例2：所有字符都重复
        testCase("bbbbb", 1, "\"b\"");

        // 测试用例3：包含多种字符
        testCase("pwwkew", 3, "\"wke\"或\"kew\"");

        // 测试用例4：空字符串
        testCase("", 0, "\"\"");

        // 测试用例5：单个字符
        testCase("a", 1, "\"a\"");

        // 测试用例6：所有字符都不重复
        testCase("abcdef", 6, "\"abcdef\"");

        // 测试用例7：包含数字和字母
        testCase("abc123def", 9, "\"abc123def\"");

        // 测试用例8：重复字符交替出现
        testCase("abababab", 2, "\"ab\"");

        System.out.println();
    }

    /**
     * 测试单个用例
     */
    private void testCase(String s, int expected, String expectedSubstring) {
        System.out.println("输入: s = \"" + s + "\"");
        System.out.println("预期输出: " + expected + " (" + expectedSubstring + ")");

        // 测试滑动窗口法
        int result1 = lengthOfLongestSubstring(s);
        int result2 = lengthOfLongestSubstringOptimized(s);
        String actualSubstring = getLongestSubstring(s);

        System.out.println("滑动窗口结果: " + result1);
        System.out.println("优化滑动窗口结果: " + result2);
        System.out.println("最长子串内容: \"" + actualSubstring + "\"");

        if (result1 == expected && result2 == expected) {
            System.out.println("✅ 结果正确！");
        } else {
            System.out.println("❌ 结果错误！");
        }

        // 性能对比（对于暴力解法，仅在小字符串上测试）
        if (s.length() <= 10) {
            long startTime = System.nanoTime();
            int bruteResult = lengthOfLongestSubstringBruteForce(s);
            long endTime = System.nanoTime();
            double timeMs = (endTime - startTime) / 1_000_000.0;

            System.out.println("暴力解法结果: " + bruteResult + " (耗时: " + String.format("%.3f", timeMs) + "ms)");
        }

        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Longest Substring Without Repeating Characters";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：无重复字符的最长子串 (Longest Substring Without Repeating Characters)");
        System.out.println("LeetCode题号: 3");
        System.out.println("难度：" + getDifficulty());
        System.out.println("数据结构：字符串、滑动窗口");
        System.out.println("解法：滑动窗口 + HashSet/HashMap");
        System.out.println("时间复杂度：O(n)");
        System.out.println("空间复杂度：O(min(m,n))，其中m是字符集大小");
        System.out.println("关键点：窗口收缩策略、字符位置记录");
        System.out.println();
    }

    @Override
    public void test() {
        // 调用原有的测试方法
        printAlgorithmInfo();
        testInternal();
    }

    /**
     * 直接运行这个类的main方法来单独测试
     * 运行命令：mvn exec:java -Dexec.mainClass="com.algorithm.leetcode.LongestSubstringWithoutRepeatingCharacters"
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：无重复字符的最长子串 ===\n");

        LongestSubstringWithoutRepeatingCharacters algorithm = new LongestSubstringWithoutRepeatingCharacters();
        algorithm.test();
    }
}
