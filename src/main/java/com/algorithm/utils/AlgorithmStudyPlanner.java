package com.algorithm.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 算法学习计划生成器
 * 根据用户当前水平生成个性化学习计划
 */
public class AlgorithmStudyPlanner {

    public enum SkillLevel {
        BEGINNER("初学者", Arrays.asList(
            "数组基础", "字符串基础", "简单数学题", "链表基础"
        )),
        INTERMEDIATE("中级", Arrays.asList(
            "树结构", "动态规划入门", "图论基础", "排序算法"
        )),
        ADVANCED("高级", Arrays.asList(
            "高级DP", "图论进阶", "系统设计", "算法优化"
        ));

        private final String description;
        private final List<String> focusAreas;

        SkillLevel(String description, List<String> focusAreas) {
            this.description = description;
            this.focusAreas = focusAreas;
        }
    }

    private SkillLevel currentLevel;
    private int dailyHours;
    private int weeks;

    public AlgorithmStudyPlanner(SkillLevel level, int dailyHours, int weeks) {
        this.currentLevel = level;
        this.dailyHours = dailyHours;
        this.weeks = weeks;
    }

    /**
     * 生成学习计划
     */
    public void generateStudyPlan() {
        System.out.println("=== 个性化算法学习计划 ===");
        System.out.println("当前水平: " + currentLevel.description);
        System.out.println("每日学习时间: " + dailyHours + " 小时");
        System.out.println("计划周期: " + weeks + " 周");
        System.out.println("专注领域: " + String.join(", ", currentLevel.focusAreas));
        System.out.println();

        LocalDate startDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

        for (int week = 1; week <= weeks; week++) {
            System.out.println("第 " + week + " 周 (" + startDate.format(formatter) + " - " +
                             startDate.plusDays(6).format(formatter) + ")");

            generateWeeklyPlan(week);
            System.out.println();

            startDate = startDate.plusWeeks(1);
        }

        printProgressTracking();
    }

    private void generateWeeklyPlan(int week) {
        Map<String, Integer> dailyTasks = getDailyTasks();

        for (int day = 1; day <= 7; day++) {
            System.out.println("  Day " + day + ":");
            List<String> tasks = generateDailyTasks(dailyTasks, day);
            for (String task : tasks) {
                System.out.println("    • " + task);
            }
        }
    }

    private Map<String, Integer> getDailyTasks() {
        Map<String, Integer> tasks = new HashMap<>();

        switch (currentLevel) {
            case BEGINNER:
                tasks.put("LeetCode Easy题", 3);
                tasks.put("基础概念复习", 1);
                tasks.put("代码实现练习", 2);
                break;
            case INTERMEDIATE:
                tasks.put("LeetCode Medium题", 2);
                tasks.put("算法思维训练", 1);
                tasks.put("错题复习", 1);
                tasks.put("复杂度分析练习", 1);
                break;
            case ADVANCED:
                tasks.put("LeetCode Hard题", 1);
                tasks.put("算法优化", 1);
                tasks.put("系统设计题", 1);
                tasks.put("面试题模拟", 1);
                break;
        }

        return tasks;
    }

    private List<String> generateDailyTasks(Map<String, Integer> dailyTasks, int day) {
        List<String> tasks = new ArrayList<>();

        // 周末增加复习时间
        boolean isWeekend = (day == 6 || day == 7);
        int totalTasks = isWeekend ? dailyTasks.size() + 1 : dailyTasks.size();

        for (Map.Entry<String, Integer> entry : dailyTasks.entrySet()) {
            tasks.add(entry.getKey() + " (" + entry.getValue() + " 道题)");
        }

        if (isWeekend) {
            tasks.add("本周错题复习 (2 道题)");
        }

        return tasks;
    }

    private void printProgressTracking() {
        System.out.println("=== 进度跟踪建议 ===");
        System.out.println("📊 每日记录:");
        System.out.println("  • 完成题数: ___/___");
        System.out.println("  • 遇到难点: ___________");
        System.out.println("  • 学习收获: ___________");
        System.out.println("  • 明日计划: ___________");
        System.out.println();

        System.out.println("📈 周度评估:");
        System.out.println("  • 掌握程度: 0-10分");
        System.out.println("  • 薄弱环节: ___________");
        System.out.println("  • 改进方向: ___________");
        System.out.println("  • 下周重点: ___________");
        System.out.println();

        System.out.println("🎯 学习目标:");
        switch (currentLevel) {
            case BEGINNER:
                System.out.println("  • 掌握基本数据结构操作");
                System.out.println("  • 理解常见算法思想");
                System.out.println("  • 能够独立解决Easy题");
                break;
            case INTERMEDIATE:
                System.out.println("  • 熟练使用多种解题方法");
                System.out.println("  • 掌握时间空间复杂度分析");
                System.out.println("  • 稳定解决Medium题");
                break;
            case ADVANCED:
                System.out.println("  • 深入理解算法原理");
                System.out.println("  • 能够优化算法性能");
                System.out.println("  • 应对Hard题和系统设计题");
                break;
        }
    }

    /**
     * 生成专项练习计划
     */
    public void generateTopicPlan(String topic) {
        System.out.println("=== " + topic + "专项练习计划 ===");

        List<String> problems = getProblemsByTopic(topic);

        for (int i = 0; i < problems.size(); i++) {
            int day = i + 1;
            System.out.println("Day " + day + ": " + problems.get(i));
        }

        System.out.println("\n💡 练习建议:");
        System.out.println("  • 先独立思考 15-20 分钟");
        System.out.println("  • 查看多种解法思路");
        System.out.println("  • 手写代码实现");
        System.out.println("  • 分析时间空间复杂度");
        System.out.println("  • 记录解题心得和技巧");
    }

    private List<String> getProblemsByTopic(String topic) {
        Map<String, List<String>> topicProblems = new HashMap<>();

        topicProblems.put("数组", Arrays.asList(
            "283. 移动零", "1. 两数之和", "15. 三数之和",
            "11. 盛最多水的容器", "42. 接雨水", "238. 除自身以外数组的乘积"
        ));

        topicProblems.put("字符串", Arrays.asList(
            "3. 无重复字符的最长子串", "5. 最长回文子串", "20. 有效的括号",
            "49. 字母异位词分组", "76. 最小覆盖子串", "151. 翻转字符串里的单词"
        ));

        topicProblems.put("链表", Arrays.asList(
            "206. 反转链表", "21. 合并两个有序链表", "141. 环形链表",
            "142. 环形链表 II", "23. 合并K个升序链表", "25. K 个一组翻转链表"
        ));

        topicProblems.put("树", Arrays.asList(
            "104. 二叉树的最大深度", "101. 对称二叉树", "102. 二叉树的层序遍历",
            "236. 二叉树的最近公共祖先", "124. 二叉树中的最大路径和", "297. 二叉树的序列化与反序列化"
        ));

        topicProblems.put("动态规划", Arrays.asList(
            "70. 爬楼梯", "53. 最大子序和", "121. 买卖股票的最佳时机",
            "198. 打家劫舍", "322. 零钱兑换", "72. 编辑距离"
        ));

        return topicProblems.getOrDefault(topic, Arrays.asList("请指定有效的专题"));
    }

    public static void main(String[] args) {
        System.out.println("=== 算法学习计划生成器 ===");
        System.out.println();

        // 示例：为中级水平生成 8 周学习计划
        AlgorithmStudyPlanner planner = new AlgorithmStudyPlanner(
            SkillLevel.INTERMEDIATE, 2, 8
        );

        planner.generateStudyPlan();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("数组专项练习计划:");
        planner.generateTopicPlan("数组");
    }
}
