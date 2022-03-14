/**
 * @author psj
 * @date 2022/3/14 10:30
 * @File: numEnclaves.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/number-of-enclaves/
// 给你一个大小为m x n的二进制矩阵 grid，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
// 一次移动是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过grid的边界。返回网格中无法在任意次数的移动中离开网格边界的陆地单元格的数量

public class numEnclaves {
    // 题1254一样求封闭岛屿
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            // 将左边界的陆地以及和其相连的陆地变为水
            dfs(grid, i, 0);
            // 将右边界的陆地以及和其相连的陆地变为水
            dfs(grid, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            // 将上边界的陆地以及和其相连的陆地变为水
            dfs(grid, 0, i);
            // 将下边界的陆地以及和其相连的陆地变为水
            dfs(grid, m - 1, i);
        }

        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 发现了一个岛屿
                if (grid[i][j] == 1) {
                    result++;
                    // 因为是求单元格的数量，所以不需要将符合条件的单元格周围置为1
                    // 最终结果即把边界的陆地和与边界相连的陆地置为1后，剩余的单元格数量
                }
            }
        }
        return result;
    }

    public void dfs(int[][] grid, int i, int j){
        int m = grid.length;
        int n = grid[0].length;

        // 搜索边界超过网格范围
        if (i >= m || i < 0 || j < 0 || j >= n) {
            return;
        }
        // 已经是海水就停止向当前位置四周扩展
        if (grid[i][j] == 0) {
            return;
        }

        // 将扩展到的部分置为海水
        grid[i][j] = 0;  // 相当于一个visited[i][j]=true，防止重复遍历

        // 往四周扩展
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}
