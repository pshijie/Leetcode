/**
 * @author psj
 * @date 2022/3/14 9:38
 * @File: subarraySum.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/subarray-sum-equals-k/
// 给你一个整数数组nums和一个整数 k，请你统计并返回该数组中和为k的连续子数组的个数

public class subarraySum {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // 记录nums[0...i-1]的前缀和
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // 计算子数组nums[j..i-1]的元素和
                if (preSum[i] - preSum[j] == k) {
                    result++;
                }
            }
        }
        return result;

    }
}
