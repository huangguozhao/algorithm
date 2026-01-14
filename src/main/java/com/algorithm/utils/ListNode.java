package com.algorithm.utils;

/**
 * 链表节点类
 * 用于表示单向链表的节点
 */
public class ListNode {
    public int val;        // 节点值
    public ListNode next;  // 指向下一个节点

    // 构造函数
    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 从数组创建链表
     * @param arr 数组，表示链表的值
     * @return 链表头节点
     */
    public static ListNode createFromArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * 将链表转换为字符串表示
     * @param head 链表头节点
     * @return 字符串表示
     */
    public static String toString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * 获取链表长度
     * @param head 链表头节点
     * @return 链表长度
     */
    public static int getLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    /**
     * 复制链表
     * @param head 原链表头节点
     * @return 复制后的链表头节点
     */
    public static ListNode copy(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        ListNode original = head;
        while (original != null) {
            current.next = new ListNode(original.val);
            current = current.next;
            original = original.next;
        }

        return dummy.next;
    }
}

