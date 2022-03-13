import java.util.PriorityQueue;

/**
 * @author psj
 * @date 2022/3/13 10:01
 * @File: findKthLargest.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
// 给定整数数组nums和整数 k，请返回数组中第k个最大的元素。
// 请注意你需要找的是数组排序后的第k个最大的元素，而不是第k个不同的元素

public class findKthLargest {
    // 方法1：使用小顶堆，时间复杂度为O(N*logk)(删除和添加元素的复杂度为O(logk))，空间复杂度为O(k)
    public int findKthLargest_heap(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int e : nums) {
            pq.offer(e);
            // 当堆中元素多于k个时，就删除堆顶元素
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // 此时堆中就剩下nums中k个大的元素，而堆顶就是这k个元素中最小的一个，也就是第k大的元素
        return pq.peek();
    }

    // 方法2：利用快排
    public int findKthLargest(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        // 找第k个元素实际就是找nums排序后nums[length-k]
        k = nums.length - k;
        while (low <= high) {
            int p = partition(nums, low, high);
            if (p < k) {
                // 第k大的元素在nums[p+1..high]中
                low = p + 1;
            } else if (p > k) {
                // 第k大的元素在nums[low..p-1]中
                high = p - 1;
            } else {
                return nums[p];
            }
        }
        return -1;
    }

    public void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        int p = partition(nums, low, high);
        sort(nums, 0, p - 1);
        sort(nums, p + 1, high);
    }

    // 找出分界点（每执行一次就确定一个元素的最终放置位置，即确定是第几大的元素）
    public int partition(int[] nums, int low, int high) {
        if (low == high) {
            return low;
        }

        int pivot = nums[low];
        // j = high + 1是因为while循环中会先执行--操作
        int i = low, j = high + 1;
        while (true) {
            // 保证nums[lo..i]都小于pivot
            while (nums[++i] < pivot) {
                if (i == high) {
                    break;
                }
            }
            // 保证nums[j..hi]都大于pivot
            while (nums[--j] > pivot) {
                if (j == low) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            // 执行到该步说明nums[i]>pivot && nums[j]<pivot,需要将它们进行交换
            swap(nums, i, j);
        }

        // 将pivot交换到正确的位置
        swap(nums, j, low);
        // 此时nums[lo..j-1]<nums[j]<nums[j+1..hi]
        return j;


    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
