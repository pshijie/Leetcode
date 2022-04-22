/**
 * @author psj
 * @date 2022/4/22 9:26
 * @File: findNumberOfLIS.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/
// 给定一个未排序的整数数组nums，返回最长递增子序列的个数 。
// 注意这个数列必须是严格递增的

public class findNumberOfLIS {
    // 注意:可以有多个不同的最长子序列,只要长度都为最长的即可
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]表示以nums[i]结尾的最长上升子序列的长度
        int[] dp = new int[n];
        // cnt[i]表示以nums[i]结尾的最长上升子序列的个数
        int[] cnt = new int[n];
        // 记录nums的最长上升子序列的长度
        int maxLen = 0;
        // 最后的结果为所有满足dp[i]=maxLen的i对于的cnt[i]之和
        int result = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        // 原本以j为结尾的最长子序列个数为num,
                        // 现在在该最长子序列基础上假设nums[i],最长子序列个数没变,长度变了
                        cnt[i] = cnt[j];
                        // 假设nums=[1,3,5,7],之前nums[j]=3,nums[i]=7时已经将dp[i]=4
                        // 此时nums[j]=5,满足dp[j]+1==dp[i],但是由于遍历的下标j不一样,所以将最长序列个数进行累加
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            // 更新最长序列的长度和数量
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                result = cnt[i];
            } else if (dp[i] == maxLen) {
                result += cnt[i];
            }
        }
        return result;
    }
}
