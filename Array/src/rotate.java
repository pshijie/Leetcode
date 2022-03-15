/**
 * @author psj
 * @date 2022/3/15 10:19
 * @File: rotate.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/rotate-image/
// 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转90度
// 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像

public class rotate {
    // 不要考虑旋转前和旋转后的坐标映射，将旋转拆解为两个步骤：
    //      1.将矩阵沿主对角线(即左上角到右下角的线)对折：
    //          1  2  3  4       1  5  7  4
    //          5  6  7  8  ---> 2  6  6  1
    //          7  6  5  4       3  7  5  2
    //          4  1  2  3       4  8  4  3
    //      2.将矩阵每一行进行翻转：
    //          1  5  7  4       4  7  5  4
    //          2  6  6  1  ---> 1  6  6  2
    //          3  7  5  2       2  5  7  3
    //          4  8  4  3       3  4  8  4
    // 经过这两个步骤后实现了顺时针旋转90°的效果

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 实现步骤1
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 实现步骤2
        for (int[] row : matrix) {
            reverse(row);
        }
    }

    // 将当前行的元素进行翻转
    public void reverse(int[] row) {
        int i = 0, j = row.length - 1;
        while (i < j) {
            int temp = row[i];
            row[i] = row[j];
            row[j] = temp;
            i++;
            j--;
        }
    }
}
