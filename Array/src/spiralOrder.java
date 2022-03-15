import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/3/15 10:17
 * @File: spiralOrder.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/spiral-matrix/
// 给你一个m行n列的矩阵matrix ，请按照顺时针螺旋顺序，返回矩阵中的所有元素

public class spiralOrder {
    // 对于每一部分的矩阵都采取上右下左的顺序遍历数组(每次遍历框内的数组)
    //          ①
    //   ----------------
    //   |1   2   3   4 |           1   2   3   4
    // ④ |5   6   7   8 | ②   -->  ------------
    //   |9   10  11  12|          |5   6   7 | 8
    //   ----------------          |9   10  11| 12
    //          ③                  ------------

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 四个边界
        int upper = 0;
        int lower = m - 1;
        int left = 0;
        int right = n - 1;

        LinkedList<Integer> result = new LinkedList<>();
        // 如果list存储的元素个数等于m*n，说明遍历完了整个数组
        while (result.size() < m * n) {
            // 在上边界从左到右遍历
            if (upper <= lower) {
                for (int i = left; i <= right; i++) {
                    result.add(matrix[upper][i]);
                }
                // 上边界下移
                upper++;
            }
            // 在右边界从上到下遍历
            if (left <= right) {
                for (int i = upper; i <= lower; i++) {
                    result.add(matrix[i][right]);
                }
                // 右边界左移
                right--;
            }
            // 在下边界从右到左遍历
            if (upper <= lower) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[lower][i]);
                }
                // 上边界下移
                lower--;
            }
            // 在左边界从下到上遍历
            if (left <= right) {
                for (int i = lower; i >= upper; i--) {
                    result.add(matrix[i][left]);
                }
                // 右边界左移
                left++;
            }
        }
        return result;
    }
}
