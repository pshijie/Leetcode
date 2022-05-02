/**
 * @author psj
 * @date 2022/5/2 9:37
 * @File: sortedSquares.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/squares-of-a-sorted-array/
//给你一个按非递减顺序排序的整数数组nums，返回每个数字的平方组成的新数组，要求也按非递减顺序排序

public class sortedSquares {
    // 双指针：原数组平方后的最大值只可能由原数组最左边(可能是很小的负数，平方后很大)或者最右边产生
    public int[] sortedSquares(int[] nums) {
        // 左指针指向原数组的最左边
        int left = 0;
        // 右指针指向原数组的最右边
        int right = nums.length - 1;

        int[] result = new int[nums.length];
        // 指向result的指针，表示当前写入result数组的位置
        // 之所以从result最后一个数组写是因为该位置存储最大值
        int index = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                result[index] = nums[left] * nums[left];
                left++;
                index--;
            } else {
                result[index] = nums[right] * nums[right];
                right--;
                index--;
            }
        }
        return result;
    }
}
