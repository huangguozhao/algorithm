package com.algorithm.utils;

/**
 * 算法测试接口
 * 定义算法测试的基本方法
 */
public interface AlgorithmTest {

    /**
     * 打印算法信息
     */
    void printAlgorithmInfo();

    /**
     * 运行算法测试
     */
    void test();

    /**
     * 获取算法名称
     */
    String getAlgorithmName();

    /**
     * 获取算法难度
     */
    String getDifficulty();
}
