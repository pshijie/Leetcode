/**
 * @author psj
 * @date 2022/4/28 9:01
 * @File: translateNum.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
// 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
// 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法

public class translateNum {
    // 动态规划
    public int translateNum(int num) {
        String s = String.valueOf(num);
        // dp[i]表示以num[i-1]结尾的数字可以被翻译的数量
        int[] dp = new int[s.length() + 1];
        dp[1] = 1;
        // dp[0]无数字，根据dp[2]-dp[1]=1求得(假设前两个数组成的temp属于[10,25]，即使不属于也不会影响结果，进入else后dp[2]=1)
        dp[0] = 1;
        for (int i = 2; i <= s.length(); i++) {
            // num[i-2]和num[i-1]可以组成一个数temp
            String temp = s.substring(i - 2, i);
            // 当10<=temp<=25时，temp是可以被视为一个整体翻译的，所以dp[i]=dp[i-1]+dp[i-2]
            // 当temp<10或temp>25时，temp是不能被视为一个整体翻译，所以dp[i]=dp[i-1]
            if (temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }

        }
        return dp[s.length()];
    }
}
