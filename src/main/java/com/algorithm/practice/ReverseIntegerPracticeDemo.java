package com.algorithm.practice;

import com.algorithm.leetcode.ReverseInteger;

/**
 * 单题练习法演示 - 以整数反转为例
 * 展示完整的5步练习流程
 */
public class ReverseIntegerPracticeDemo {

    public static void main(String[] args) {
        demonstratePracticeProcess();
    }

    public static void demonstratePracticeProcess() {
        System.out.println("=== 单题练习法演示：整数反转 ===\n");

        // Step 1: 理解题意
        System.out.println("Step 1: 理解题意 (8分钟)");
        System.out.println("题目：将32位有符号整数x的数字部分反转");
        System.out.println("输入：int x (-2^31 <= x <= 2^31-1)");
        System.out.println("输出：反转后的整数，溢出则返回0");
        System.out.println("环境限制：不能使用64位整数(long)");
        System.out.println("示例：");
        System.out.println("  123 → 321");
        System.out.println("  -123 → -321");
        System.out.println("  120 → 21 (末尾0要去掉)");
        System.out.println("  0 → 0");
        System.out.println("边界：Integer.MAX_VALUE=2147483647, Integer.MIN_VALUE=-2147483648");
        System.out.println();

        // Step 2: 独立思考
        System.out.println("Step 2: 独立思考 (12分钟)");
        System.out.println("暴力解法：转为字符串反转，再转回整数");
        System.out.println("  时间O(log|x|), 空间O(log|x|), 但不符合环境限制");
        System.out.println("优化思路：用数学方法，逐位取模和除法");
        System.out.println("  result = result * 10 + x % 10, x = x / 10");
        System.out.println("溢出检测：不能用long，需要在累加前检查");
        System.out.println("  正数：result > MAX/10 或 (result == MAX/10 && digit > MAX%10)");
        System.out.println("  负数：result < MIN/10 或 (result == MIN/10 && digit < MIN%10)");
        System.out.println("算法选择：数学方法，时间O(log|x|), 空间O(1)");
        System.out.println();

        // Step 3: 查看题解
        System.out.println("Step 3: 查看题解 (6分钟)");
        System.out.println("官方题解确认思路正确：");
        System.out.println("1. 处理符号：记录正负");
        System.out.println("2. 逐位反转：while(x != 0)");
        System.out.println("3. 溢出检测：在累加前检查");
        System.out.println("4. 返回结果：恢复符号");
        System.out.println("关键点：溢出检测的边界条件判断");
        System.out.println();

        // Step 4: 复原代码
        System.out.println("Step 4: 复原代码 (15分钟)");
        System.out.println("关闭题解，自己实现：");

        int testX = 123;
        System.out.println("手动实现过程，测试输入: " + testX);

        // 手动模拟实现过程
        int result = 0;
        int x = testX;

        System.out.println("初始化: result = 0, x = " + x);
        while (x != 0) {
            int digit = x % 10;
            System.out.println("取个位数: " + x + " % 10 = " + digit);

            // 检查正数溢出
            if (result > Integer.MAX_VALUE / 10 ||
                (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                System.out.println("检测到正数溢出，返回 0");
                result = 0;
                break;
            }

            // 检查负数溢出
            if (result < Integer.MIN_VALUE / 10 ||
                (result == Integer.MIN_VALUE / 10 && digit < Integer.MIN_VALUE % 10)) {
                System.out.println("检测到负数溢出，返回 0");
                result = 0;
                break;
            }

            result = result * 10 + digit;
            System.out.println("累加结果: result = " + (result - digit) + " * 10 + " + digit + " = " + result);

            x = x / 10;
            System.out.println("去掉个位: x = " + (x * 10 + digit) + " / 10 = " + x);
            System.out.println();
        }

        System.out.println("最终结果: " + result);
        System.out.println();

        // 验证结果
        System.out.println("Step 4.5: 测试验证");
        ReverseInteger solution = new ReverseInteger();
        int correctResult = solution.reverseMath(testX);
        System.out.println("正确结果: " + correctResult);
        System.out.println("是否正确: " + (result == correctResult));
        System.out.println();

        // Step 5: 总结反思
        System.out.println("Step 5: 总结反思 (4分钟)");
        System.out.println("算法总结：数学方法，核心是溢出检测");
        System.out.println("时间复杂度：O(log|x|)，数字位数决定循环次数");
        System.out.println("空间复杂度：O(1)，只用常量空间");
        System.out.println("关键点：");
        System.out.println("1. 溢出检测：在累加前检查边界条件");
        System.out.println("2. 数学运算：result * 10 + digit, x / 10");
        System.out.println("3. 边界处理：Integer.MAX_VALUE和MIN_VALUE的处理");
        System.out.println("相似题目：字符串反转，但这个有溢出限制");
        System.out.println("心得体会：数学方法巧妙，避免了字符串操作，重点在于溢出检测的条件判断");
        System.out.println();

        // 扩展练习
        System.out.println("扩展练习：不同输入的测试");
        testDifferentInputs();
    }

    private static void testDifferentInputs() {
        ReverseInteger solution = new ReverseInteger();
        int[] testCases = {123, -123, 120, 0, 1534236469, -1534236469, Integer.MAX_VALUE, Integer.MIN_VALUE};

        for (int testCase : testCases) {
            int result = solution.reverseMath(testCase);
            System.out.println("输入: " + testCase + " → 输出: " + result);
        }
    }
}
