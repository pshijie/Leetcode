import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author psj
 * @date 2022/2/16 22:32
 * @File: lengthOfLIS.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/longest-increasing-subsequence/
// 给你一个整数数组nums，找到其中最长严格递增子序列的长度。
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如[3,6,2,7]是数组[0,3,1,6,2,2,7]的子序列。

public class lengthOfLIS {
    // 时间复杂度O(n^2)
    public int lengthOfLIS(int[] nums) {
        // dp[i]表示以nums[i]结尾的最长递增子序列长度
        int[] dp = new int[nums.length];
        // base case:初始化为1，因为如果该数组为递减序列，则最长递增子序列长度为1
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // 如果当前结尾数nums[i]大于nums[j]，则在dp[j]的基础上加1即可得到当前的dp[i]
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 遍历每个以nums[i]为结尾的最长子序列长度，求其中的最大值
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
