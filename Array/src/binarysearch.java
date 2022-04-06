/**
 * @author psj
 * @date 2022/4/6 10:14
 * @File: binarysearch.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/binary-search/
// 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target，
// 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1

public class binarysearch {
    public int binarysearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
