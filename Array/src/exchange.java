/**
 * @author psj
 * @date 2022/4/15 9:21
 * @File: exchange.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
// 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分

public class exchange {
    // 双指针
    public int[] exchange(int[] nums) {
        // 左右两个指针分别从数组开头和结尾向中间遍历
        int left = 0;
        int right = nums.length - 1;
        int temp;
        while (left < right) {
            // 如果left指针遇到奇数就继续向中间移动，直到遇到偶数
            while (left < right && (nums[left] & 1) == 1) {
                left++;
            }
            // 如果right指针遇到偶数就继续向中间移动，直到遇到奇数
            while (left < right && (nums[right] & 1) == 0) {
                right--;
            }
            // 交换此时left和right指向的数组元素
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
        return nums;
    }
}
