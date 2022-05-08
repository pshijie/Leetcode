/**
 * @author psj
 * @date 2022/5/8 9:01
 * @File: minArray.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
// 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
// 给你一个可能存在重复元素值的数组numbers，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素

public class minArray {
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                // 如果numbers[mid]=numbers[right],如[3,4,5,1,1,1,1,1,1]和[3,4,1,1,1,1,1]
                // 不清楚在左半区间还是右半区间，所以只能全部遍历找最小值
                return findMin(numbers, left, right);
            }
        }
        return numbers[left];
    }

    // 找出区间[start,end]的最小值
    public int findMin(int[] numbers, int start, int end) {
        int result = numbers[start];
        for (int i = start; i <= end; i++) {
            if (numbers[i] < result) {
                result = numbers[i];
            }
        }
        return result;
    }
}
