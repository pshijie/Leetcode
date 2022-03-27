/**
 * @author psj
 * @date 2022/3/27 9:24
 * @File: findPeakElement.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/find-peak-element/
// 给你一个整数数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可
// 峰值元素是指其值严格大于左右相邻值的元素
public class findPeakElement {
    // 方法1:直接寻找最大值
    public int findPeakElement_max(int[] nums) {
        int max_index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[max_index]) {
                max_index = i;
            }
        }
        return max_index;
    }

    // 方法2：二分查找
    // 因为num[-1]=nums[n]=负无穷,
    // 所以只要数组中存在一个元素比相邻元素大，那么沿着它一定可以找到一个峰值(即使找到边界，因为上述原因，边界值一定是峰值)
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        for (; left < right; ) {
            int mid = left + (right - left) / 2;
            //  当中间位置左侧的值大于右侧，所以沿着左边一定能找到峰值
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            //  当中间位置左侧的值小于右侧，所以沿着右边一定能找到峰值
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
