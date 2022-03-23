/**
 * @author psj
 * @date 2022/3/23 10:00
 * @File: maximalSquare.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/maximal-square/
// 在一个由'0'和'1'组成的二维矩阵内，找到只包含'1'的最大正方形，并返回其面积

public class maximalSquare {
    public int maximalSquare(char[][] matrix) {
        int result = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int M = matrix.length;
        int N = matrix[0].length;

        // dp[i][j]表示以matrix[i][j]为右下角，且只包含1的正方形的边长的最大值
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                // 这一步的continue就确保了以下这种情况：
                // 0 0 0 0 0 0
                // 0 1 1 0 0 0
                // 0 1 1 0 0 0
                // 0 0 0 0 0 0
                // 0 0 0 0 1 0
                // 当判断dp[4][3]的时候，因为dp[3][2]为0，所以不会对dp[4][3]进行更新(还是初始化的0)
                if (matrix[i - 1][j - 1] == '0') {
                    continue;
                }
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                result = Math.max(result, dp[i][j]);
            }
        }
        return result * result;
    }
}
