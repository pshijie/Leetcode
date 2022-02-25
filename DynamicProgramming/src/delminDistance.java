import java.util.Arrays;

/**
 * @author psj
 * @date 2022/2/25 9:30
 * @File: delminDistance.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/delete-operation-for-two-strings/
// 给定两个单词word1和word2,返回使得word1和 word2相同所需的最小步数。每步可以删除任意一个字符串中的一个字符

public class delminDistance {
    // ***知道两个字符串的最长公共子序列，然后将每个字符串长度减去该值相加即可
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int lcs = longestCommonSubsequence(word1, word2);
        return m - lcs + n - lcs;
    }

    public int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // dp[i][j]表示s1[0..i-1] 和 s2[0..j-1]的lcs长度
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }

        return dp[m][n];
    }

    // 方法2：备忘录
    int[][] memo;

    public int minDistance_memo(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int lcs = dp_memo(word1, 0, word2, 0);
        return m - lcs + n - lcs;
    }

    public int dp_memo(String word1, int i, String word2, int j) {
        // base case
        // 其中一个字符串遍历完毕，为空串了
        if (i == word1.length() || j == word2.length()) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = 1 + dp_memo(word1, i + 1, word2, j + 1);
        } else {
            memo[i][j] = Math.max(dp_memo(word1, i + 1, word2, j),
                    Math.max(dp_memo(word1, i, word2, j + 1), dp_memo(word1, i + 1, word2, j + 1)));
        }

        return memo[i][j];

    }

}
