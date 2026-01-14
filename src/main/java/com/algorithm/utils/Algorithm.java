package com.algorithm.utils;

/**
 * 算法接口
 * 定义算法的基本操作
 */
public interface Algorithm {

    /**
     * 获取算法名称
     */
    String getAlgorithmName();

    /**
     * 获取算法描述
     */
    String getDescription();

    /**
     * 获取时间复杂度
     */
    String getTimeComplexity();

    /**
     * 获取空间复杂度
     */
    String getSpaceComplexity();

    /**
     * 执行算法
     */
    void execute();
}

