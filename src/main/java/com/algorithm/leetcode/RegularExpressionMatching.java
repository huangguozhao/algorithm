package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ArrayUtils;

/**
 * LeetCode 10. 正则表达式匹配
 *
 * 题目描述：
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个字符
 *
 * 所谓匹配，是要涵盖整个字符串 s 的，而不是部分字符串。
 *
 * 示例：
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。
 * 因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 解法：
 * 使用动态规划，创建一个二维DP表 dp[i][j] 表示 s[0..i-1] 和 p[0..j-1] 是否匹配
 */
public class RegularExpressionMatching implements AlgorithmTest {

    /**
     * 解法：动态规划 - 详细解释版
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(m*n)
     *
     * 核心思想：
     * dp[i][j] 表示字符串s的前i个字符(s[0..i-1])是否能被正则表达式p的前j个字符(p[0..j-1])匹配
     *
     * 举例说明：
     * s = "aa", p = "a*" 时，dp表的构建过程：
     *
     * dp表结构：
     *     ""  "a"  "*"   (p的各列，对应p[0], p[1])
     * ""   T   F    T    (第0行：空串s匹配不同长度的p)
     * "a"  F   T    T    (第1行：s[0]='a'匹配不同长度的p)
     * "a"  F   F    T    (第2行：s[0..1]="aa"匹配不同长度的p)
     * (s的各行，对应s[0], s[1])
     *
     * 状态转移规则：
     * 1. 普通字符：dp[i][j] = dp[i-1][j-1] && (s[i-1] == p[j-1])
     * 2. '.'通配符：dp[i][j] = dp[i-1][j-1]  (因为.可以匹配任何字符)
     * 3. '*'通配符：两种情况
     *    - 匹配0个：dp[i][j] = dp[i][j-2]  (忽略前面的字符和*)
     *    - 匹配1个或多个：如果当前字符匹配，则dp[i][j] = dp[i-1][j] (继续使用这个*)
     *
     * 理解的关键：
     * - dp[i][j]依赖于dp[i-1][j-1]、dp[i][j-2]、dp[i-1][j]
     * - *的处理最复杂，因为它可以匹配0、1、2、...个前面的字符
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int m = s.length();
        int n = p.length();

        // dp[i][j] 表示 s[0..i-1] 和 p[0..j-1] 是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 初始化：空串匹配空串
        dp[0][0] = true;

        // 处理第一行：s为空串，p不为空串的情况
        // 只有当p形如 "a*b*c*" 这样的形式才能匹配空串
        for (int j = 2; j <= n; j += 2) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 填充dp表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char current = p.charAt(j - 1);

                if (current == '*') {
                    // '*' 匹配的情况：
                    // 1. 匹配0个前面的字符：相当于忽略 p[j-2] 和 '*'
                    dp[i][j] = dp[i][j - 2];

                    // 2. 匹配1个或多个前面的字符：需要前面的字符匹配，且s的前一个状态也要匹配
                    char prevChar = p.charAt(j - 2);
                    if (prevChar == s.charAt(i - 1) || prevChar == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    // 普通字符或 '.' 的匹配
                    if (current == s.charAt(i - 1) || current == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }

        return dp[m][n];
    }

    /**
     * 测试方法（内部实现）
     */
    public void testInternal() {
        System.out.println("=== LeetCode 10. 正则表达式匹配 ===\n");

        // 测试用例1：基本匹配
        testCase("aa", "a", false, "基本匹配：单个字符不匹配多个字符");

        // 测试用例2：* 匹配零个
        testCase("aa", "a*", true, "* 匹配零个或多个前面的字符");

        // 测试用例3：* 匹配多个
        testCase("aaa", "a*", true, "* 匹配多个相同字符");

        // 测试用例4：. 匹配任意字符
        testCase("ab", ".*", true, ". 匹配任意字符，* 匹配多个");

        // 测试用例5：复杂匹配
        testCase("aab", "c*a*b", true, "复杂正则表达式匹配");

        // 测试用例6：完全匹配
        testCase("mississippi", "mis*is*p*.", false, "复杂字符串匹配");

        // 测试用例7：空串匹配
        testCase("", "", true, "空串匹配空串");

        // 测试用例8：空串匹配正则
        testCase("", "a*", true, "空串匹配 a*");

        // 测试用例9：单个字符匹配
        testCase("a", "a", true, "单个字符完全匹配");

        // 测试用例10：. 匹配单个字符
        testCase("a", ".", true, ". 匹配单个字符");

        // 测试用例11：* 匹配单个字符
        testCase("a", "a*", true, "* 可以匹配一个字符");

        // 测试用例12：多个 . 匹配
        testCase("abc", "...", true, "多个 . 匹配多个字符");

        // 测试用例13：交替匹配
        testCase("aaa", "ab*ac*a", true, "ab*ac*a 匹配 aaa");

        // 测试用例14：边界情况
        testCase("a", "ab*", true, "ab* 匹配 a");

        System.out.println("\n=== 测试完成 ===");

        // 性能测试
        performanceTest();

        // 详细的DP过程演示
        dpProcessDemo();

        // 最简单的例子演示
        simpleExampleDemo();
    }

    /**
     * 详细的DP过程演示
     * 用简单的例子展示DP表是如何一步步构建的
     */
    private void dpProcessDemo() {
        System.out.println("\n=== DP过程详细演示 ===");

        // 选择一个简单的例子
        String s = "aa";
        String p = "a*";

        System.out.println("示例：s = \"" + s + "\", p = \"" + p + "\"");
        System.out.println("目标：演示DP表 dp[" + (s.length()+1) + "][" + (p.length()+1) + "] 的构建过程\n");

        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        System.out.println("1. 初始化：");
        System.out.println("   dp[0][0] = true  // 空串匹配空串");

        dp[0][0] = true;

        // 初始化第一行（s为空串的情况）
        System.out.println("\n2. 初始化第一行（s为空串，p长度从0到" + n + "）:");
        for (int j = 1; j <= n; j++) {
            if (j >= 2 && p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
                System.out.println("   dp[0][" + j + "] = dp[0][" + (j-2) + "] = " + dp[0][j] +
                                 "  // p[" + (j-1) + "]='*', 可以匹配0个前面的字符");
            } else {
                System.out.println("   dp[0][" + j + "] = false  // 空串无法匹配非空的p");
            }
        }

        // 逐步填充DP表
        System.out.println("\n3. 逐步填充DP表：");
        for (int i = 1; i <= m; i++) {
            System.out.println("\n   处理s[" + (i-1) + "] = '" + s.charAt(i-1) + "' 时：");

            for (int j = 1; j <= n; j++) {
                char current = p.charAt(j - 1);
                System.out.println("     计算dp[" + i + "][" + j + "] (p[" + (j-1) + "] = '" + current + "'):");

                if (current == '*') {
                    // * 的匹配逻辑
                    char prevChar = p.charAt(j - 2);
                    System.out.println("       '*'的情况：");

                    // 匹配0个
                    boolean matchZero = dp[i][j - 2];
                    System.out.println("       - 匹配0个：" + matchZero + " (来自dp[" + i + "][" + (j-2) + "])");

                    // 匹配1个或多个
                    boolean matchMore = false;
                    if (prevChar == s.charAt(i-1) || prevChar == '.') {
                        matchMore = dp[i - 1][j];
                        System.out.println("       - 匹配多个：当前字符'" + s.charAt(i-1) + "'匹配前面的'" + prevChar +
                                         "', 使用dp[" + (i-1) + "][" + j + "] = " + matchMore);
                    } else {
                        System.out.println("       - 匹配多个：当前字符'" + s.charAt(i-1) + "'不匹配前面的'" + prevChar + "', 不能匹配");
                    }

                    dp[i][j] = matchZero || matchMore;
                    System.out.println("       → dp[" + i + "][" + j + "] = " + matchZero + " || " + matchMore + " = " + dp[i][j]);

                } else {
                    // 普通字符或.的匹配
                    boolean charsMatch = (current == s.charAt(i-1) || current == '.');
                    boolean prevMatch = dp[i-1][j-1];

                    dp[i][j] = charsMatch && prevMatch;
                    System.out.println("       普通字符匹配：" + charsMatch + " && " + prevMatch + " = " + dp[i][j] +
                                     " (来自dp[" + (i-1) + "][" + (j-1) + "])");
                }
            }

            // 显示当前行的状态
            System.out.print("   当前dp表第" + i + "行状态: [");
            for (int k = 0; k <= n; k++) {
                System.out.print(dp[i][k] ? "T" : "F");
                if (k < n) System.out.print(",");
            }
            System.out.println("]");
        }

        // 显示最终结果
        System.out.println("\n4. 最终结果：");
        System.out.println("   dp[" + m + "][" + n + "] = " + dp[m][n]);
        System.out.println("   即字符串 \"" + s + "\" " + (dp[m][n] ? "能" : "不能") + "被正则表达式 \"" + p + "\" 匹配");

        // 显示完整的DP表
        System.out.println("\n5. 完整的DP表：");
        System.out.print("    ");
        for (int j = 0; j <= n; j++) {
            if (j == 0) System.out.print("\"\"");
            else System.out.print("\"" + p.charAt(j-1) + "\"");
            System.out.print(" ");
        }
        System.out.println();

        for (int i = 0; i <= m; i++) {
            if (i == 0) System.out.print("\"\" ");
            else System.out.print("\"" + s.charAt(i-1) + "\" ");
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] ? " T " : " F ");
            }
            System.out.println();
        }
    }

    /**
     * 最简单的例子演示
     * 用最基本的匹配来解释DP原理
     */
    private void simpleExampleDemo() {
        System.out.println("\n=== 最简单例子演示：s=\"a\", p=\"a\" ===");

        String s = "a";
        String p = "a";

        System.out.println("目标：理解dp[i][j]的含义");
        System.out.println("s = \"" + s + "\", p = \"" + p + "\"");
        System.out.println("需要构建dp[2][2]的表（包含空串的情况）\n");

        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 手动演示每一步
        System.out.println("第0步：初始化");
        System.out.println("dp[0][0] = true  // 空串\"\"能被空串\"\"匹配");
        dp[0][0] = true;
        System.out.println("dp[0][1] = false // 空串\"\"不能被\"a\"匹配");
        System.out.println("dp[1][0] = false // \"a\"不能被空串\"\"匹配\n");

        System.out.println("第1步：计算dp[1][1]");
        System.out.println("当前位置：s的第1个字符'a' vs p的第1个字符'a'");
        System.out.println("- 这是一个普通字符匹配");
        System.out.println("- 需要检查：前一个状态dp[0][0] AND (s[0]=='a' AND p[0]=='a')");
        System.out.println("- 计算：true AND ('a'=='a') = true");
        System.out.println("- 所以dp[1][1] = true\n");

        dp[1][1] = dp[0][0] && (s.charAt(0) == p.charAt(0));

        // 显示结果
        System.out.println("最终DP表：");
        System.out.println("      空串  \"a\"");
        System.out.println("空串   T     F");
        System.out.println("\"a\"   F     T");
        System.out.println("\n结论：dp[1][1] = " + dp[1][1] + "，说明\"a\"能被\"a\"匹配");

        System.out.println("\n=== 另一个简单例子：s=\"a\", p=\".\" ===");
        String s2 = "a";
        String p2 = ".";
        boolean[][] dp2 = new boolean[2][2];
        dp2[0][0] = true;

        System.out.println("s = \"" + s2 + "\", p = \"" + p2 + "\"");
        System.out.println("dp[1][1]计算：");
        System.out.println("- '.'可以匹配任何字符");
        System.out.println("- dp[1][1] = dp[0][0] AND (s[0]=='a'能被'.'匹配)");
        System.out.println("- 计算：true AND true = true");

        dp2[1][1] = dp2[0][0] && true; // .可以匹配任何字符

        System.out.println("最终DP表：");
        System.out.println("      空串  \".\"");
        System.out.println("空串   T     F");
        System.out.println("\"a\"   F     T");
        System.out.println("\n结论：dp[1][1] = " + dp2[1][1] + "，说明\"a\"能被\".\"匹配");

        System.out.println("\n=== 关键理解点 ===");
        System.out.println("1. dp[i][j]的含义：s的前i个字符是否匹配p的前j个字符");
        System.out.println("2. 行索引i对应s的长度，列索引j对应p的长度");
        System.out.println("3. dp[0][0]总是true：空串匹配空串");
        System.out.println("4. 状态转移依赖于左上角、上方、左方的值");
        System.out.println("5. *的处理最复杂：既可以匹配0个，也可以匹配多个");

        System.out.println("\n=== 状态转移图解 ===");
        System.out.println("理解dp[i][j]如何从之前的dp值计算而来：");
        System.out.println();
        System.out.println("对于普通字符或'.'：");
        System.out.println("dp[i][j] ← dp[i-1][j-1]  // 依赖左上角");
        System.out.println("         ∧");
        System.out.println("         │ 字符匹配 && 前一个状态匹配");
        System.out.println();
        System.out.println("对于'*'字符：");
        System.out.println("dp[i][j] ← dp[i][j-2]    // 匹配0个（忽略*前面的字符）");
        System.out.println("         ∨");
        System.out.println("         dp[i-1][j]      // 匹配多个（如果当前字符匹配，继续使用*）");
        System.out.println("         ∧");
        System.out.println("         │ 当前字符匹配*前面的字符");
        System.out.println();
        System.out.println("记忆口诀：");
        System.out.println("- 普通字符：看左上角，字符匹配才行");
        System.out.println("- *号处理：0个或多个，看左边和上方");
        System.out.println("- 边界情况：空串匹配要特殊处理");

        System.out.println("\n=== 练习建议 ===");
        System.out.println("1. 先理解dp[i][j]的含义");
        System.out.println("2. 画出小的DP表，手动计算几个简单的例子");
        System.out.println("3. 重点理解*的两种匹配方式");
        System.out.println("4. 遇到复杂情况时，画图分析依赖关系");

        System.out.println("\n=== 思维导图：DP表构建流程 ===");
        System.out.println("1. 创建DP表：boolean dp[m+1][n+1]");
        System.out.println("   ┌─ 行i=0到m：字符串s的长度（包含空串）");
        System.out.println("   │  列j=0到n：正则p的长度（包含空串）");
        System.out.println("   └─ dp[i][j]：s的前i个字符 vs p的前j个字符");
        System.out.println();
        System.out.println("2. 初始化边界：");
        System.out.println("   dp[0][0] = true    // 空串匹配空串");
        System.out.println("   dp[0][j] 处理     // 空串匹配p的各种情况");
        System.out.println("   dp[i][0] 通常false // 非空串匹配空正则（除非特殊情况）");
        System.out.println();
        System.out.println("3. 状态转移：");
        System.out.println("   for i=1 to m:");
        System.out.println("     for j=1 to n:");
        System.out.println("       if p[j-1] 是普通字符或'.':");
        System.out.println("         dp[i][j] = dp[i-1][j-1] && 字符匹配");
        System.out.println("       else if p[j-1] == '*':");
        System.out.println("         dp[i][j] = dp[i][j-2] || (字符匹配 && dp[i-1][j])");
        System.out.println();
        System.out.println("4. 结果：dp[m][n] 即为最终答案");
    }

    /**
     * 性能测试方法
     */
    private void performanceTest() {
        System.out.println("\n=== 性能测试 ===");

        // 测试用例：中等规模的字符串
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String p = "a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*a*";

        System.out.println("性能测试：");
        System.out.println("字符串 s 长度: " + s.length());
        System.out.println("正则表达式 p 长度: " + p.length());

        long startTime = System.nanoTime();
        boolean result = isMatch(s, p);
        long endTime = System.nanoTime();

        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.println("匹配结果: " + result);
        System.out.println("执行时间: " + String.format("%.3f", durationMs) + " ms");
        System.out.println("时间复杂度验证: O(m*n) = O(" + s.length() + "*" + p.length() + ") = O(" + (s.length() * p.length()) + ")");
    }

    /**
     * 辅助测试方法
     */
    private void testCase(String s, String p, boolean expected, String description) {
        System.out.println("测试用例：" + description);
        System.out.println("字符串 s: \"" + s + "\"");
        System.out.println("正则表达式 p: \"" + p + "\"");
        System.out.println("期望结果: " + expected);

        boolean actual = isMatch(s, p);
        System.out.println("实际结果: " + actual);
        System.out.println("测试结果: " + (actual == expected ? "✓ 通过" : "✗ 失败"));
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Regular Expression Matching";
    }

    @Override
    public String getDifficulty() {
        return "困难";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：正则表达式匹配 (Regular Expression Matching)");
        System.out.println("LeetCode题号: 10");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：动态规划");
        System.out.println("时间复杂度：O(m*n)");
        System.out.println("空间复杂度：O(m*n)");
        System.out.println("核心思路：使用DP表记录子串匹配状态");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    /**
     * 单独测试这个算法
     * 运行命令：java -cp target/classes com.algorithm.leetcode.RegularExpressionMatching
     */
    public static void main(String[] args) {
        System.out.println("=== 单独测试：正则表达式匹配 ===\n");

        RegularExpressionMatching algorithm = new RegularExpressionMatching();
        algorithm.test();
    }
}
