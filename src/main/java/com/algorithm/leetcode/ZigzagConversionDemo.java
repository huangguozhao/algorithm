package com.algorithm.leetcode;

/**
 * Z字形变换演示程序
 * 展示Z字形变换的详细过程
 */
public class ZigzagConversionDemo {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        demonstrateZigzag(s, numRows);
    }

    public static void demonstrateZigzag(String s, int numRows) {
        System.out.println("=== Z字形变换演示 ===");
        System.out.println("输入字符串: \"" + s + "\"");
        System.out.println("行数: " + numRows);
        System.out.println();

        if (numRows == 1 || numRows >= s.length()) {
            System.out.println("特殊情况：行数为1或行数大于等于字符串长度");
            System.out.println("直接输出原字符串: \"" + s + "\"");
            return;
        }

        // 创建行数组
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        // 模拟Z字形轨迹
        int currentRow = 0;
        boolean goingDown = false;

        System.out.println("开始模拟Z字形轨迹：");
        System.out.println();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            rows[currentRow].append(c);

            System.out.println("步骤 " + (i + 1) + ": 字符 '" + c + "' 放到第 " + (currentRow + 1) + " 行");
            printCurrentState(rows, numRows);
            System.out.println("当前方向: " + (goingDown ? "向下" : "向上"));

            // 在第一行或最后一行时改变方向
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
                System.out.println("到达边界，改变方向为: " + (goingDown ? "向下" : "向上"));
            }

            // 根据方向移动到下一行
            currentRow += goingDown ? 1 : -1;
            System.out.println("移动到下一行: 第 " + (currentRow + 1) + " 行");
            System.out.println();
        }

        // 显示最终的Z字形排列
        System.out.println("最终的Z字形排列：");
        printZigzagPattern(rows, numRows);
        System.out.println();

        // 拼接结果
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        System.out.println("从左到右逐行读取结果: \"" + result.toString() + "\"");
    }

    private static void printCurrentState(StringBuilder[] rows, int numRows) {
        System.out.println("当前各行状态:");
        for (int i = 0; i < numRows; i++) {
            System.out.println("行 " + (i + 1) + ": " + rows[i].toString());
        }
    }

    private static void printZigzagPattern(StringBuilder[] rows, int numRows) {
        // 计算每行的最大长度，用于对齐显示
        int maxLength = 0;
        for (StringBuilder row : rows) {
            maxLength = Math.max(maxLength, row.length());
        }

        // 打印Z字形图案
        for (int row = 0; row < numRows; row++) {
            StringBuilder line = new StringBuilder();
            String rowContent = rows[row].toString();

            for (int col = 0; col < maxLength; col++) {
                if (col < rowContent.length()) {
                    line.append(rowContent.charAt(col));
                } else {
                    line.append(" ");
                }

                // 在字符之间添加空格以形成Z字形外观
                if (col < maxLength - 1) {
                    line.append(" ");
                }
            }

            System.out.println(line.toString());
        }
    }
}
