import java.util.Arrays;

/**
 * @author psj
 * @date 2022/2/24 10:53
 * @File: longestCommonSubsequence.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/longest-common-subsequence/
// 给定两个字符串text1和text2，返回这两个字符串的最长公共子序列的长度。如果不存在公共子序列，返回0
// 子序列:由原字符串在不改变字符的相对顺序的情况下删除某些字符(也可以不删除任何字符)后组成的新字符串

public class longestCommonSubsequence {
    // 方法1：自顶向下+备忘录
    int[][] memo;

    public int longestCommonSubsequence_memo(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // -1表示未计算
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp_memo(text1, 0, text2, 0);

    }

    // 计算s1[i...]和s2[j...]的最长公共子序列
    public int dp_memo(String s1, int i, String s2, int j) {
        // base case
        // 其中一个字符串遍历完毕，为空串了
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            // s1[i]和s2[j]都在最长子序列中
            memo[i][j] = 1 + dp_memo(s1, i + 1, s2, j + 1);
        } else {
            memo[i][j] = Math.max(
                    dp_memo(s1, i + 1, s2, j),  // s1[i]不在最长子序列
                    Math.max(dp_memo(s1, i, s2, j + 1),  // s2[j]不在最长子序列
                            dp_memo(s1, i + 1, s2, j + 1))  // 都不在
            );
        }
        return memo[i][j];  // 最终返回memo[0][0],记录s1[0...]和s2[0...]的lcs

    }

    // 方法2：自底向上
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // dp[i][j]表示s1[0..i-1] 和 s2[0..j-1]的lcs长度
        int[][] dp = new int[m + 1][n + 1];

        // 假设text1的长度为5，另一个为空串，则dp[5][0]表示lcs为0
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], Math.max(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[m][n];
    }
}
