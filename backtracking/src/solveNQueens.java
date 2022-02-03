import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author psj
 * @date 2022/2/3 22:08
 * @File: solveNQueens.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/n-queens/
// 给你一个整数n ，返回所有不同的n皇后问题的解决方案
// n皇后问题研究的是如何将n个皇后放置在n×n的棋盘上，并且使皇后彼此之间不能相互攻击(不在同一列/行/斜对角)

public class solveNQueens {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // 构建原始棋盘
        char[][] board = new char[n][n];
        for (char[] c : board) {
            Arrays.fill(c, '.');
        }
        backtrace(board, 0);
        return res;
    }

    // 路径：board中小于row的行都已经放置了皇后
    // 选择列表：第row行的所有列都是放置皇后的选择
    // 结束条件：row超过board的最后一行
    public void backtrace(char[][] board, int row) {
        // 当超过最后一行时就为结束(小于row的行都已经放置了皇后)
        if (row == board.length) {
            // 将board转换为字符串
            List<String> list = new ArrayList<>();
            for (char[] c : board) {
                list.add(String.copyValueOf(c));
            }

            res.add(list);
            return;
        }

        int n = board[row].length;

        for (int col = 0; col < n; col++) {
            if (!isVaild(board, row, col)) {
                continue;
            }

            // 做选择
            board[row][col] = 'Q';

            // 进入下一行
            backtrace(board, row + 1);

            // 撤销选择
            board[row][col] = '.';
        }

    }

    // 只需要检查上/左上/右上三个方向，因为每一行只放一个皇后，所以左/右不用检查，皇后是从上往下放的，所以下/左下/右下不用检查
    public boolean isVaild(char[][] board, int row, int col) {
        int n = board.length;

        // 检查上
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // 检查右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // 检查左上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }
}
