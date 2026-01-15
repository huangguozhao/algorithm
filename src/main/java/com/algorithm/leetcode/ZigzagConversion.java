package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

/**
 * LeetCode 6. Z字形变换
 *
 * 题目描述：
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串。
 *
 * 示例：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 *
 * 解法：
 * 1. 模拟法：使用数组或列表模拟每一行的字符，按Z字形轨迹填充
 * 2. 数学公式法：直接计算每个字符在周期中的位置
 */
public class ZigzagConversion implements AlgorithmTest {

    /**
     * 解法一：模拟法（推荐）
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * 思路：创建一个StringBuilder数组来存储每一行的字符。
     * 按照Z字形的轨迹遍历字符串，将每个字符放到对应的行中。
     */
    public String convertSimulation(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
        // 创建numRows个StringBuilder来存储每一行的字符
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        // 当前行号
        int currentRow = 0;
        // 方向：true表示向下，false表示向上
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);

            // 在第一行或最后一行时改变方向
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }

            // 根据方向移动到下一行
            currentRow += goingDown ? 1 : -1;
        }

        // 将所有行的字符拼接起来
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    /**
     * 解法二：数学公式法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * 思路：Z字形变换的周期是 2 * (numRows - 1)
     * 对于每个周期内的字符，可以直接计算它应该在哪一行
     */
    public String convertMath(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        StringBuilder result = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * (numRows - 1);

        // 遍历每一行
        for (int row = 0; row < numRows; row++) {
            // 对于每一行，找到所有属于这一行的字符
            for (int j = 0; j + row < n; j += cycleLen) {
                // 添加当前周期中这一行的字符
                result.append(s.charAt(j + row));

                // 如果不是第一行和最后一行，还需要在周期中间添加另一个字符
                if (row != 0 && row != numRows - 1 && j + cycleLen - row < n) {
                    result.append(s.charAt(j + cycleLen - row));
                }
            }
        }

        return result.toString();
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 6. Z字形变换 ===\n");

        // 测试用例1：标准示例
        String s1 = "PAYPALISHIRING";
        int numRows1 = 3;
        System.out.println("测试用例1：");
        System.out.println("输入字符串: \"" + s1 + "\"");
        System.out.println("行数: " + numRows1);

        System.out.print("模拟法结果: \"");
        String result1 = convertSimulation(s1, numRows1);
        System.out.println(result1 + "\"");

        System.out.print("数学公式法结果: \"");
        result1 = convertMath(s1, numRows1);
        System.out.println(result1 + "\"");

        System.out.println();

        // 测试用例2：4行
        String s2 = "PAYPALISHIRING";
        int numRows2 = 4;
        System.out.println("测试用例2（4行）:");
        System.out.println("输入字符串: \"" + s2 + "\"");
        System.out.println("行数: " + numRows2);

        System.out.print("模拟法结果: \"");
        String result2 = convertSimulation(s2, numRows2);
        System.out.println(result2 + "\"");

        System.out.print("数学公式法结果: \"");
        result2 = convertMath(s2, numRows2);
        System.out.println(result2 + "\"");

        System.out.println();

        // 测试用例3：单行
        String s3 = "A";
        int numRows3 = 1;
        System.out.println("测试用例3（单行）:");
        System.out.println("输入字符串: \"" + s3 + "\"");
        System.out.println("行数: " + numRows3);

        System.out.print("模拟法结果: \"");
        String result3 = convertSimulation(s3, numRows3);
        System.out.println(result3 + "\"");

        System.out.println();

        // 测试用例4：两行
        String s4 = "ABCD";
        int numRows4 = 2;
        System.out.println("测试用例4（两行）:");
        System.out.println("输入字符串: \"" + s4 + "\"");
        System.out.println("行数: " + numRows4);

        System.out.print("模拟法结果: \"");
        String result4 = convertSimulation(s4, numRows4);
        System.out.println(result4 + "\"");

        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Zigzag Conversion";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：Z字形变换 (Zigzag Conversion)");
        System.out.println("LeetCode题号: 6");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：");
        System.out.println("1. 模拟法 - 时间复杂度 O(n), 空间复杂度 O(n)");
        System.out.println("2. 数学公式法 - 时间复杂度 O(n), 空间复杂度 O(n)");
        System.out.println("推荐解法：模拟法（更直观易懂）");
        System.out.println();
    }

    @Override
    public void test() {
        // 调用原有的测试方法
        printAlgorithmInfo();
        testInternal();
    }

    /**
     * 单独测试这个算法
     * 运行命令：java -cp target/classes com.algorithm.leetcode.ZigzagConversion
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：Z字形变换 ===\n");

        ZigzagConversion algorithm = new ZigzagConversion();
        algorithm.test();
    }
}
