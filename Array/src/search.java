/**
 * @author psj
 * @date 2022/3/15 9:05
 * @File: search.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/search-in-rotated-sorted-array/

public class search {
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 先根据 nums[mid] 与 nums[lo] 的关系判断 mid 是在左段还是右段
            if (nums[mid] >= nums[left]) {
                // mid在左半段，说明[left,mid]是有序的，利用二分法判断target的位置
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {  // mid在有半段，说明[mid,right]是有序的
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

        }
        return -1;
    }
}
