/**
 * @author psj
 * @date 2022/2/24 10:30
 * @File: maxSubArray.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/maximum-subarray/
// 给你一个整数数组nums,请你找出一个具有最大和的连续子数组(子数组最少包含一个元素)，返回其最大和.子数组是数组中的一个连续部分。

public class maxSubArray {
    // 方法1：使用dp数组，时间复杂度O(N)，空间复杂度O(N)
    public int maxSubArray_1(int[] nums) {
        int n = nums.length;
        // 如果数组长度为0
        if (n == 0) {
            return 0;
        }

        // dp[i]表示以nums[i]结尾的最大子数组和
        int[] dp = new int[n];

        // base case
        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            // ***一种情况是nums[i]单独作为一个子数组，另一种情况是将nums[i]作为前面子数组的一部分***
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    // 方法2：使用变量,空间复杂度为O(1)
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // 如果数组长度为0
        if (n == 0) {
            return 0;
        }

        // base case
        // ***dp[i]只和dp[i-1]有关系，所以只使用两个变量即可***
        int dp_0 = nums[0];  // 相当于dp[i - 1]
        int dp_1 = 0;
        int res = dp_0;

        for (int i = 1; i < n; i++) {
            dp_1 = Math.max(nums[i], dp_0 + nums[i]);
            dp_0 = dp_1;
            res = Math.max(res, dp_1);
        }

        return res;
    }
}
