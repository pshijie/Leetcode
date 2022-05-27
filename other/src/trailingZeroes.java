/**
 * @author psj
 * @date 2022/5/27 9:02
 * @File: trailingZeroes.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/factorial-trailing-zeroes/
// 给定一个整数n，返回n!结果中尾随零的数量

public class trailingZeroes {
    // 问题转化为：n!最多可以分解出多少个因子2和5(因为两个数相乘末尾有0,则两个数中肯定有数的因子为2和5)
    // 比如25!=1*2*...25，其中乘数中的5、10、15、20各可以提供一个5,25可以提供两个5
    // 每个5和乘数(偶数)中的因子2相乘后形成10,意味这乘积末尾多一个0
    // 所以25!末尾一共有6个0
    // 现在的目标就是计算所有乘数中有多少个5的倍数,假设计算125!:
    // 1.125/5=25表示125个数中有25个5的倍数(如5、10、25等),每个提供一个0
    // 2.125/25=25表示125个数中有5个25的倍数(如25、50、75、125等),每个在1的基础上再提供一个0
    // 3.125/5=25表示125个数中有1个125的倍数(125),每个数在2的基础上再提供一个0
    public int trailingZeroes(int n) {
        int result = 0;
        long divisor = 5;  // 因为后续有乘5的操作,怕出现整型溢出
        while (divisor <= n) {
            result += n / divisor;
            divisor *= 5;
        }
        return result;
    }
}
