package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 13. 罗马数字转整数
 *
 * 题目描述：
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如，罗马数字 2 写做 II，即为两个并列的 1。12 写做 XII，即为 X + II。
 * 27 写做 XXVII, 即为 XX + V + II。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4。
 * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 *
 * 给你一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * 解法：
 * 使用HashMap存储罗马数字到整数的映射，从右向左遍历字符串
 */
public class RomanToInteger implements AlgorithmTest {

    /**
     * 解法：从右向左遍历
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 思路：
     * 1. 创建罗马数字到整数的映射表
     * 2. 从右向左遍历罗马数字字符串
     * 3. 如果当前字符对应的值小于右边字符的值，则减去当前值，否则加上当前值
     * 4. 累加所有值得到结果
     */
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 创建罗马数字到整数的映射
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        // 从右向左遍历
        for (int i = s.length() - 1; i >= 0; i--) {
            char currentChar = s.charAt(i);
            int currentValue = romanMap.get(currentChar);

            // 如果当前值小于右边的值，说明是减法（如IV中的I）
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            prevValue = currentValue;
        }

        return result;
    }

    /**
     * 解法二：从左向右遍历（更直观但稍复杂）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 思路：
     * 1. 创建罗马数字到整数的映射表
     * 2. 从左向右遍历罗马数字字符串
     * 3. 如果当前字符的值小于下一个字符的值，则减去当前值，否则加上当前值
     */
    public int romanToIntLeftToRight(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int currentValue = romanMap.get(s.charAt(i));

            // 如果不是最后一个字符，且当前值小于下一个值，则减去当前值
            if (i < n - 1 && currentValue < romanMap.get(s.charAt(i + 1))) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
        }

        return result;
    }

    /**
     * 解法三：暴力累加（仅用于学习对比）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 思路：
     * 简单地将每个罗马数字对应的值累加，但不能正确处理特殊情况
     * 这个方法是错误的，仅用于展示为什么需要考虑特殊规则
     */
    public int romanToIntWrong(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        for (char c : s.toCharArray()) {
            result += romanMap.get(c);
        }

        return result;
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 13. 罗马数字转整数 ===\n");

        // 测试用例1：基本测试
        testCase("I", 1, "基本测试：I");
        testCase("II", 2, "基本测试：II");
        testCase("III", 3, "基本测试：III");
        testCase("IV", 4, "特殊情况：IV (4)");
        testCase("V", 5, "基本测试：V");
        testCase("VI", 6, "基本测试：VI");
        testCase("VII", 7, "基本测试：VII");
        testCase("VIII", 8, "基本测试：VIII");
        testCase("IX", 9, "特殊情况：IX (9)");
        testCase("X", 10, "基本测试：X");

        // 测试用例2：十位数
        testCase("XI", 11, "十位数：XI");
        testCase("XV", 15, "特殊情况：XV (15)");
        testCase("XIX", 19, "特殊情况：XIX (19)");
        testCase("XX", 20, "十位数：XX");
        testCase("XXV", 25, "十位数：XXV");
        testCase("XXX", 30, "十位数：XXX");
        testCase("XL", 40, "特殊情况：XL (40)");
        testCase("L", 50, "十位数：L");
        testCase("LX", 60, "十位数：LX");
        testCase("XC", 90, "特殊情况：XC (90)");

        // 测试用例3：百位数
        testCase("C", 100, "百位数：C");
        testCase("CC", 200, "百位数：CC");
        testCase("CCC", 300, "百位数：CCC");
        testCase("CD", 400, "特殊情况：CD (400)");
        testCase("D", 500, "百位数：D");
        testCase("DC", 600, "百位数：DC");
        testCase("CM", 900, "特殊情况：CM (900)");

        // 测试用例4：千位数
        testCase("M", 1000, "千位数：M");
        testCase("MM", 2000, "千位数：MM");
        testCase("MMM", 3000, "千位数：MMM");

        // 测试用例5：复杂数字
        testCase("LVIII", 58, "复杂数字：LVIII");
        testCase("MCMXCIV", 1994, "复杂数字：MCMXCIV");
        testCase("MMMCMXCIX", 3999, "最大数字：MMMCMXCIX");

        // 算法对比测试
        System.out.println("\n=== 算法对比测试 ===");
        String testRoman = "MCMXCIV";
        int expected = 1994;

        System.out.println("测试罗马数字: \"" + testRoman + "\" (期望结果: " + expected + ")");

        // 测试从右向左的算法
        long startTime = System.nanoTime();
        int result1 = romanToInt(testRoman);
        long endTime = System.nanoTime();
        double time1 = (endTime - startTime) / 1_000_000.0;

        // 测试从左向右的算法
        startTime = System.nanoTime();
        int result2 = romanToIntLeftToRight(testRoman);
        endTime = System.nanoTime();
        double time2 = (endTime - startTime) / 1_000_000.0;

        // 测试错误的算法（仅用于展示）
        int result3 = romanToIntWrong(testRoman);

        System.out.println("从右向左算法: " + result1 + " (耗时: " + String.format("%.4f", time1) + " ms)");
        System.out.println("从左向右算法: " + result2 + " (耗时: " + String.format("%.4f", time2) + " ms)");
        System.out.println("错误算法结果: " + result3 + " (用于对比)");
        System.out.println("结果一致性: " + (result1 == expected && result2 == expected));
        System.out.println("错误算法偏差: " + (result3 - expected) + " (多算了IV=6，但应该是IV=4)");

        // 算法过程演示
        conversionProcessDemo();

        System.out.println("\n=== 测试完成 ===");
    }

    /**
     * 转换过程详细演示
     */
    private void conversionProcessDemo() {
        System.out.println("\n=== 转换过程详细演示 ===");

        // 选择一个复杂的例子
        String roman = "MCMXCIV";
        int expected = 1994;

        System.out.println("将罗马数字 \"" + roman + "\" 转换为整数的过程：");
        System.out.println("期望结果: " + expected);
        System.out.println();

        // 显示字符分解
        System.out.println("字符分解:");
        for (int i = 0; i < roman.length(); i++) {
            char c = roman.charAt(i);
            int value = getRomanValue(c);
            System.out.println("  位置" + i + ": '" + c + "' = " + value);
        }
        System.out.println();

        // 演示从右向左的算法
        System.out.println("=== 从右向左遍历算法 ===");
        int result = 0;
        int prevValue = 0;

        System.out.println("初始化: result = 0, prevValue = 0");
        System.out.println("从右向左遍历:");
        System.out.println();

        for (int i = roman.length() - 1; i >= 0; i--) {
            char currentChar = roman.charAt(i);
            int currentValue = getRomanValue(currentChar);

            System.out.println("步骤 " + (roman.length() - i) + ": 处理字符 '" + currentChar + "' (值=" + currentValue + ")");
            System.out.println("  当前result: " + result);
            System.out.println("  上一个值prevValue: " + prevValue);

            if (currentValue < prevValue) {
                result -= currentValue;
                System.out.println("  判断: " + currentValue + " < " + prevValue + "，执行减法");
                System.out.println("  result = " + (result + currentValue) + " - " + currentValue + " = " + result);
            } else {
                result += currentValue;
                System.out.println("  判断: " + currentValue + " >= " + prevValue + "，执行加法");
                System.out.println("  result = " + (result - currentValue) + " + " + currentValue + " = " + result);
            }

            prevValue = currentValue;
            System.out.println("  更新prevValue = " + prevValue);
            System.out.println("  当前result = " + result);
            System.out.println();
        }

        System.out.println("最终结果: " + result);
        System.out.println("验证正确性: " + (result == expected));
        System.out.println();

        // 分析特殊规则
        System.out.println("=== 特殊规则分析 ===");
        System.out.println("罗马数字中的减法规则:");
        System.out.println("1. I(1) 在 V(5) 和 X(10) 左边: IV=4, IX=9");
        System.out.println("2. X(10) 在 L(50) 和 C(100) 左边: XL=40, XC=90");
        System.out.println("3. C(100) 在 D(500) 和 M(1000) 左边: CD=400, CM=900");
        System.out.println();
        System.out.println("在 \"MCMXCIV\" 中:");
        System.out.println("- CM: C(100) 在 M(1000) 左边，CM = 1000 - 100 = 900");
        System.out.println("- XC: X(10) 在 C(100) 左边，XC = 100 - 10 = 90");
        System.out.println("- IV: I(1) 在 V(5) 左边，IV = 5 - 1 = 4");
        System.out.println("- 其余都是加法: M=1000, D=500");
        System.out.println();
        System.out.println("总和: 1000 + 900 + 500 + 90 + 4 = 2494? 等等，这不对！");
        System.out.println("实际计算: M=1000, CM=900, XC=90, IV=4 → 1000+900=1900, +90=1990, +4=1994");
        System.out.println("关键: CM中的C已经被减去了，剩下的M、D、XC、IV都是正常相加");

        // 另一个简单例子
        System.out.println("\n=== 简单例子：IV ===");
        String simpleRoman = "IV";
        int simpleResult = romanToInt(simpleRoman);
        System.out.println("罗马数字: \"" + simpleRoman + "\"");
        System.out.println("手动计算: I=1, V=5, 但I在V左边，所以是 5 - 1 = 4");
        System.out.println("算法结果: " + simpleResult);
        System.out.println("验证: " + (simpleResult == 4));
    }

    /**
     * 获取罗马数字对应的值
     */
    private int getRomanValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    /**
     * 辅助测试方法
     */
    private void testCase(String roman, int expected, String description) {
        int actual = romanToInt(roman);
        System.out.println(description);
        System.out.println("  输入: \"" + roman + "\"");
        System.out.println("  实际输出: " + actual);
        System.out.println("  期望输出: " + expected);
        System.out.println("  测试结果: " + (actual == expected ? "✓ 通过" : "✗ 失败"));
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Roman to Integer";
    }

    @Override
    public String getDifficulty() {
        return "简单";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：罗马数字转整数 (Roman to Integer)");
        System.out.println("LeetCode题号: 13");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：从右向左遍历");
        System.out.println("时间复杂度：O(n)");
        System.out.println("空间复杂度：O(1)");
        System.out.println("核心思路：利用罗马数字减法规则，从右向左累加");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    /**
     * 单独测试这个算法
     * 运行命令：java -cp target/classes com.algorithm.leetcode.RomanToInteger
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：罗马数字转整数 ===\n");

        RomanToInteger algorithm = new RomanToInteger();
        algorithm.test();
    }
}
