/**
 * @author psj
 * @date 2022/4/12 9:15
 * @File: searchRange.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
// 给定一个按照升序排列的整数数组nums，和一个目标值target。找出给定目标值在数组中的开始位置和结束位置
// 如果数组中不存在目标值 target，返回 [-1, -1]

public class searchRange {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0 || nums == null) {
            return new int[]{-1, -1};
        }
        int left_bound = left_bound(nums, target);
        int right_bound = right_bound(nums, target);
        return new int[]{left_bound, right_bound};
    }

    // 查找值为target的最左侧元素
    public int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 继续向左半区间查找
                right = mid - 1;
            }
        }
        // 判断left是否越界
        // 1.left不断向右移动，导致left越过数组的右边界
        // 2.right不断向左移动，最后right到-1位置，left没变，此时nums[left] != target
        // 3.left和right都移动，但是移动跳出循环后，nums[left] != target
        // 假设已经找到值为target的元素，由于需要找到最左侧的需要right向左移动，
        // 如果后续没有值target的元素，此不是因为nums[left] != target而返回-1？
        // 因为数组是排序数组，如果有多个值为target的元素则一定是连续的，如[1,2,2,3]
        // target=2时，最后因为left=1 > right=0，所以跳出while循环，但是并不满足下面if条件
        // 所以还是返回left=1
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    public int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 继续向右半区间查找
                left = mid + 1;
            }
        }
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;

    }
}
