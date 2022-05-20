import java.util.Arrays;
import java.util.Map;

/**
 * @author psj
 * @date 2022/5/20 8:48
 * @File: findLengthOfLCIS.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/longest-continuous-increasing-subsequence/
// 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。

public class findLengthOfLCIS {
    // 方法1:动态规划
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int result = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            result = Math.max(result, dp[i]);

        }
        return result;
    }

    // 方法2:遍历
    public int findLengthOfLCIS_traverse(int[] nums) {
        int n = nums.length;
        int result = 1;
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            result = count > result ? count : result;
        }
        return result;
    }
}