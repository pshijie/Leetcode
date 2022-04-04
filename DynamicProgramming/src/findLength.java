/**
 * @author psj
 * @date 2022/4/4 9:28
 * @File: findLength.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
// 给两个整数数组nums1和nums2 ，返回两个数组中公共的、长度最长的子数组的长度

public class findLength {
    // 动态规划
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int result = Integer.MIN_VALUE;
        // dp[i][j]表示num1[0..i]和num2[0..j]的最长子数组长度
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
    }
}
