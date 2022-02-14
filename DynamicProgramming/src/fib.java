/**
 * @author psj
 * @date 2022/2/14 20:51
 * @File: fib.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/fibonacci-number/
// 斐波那契数(通常用F(n)表示)形成的序列称为斐波那契数列。该数列由0和1开始，后面的每一项数字都是前面两项数字的和
// 该问题可以视为一颗递归树，假设n=10：
//                       f(10)
//                      /     \
//                    f(9)    f(8)
//                   /   \    /   \
//                 f(8) f(7) f(7) f(6)
//                        .....
//                 /  \      /  \
//               f(1) f(2) f(1) f(2) .....
//  从上图可以看出，f(8)、f(7)等都被计算了两次(重叠子问题)，所以子问题个数为O(2^n)，每个问题是一个加分，时间复杂度为O(1)，所以总的时间复杂度为O(2^n)
//  使用备忘录后，相当于完成了剪枝，剩余的节点树为O(n)，即子问题个数为O(n)，时间复杂度为O(n)


/**
 * tips:
 * 本题的递归树是自顶向下，而动态规划是自底向上
 */
public class fib {
    // 不带备忘录：会有重叠子问题。时间复杂度O(2^n)
    public int fib_noTrick(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib_noTrick(n - 1) + fib_noTrick(n - 2);
    }

    // 带备忘录：遇到子问题先去备忘录查看是否有记录，有的话可以直接使用
    public int fib_memo(int n) {
        // 备忘录初始化为0
        int[] memo = new int[n + 1];
        return helper(memo, n);

    }

    public int helper(int[] memo, int n) {
        // base case
        if (n == 0 || n == 1) {
            return n;
        }
        // 已经在备忘录中，直接使用。时间复杂度O(n)
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }

    // 自底向上
    public int fib_bottomtop(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        // base case
        dp[0] = 0;
        dp[1] = 1;
        // 状态转移
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // 简化备忘录：当前状态只和之前两个状态有关，所以不需要dp数组所有的值，优化空间复杂度为O(1)
    public int fib_mindp(int n) {
        // base case
        if (n == 0 || n == 1) {
            return n;
        }
        // 表示dp[i-1]和dp[i-2]
        int dp_1 = 1, dp_2 = 0;
        for (int i = 2; i <= n; i++) {
            int dp = dp_1 + dp_2;
            // 更新dp[i-1]和dp[i-2]
            dp_2 = dp_1;
            dp_1 = dp;
        }
        return dp_1;
    }

}
