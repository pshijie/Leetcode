import java.util.Arrays;

/**
 * @author psj
 * @date 2022/3/6 9:43
 * @File: rob.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/house-robber/

public class rob {
    // 方法1：暴力(超时)
    public int rob_force(int[] nums) {
        return dp_force(nums, 0);
    }

    // 返回nums[i,..]能抢到的最大值
    public int dp_force(int[] nums, int i) {
        if (i >= nums.length) {
            return 0;
        }
        // 即分为不抢和抢当前家，不抢的话就去下一家判断，抢的话就去下下家判断
        int res = Math.max(dp_force(nums, i + 1), nums[i] + dp_force(nums, i + 2));

        return res;
    }


    // 方法2：备忘录
    int[] memo;

    public int rob_memo(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp_memo(nums, 0);
    }

    public int dp_memo(int[] nums, int i) {
        if (i >= nums.length) {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        int res = Math.max(dp_memo(nums, i + 1), nums[i] + dp_memo(nums, i + 2));
        memo[i] = res;  // 记入备忘录
        return res;
    }

    // 方法3:自顶向下
    public int rob_twoDim(int[] nums) {
        int n = nums.length;
        // dp[i]表示从第1间到第i+1间房子最多能抢到的钱
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i < n + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }

        return dp[n];
    }

}
