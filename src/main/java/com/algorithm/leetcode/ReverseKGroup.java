package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ListNode;

/**
 * LeetCode 25. K 个一组翻转链表
 *
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，
 * 那请将最后剩余的节点保持原有顺序。
 *
 * 注：你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class ReverseKGroup implements AlgorithmTest {

    /**
     * 迭代法：使用哑节点，每次翻转k个节点
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        // 创建哑节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 当前组的开始位置
        ListNode groupStart = dummy;

        while (true) {
            // 检查是否有足够的节点进行翻转
            ListNode curr = groupStart;
            for (int i = 0; i < k; i++) {
                curr = curr.next;
                if (curr == null) {
                    // 剩余节点不足k个，返回结果
                    return dummy.next;
                }
            }

            // 翻转当前k个节点
            ListNode prev = groupStart;
            curr = groupStart.next;

            // 翻转k个节点
            for (int i = 0; i < k; i++) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            // 连接翻转后的组
            ListNode temp = groupStart.next;
            groupStart.next = prev;
            temp.next = curr;

            // 移动到下一组的开始位置
            groupStart = temp;
        }
    }

    /**
     * 递归法：递归处理每k个节点
     * 时间复杂度：O(n)
     * 空间复杂度：O(n/k) - 递归栈空间
     */
    public ListNode reverseKGroupRecursive(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        // 检查是否有足够的节点进行翻转
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) {
            curr = curr.next;
            count++;
        }

        // 如果节点数不足k个，返回原链表
        if (count < k) {
            return head;
        }

        // 递归处理剩余的节点
        ListNode newHead = reverseKGroupRecursive(curr, k);

        // 翻转当前k个节点
        ListNode prev = null;
        curr = head;
        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // 将翻转后的组连接到递归处理的结果
        head.next = newHead;

        return prev;
    }


    public void testInternal() {
        System.out.println("=== LeetCode 25. K 个一组翻转链表 ===\n");

        testCase(new int[]{1, 2, 3, 4, 5}, 2, "[2 -> 1 -> 4 -> 3 -> 5]", "示例1：k=2，正常情况");
        testCase(new int[]{1, 2, 3, 4, 5}, 3, "[3 -> 2 -> 1 -> 4 -> 5]", "示例2：k=3，正常情况");
        testCase(new int[]{}, 2, "[]", "空链表");
        testCase(new int[]{1}, 2, "[1]", "单节点链表，k=2");
        testCase(new int[]{1, 2, 3}, 2, "[2 -> 1 -> 3]", "三个节点，k=2");
        testCase(new int[]{1, 2, 3, 4, 5, 6}, 2, "[2 -> 1 -> 4 -> 3 -> 6 -> 5]", "六个节点，k=2");
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7}, 3, "[3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7]", "七个节点，k=3");
        testCase(new int[]{1, 2, 3, 4}, 1, "[1 -> 2 -> 3 -> 4]", "k=1，不翻转");
    }

    private void testCase(int[] input, int k, String expectedStr, String desc) {
        ListNode head = ListNode.createFromArray(input);
        System.out.println(desc);
        System.out.println(" 输入链表: " + ListNode.toString(head));
        System.out.println(" k = " + k);

        // 测试迭代法
        ListNode resultIterative = reverseKGroup(ListNode.copy(head), k);
        System.out.println(" 迭代法输出: " + ListNode.toString(resultIterative));

        // 测试递归法
        ListNode resultRecursive = reverseKGroupRecursive(ListNode.copy(head), k);
        System.out.println(" 递归法输出: " + ListNode.toString(resultRecursive));

        System.out.println(" 期望输出: " + expectedStr);
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Reverse Nodes in k-Group";
    }

    @Override
    public String getDifficulty() {
        return "困难";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：K 个一组翻转链表 (Reverse Nodes in k-Group)");
        System.out.println("LeetCode题号: 25");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：迭代（哑节点）或递归");
        System.out.println("时间复杂度：O(n)");
        System.out.println("空间复杂度：迭代O(1)，递归O(n/k)");
        System.out.println("关键点：检查剩余节点是否足够k个，不足则保持原顺序");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    public static void main(String[] args) {
        System.out.println("=== 单独测试：K 个一组翻转链表 ===\n");
        ReverseKGroup alg = new ReverseKGroup();
        alg.test();
    }
}
