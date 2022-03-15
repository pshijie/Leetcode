/**
 * @author psj
 * @date 2022/3/15 10:57
 * @File: generateMatrix.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/spiral-matrix-ii/
// 你一个正整数n ，生成一个包含1到n^2所有元素，且元素按顺时针顺序螺旋排列的n x n 正方形矩阵 matrix

public class generateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        // 四个边界
        int upper = 0;
        int lower = n - 1;
        int left = 0;
        int right = n - 1;

        int num = 1;
        // 如果list存储的元素个数等于m*n，说明遍历完了整个数组
        while (num <= n * n) {
            // 在上边界从左到右遍历数组，并填入矩阵
            if (upper <= lower) {
                for (int i = left; i <= right; i++) {
                    matrix[upper][i] = num;
                    num++;
                }
                // 上边界下移
                upper++;
            }
            // 在右边界从上到下遍历
            if (left <= right) {
                for (int i = upper; i <= lower; i++) {
                    matrix[i][right] = num;
                    num++;
                }
                // 右边界左移
                right--;
            }
            // 在下边界从右到左遍历
            if (upper <= lower) {
                for (int i = right; i >= left; i--) {
                    matrix[lower][i] = num;
                    num++;
                }
                // 上边界下移
                lower--;
            }
            // 在左边界从下到上遍历
            if (left <= right) {
                for (int i = lower; i >= upper; i--) {
                    matrix[i][left] = num;
                    num++;
                }
                // 右边界左移
                left++;
            }
        }
        return matrix;
    }
}
