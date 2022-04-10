import java.util.Arrays;

/**
 * @author psj
 * @date 2022/4/10 9:49
 * @File: threeSumClosest.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/3sum-closest/
// 给你一个长度为n的整数数组nums和一个目标值target。请你从nums中选出三个整数，使它们的和与target最接近。返回这三个数的和。
// 假定每组输入只存在恰好一个解

public class threeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        // 先将数组进行排序
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        // 固定一个元素，然后使用双指针对后续元素进行遍历
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                // 存在三个元素的和离target更近就更新
                if (Math.abs(target - sum) < Math.abs(target - result)) {
                    result = sum;
                }
                if (sum > target) {
                    end--;
                } else if (sum < target) {
                    start++;
                } else {
                    return result;
                }
            }
        }
        return result;
    }

}
