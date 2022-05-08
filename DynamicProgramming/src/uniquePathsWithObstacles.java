/**
 * @author psj
 * @date 2022/5/8 8:37
 * @File: uniquePathsWithObstacles.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/unique-paths-ii/
// 一个机器人位于一个mxn网格的左上角。机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角。
// 现在考虑网格中有障碍物(1)。那么从左上角到右下角将会有多少条不同的路径？

public class uniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // dp[i][j]表示从左上角到当前位置(i,j)的路径数目
        int[][] dp = new int[m][n];
        // 在第一列上，当前位置如果是障碍物，则该列后续所有位置无法达到(并非将所有非障碍物全部赋值为1)
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
                dp[i][0] = 1;
        }
        // 在第一行上，当前位置如果是障碍物，则该行后续所有位置无法达到(并非将所有非障碍物全部赋值为1)
        for (int i = 0; i < n&& obstacleGrid[0][i] == 0; i++) {
                dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m - 1][n - 1];

    }
}
