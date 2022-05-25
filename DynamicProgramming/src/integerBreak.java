/**
 * @author psj
 * @date 2022/5/25 9:23
 * @File: integerBreak.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/integer-break/
// 给定一个正整数n,将其拆分为k个正整数的和(k >= 2),并使这些整数的乘积最大化.返回可以获得的最大乘积

public class integerBreak {
    // 方法1:动态规划
    public int integerBreak(int n) {
        // dp[i]表示将正整数i拆分成至少两个正整数的和之后，这些正整数的最大乘积
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                // 将i拆分成j和i-j的和,且i-j不再拆分成多个正整数，此时的乘积是j*(i-j)
                // 将i拆分成j和i−j的和,且i−j继续拆分成多个正整数，此时的乘积是j×dp[i−j]
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }

    // 方法2:数学方法
    // ①当所有拆分出的数字相等时，乘积最大
    // ②最优拆分数字为3
    public int integerBreak_math(int n) {
        // 当n>3时，求n除以3的整数部分a和余数部分b(即n=3a+b)并分为以下三种情况:
        // 1.b=0,直接返回3^a(满足定理①和②)
        // 2.b=1,将一个1+3分为2+2(因为2*2>1*3),返回3^(a-1)*4
        // 3.b=2,返回3^a*2
        if (n <= 3) {
            return n - 1;
        }
        int a = n / 3;
        int b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        }
        return (int) Math.pow(3, a) * 2;
    }
}