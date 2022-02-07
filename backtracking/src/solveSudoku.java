/**
 * @author psj
 * @date 2022/2/7 20:56
 * @File: solveSudoku.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/sudoku-solver/
// 数独的解法需遵循如下规则：
//     1.数字 1-9 在每一行只能出现一次。
//     2.数字 1-9 在每一列只能出现一次。
//     3.数字 1-9 在每一个以粗实线分隔的3x3宫内只能出现一次

/**
 * tips:
 * (row / 3) * 3用于保证不会边界溢出，比如遍历到第8行，即(8/3)*3=6,再加上后面的i/3是不会超过边界的
 */
public class solveSudoku {
    public void solveSudoku(char[][] board) {
        if (board.length == 0) {
            return;
        }
        backtrace(board, 0, 0);
    }

    // 对棋盘每一个位置进行穷举
    public boolean backtrace(char[][] board, int row, int col) {
        // base case
        // 找到一个可行解后
        if (row == 9) {
            return true;
        }
        // 当穷举到最后一列时
        if (col == 9) {
            // 从下一行开始穷举
            return backtrace(board, row + 1, 0);
        }

        // 当前遍历的位置已经有数字
        if (board[row][col] != '.') {
            return backtrace(board, row, col + 1);
        }
        // 9个数都放到棋盘每个位置试下
        for (char ch = '1'; ch <= '9'; ch++) {
            if (!isVaild(board, row, col, ch)) {
                continue;
            }

            // 选择字符ch填充到board[row][col]
            board[row][col] = ch;
            // 每一行一列一列进行填充，找到可行解就停止
            if (backtrace(board, row, col + 1)) {
                return true;
            }
            // 撤销选择
            board[row][col] = '.';
        }
        return false;
    }

    public boolean isVaild(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            // 判断行中是否有重复字符
            if (board[row][i] == ch) {
                return false;
            }
            // 判断列中是否有重复字符
            if (board[i][col] == ch) {
                return false;
            }
            // 判断方框中是否有重复字符
            if (board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == ch) {
                return false;
            }

        }
        return true;
    }
}
