/**
 * @author psj
 * @date 2022/4/26 9:00
 * @File: numSquares.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/perfect-squares/
// 给你一个整数n ，返回和为n的完全平方数的最少数量。1、4、9这样的数就是完全平方数

public class numSquares {
    // 动态规划
    // 背包问题:完全平方数就是物品（可以无限件使用），凑个正整数n就是背包，问凑满这个背包最少有多少物品
    public int numSquares(int n) {
        // dp[i]：表示最少需要dp[i]个数的平方表示整数i
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = min + 1;  // 加1表示选择了min对应的j为其中一个完全平方数
        }
        return dp[n];
    }

    // 方法2:
}
