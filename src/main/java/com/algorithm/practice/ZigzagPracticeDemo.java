package com.algorithm.practice;

import com.algorithm.leetcode.ZigzagConversion;

/**
 * 单题练习法演示 - 以Z字形变换为例
 * 展示完整的5步练习流程
 */
public class ZigzagPracticeDemo {

    public static void main(String[] args) {
        demonstratePracticeProcess();
    }

    public static void demonstratePracticeProcess() {
        System.out.println("=== 单题练习法演示：Z字形变换 ===\n");

        // Step 1: 理解题意
        System.out.println("Step 1: 理解题意 (8分钟)");
        System.out.println("题目：将字符串按Z字形排列，然后逐行读取");
        System.out.println("输入：字符串s，行数numRows");
        System.out.println("输出：重新排列后的字符串");
        System.out.println("示例：PAYPALISHIRING, 3行 → PAHNAPLSIIGYIR");
        System.out.println("边界：numRows=1时直接返回，numRows>=len时直接返回");
        System.out.println();

        // Step 2: 独立思考
        System.out.println("Step 2: 独立思考 (12分钟)");
        System.out.println("暴力解法：创建一个numRows x len的二维数组，模拟Z字形填充");
        System.out.println("时间复杂度：O(numRows * len)，空间O(numRows * len)");
        System.out.println("优化思路：不需要完整矩阵，只需要numRows个字符串缓冲区");
        System.out.println("时间复杂度：O(len)，空间O(len)");
        System.out.println("算法选择：模拟法，用StringBuilder数组存储每行字符");
        System.out.println();

        // Step 3: 查看题解
        System.out.println("Step 3: 查看题解 (6分钟)");
        System.out.println("官方题解思路：");
        System.out.println("1. 创建numRows个StringBuilder");
        System.out.println("2. 按Z字形轨迹遍历字符，放到对应行");
        System.out.println("3. 改变方向：到第0行或最后一行时转向");
        System.out.println("4. 拼接所有行得到结果");
        System.out.println("复杂度：时间O(n)，空间O(n)");
        System.out.println();

        // Step 4: 复原代码
        System.out.println("Step 4: 复原代码 (15分钟)");
        System.out.println("关闭题解，自己实现：");

        String testS = "PAYPALISHIRING";
        int testRows = 3;

        // 手动实现一遍
        System.out.println("手动实现过程：");
        StringBuilder[] rows = new StringBuilder[testRows];
        for (int i = 0; i < testRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        System.out.println("初始化：currentRow=0, goingDown=false");
        for (int i = 0; i < testS.length(); i++) {
            char c = testS.charAt(i);
            rows[currentRow].append(c);
            System.out.println("字符 '" + c + "' 放到第 " + (currentRow + 1) + " 行");

            if (currentRow == 0 || currentRow == testRows - 1) {
                goingDown = !goingDown;
                System.out.println("到达边界，方向改变为: " + (goingDown ? "向下" : "向上"));
            }

            currentRow += goingDown ? 1 : -1;
            System.out.println("移动到第 " + (currentRow + 1) + " 行");
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        System.out.println("拼接结果: \"" + result + "\"");
        System.out.println();

        // 验证结果
        System.out.println("Step 4.5: 测试验证");
        ZigzagConversion solution = new ZigzagConversion();
        String correctResult = solution.convertSimulation(testS, testRows);
        System.out.println("正确结果: \"" + correctResult + "\"");
        System.out.println("是否正确: " + result.toString().equals(correctResult));
        System.out.println();

        // Step 5: 总结反思
        System.out.println("Step 5: 总结反思 (4分钟)");
        System.out.println("算法总结：模拟法，核心是方向控制");
        System.out.println("时间复杂度：O(n)，每个字符访问一次");
        System.out.println("空间复杂度：O(n)，存储所有字符");
        System.out.println("关键点：");
        System.out.println("1. 方向控制：到边界时改变方向");
        System.out.println("2. 行索引计算：goingDown ? +1 : -1");
        System.out.println("3. 边界处理：numRows=1的特殊情况");
        System.out.println("相似题目：6.Z字形变换（本题）");
        System.out.println("心得体会：模拟题的关键是找到正确的遍历规律，控制好方向变化");
        System.out.println();

        // 扩展练习
        System.out.println("扩展练习：不同行数的测试");
        testDifferentRows();
    }

    private static void testDifferentRows() {
        ZigzagConversion solution = new ZigzagConversion();
        String s = "PAYPALISHIRING";

        for (int rows = 1; rows <= 5; rows++) {
            String result = solution.convertSimulation(s, rows);
            System.out.println(rows + " 行: \"" + result + "\"");
        }
    }
}
