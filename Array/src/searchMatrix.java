/**
 * @author psj
 * @date 2022/3/31 9:56
 * @File: searchMatrix.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
// 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性:
// 每行的元素从左到右升序排列
// 每列的元素从上到下升序排列

public class searchMatrix {
    // 方法1：暴力
    public boolean searchMatrix_force(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (target == matrix[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    // 方法2：对每一行进行二分查找
    public boolean searchMatrix_bs(int[][] matrix, int target) {
        for (int[] row : matrix) {
            boolean flag = binarySearch(row, target);
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public boolean binarySearch(int[] row, int target) {
        int i = 0, j = row.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (row[mid] > target) {
                j = mid - 1;
            } else if (row[mid] < target) {
                i = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    // 方法3：从右上角开始遍历
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = 0, col = matrix[0].length - 1;
        while (col >= 0 && row < matrix.length) {
            // 假设target小于右上角的值，说明在右上角所在的列不可能存在target，因为该列上其他元素肯定比右上角大，将当前列删除(即将右上角的位置向左移动一位)
            if (target < matrix[row][col]) {
                col--;
                // 假设target大于右上角的值，说明在右上角所在的行不可能存在target，因为该行上其他元素肯定比右上角小，将当前行删除(即将右上角的位置向下移动一位)
            } else if (target > matrix[row][col]) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }

}
