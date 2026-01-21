package com.algorithm.leetcode;

import com.algorithm.utils.AlgorithmTest;
import com.algorithm.utils.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode 23. 合并K个升序链表
 *
 * 题目描述：
 * 给你一个链表数组，每个链表都已经按升序排列。请将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 解法：优先队列（最小堆）将每个链表的当前节点放入堆中，每次取出最小节点并推进对应链表。
 */
public class MergeKSortedLists implements AlgorithmTest {

    /**
     * 使用优先队列（最小堆）
     * 时间复杂度：O(N log k)，N为所有节点总数，k为链表数量
     * 空间复杂度：O(k)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        Queue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) pq.offer(node.next);
        }

        return dummy.next;
    }

    public void testInternal() {
        System.out.println("=== LeetCode 23. 合并K个升序链表 ===\n");

        testCase(new int[][]{{1,4,5},{1,3,4},{2,6}}, "[1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6]", "示例1");
        testCase(new int[][]{}, "[]", "空数组");
        testCase(new int[][]{{}}, "[]", "包含空链表");
        testCase(new int[][]{{2},{1}}, "[1 -> 2]", "两个单元素链表");
    }

    private void testCase(int[][] arrays, String expectedStr, String desc) {
        ListNode[] lists = new ListNode[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            lists[i] = ListNode.createFromArray(arrays[i]);
        }
        System.out.println(desc);
        System.out.print(" 输入: ");
        if (lists.length == 0) System.out.println("[]");
        else {
            for (ListNode l : lists) {
                System.out.print(ListNode.toString(l) + " ");
            }
            System.out.println();
        }

        ListNode merged = mergeKLists(lists);
        System.out.println(" 实际输出: " + ListNode.toString(merged));
        System.out.println(" 期望输出: " + expectedStr);
        System.out.println();
    }

    @Override
    public String getAlgorithmName() {
        return "Merge K Sorted Lists";
    }

    @Override
    public String getDifficulty() {
        return "困难";
    }

    @Override
    public void printAlgorithmInfo() {
        System.out.println("=== 算法信息 ===");
        System.out.println("题目：合并K个升序链表 (Merge K Sorted Lists)");
        System.out.println("LeetCode题号: 23");
        System.out.println("难度：" + getDifficulty());
        System.out.println("解法：优先队列（最小堆）");
        System.out.println();
    }

    @Override
    public void test() {
        printAlgorithmInfo();
        testInternal();
    }

    public static void main(String[] args) {
        System.out.println("=== 单独测试：合并K个升序链表 ===\n");
        MergeKSortedLists alg = new MergeKSortedLists();
        alg.test();
    }
}


