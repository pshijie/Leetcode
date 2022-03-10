/**
 * @author psj
 * @date 2022/3/10 8:53
 * @File: minInsertions.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
// 给你一个字符串s ，每一次操作你都可以在字符串的任意位置插入任意字符。请你返回让s成为回文串的最少操作次数

/**
 * 回文串问题都是从中间往两边计算
 */
public class minInsertions {
    public int minInsertions(String s) {
        int n = s.length();
        // dp[i][j]表示对于字符串s[i...j]，最少需要进行dp[i][j]次插入
        // 当知道dp[i+1][j-1]的值后，也就可以认为此时的s[i+1...j-1]是回文串
        int[][] dp = new int[n][n];

        // base case：只有一个字符s[i]，所以已经是回文串了，不需要添加
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        // 已知dp[i+1][j-1](s[i+1...j-1]已经是回文串)，要计算dp[i][j],就要确定s[i]和s[j]的关系
        //      1.当s[i]=s[j]时，s[i...j]构成回文串，不需要添加
        //      2.当s[i]!=s[j]时，最简单的方式就是直接往s[i]和s[i+1]与s[j]后各自添加字符变为回文串:
        //        即x b a a a b y -> x y a a a b y x
        //        但是如果遇到如：x a a a a a ,要将s[i...j]变为回文串，可以先考虑s[i+1...y]和s[i...j-1]变为回文串需要添加的字符串数
        //        像s[i+1...y]本身就是回文串，不需要在左侧进行插入，只需要在s[j]后添加x皆可
        //        所以需要判断dp[i+1][j]和dp[i][j-1]的值，选取最小的那一个加上1

        // dp[0][n-1]在dp矩阵右上角，且i要小于j(等于的情况就是base case)
        for (int i = n-2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }
}
