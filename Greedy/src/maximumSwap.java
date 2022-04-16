/**
 * @author psj
 * @date 2022/4/16 8:47
 * @File: maximumSwap.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/maximum-swap/
// 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值

public class maximumSwap {
    // 从左往右遍历每个数字，找到比当前数字大且最靠右的数字交换
    // 如果没有找到比当前数字大的数就遍历下一个
    public static int maximumSwap(int num) {
        // 将数字转换为数组方便进行交换操作
        char[] arrNum = String.valueOf(num).toCharArray();
        for (int i = 0; i < arrNum.length - 1; i++) {
            int max = i + 1;
            // 找到i之后(不包含i)最靠右且值最大的索引max
            for (int j = i + 1; j < arrNum.length; j++) {
                max = arrNum[j] >= arrNum[max] ? j : max;
            }
            // 因为是从左到右遍历的，所以找到第一个满足arrNum[max] > arrNum[i]换的肯定是最高位的数
            if (arrNum[max] > arrNum[i]) {
                swap(arrNum, i, max);
                return Integer.valueOf(String.valueOf(arrNum));
            }

        }
        return num;
    }

    public static void swap(char[] arrNum, int i, int j) {
        char temp = arrNum[i];
        arrNum[i] = arrNum[j];
        arrNum[j] = temp;
    }

    public static void main(String[] args) {
        int i = maximumSwap(2828);
        System.out.println(i);
    }
}
