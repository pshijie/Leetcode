/**
 * @author psj
 * @date 2022/5/5 8:54
 * @File: findNthDigit.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/nth-digit/
// 给你一个整数n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第n位上的数字

public class findNthDigit {
    // 模拟法
    // x位数一共有9*10^(x-1)个数(如两位数一共有90个数，10-99)
    // 所有x位数的为位数和为x*9*10^(x-1)(如两位数10-99的位数和为2*90=180)
    public int findNthDigit(int n) {
        int digit = 1;  // 表示当前位数(即第几组)
        long nine = 9;
        long count = 0;

        // 按位数分组,找到n属于哪一组(看分到1-9还是10-99...)
        while (true) {
            count = digit * nine;
            if (n <= count) {
                break;
            }
            n -= count;
            digit++;
            nine *= 10;
        }
        // 定位到属于该组的哪个数字
        // 为什么减1?比如原始n=121属于第三组,此时n=22(121-9-90),应该对应数字107=(22-1)/3+100
        long num = (long) Math.pow(10, digit - 1) + (n - 1) / digit;
        // 定位到属于该数字的哪个字符(看属于989这三位中的哪一位)
        // 此时看属于数字107中的哪一个数字,需要进行取余操作，对应数字1
        char c = String.valueOf(num).charAt((n - 1) % digit);
        return (int) (c - '0');

    }
}
