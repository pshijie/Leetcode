import java.util.Arrays;

/**
 * @author psj
 * @date 2022/2/25 9:54
 * @File: minimumDeleteSum.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings/
// 给定两个字符串s1和s2，返回使两个字符串相等所需删除字符的ASCII值的最小和

public class minimumDeleteSum {

    // 方法1：备忘录
    // 记录s1[i..]和s2[j..]删除成相同字符串最小的ASCII码的和
    int[][] memo;

    public int minimumDeleteSum_memo(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp_memo(s1, 0, s2, 0);
    }

    public int dp_memo(String s1, int i, String s2, int j) {
        int res = 0;

        // base case
        // s1已经遍历完毕，则s2剩下的字符都要删除
        if (i == s1.length()) {
            for (; j < s2.length(); j++) {
                res += s2.charAt(j);
            }
            return res;
        }
        // s2已经遍历完毕，则s1剩下的字符都要删除
        if (j == s2.length()) {
            for (; i < s1.length(); i++) {
                res += s1.charAt(i);
            }
            return res;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // s1[i]和s2[j]都在lcs中，就不用进行删除操作
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp_memo(s1, i + 1, s2, j + 1);
        } else {
            memo[i][j] = Math.min(
                    // s1[i]不在lcs中，需要删除
                    s1.charAt(i) + dp_memo(s1, i + 1, s2, j),
                    // s2[j]不在lcs中，需要删除
                    Math.min(s2.charAt(j) + dp_memo(s1, i, s2, j + 1),
                    // s1[i]和s2[j]不在lcs中，都需要删除
                    s1.charAt(i) + s2.charAt(j) + dp_memo(s1, i + 1, s2, j + 1)
                    ));
        }

        return memo[i][j];
    }

    // 方法2：自底向上
    // ***要删除的ASCII码等于两个字符串ASCII码和减去lcs的ASCII码的和
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int sum = 0;  // 记录两个字符串的ASCII总和

        for (int i = 0; i < m; i++) {
            sum += s1.charAt(i);
        }
        for (int i = 0; i < n; i++) {
            sum += s2.charAt(i);
        }

        // dp[i][j]表示s1[0..i-1]和s2[0..j-1]的lcs的ASCII码总和
        int[][] dp = new int[m+1][n+1];

        // lcs的长度为0，所以ASCII总和为0
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + s1.charAt(i-1);
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }

        return sum - dp[m][n]*2;  // 乘以2是因为lcs在两个字符串上都要删除一次
    }

}
