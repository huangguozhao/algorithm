package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ListNode;

/**
 * LeetCode 19. 删除链表的倒数第 N 个节点
 *
 * 题目描述：
 * 给你一个链表，删除链表的倒数第 n 个节点，并且返回链表的头节点。
 *
 * 解法：双指针（快慢指针）只遍历一次
 */
public class RemoveNthFromEnd implements AlgorithmTest {

    /**
     * 使用哑节点和双指针，先让快指针走 n 步，
     * 然后快慢同时走，直到快到达末尾，慢的下一个就是要删除的节点。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast先走n步
        for (int i = 0; i < n; i++) {
            if (fast.next != null) fast = fast.next;
            else return head; // n超出长度时不修改
        }

        // 同步走，直到fast到末尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除slow.next
        ListNode toDelete = slow.next;
        if (toDelete != null) {
            slow.next = toDelete.next;
            toDelete.next = null;
        }

        return dummy.next;
    }

    public void testInternal() {
        System.out.println("=== LeetCode 19. 删除链表的倒数第 N 个节点 ===\n");

        testCase(new int[]{1,2,3,4,5}, 2, "[1 -> 2 -> 3 -> 5]", "删除倒数第2个（中间节点）");
        testCase(new int[]{1}, 1, "[]", "单节点删除");
        testCase(new int[]{1,2}, 1, "[1]", "删除尾节点");
        testCase(new int[]{1,2}, 2, "[2]", "删除头节点");
        testCase(new int[]{1,2,3}, 3, "[2 -> 3]", "删除头节点（n等于长度）");
    }

    private void testCase(int[] arr, int n, String expectedStr, String desc) {
        ListNode head = ListNode.createFromArray(arr);
        System.out.println(desc);
        System.out.println(" 输入: " + ListNode.toString(head) + ", n=" + n);
        ListNode res = removeNthFromEnd(head, n);
        System.out.println(" 实际输出: " + ListNode.toString(res));
        System.out.println(" 期望输出: " + expectedStr);
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Remove Nth Node From End of List";
    }

    @Override
    public String getDifficulty() {
        return "中等";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：删除链表的倒数第 N 个节点 (Remove Nth From End of List)");
        System.out.println("LeetCode题号: 19");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：快慢指针（双指针），一次遍历完成");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    public static void main(String[] args) {
        System.out.println("=== 单独测试：删除链表的倒数第 N 个节点 ===\n");
        RemoveNthFromEnd alg = new RemoveNthFromEnd();
        alg.test();
    }
}


