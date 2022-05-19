/**
 * @author psj
 * @date 2022/5/19 8:43
 * @File: maximalRectangle.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/maximal-rectangle/
// 给定一个仅包含0和1、大小为rowsxcols的二维二进制矩阵，找出只包含1的最大矩形，并返回其面积

public class maximalRectangle {
    // 假设矩阵为:
    // 1 1 0                                1 1 0
    // 1 0 1  找出每个位置从上到下连续1的数目   2 0 1
    // 1 1 1                                3 1 2
    // 然后每个点分别向左和向右找大于当前位置元素的个数,
    // 比如在matrix[2][1]=1左/右边分别为3和2，都大于1，所以个数为3(包括自己)
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] matrix_sum = new int[m][n];
        // 将原始矩阵第一行直接赋值给新矩阵，因为第一行没有上面的元素进行累加
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == '1'){
                matrix_sum[0][i] = 1;  // 转换为数字才能往下计算
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    matrix_sum[i][j] = matrix_sum[i - 1][j] + 1;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前位置的元素值*当前行的宽度不大于当前的最大值,不需要往下执行
                if (matrix_sum[i][j] * n <= result){
                    continue;
                }
                // 当前位置元素值不为0时开始向两边扩展
                if (matrix_sum[i][j] != 0) {
                    int cnt = 1;  // 当前元素自己
                    // 开始向右遍历
                    for (int k = j + 1; k < n; k++) {
                        if (matrix_sum[i][k] < matrix_sum[i][j]) {
                            break;
                        }
                        cnt++;
                    }
                    // 开始向左遍历
                    for (int k = j - 1; k >= 0; k--) {
                        if (matrix_sum[i][k] < matrix_sum[i][j]) {
                            break;
                        }
                        cnt++;
                    }
                    result = Math.max(result, cnt * matrix_sum[i][j]);
                }
            }
        }
        return result;
    }
}
