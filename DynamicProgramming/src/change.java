/**
 * @author psj
 * @date 2022/2/26 10:40
 * @File: change.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/coin-change-2/
// 给你一个整数数组coins表示不同面额的硬币，另给一个整数amount表示总金额
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0

public class change {
    public int change_org(int amount, int[] coins) {

        int n = coins.length;
        // dp[i][j]表示使用前i个面额的硬币凑成总金额j的组合数
        int[][] dp = new int[n + 1][amount + 1];

        // base case
        // 使用0个币组合为0元存在一种方式
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            // 当前考虑的硬币面额
            int temp = coins[i - 1];
            for (int j = 0; j <= amount; j++) {
                // 每隔面额的硬币可以使用多枚
                for (int k = 0; k * temp <= j; k++) {
                    // 分为使用coins[i]和不使用coins[i]，如果使用了分为使用多少枚
                    // 已经包含了不使用的情况，即k=0时
                    dp[i][j] += dp[i - 1][j - k * temp];
                }
            }
        }

        return dp[n][amount];
    }

    public int change(int amount, int[] coins) {

        int n = coins.length;
        // dp[i][j]表示使用前i个面额的硬币凑成总金额j的组合数
        int[][] dp = new int[n + 1][amount + 1];

        // base case
        // 使用多个个币组合为0元存在一种方式
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            // 当前考虑的硬币面额
            int temp = coins[i - 1];
            for (int j = 1; j <= amount; j++) {
                if (j - temp >= 0) {
                    // 还是和第一种方法思路一致，只不过进行了化简，减少一个for循环
                    // dp[i][j] = dp[i-1][j] + dp[i-1][j-temp] + dp[i-1][j-2*temp] + ...
                    // dp[i][j-temp] = dp[i-1][j-temp] + dp[i-1][j-2*temp] + dp[i-1][j-3*temp] + ...
                    // dp[i][j] = dp[i-1][j] + dp[i][j-temp]
                    dp[i][j] = dp[i - 1][j] + dp[i][j - temp];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }

            return dp[n][amount];
        }

        return dp[n][amount];
    }
}
