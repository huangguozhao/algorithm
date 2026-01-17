package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;

/**
 * LeetCode 12. 整数转罗马数字
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
 * 给你一个整数，将其转为罗马数字。n 的范围为 1 <= n <= 3999
 *
 * 解法：
 * 使用贪心算法，从最大的罗马数字开始，尽可能多地减去对应的数值
 */
public class IntegerToRoman implements AlgorithmTest {

    /**
     * 解法：贪心算法
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * 思路：
     * 1. 创建数值和罗马数字的映射表，从大到小排列
     * 2. 从最大的数值开始，尽可能多地减去该数值，并添加对应的罗马数字
     * 3. 重复直到数字变为0
     */
    public String intToRoman(int num) {
        // 定义数值和罗马数字的映射，从大到小排列
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();

        // 贪心算法：从最大的数值开始
        for (int i = 0; i < values.length; i++) {
            // 尽可能多地减去当前数值
            while (num >= values[i]) {
                result.append(romans[i]);
                num -= values[i];
            }
        }

        return result.toString();
    }

    /**
     * 解法二：硬编码法（仅用于学习对比）
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * 思路：
     * 直接将数字按千位、百位、十位、个位分别处理
     * 每个位数都有固定的罗马数字表示
     */
    public String intToRomanHardcoded(int num) {
        // 千位：0-3个M
        String[] thousands = {"", "M", "MM", "MMM"};

        // 百位：C, CC, CCC, CD, D, DC, DCC, DCCC, CM
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};

        // 十位：X, XX, XXX, XL, L, LX, LXX, LXXX, XC
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};

        // 个位：I, II, III, IV, V, VI, VII, VIII, IX
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num / 1000] +
               hundreds[(num % 1000) / 100] +
               tens[(num % 100) / 10] +
               ones[num % 10];
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 12. 整数转罗马数字 ===\n");

        // 测试用例1：基本测试
        testCase(1, "I", "基本测试：1");
        testCase(2, "II", "基本测试：2");
        testCase(3, "III", "基本测试：3");
        testCase(4, "IV", "特殊情况：4");
        testCase(5, "V", "基本测试：5");
        testCase(6, "VI", "基本测试：6");
        testCase(7, "VII", "基本测试：7");
        testCase(8, "VIII", "基本测试：8");
        testCase(9, "IX", "特殊情况：9");
        testCase(10, "X", "基本测试：10");

        // 测试用例2：十位数
        testCase(11, "XI", "十位数：11");
        testCase(15, "XV", "特殊情况：15");
        testCase(19, "XIX", "特殊情况：19");
        testCase(20, "XX", "十位数：20");
        testCase(25, "XXV", "十位数：25");
        testCase(30, "XXX", "十位数：30");
        testCase(40, "XL", "特殊情况：40");
        testCase(50, "L", "十位数：50");
        testCase(60, "LX", "十位数：60");
        testCase(90, "XC", "特殊情况：90");

        // 测试用例3：百位数
        testCase(100, "C", "百位数：100");
        testCase(200, "CC", "百位数：200");
        testCase(300, "CCC", "百位数：300");
        testCase(400, "CD", "特殊情况：400");
        testCase(500, "D", "百位数：500");
        testCase(600, "DC", "百位数：600");
        testCase(900, "CM", "特殊情况：900");

        // 测试用例4：千位数
        testCase(1000, "M", "千位数：1000");
        testCase(2000, "MM", "千位数：2000");
        testCase(3000, "MMM", "千位数：3000");

        // 测试用例5：复杂数字
        testCase(58, "LVIII", "复杂数字：58");
        testCase(1994, "MCMXCIV", "复杂数字：1994");
        testCase(3999, "MMMCMXCIX", "最大数字：3999");

        // 测试用例6：边界情况
        testCase(1, "I", "最小数字：1");

        System.out.println("\n=== 算法对比测试 ===");
        int testNum = 1994;
        System.out.println("测试数字: " + testNum);

        // 测试贪心算法
        long startTime = System.nanoTime();
        String result1 = intToRoman(testNum);
        long endTime = System.nanoTime();
        double time1 = (endTime - startTime) / 1_000_000.0;

        // 测试硬编码算法
        startTime = System.nanoTime();
        String result2 = intToRomanHardcoded(testNum);
        endTime = System.nanoTime();
        double time2 = (endTime - startTime) / 1_000_000.0;

        System.out.println("贪心算法结果: " + result1 + " (耗时: " + String.format("%.4f", time1) + " ms)");
        System.out.println("硬编码结果: " + result2 + " (耗时: " + String.format("%.4f", time2) + " ms)");
        System.out.println("结果一致: " + result1.equals(result2));

        // 贪心算法过程演示
        greedyProcessDemo();

        System.out.println("\n=== 测试完成 ===");
    }

    /**
     * 贪心算法过程详细演示
     */
    private void greedyProcessDemo() {
        System.out.println("\n=== 贪心算法过程详细演示 ===");

        int num = 1994;
        System.out.println("将数字 " + num + " 转换为罗马数字的过程：");
        System.out.println();

        // 数值和罗马数字的映射
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();
        int remaining = num;

        System.out.println("映射表（从大到小）:");
        for (int i = 0; i < values.length; i++) {
            System.out.println("  " + values[i] + " -> \"" + romans[i] + "\"");
        }
        System.out.println();

        System.out.println("转换过程：");
        for (int i = 0; i < values.length && remaining > 0; i++) {
            int value = values[i];
            String roman = romans[i];

            if (remaining >= value) {
                int count = remaining / value;
                System.out.println("使用 " + value + "(\"" + roman + "\") " + count + " 次:");
                System.out.println("  剩余数字: " + remaining);
                System.out.println("  匹配次数: " + remaining + " / " + value + " = " + count);

                for (int j = 0; j < count; j++) {
                    result.append(roman);
                    remaining -= value;
                }

                System.out.println("  添加罗马数字: \"" + roman + "\" × " + count);
                System.out.println("  当前结果: \"" + result.toString() + "\"");
                System.out.println("  剩余数字: " + remaining);
                System.out.println();
            }
        }

        System.out.println("最终结果: " + num + " -> \"" + result.toString() + "\"");
        System.out.println();

        // 验证结果
        System.out.println("=== 为什么贪心算法是正确的？ ===");
        System.out.println("罗马数字的特点：");
        System.out.println("1. 数值按照从大到小排列");
        System.out.println("2. 特殊规则（如4=IV, 9=IX）已经包含在映射表中");
        System.out.println("3. 每个数值都是唯一的，不存在重叠");
        System.out.println("4. 贪心选择：每次选择最大的可用数值");
        System.out.println();
        System.out.println("证明贪心正确性：");
        System.out.println("假设存在更优解，那么在某个位置使用了更小的数值");
        System.out.println("但由于数值是递减的，更小的数值必然在更大的数值之后使用");
        System.out.println("这与贪心选择矛盾，因此贪心算法是最优的");
        System.out.println();

        // 另一个简单例子
        System.out.println("=== 简单例子：58 ===");
        int simpleNum = 58;
        String simpleResult = intToRoman(simpleNum);
        System.out.println("数字: " + simpleNum);
        System.out.println("分析: 50(L) + 5(V) + 3(III) = 50 + 5 + 3 = 58");
        System.out.println("结果: \"" + simpleResult + "\"");
        System.out.println("验证: L=50, V=5, I=1, I=1, I=1 → 50+5+1+1+1=58 ✓");
    }

    /**
     * 辅助测试方法
     */
    private void testCase(int num, String expected, String description) {
        String actual = intToRoman(num);
        System.out.println(description);
        System.out.println("  输入: " + num);
        System.out.println("  实际输出: " + actual);
        System.out.println("  期望输出: " + expected);
        System.out.println("  测试结果: " + (actual.equals(expected) ? "✓ 通过" : "✗ 失败"));
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Integer to Roman";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：整数转罗马数字 (Integer to Roman)");
        System.out.println("LeetCode题号: 12");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：贪心算法");
        System.out.println("时间复杂度：O(1)");
        System.out.println("空间复杂度：O(1)");
        System.out.println("核心思路：从大到小依次匹配罗马数字");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    /**
     * 单独测试这个算法
     * 运行命令：java -cp target/classes com.algorithm.leetcode.IntegerToRoman
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：整数转罗马数字 ===\n");

        IntegerToRoman algorithm = new IntegerToRoman();
        algorithm.test();
    }
}
