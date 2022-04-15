/**
 * @author psj
 * @date 2022/4/15 9:40
 * @File: reversePairs.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
// 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数

public class reversePairs {
    // 归并排序的思想。假如数组为[6,5,1,2]中6能构成的逆序对，可以知道为(6,5)、(6,1)、(6,2)
    // 如果将[5,1,2]进行排序为[1,2,5]后，6能构成的逆序对还是原来的三对，所以在求出[5,1,2]的所有逆序对的前提下将其排序是不会改变值的
    // 而排序的原因是因为利用归并排序思路会排序数组，但是其中的分治能加快计算

    int result = 0;

    public int reversePairs(int[] nums) {
        merge(nums, 0, nums.length - 1);
        return result;
    }

    public void merge(int[] nums, int left, int right) {
        int mid = left + (right - left) / 2;
        if (left < right) {
            merge(nums, left, mid);
            merge(nums, mid + 1, right);
            mergeSort(nums, left, mid, right);
        }
    }

    public void mergeSort(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int index = 0;  // temp数组的下标
        int temp1 = left, temp2 = mid + 1;
        while (temp1 <= mid && temp2 <= right) {
            if (nums[temp1] <= nums[temp2]) {
                temp[index] = nums[temp1];
                index++;
                temp1++;
            } else {
                // 统计逆序对
                // 执行到该步时说明左半数组中temp1的元素大于右半数组中temp2的元素
                // 注意两个数组此时是排好序的状态，所以左半数组temp1位置后的值肯定也h大于右半数组中temp2的元素
                result += (mid - temp1 + 1);
                temp[index] = nums[temp2];
                index++;
                temp2++;
            }
        }
        // 将左半部分剩余部分加入到temp数组中
        while (temp1 <= mid) {
            temp[index] = nums[temp1];
            index++;
            temp1++;
        }
        // 将右半部分剩余部分加入到temp数组中
        while (temp2 <= right) {
            temp[index] = nums[temp2];
            index++;
            temp2++;
        }
        // 将temp数组覆盖原数组,注意是覆盖当前排好序的部分，即nums[left,...right]
        for (int i = 0; i < temp.length; i++) {
            nums[i + left] = temp[i];
        }
    }
}
