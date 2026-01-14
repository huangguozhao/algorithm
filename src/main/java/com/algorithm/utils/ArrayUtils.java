package com.algorithm.utils;

/**
 * 数组工具类
 * 提供数组操作的常用方法
 */
public class ArrayUtils {

    /**
     * 打印数组
     */
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * 打印二维数组
     */
    public static void printArray(int[][] arr) {
        System.out.println("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("  ");
            printArray(arr[i]);
            if (i < arr.length - 1) {
                System.out.println(",");
            }
        }
        System.out.println("]");
    }

    /**
     * 交换数组中的两个元素
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 复制数组
     */
    public static int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        return copy;
    }

    /**
     * 生成随机数组
     */
    public static int[] generateRandomArray(int size, int min, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * (max - min + 1)) + min;
        }
        return arr;
    }

    /**
     * 检查数组是否已排序
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}

