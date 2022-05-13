/**
 * @author psj
 * @date 2022/5/13 9:03
 * @File: isInterleave.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/interleaving-string/
// 给定三个字符串 s1、s2、s3，请你帮忙验证 s3是否是由s1和s2交错组成的

public class isInterleave {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if (s3.length() != m + n) {
            return false;
        }
        // dp[i][j]表示s1的前i个字符和s2的前j个字符拼接为s3的i+j个字符
        boolean dp[][] = new boolean[m+1][n+1];
        dp[0][0] = true;
        // 遇到不相符的就停止
        for (int i = 1; i <= m && s1.charAt(i - 1) == s3.charAt(i - 1); i++) {
            dp[i][0] = true;
        }
        // 遇到不相符的就停止
        for (int j = 1; j <= n && s2.charAt(j - 1) == s3.charAt(j - 1); j++) {
            dp[0][j] = true;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // s3的第i+j个字符是s1的第i个字符或者由s2的第j个字符
                // 在转移矩阵中可以视为向下移动一个或者向右移动一格
                dp[i][j] = (dp[i - 1][j] && s3.charAt(i - 1 + j) == s1.charAt(i - 1))
                        || (dp[i][j - 1] && s3.charAt(j - 1 + i) == s2.charAt(j - 1));
            }
        }
        return dp[m][n];
    }
}
