/**
 * @author psj
 * @date 2022/4/30 9:26
 * @File: findNumberIn2DArray.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
// 在一个n*m的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数

public class findNumberIn2DArray {
    // 每次以当前矩阵左下角的元素为标志flag:
    // 1.如果target < flag,说明flag是在行的元素肯定是大于target的(flag在行的最左边,行中最小)，所以将flag向上移(即将矩阵的行缩小)
    // 1.如果target > flag,说明flag是在列的元素肯定是小于target的(flag在列的最下边,列中最大)，所以将flag向右移(即将矩阵的列缩小)
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j < matrix[0].length) {
            if (target < matrix[i][j]) {
                i--;
            } else if (target > matrix[i][j]) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}
