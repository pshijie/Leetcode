import java.util.Arrays;

/**
 * @author psj
 * @date 2022/2/18 16:35
 * @File: minFallingPathSum.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/minimum-falling-path-sum/
// 给你一个nxn的方形整数数组matrix,请你找出并返回通过matrix的下降路径的最小和
// 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。
// 在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
// 具体来说，位置(row, col)的下一个元素应当是(row + 1, col - 1)、(row + 1, col)或者(row + 1, col + 1)

// 自顶向下的思想：
// 对于matrix[i][j]，只有可能从matrix[i-1][j]，matrix[i-1][j-1]，matrix[i-1][j+1]转移过来，
// 所以要知道到matrix[i-1][j]，matrix[i-1][j-1]，matrix[i-1][j+1]这三个位置的最小路径和，然后加上matrix[i][j]值即可

public class minFallingPathSum {
    // 方法1:暴力法
    public int minFallingPathSum_nomemo(int[][] matrix) {
        int n = matrix.length;
        int res = Integer.MAX_VALUE;

        // 终点可能是最后一行的任何一列，所以要对每一列进行大小比较
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp_nomemo(matrix, n - 1, i));
        }

        return res;
    }

    public int dp_nomemo(int[][] matrix, int i, int j) {
        // 非法索引
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            return 99999;  // 返回处于[10001,inf]的数值即可
        }

        // base case
        if (i == 0) {
            return matrix[i][j];
        }

        return matrix[i][j] +
                Math.min(dp_nomemo(matrix, i - 1, j),
                        Math.min(dp_nomemo(matrix, i - 1, j - 1), dp_nomemo(matrix, i - 1, j + 1)));
    }

    // 方法2：使用备忘录
    int[][] memo;

    public int minFallingPathSum_memo(int[][] matrix) {
        int n = matrix.length;
        int res = Integer.MAX_VALUE;

        memo = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], 66666);  // 设置的值不处于[-10000,10000]即可
        }

        // 终点可能是最后一行的任何一列，所以要对每一列进行大小比较
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp_memo(matrix, n - 1, i));
        }

        return res;
    }

    public int dp_memo(int[][] matrix, int i, int j) {
        // 非法索引
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            return 99999;  // 返回处于[10001,inf]的数值即可
        }

        // base case
        if (i == 0) {
            return matrix[i][j];
        }

        if (memo[i][j] != 66666) {
            return memo[i][j];
        }

        memo[i][j] = matrix[i][j] +
                     Math.min(dp_memo(matrix, i - 1, j),
                              Math.min(dp_memo(matrix, i - 1, j - 1),
                                       dp_memo(matrix, i - 1, j + 1)));

        return memo[i][j];
    }
}
