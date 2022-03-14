/**
 * @author psj
 * @date 2022/3/14 8:59
 * @File: NumArray.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/range-sum-query-immutable/
// 给定一个整数数组nums，处理以下类型的多个查询:
//      计算索引left和right(包含left和right)之间的nums元素的和，其中left <= right
public class NumArray {
    // 只要做减法操作，所以时间复杂度O(1)
    // 前缀和数组,preSum[i]记录nums[0...i-1]的累加和
    private int[] preSum;

    public NumArray(int[] nums) {
        preSum = new int[nums.length + 1];
        // 默认preSum[0]=0
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int left, int right) {
        return preSum[right + 1] - preSum[left];
    }
}
