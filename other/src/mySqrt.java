/**
 * @author psj
 * @date 2022/3/22 10:26
 * @File: mySqrt.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/sqrtx/
// 给你一个非负整数x ，计算并返回x的算术平方根
public class mySqrt {
    // 二分查找
    public int mySqrt(int x) {
        int l = 0, r = x;
        int result = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                result = mid;
                l = mid + 1;
            }else {
                // mid*mid > x的时候
                r = mid-1;
            }
        }
        return result;
    }
}
