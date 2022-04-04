/**
 * @author psj
 * @date 2022/4/4 9:15
 * @File: uniquePaths.java
 * @Software: IntelliJ IDEA
 */
// 一个机器人位于一个 m x n 网格的左上角,机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角。问总共有多少条不同的路径
public class uniquePaths {
    public int uniquePaths(int m, int n) {
        // dp[i][j]表示从(0,0)到(i-1,j-1)的总路径数
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];

    }
}
