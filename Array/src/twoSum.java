/**
 * @author psj
 * @date 2022/5/14 8:53
 * @File: twoSum.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
// 给你一个下标从1开始的整数数组numbers，该数组已按非递减顺序排列，请你从数组中找出满足相加之和等于目标数target的两个数

public class twoSum {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int left = 0, right = n - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] < target) {
                left++;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[]{-1, -1};
    }
}
