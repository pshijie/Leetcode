/**
 * @author psj
 * @date 2022/3/6 10:07
 * @File: robII.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/house-robber-ii/

public class robII {
    // 因为第一间房间和最后一间房间不能同时被抢，所以可以分为以下三种情况
    // 选择抢nums[i..j]上的房子
    // 情况1:
    //      2 3 4 5 6 8 5
    //      i         j
    // 情况1:
    //      2 3 4 5 6 8 5
    //        i         j
    // 情况3:这种情况是上面情况的子集，肯定不会大于情况1和2
    //      2 3 4 5 6 8 5
    //        i       j

    // 方法1：一维数组dp
    public int rob_oneDim(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // dp[i]表示从第1间到第i+1间房子最多能抢到的钱
        int[] dp_1 = new int[n + 1];
        int[] dp_2 = new int[n + 1];
        dp_1[1] = nums[0];
        dp_2[2] = nums[1];  // 这种情况跳过了nums[0]
        // 到第i家有两种情况：没偷第i-1家和偷了第i-2家
        for (int i = 2; i <= n - 1; i++) {  // 通过控制下标来控制需要计算的范围
            dp_1[i] = Math.max(dp_1[i - 1], dp_1[i - 2] + nums[i - 1]);
        }
        for (int i = 3; i <= n; i++) {
            dp_2[i] = Math.max(dp_2[i - 1], dp_2[i - 2] + nums[i - 1]);
        }

        return Math.max(dp_1[n - 1], dp_2[n]);
    }

    // 方法2：变量dp
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        return Math.max(robRange(nums, 0, n - 2),
                robRange(nums, 1, n - 1));
    }

    // 计算nums[start..end]之间的最优结果
    public int robRange(int[] nums, int start, int end) {
        int dp_i_1 = 0, dp_i_2 = 0;
        int dp_i = 0;
        // dp[i]表示从第i家开始抢最优解
        // dp[i]的最优解dp[i+1]和dp[i+2]决定(自顶向下)
        for (int i = end; i >= start; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}
