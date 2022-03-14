/**
 * @author psj
 * @date 2022/3/14 10:12
 * @File: closedIsland.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/number-of-closed-islands/
// 二维矩阵grid 由0（土地）和1（水）组成。岛是由最大的4个方向连通的0组成的群，封闭岛是一个完全由1包围（左、上、右、下）的岛
// 请返回封闭岛屿的数目

public class closedIsland {
    // 和题目200不同，假设在四条边界存在陆地是不算一个封闭岛屿的，所以先把边界上的陆地以及和其相连陆地全部变为水，保证不会重复遍历
    public int closedIsland(int[][] grid) {
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
                if (grid[i][j] == 0) {
                    result++;
                    // 将和该岛屿相连的陆地变为1，相当于将该岛屿范围扩展到最大，然后全部置为1(意味着全部属于该岛屿)
                    dfs(grid, i, j);
                }
            }
        }
        return result;
    }

    public void dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        // 搜索边界超过网格范围
        if (i >= m || i < 0 || j < 0 || j >= n) {
            return;
        }
        // 已经是海水就停止向当前位置四周扩展
        if (grid[i][j] == 1) {
            return;
        }

        // 将扩展到的部分置为海水
        grid[i][j] = 1;  // 相当于一个visited[i][j]=true，防止重复遍历
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}
