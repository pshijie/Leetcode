import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author psj
 * @date 2022/3/20 9:13
 * @File: sortArray.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/sort-an-array/
// 给你一个整数数组nums，请你将该数组升序排列

public class sortArray {
    public int[] sortArray(int[] nums) {
        Merge merge = new Merge();
        merge.sort(nums);
        return nums;
    }


}

// 归并排序的具体实现
class Merge {
    // 用于辅助合并有序数组
    public static int[] temp;

    public static void sort(int[] nums) {
        // 给辅助数组开辟空间
        temp = new int[nums.length];
        // 对整个数组进行排序
        sort(nums, 0, nums.length - 1);
    }

    // 将数组nums[lo...hi]进行排序
    public static void sort(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        // 防止溢出
        int mid = lo + (hi - lo) / 2;

        // 对左半数组进行排序
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);

    }

    // 将nums[lo..mid]和mid[mid+1..hi]合并为有序数组
    public static void merge(int[] nums, int lo, int mid, int hi) {
        // 先把 nums[lo..hi] 复制到辅助数组中
        // 以便合并后的结果能够直接存入nums
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        // 使用双指针
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            // 左半部分已经合并好了,之后每次for循环都会执行这一步把右半部分合并
            if (i == mid + 1) {
                nums[k] = temp[j++];
                // 左半部分已经合并好了，,之后每次for循环都会执行这一步把左半部分合并
            } else if (j == hi + 1) {
                nums[k] = temp[i++];
                // 左半部分和右半部分都还有元素可以进行比较
            } else if (temp[i] > temp[j]) {
                nums[k] = temp[j++];
            } else {
                nums[k] = temp[i++];
            }
        }
    }

}