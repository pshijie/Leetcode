/**
 * @author psj
 * @date 2022/5/16 8:47
 * @File: numDistinct.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/distinct-subsequences/
// 给定一个字符串s和一个字符串t ，计算在s的子序列中t出现的个数。
// 字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
// 例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是

public class numDistinct {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        s = " " + s;
        t = " " + t;
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        // dp[i][j]表示对于t中的[0,j]个字符,s中的[0,i]个字符匹配的个数
        int[][] dp = new int[m + 1][n + 1];
        // 当往两个字符串前插入空格之后，dp[i][0] = 1 是一个显而易见的初始化条件
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 当charS[i] == charT[j],分为以下两个情况:
                // 使用charS[i]和不使用charS[i]参与匹配
                if (charS[i] == charT[j]) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                } else {
                    // 不使用charS[i]进行匹配
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}
