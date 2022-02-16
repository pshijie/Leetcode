/**
 * @author psj
 * @date 2022/2/16 21:39
 * @File: coinChange.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/coin-change/
// 给你一个整数数组coins,表示不同面额的硬币;以及一个整数amount,表示总金额。计算并返回可以凑成总金额所需的最少的硬币个数,如果没有任何一种硬币组合能组成总金额，返回-1 。

import java.util.Arrays;

/**
 * 1.要得到最优子结构，需要保证子问题之间是相互独立的
 * 2.进行初始化时最好不要初始化为Integer.MAX_VALUE，因为在该值上再加数(比如执行dp[i-coin]+1)会溢出
 */
public class coinChange {
    // 使用动态规划需要确定以下几点：
    // 假设原问题为凑成11元，可以通过子问题(凑成10元)再加上一个1元硬币即可
    // 1.确定base case：目标金额为0时返回0，目标是金额小于0时无解，返回-1
    // 2.确定状态：即原问题和子问题中变化的量，amount明显会随着硬币数增加而改变，硬币数量因为无限所以不变
    // 3.确定选择：即导致状态变化的行为，所以硬币的面值就是选择
    // 4.明确dp数组：dp[n]表示输入目标金额n，返回最少硬币的数目

    // 方法1：存在重叠子问题，树上的节点数为O(n^k)(k为硬币面额数),每个子问题有一个for循环，所以总时间复杂度为O(k*n^k)
    //                              11
    //                      1/      2|      5\
    //                     10        9        6
    //                  1/ 2| 5\ 1/ 2| 5\  1/2| 5\
    //                  9   8  5 8   7   4 5  4  1
    //                            .......
    public int coinChange_nomemo(int[] coins, int amount) {
        return dp_nomemo(coins, amount);
    }

    public int dp_nomemo(int[] coins, int amount) {
        // base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 计算子问题的结果
            int subProblem = dp_nomemo(coins, amount - coin);
            if (subProblem == -1) {
                continue;
            }
            // 加1表示在子问题的基础上加上当前硬币coin就能凑成原问题
            res = Math.min(res, subProblem + 1);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // 方法2：使用备忘录消除重叠子问题，子问题数目为O(n),n为总金额数，总时间复杂度为O(kn)
    int[] memo;

    public int coinChange_memo(int[] coins, int amount) {
        memo = new int[amount + 1];
        Arrays.fill(memo, -1);
        return dp_memo(coins, amount);
    }

    public int dp_memo(int[] coins, int amount) {
        // base case
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        // 查看备忘录
        if (memo[amount] != -1) {
            return memo[amount];
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 计算子问题的结果
            int subProblem = dp_memo(coins, amount - coin);
            if (subProblem == -1) {
                continue;
            }
            // 加1表示在子问题的基础上加上当前硬币coin就能凑成原问题
            res = Math.min(res, subProblem + 1);
        }

        // 把结果存入备忘录
        memo[amount] = (res == Integer.MAX_VALUE ? -1 : res);
        return memo[amount];
    }

    // 方法3：自底向上消除重叠子问题
    public int coinChange(int[] coins, int amount) {
        // 目标金额为n时，至少需要dp[n]枚硬币凑出
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        // base case
        dp[0] = 0;
        // 状态(amount的值)的所有取值
        for (int i = 0; i < dp.length; i++) {
            // 找出当前状态dp[i]在所有选择下的最小值
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }

        }
        // 如果dp[amount]还是为初始值，说明没有硬币可以组合
        return (dp[amount] == amount + 1) ? -1 : dp[amount];

    }
}
