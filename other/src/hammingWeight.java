/**
 * @author psj
 * @date 2022/5/3 8:45
 * @File: hammingWeight.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/number-of-1-bits/
// 写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数

public class hammingWeight {
    // 方法1：遍历
    public int hammingWeight_iter(int n) {
        int result = 0;
        // int类型为32位
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                result++;
            }
        }
        return result;
    }

    // 方法2：位运算
    //  n&(n-1)表示将n二进制位数的最后一个1删除，如n=0101,n-1=0100,n&(n-1)=0100
    public int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            n = n & (n - 1);
            result++;
        }
        return result;
    }
}
