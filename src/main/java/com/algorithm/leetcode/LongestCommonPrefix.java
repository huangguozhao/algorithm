package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

/**
 * LeetCode 14. 最长公共前缀
 *
 * 题目描述：
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * 解法：
 * 使用纵向扫描法，从第一个字符开始，逐个比较所有字符串的对应位置字符
 */
public class LongestCommonPrefix implements AlgorithmTest {

    /**
     * 解法：纵向扫描法
     * 时间复杂度：O(m*n)，其中m是字符串平均长度，n是字符串数量
     * 空间复杂度：O(1)
     *
     * 思路：
     * 1. 如果数组为空或只有一个字符串，直接返回相应的结果
     * 2. 以第一个字符串为基准，从左到右遍历每个字符
     * 3. 对于每个字符位置，检查所有字符串的对应位置是否都相同
     * 4. 如果发现不同，返回当前已找到的公共前缀
     * 5. 如果遍历完第一个字符串都没有发现不同，返回整个第一个字符串
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 以第一个字符串为基准
        String firstStr = strs[0];

        // 遍历第一个字符串的每个字符
        for (int i = 0; i < firstStr.length(); i++) {
            char currentChar = firstStr.charAt(i);

            // 检查其他所有字符串的对应位置字符是否相同
            for (int j = 1; j < strs.length; j++) {
                // 如果其他字符串长度不够，或字符不同，返回当前前缀
                if (i >= strs[j].length() || strs[j].charAt(i) != currentChar) {
                    return firstStr.substring(0, i);
                }
            }
        }

        // 如果遍历完第一个字符串都没有发现不同，返回整个第一个字符串
        return firstStr;
    }

    /**
     * 解法二：横向扫描法（两两比较）
     * 时间复杂度：O(m*n)，其中m是字符串平均长度，n是字符串数量
     * 空间复杂度：O(1)
     *
     * 思路：
     * 1. 选择第一个字符串作为初始前缀
     * 2. 依次与其他字符串比较，更新公共前缀
     * 3. 每次比较时，找到两个字符串的最长公共前缀
     */
    public String longestCommonPrefixHorizontal(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 以第一个字符串为初始公共前缀
        String prefix = strs[0];

        // 依次与其他字符串比较
        for (int i = 1; i < strs.length; i++) {
            prefix = findCommonPrefix(prefix, strs[i]);
            // 如果前缀变为空，说明没有公共前缀
            if (prefix.isEmpty()) {
                return "";
            }
        }

        return prefix;
    }

    /**
     * 辅助方法：找到两个字符串的最长公共前缀
     */
    private String findCommonPrefix(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        int index = 0;

        while (index < minLength && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }

        return str1.substring(0, index);
    }

    /**
     * 解法三：分治法
     * 时间复杂度：O(m*n)，其中m是字符串平均长度，n是字符串数量
     * 空间复杂度：O(log n) - 递归栈空间
     *
     * 思路：
     * 1. 将字符串数组分成两半
     * 2. 递归处理左右两半，得到各自的公共前缀
     * 3. 合并两个公共前缀的结果
     */
    public String longestCommonPrefixDivideConquer(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        return divideConquer(strs, 0, strs.length - 1);
    }

    /**
     * 分治递归函数
     */
    private String divideConquer(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        }

        int mid = left + (right - left) / 2;

        // 分治处理左右两半
        String leftPrefix = divideConquer(strs, left, mid);
        String rightPrefix = divideConquer(strs, mid + 1, right);

        // 合并结果
        return findCommonPrefix(leftPrefix, rightPrefix);
    }

    /**
     * 解法四：二分查找法
     * 时间复杂度：O(m*n*log m)，其中m是字符串平均长度，n是字符串数量
     * 空间复杂度：O(1)
     *
     * 思路：
     * 1. 找到最短字符串的长度，作为二分查找的上界
     * 2. 在[0, minLength]范围内二分查找最长公共前缀的长度
     * 3. 对于每个中间长度，检查是否所有字符串都以该长度为前缀
     */
    public String longestCommonPrefixBinarySearch(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 找到最短字符串的长度
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }

        int left = 0;
        int right = minLength;

        // 二分查找最长公共前缀的长度
        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (isCommonPrefix(strs, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return strs[0].substring(0, left);
    }

    /**
     * 检查长度为length的前缀是否是所有字符串的公共前缀
     */
    private boolean isCommonPrefix(String[] strs, int length) {
        String prefix = strs[0].substring(0, length);

        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(prefix)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 14. 最长公共前缀 ===\n");

        // 测试用例1：标准示例
        testCase(new String[]{"flower", "flow", "flight"}, "fl", "标准示例");

        // 测试用例2：无公共前缀
        testCase(new String[]{"dog", "racecar", "car"}, "", "无公共前缀");

        // 测试用例3：完全相同
        testCase(new String[]{"flower", "flower", "flower"}, "flower", "完全相同");

        // 测试用例4：只有一个字符串
        testCase(new String[]{"single"}, "single", "只有一个字符串");

        // 测试用例5：空数组
        testCase(new String[]{}, "", "空数组");

        // 测试用例6：包含空字符串
        testCase(new String[]{"", "abc"}, "", "包含空字符串");

        // 测试用例7：不同长度
        testCase(new String[]{"ab", "a"}, "a", "不同长度");

        // 测试用例8：前缀相同但后续不同
        testCase(new String[]{"abc", "abd", "abe"}, "ab", "前缀相同");

        // 测试用例9：一个字符
        testCase(new String[]{"a", "a", "a"}, "a", "一个字符");

        // 测试用例10：长字符串
        testCase(new String[]{"programming", "program", "pro"}, "pro", "长字符串");

        // 算法对比测试
        System.out.println("\n=== 算法对比测试 ===");
        String[] testStrs = {"flower", "flow", "flight"};
        String expected = "fl";

        // 测试纵向扫描法
        long startTime = System.nanoTime();
        String result1 = longestCommonPrefix(testStrs);
        long endTime = System.nanoTime();
        double time1 = (endTime - startTime) / 1_000_000.0;

        // 测试横向扫描法
        startTime = System.nanoTime();
        String result2 = longestCommonPrefixHorizontal(testStrs);
        endTime = System.nanoTime();
        double time2 = (endTime - startTime) / 1_000_000.0;

        // 测试分治法
        startTime = System.nanoTime();
        String result3 = longestCommonPrefixDivideConquer(testStrs);
        endTime = System.nanoTime();
        double time3 = (endTime - startTime) / 1_000_000.0;

        // 测试二分查找法
        startTime = System.nanoTime();
        String result4 = longestCommonPrefixBinarySearch(testStrs);
        endTime = System.nanoTime();
        double time4 = (endTime - startTime) / 1_000_000.0;

        System.out.println("测试字符串数组: [flower, flow, flight]");
        System.out.println("期望结果: \"" + expected + "\"");
        System.out.println();
        System.out.println("纵向扫描法: \"" + result1 + "\" (耗时: " + String.format("%.4f", time1) + " ms)");
        System.out.println("横向扫描法: \"" + result2 + "\" (耗时: " + String.format("%.4f", time2) + " ms)");
        System.out.println("分治法: \"" + result3 + "\" (耗时: " + String.format("%.4f", time3) + " ms)");
        System.out.println("二分查找法: \"" + result4 + "\" (耗时: " + String.format("%.4f", time4) + " ms)");
        System.out.println();
        System.out.println("结果一致性: " + (result1.equals(expected) && result2.equals(expected) &&
                                           result3.equals(expected) && result4.equals(expected)));

        // 过程演示
        prefixProcessDemo();

        System.out.println("\n=== 测试完成 ===");
    }

    /**
     * 最长公共前缀过程演示
     */
    private void prefixProcessDemo() {
        System.out.println("\n=== 纵向扫描过程详细演示 ===");

        String[] strs = {"flower", "flow", "flight"};
        System.out.println("示例字符串数组: [flower, flow, flight]");
        System.out.println("目标：找到最长公共前缀");
        System.out.println();

        System.out.println("步骤演示：");
        System.out.println("以第一个字符串 \"flower\" 为基准");
        System.out.println();

        // 模拟纵向扫描过程
        String firstStr = strs[0];
        int prefixLength = 0;

        for (int i = 0; i < firstStr.length(); i++) {
            char currentChar = firstStr.charAt(i);
            System.out.println("检查位置 " + i + ": 字符 '" + currentChar + "'");

            boolean allMatch = true;
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length()) {
                    System.out.println("  字符串 \"" + strs[j] + "\" 长度不够，停止检查");
                    allMatch = false;
                    break;
                }

                char otherChar = strs[j].charAt(i);
                System.out.println("  与 \"" + strs[j] + "\" 的第" + i + "个字符 '" + otherChar + "' 比较");

                if (otherChar != currentChar) {
                    System.out.println("  字符不同，找到公共前缀长度: " + i);
                    allMatch = false;
                    break;
                }
            }

            if (!allMatch) {
                prefixLength = i;
                break;
            } else {
                System.out.println("  所有字符串都匹配，继续检查下一位置");
                prefixLength = i + 1;
            }
            System.out.println();
        }

        String result = firstStr.substring(0, prefixLength);
        System.out.println("最终结果: 最长公共前缀为 \"" + result + "\" (长度: " + prefixLength + ")");
        System.out.println();

        // 解释为什么这个方法正确
        System.out.println("=== 为什么纵向扫描法正确？ ===");
        System.out.println("1. 选择第一个字符串作为基准，避免了找最短字符串的额外步骤");
        System.out.println("2. 逐个字符检查，确保所有字符串在该位置都相同");
        System.out.println("3. 一旦发现不同，立即返回当前已找到的前缀");
        System.out.println("4. 时间复杂度O(m*n)，其中m是最短字符串长度，n是字符串数量");
        System.out.println("5. 空间复杂度O(1)，只使用了常数额外空间");
    }

    /**
     * 辅助测试方法
     */
    private void testCase(String[] strs, String expected, String description) {
        String actual = longestCommonPrefix(strs);
        System.out.println(description);
        System.out.print("输入: [");
        for (int i = 0; i < strs.length; i++) {
            System.out.print("\"" + strs[i] + "\"");
            if (i < strs.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println("实际输出: \"" + actual + "\"");
        System.out.println("期望输出: \"" + expected + "\"");
        System.out.println("测试结果: " + (actual.equals(expected) ? "✓ 通过" : "✗ 失败"));
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Longest Common Prefix";
    }

    @Override
    public String getDifficulty() {
        return "简单";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：最长公共前缀 (Longest Common Prefix)");
        System.out.println("LeetCode题号: 14");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：纵向扫描法");
        System.out.println("时间复杂度：O(m*n)");
        System.out.println("空间复杂度：O(1)");
        System.out.println("核心思路：逐个字符比较所有字符串的对应位置");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    /**
     * 单独测试这个算法
     * 运行命令：java -cp target/classes com.algorithm.leetcode.LongestCommonPrefix
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：最长公共前缀 ===\n");

        LongestCommonPrefix algorithm = new LongestCommonPrefix();
        algorithm.test();
    }
}
