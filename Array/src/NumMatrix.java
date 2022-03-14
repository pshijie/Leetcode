/**
 * @author psj
 * @date 2022/3/14 9:09
 * @File: NumMatrix.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
// 给定一个二维矩阵 matrix，以下类型的多个请求：
//      计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2)

public class NumMatrix {
    // 不使用嵌套循环，使用前缀和
    // 假设要计算左上角(1,1)到右下角(2,2)的子矩阵和：
    // 1 2 4 5 6    |1 2 4| 5 6   |1 2| 4 5 6   |1 2 4| 5 6   |1 2| 4 5 6
    //  ------      |1 2 4| 5 6   |1 2| 4 5 6   |1 2 4| 5 6   |1 2| 4 5 6
    // 1| 2 4| 5 6 =|1 2 4| 5 6 - |1 2| 4 5 6 - -------     + -----
    // 1| 2 4| 5 6  -------       -----          1 2 4  5 6    1 2  4 5 6
    //  ------       1 2 4  5 6    1 2  4 5 6    1 2 4  5 6    1 2  4 5 6
    //  1 2 4 5 6    1 2 4  5 6    1 2  4 5 6    1 2 4  5 6    1 2  4 5 6

    // 前缀和，用于记录左上角为(0,0)，右下角为(i-1,j-1)的子矩阵和
    private int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 0 || n == 0) {
            return;
        }
        preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i-1][j-1];
            }
        }
    }

    // 空间换时间，该方法的时间复杂度为O(1)(不包括计算preSum的时间复杂度)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];
    }
}
