package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ListNode;

/**
 * LeetCode 21. 合并两个有序链表
 *
 * 将两个升序链表合并为一个新的升序链表并返回。
 */
public class MergeTwoSortedLists implements AlgorithmTest {

    /**
     * 迭代法：使用哑节点连接两条链表较小的节点
     * 时间复杂度：O(m+n)
     * 空间复杂度：O(1)
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // 连接剩余节点
        if (l1 != null) tail.next = l1;
        else tail.next = l2;

        return dummy.next;
    }

    /**
     * 递归法（可选）：返回合并后的头节点
     */
    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }

    public void testInternal() {
        System.out.println("=== LeetCode 21. 合并两个有序链表 ===\n");

        testCase(new int[]{1,2,4}, new int[]{1,3,4}, "[1 -> 1 -> 2 -> 3 -> 4 -> 4]", "示例1");
        testCase(new int[]{}, new int[]{}, "[]", "两个空链表");
        testCase(new int[]{}, new int[]{0}, "[0]", "一个空链表");
        testCase(new int[]{2,5,7}, new int[]{1,3,4,6}, "[1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7]", "不同长度链表");
    }

    private void testCase(int[] a1, int[] a2, String expectedStr, String desc) {
        ListNode l1 = ListNode.createFromArray(a1);
        ListNode l2 = ListNode.createFromArray(a2);
        System.out.println(desc);
        System.out.println(" 输入 l1: " + ListNode.toString(l1));
        System.out.println(" 输入 l2: " + ListNode.toString(l2));

        ListNode merged = mergeTwoLists(l1, l2);
        System.out.println(" 实际输出: " + ListNode.toString(merged));
        System.out.println(" 期望输出: " + expectedStr);
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Merge Two Sorted Lists";
    }

    @Override
    public String getDifficulty() {
        return "简单";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：合并两个有序链表 (Merge Two Sorted Lists)");
        System.out.println("LeetCode题号: 21");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：迭代（哑节点）或递归");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    public static void main(String[] args) {
        System.out.println("=== 单独测试：合并两个有序链表 ===\n");
        MergeTwoSortedLists alg = new MergeTwoSortedLists();
        alg.test();
    }
}


