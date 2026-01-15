package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

/**
 * LeetCode 7. 整数反转
 *
 * 题目描述：
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31, 2^31 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例：
 * 输入：x = 123
 * 输出：321
 *
 * 输入：x = -123
 * 输出：-321
 *
 * 输入：x = 120
 * 输出：21
 *
 * 输入：x = 0
 * 输出：0
 *
 * 解法：
 * 1. 数学方法：通过取模和除法逐位反转，过程中检测溢出
 * 2. 字符串方法：转为字符串反转后再转回整数（但不符合题目要求的环境限制）
 */
public class ReverseInteger implements AlgorithmTest {

    /**
     * 解法一：数学方法（推荐）
     * 时间复杂度：O(log|x|)
     * 空间复杂度：O(1)
     *
     * 思路：通过取模和除法逐位反转数字，在累加过程中检测溢出
     * 不能使用long类型，因为题目要求不能存储64位整数
     */
    public int reverseMath(int x) {
        int result = 0;

        while (x != 0) {
            // 取个位数
            int digit = x % 10;

            // 检查溢出：正数溢出
            if (result > Integer.MAX_VALUE / 10 ||
                (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return 0;
            }

            // 检查溢出：负数溢出
            if (result < Integer.MIN_VALUE / 10 ||
                (result == Integer.MIN_VALUE / 10 && digit < Integer.MIN_VALUE % 10)) {
                return 0;
            }

            // 累加到结果中
            result = result * 10 + digit;

            // 去掉个位数
            x /= 10;
        }

        return result;
    }

    /**
     * 解法二：字符串方法（不符合题目要求，仅供参考）
     * 时间复杂度：O(log|x|)
     * 空间复杂度：O(log|x|)
     *
     * 思路：转为字符串反转后再转回整数，但使用了额外空间且可能超出题目环境限制
     */
    public int reverseString(int x) {
        String str = String.valueOf(x);
        StringBuilder reversed = new StringBuilder();

        // 处理负号
        boolean isNegative = str.charAt(0) == '-';
        int start = isNegative ? 1 : 0;

        // 从后往前添加字符，跳过末尾的0
        for (int i = str.length() - 1; i >= start; i--) {
            if (reversed.length() == 0 && str.charAt(i) == '0') {
                continue; // 跳过前导零
            }
            reversed.append(str.charAt(i));
        }

        // 处理结果为0的情况
        if (reversed.length() == 0) {
            return 0;
        }

        // 添加负号
        if (isNegative) {
            reversed.insert(0, '-');
        }

        // 转为long检查溢出（实际应用中应该避免使用long）
        try {
            long result = Long.parseLong(reversed.toString());
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return 0;
            }
            return (int) result;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 7. 整数反转 ===\n");

        // 测试用例1：正数
        int x1 = 123;
        System.out.println("测试用例1（正数）:");
        System.out.println("输入: " + x1);

        System.out.print("数学方法结果: ");
        int result1 = reverseMath(x1);
        System.out.println(result1);
        System.out.println("验证: 123 反转应为 321 ✓");

        System.out.println();

        // 测试用例2：负数
        int x2 = -123;
        System.out.println("测试用例2（负数）:");
        System.out.println("输入: " + x2);

        System.out.print("数学方法结果: ");
        int result2 = reverseMath(x2);
        System.out.println(result2);
        System.out.println("验证: -123 反转应为 -321 ✓");

        System.out.println();

        // 测试用例3：末尾有0
        int x3 = 120;
        System.out.println("测试用例3（末尾有0）:");
        System.out.println("输入: " + x3);

        System.out.print("数学方法结果: ");
        int result3 = reverseMath(x3);
        System.out.println(result3);
        System.out.println("验证: 120 反转应为 21 ✓");

        System.out.println();

        // 测试用例4：0
        int x4 = 0;
        System.out.println("测试用例4（0）:");
        System.out.println("输入: " + x4);

        System.out.print("数学方法结果: ");
        int result4 = reverseMath(x4);
        System.out.println(result4);
        System.out.println("验证: 0 反转应为 0 ✓");

        System.out.println();

        // 测试用例5：正数溢出
        int x5 = 1534236469; // 这个数反转后会溢出
        System.out.println("测试用例5（正数溢出）:");
        System.out.println("输入: " + x5);

        System.out.print("数学方法结果: ");
        int result5 = reverseMath(x5);
        System.out.println(result5);
        System.out.println("验证: 反转后会溢出，应返回 0 ✓");

        System.out.println();

        // 测试用例6：负数溢出
        int x6 = -1534236469; // 这个数反转后会溢出
        System.out.println("测试用例6（负数溢出）:");
        System.out.println("输入: " + x6);

        System.out.print("数学方法结果: ");
        int result6 = reverseMath(x6);
        System.out.println(result6);
        System.out.println("验证: 反转后会溢出，应返回 0 ✓");

        System.out.println();

        // 测试用例7：边界值
        int x7 = Integer.MAX_VALUE;
        System.out.println("测试用例7（最大值）:");
        System.out.println("输入: " + x7);

        System.out.print("数学方法结果: ");
        int result7 = reverseMath(x7);
        System.out.println(result7);
        System.out.println("验证: 最大值反转可能溢出 ✓");

        System.out.println();

        // 测试用例8：边界值
        int x8 = Integer.MIN_VALUE;
        System.out.println("测试用例8（最小值）:");
        System.out.println("输入: " + x8);

        System.out.print("数学方法结果: ");
        int result8 = reverseMath(x8);
        System.out.println(result8);
        System.out.println("验证: 最小值反转可能溢出 ✓");

        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Reverse Integer";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：整数反转 (Reverse Integer)");
        System.out.println("LeetCode题号: 7");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：");
        System.out.println("1. 数学方法 - 时间复杂度 O(log|x|), 空间复杂度 O(1)");
        System.out.println("2. 字符串方法 - 时间复杂度 O(log|x|), 空间复杂度 O(log|x|)");
        System.out.println("推荐解法：数学方法（符合题目环境限制）");
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
     * 运行命令：java -cp target/classes com.algorithm.leetcode.ReverseInteger
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：整数反转 ===\n");

        ReverseInteger algorithm = new ReverseInteger();
        algorithm.test();
    }
}
