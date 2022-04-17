import java.util.Random;

/**
 * @author psj
 * @date 2022/4/17 9:02
 * @File: sortArrayQuick.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/sort-an-array/
// 给你一个整数数组nums，请你将该数组升序排序(快排)

public class sortArrayQuick {
    public int[] sortArray(int[] nums) {
        // 打乱数组，避免出现每次要排序的元素都在数组的末端，导致不平衡，耗时多
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    public void sort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        // 对nums[lo...hi]划分，使得nums[lo...p-1]<=nums[p]<nums[p+1...hi]
        int p = partition(nums, lo, hi);

        // 递归nums[lo...p-1]和nums[p+1...hi]
        sort(nums, lo, p - 1);
        sort(nums, p + 1, hi);
    }

    public int partition(int[] nums, int lo, int hi) {
        // 以每次划分nums的第一个元素为基准
        int pivot = nums[lo];
        // 定义[lo,i)<=pivot,(j,hi]>pivot
        int i = lo + 1, j = hi;
        // 确保[lo,hi]都能被遍历
        while (i <= j) {
            while (i < hi && nums[i] <= pivot) {
                i++;
            }
            while (j > lo && nums[j] > pivot) {
                j--;
            }
            // 此时的i遍历到j后面时，不符合条件
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        // 将基准元素和当前遍历到的位置元素交换
        swap(nums, lo, j);
        return j;

    }

    public void shuffle(int[] nums) {
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            // 生成[i,n-1]之间的随机数
            int r = i + random.nextInt(nums.length - i);
            swap(nums, i, r);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
