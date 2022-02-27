import java.util.Arrays;

/**
 * @author psj
 * @date 2022/2/27 8:47
 * @File: minPathSum.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/minimum-path-sum/
// 给定一个包含非负整数的mxn网格grid,请找出一条从左上角到右下角的路径，使得路径上的数字总和为最0小(每次只能向下或者向右移动一步)

public class minPathSum {

    // 方法1：自顶向下
    public int minPathSum_nomemo(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        return dp_nomemo(grid, m - 1, n - 1);
    }

    public int dp_nomemo(int[][] grid, int i, int j) {
        // base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        return Math.min(dp_nomemo(grid, i - 1, j), dp_nomemo(grid, i, j - 1)) + grid[i][j];
    }

    // 方法2：备忘录
    int[][] memo;

    public int minPathSum_memo(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp_memo(grid, m - 1, n - 1);
    }

    public int dp_memo(int[][] grid, int i, int j) {
        // base case
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        memo[i][j] = Math.min(dp_memo(grid, i - 1, j), dp_memo(grid, i, j - 1)) + grid[i][j];
        return memo[i][j];
    }

    // 方法3：自底向下
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // dp[i][j]表示从(0,0)到(i,j)的最小距离
        int[][] dp = new int[m][n];

        // base case
        dp[0][0] = grid[0][0];
        // 在第0行或者第0列的路径和为该行/列的元素和
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }
}
