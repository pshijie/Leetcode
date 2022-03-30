/**
 * @author psj
 * @date 2022/3/30 9:54
 * @File: minSubArrayLen.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/minimum-size-subarray-sum/
// 给定一个含有 n 个正整数的数组和一个正整数 target 。
// 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0

public class minSubArrayLen {
    // 方法1：暴力法
    public int minSubArrayLen_force(int target, int[] nums) {
        int n = nums.length;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = nums[i];
            if (sum >= target) {
                return 1;
            }
            for (int j = i + 1; j < n; j++) {
                sum += nums[j];
                if (sum >= target) {
                    result = Math.min(result, j - i + 1);
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    // 方法2：双指针
    public int minSubArrayLen_two(int target, int[] nums) {
        int l = 0, r = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;

        while (r < nums.length) {
            // 将右指针向右移动，即扩大窗口
            sum += nums[r];
            r++;
            // 当累加和大于等于目标值时，就开始将左指针右移，即缩小窗口
            // 左指针右移相当于减少窗口中的和，检查不断减少后和与target的关系
            while (sum >= target) {
                result = Math.min(result, r - l);
                sum -= nums[l];
                l++;
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
