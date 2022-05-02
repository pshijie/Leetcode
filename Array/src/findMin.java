/**
 * @author psj
 * @date 2022/4/20 8:59
 * @File: findMin.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
// 已知一个长度为n的数组，预先按照升序排列，经由1到n次旋转后，得到输入数组。
// 给你一个元素值互不相同的数组nums，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的最小元素
// 必须设计一个时间复杂度为O(log n)的算法

public class findMin {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 如旋转后的数组为:5 6 7 1 2,此时nums[mid]=7>nums[right]=2
            // 则最小值在右半区域
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            // 如旋转后的数组为:5 6 7 8 9,此时nums[mid]=7<nums[right]=9
            // 或者数组为:7 8 0 1 2 5 6
            // 则最小值在左半区域(这里不是mid-1是因为while循环的条件是小于不是小于等于)
            }else if (nums[mid] < nums[right]){
                right = mid;
            }
            // 因为这里的while循环条件不是left<=right
            // 所以不需要加上nums[mid]=nums[right]
            // (首先数组没有重复元素,如果满足该条件说明mid=right,但是在循环中left始终小于right,计算的mid不可能等于right)
        }
        return nums[left];
    }
}
