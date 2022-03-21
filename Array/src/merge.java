import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author psj
 * @date 2022/3/21 8:54
 * @File: merge.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/merge-sorted-array/solution/
// 给你两个按非递减顺序排列的整数数组nums1和nums2，另有两个整数m和n，分别表示nums1和nums2中的元素数目。
// 请你合并nums2到nums1中，使合并后的数组同样按非递减顺序排列
public class merge {
    // 方法1:调用排序函数
    public void merge_sort(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }

        Arrays.sort(nums1);
    }

    // 方法2：使用额外数组
    public void merge_array(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m + n];
        int p = 0, q = 0;
        int index = 0;
        while (p < m && q < n) {
            if (nums1[p] < nums2[q]) {
                temp[index] = nums1[p++];
            } else {
                temp[index] = nums2[q++];
            }
            index++;
        }

        while (p < m) {
            temp[index++] = nums1[p++];
        }
        while (q < n) {
            temp[index++] = nums2[q++];
        }
        for (int i = 0; i < m + n; i++) {
            nums1[i] = temp[i];
        }
    }

    // 方法3：逆向遍历数组
    public void merge_reverse(int[] nums1, int m, int[] nums2, int n) {
        int p = m - 1;
        int q = n - 1;
        int k = m + n - 1;
        while (p >= 0 || q >= 0) {
            // 当num1遍历完毕，num2还没遍历完毕
            if (p == -1){
                nums1[k--] = nums2[q--];
            // 当num2遍历完毕，num1还没遍历完毕,无须重复插入，直接退出
            }else if (q==-1){
                break;
            // 因为是逆序插入，所以小的应该后插入
            }else if (nums1[p] > nums2[q]){
                nums1[k--] = nums1[p--];
            }else {
                nums1[k--] = nums2[q--];
            }
        }
    }

}
