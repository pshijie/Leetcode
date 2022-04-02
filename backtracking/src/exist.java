/**
 * @author psj
 * @date 2022/4/2 9:22
 * @File: exist.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/word-search/
// 给定一个m x n二维字符网格board和一个字符串单词word。如果word存在于网格中，返回true ；否则，返回false
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用

public class exist {
    private boolean[][] visited;
    private int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        if (m == 0) {
            return false;
        }
        visited = new boolean[m][n];
        char[] charArray = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, charArray, i, j, m, n, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // i,j为当前变量到的位置坐标，begin为单词目前匹配到的位置
    public boolean dfs(char[][] board, char[] charArray, int i, int j, int m, int n, int curIndex) {
        if (curIndex == charArray.length - 1) {
            return board[i][j] == charArray[curIndex];
        }
        // 当前网络遍历到的位置元素值等于单词当前的位置字符,开始向四周扩展
        if (board[i][j] == charArray[curIndex]) {
            // 进行选择
            visited[i][j] = true;
            for (int[] dir : direction) {
                int newI = i + dir[0];
                int newJ = j + dir[1];
                // 下一步要遍历的网络需要在边界范围内且没被访问过
                if ((newI >= 0 && newI < m && newJ >= 0 && newJ < n) && !visited[newI][newJ]) {
                    if (dfs(board, charArray, newI, newJ, m, n, curIndex + 1)) {
                        return true;
                    }
                }
            }
            // 撤销选择
            visited[i][j] = false;
        }
        return false;
    }
}
