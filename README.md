# Java算法练习项目

这是一个Java算法练习项目，包含各种经典算法的实现和练习。

## 项目结构

```
algorithm-practice/
├── src/
│   ├── main/java/com/algorithm/
│   │   ├── Main.java                    # 主类，演示算法使用
│   │   ├── sorting/                     # 排序算法
│   │   │   ├── BubbleSort.java         # 冒泡排序
│   │   │   └── QuickSort.java          # 快速排序
│   │   ├── searching/                   # 搜索算法
│   │   │   └── BinarySearch.java       # 二分搜索
│   │   ├── graph/                       # 图算法
│   │   ├── dynamicprogramming/          # 动态规划
│   │   ├── string/                      # 字符串算法
│   │   ├── math/                        # 数学算法
│   │   ├── datastructures/              # 数据结构
│   │   ├── tree/                        # 树算法
│   │   ├── greedy/                      # 贪心算法
│   │   ├── backtracking/                # 回溯算法
│   │   ├── divideconquer/               # 分治算法
│   │   ├── bitmanipulation/             # 位运算
│   │   ├── leetcode/                    # LeetCode题目
│   │   └── utils/                       # 工具类
│   │       ├── Algorithm.java          # 算法接口
│   │       └── ArrayUtils.java         # 数组工具类
│   └── test/java/com/algorithm/         # 测试代码
├── pom.xml                              # Maven配置文件
└── README.md                           # 项目说明
```

## 算法包说明

### 排序算法 (sorting)
- **BubbleSort**: 冒泡排序 - O(n²)
- **QuickSort**: 快速排序 - O(n log n) 平均情况

### 搜索算法 (searching)
- **BinarySearch**: 二分搜索 - O(log n)

### 图算法 (graph)
- 图的遍历 (DFS/BFS)
- 最短路径算法 (Dijkstra, Bellman-Ford)
- 最小生成树 (Prim, Kruskal)

### 动态规划 (dynamicprogramming)
- 背包问题
- 最长公共子序列
- 编辑距离

### 字符串算法 (string)
- 字符串匹配 (KMP, Rabin-Karp)
- 回文检测

### 数据结构 (datastructures)
- 栈、队列、链表
- 哈希表
- 堆、优先队列

### 其他算法包
- **math**: 数学算法
- **tree**: 树算法
- **greedy**: 贪心算法
- **backtracking**: 回溯算法
- **divideconquer**: 分治算法
- **bitmanipulation**: 位运算算法

## Git仓库配置

本项目同时推送到两个代码托管平台：

- **GitHub**: https://github.com/huangguozhao/algorithm
- **Gitee**: https://gitee.com/huang-guozhao/algorithm

### 推送代码

#### 方法一：自动推送（推荐）
```bash
git push origin master  # 同时推送到GitHub和Gitee
```

#### 方法二：分别推送
```bash
# 使用提供的脚本
./push-to-both.sh    # Linux/Mac
./push-to-both.ps1   # Windows PowerShell

# 或手动推送
git push origin master  # 推送到GitHub
git push gitee master   # 推送到Gitee
```

## 环境要求

- Java 11 或更高版本
- Maven 3.6+

## 编译和运行

### 使用Maven编译
```bash
mvn clean compile
```

### 运行主程序
```bash
mvn exec:java -Dexec.mainClass="com.algorithm.Main"
```

### 打包
```bash
mvn clean package
```

### 运行打包后的JAR
```bash
java -jar target/algorithm-practice-1.0.0.jar
```

### 运行测试
```bash
mvn test
```

## 使用示例

项目中的`Main.java`文件包含了各种算法的使用示例：

```java
// 排序算法示例
int[] array = {64, 34, 25, 12, 22, 11, 90};
BubbleSort bubbleSort = new BubbleSort(array);
bubbleSort.execute();

// 搜索算法示例
BinarySearch binarySearch = new BinarySearch(array, 25);
binarySearch.execute();
```

## 算法接口

所有算法都实现了`Algorithm`接口：

```java
public interface Algorithm {
    String getAlgorithmName();        // 算法名称
    String getDescription();          // 算法描述
    String getTimeComplexity();       // 时间复杂度
    String getSpaceComplexity();      // 空间复杂度
    void execute();                   // 执行算法
}
```

## 工具类

### ArrayUtils
提供数组操作的常用方法：
- `printArray(int[] arr)`: 打印数组
- `swap(int[] arr, int i, int j)`: 交换数组元素
- `copyArray(int[] arr)`: 复制数组
- `generateRandomArray(int size, int min, int max)`: 生成随机数组
- `isSorted(int[] arr)`: 检查数组是否已排序

## 添加新算法

1. 在相应包中创建新的算法类
2. 实现`Algorithm`接口
3. 在`Main.java`中添加演示代码
4. 添加相应的测试类

## 贡献

欢迎提交Pull Request来添加新的算法实现！

## 许可证

MIT License

