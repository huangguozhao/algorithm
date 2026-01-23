package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ListNode;

/**
 * LeetCode 24. 两两交换链表中的节点
 *
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 */
public class SwapNodesInPairs implements AlgorithmTest {

    /**
     * 迭代法：使用哑节点，每次交换一对节点
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode swapPairs(ListNode head) {
        // 创建哑节点，指向头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 当前节点的前一个节点
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            // 当前要交换的两个节点
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // 交换节点：prev -> second -> first -> ...
            prev.next = second;
            first.next = second.next;
            second.next = first;

            // 更新prev指针，移动到下一对节点的前面
            prev = first;
        }

        return dummy.next;
    }

    /**
     * 递归法：递归处理剩余的节点
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) - 递归栈空间
     */
    public ListNode swapPairsRecursive(ListNode head) {
        // 递归终止条件：节点数少于2个
        if (head == null || head.next == null) {
            return head;
        }

        // 下一对节点的头节点
        ListNode nextPair = head.next.next;

        // 交换当前两个节点
        ListNode newHead = head.next;
        newHead.next = head;

        // 递归处理剩余节点，并连接到当前交换后的节点
        head.next = swapPairsRecursive(nextPair);

        return newHead;
    }

    public void testInternal() {
        System.out.println("=== LeetCode 24. 两两交换链表中的节点 ===\n");

        testCase(new int[]{1, 2, 3, 4}, "[2 -> 1 -> 4 -> 3]", "示例1：正常情况");
        testCase(new int[]{}, "[]", "空链表");
        testCase(new int[]{1}, "[1]", "单节点链表");
        testCase(new int[]{1, 2, 3}, "[2 -> 1 -> 3]", "奇数个节点");
        testCase(new int[]{1, 2, 3, 4, 5, 6}, "[2 -> 1 -> 4 -> 3 -> 6 -> 5]", "六个节点");
    }

    private void testCase(int[] input, String expectedStr, String desc) {
        ListNode head = ListNode.createFromArray(input);
        System.out.println(desc);
        System.out.println(" 输入链表: " + ListNode.toString(head));

        // 测试迭代法
        ListNode resultIterative = swapPairs(ListNode.copy(head));
        System.out.println(" 迭代法输出: " + ListNode.toString(resultIterative));

        // 测试递归法
        ListNode resultRecursive = swapPairsRecursive(ListNode.copy(head));
        System.out.println(" 递归法输出: " + ListNode.toString(resultRecursive));

        System.out.println(" 期望输出: " + expectedStr);
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Swap Nodes in Pairs";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：两两交换链表中的节点 (Swap Nodes in Pairs)");
        System.out.println("LeetCode题号: 24");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：迭代（哑节点）或递归");
        System.out.println("时间复杂度：O(n)");
        System.out.println("空间复杂度：迭代O(1)，递归O(n)");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    public static void main(String[] args) {
        System.out.println("=== 单独测试：两两交换链表中的节点 ===\n");
        SwapNodesInPairs alg = new SwapNodesInPairs();
        alg.test();
    }
}
