/**
 * @author psj
 * @date 2022/3/14 9:50
 * @File: numIslands.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/number-of-islands/
// 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。此外，你可以假设该网格的四条边均被水包围。

public class numIslands {
    // DFS
    public int numIslands(char[][] grid) {
        int result = 0;
        int m = grid.length;
        int n = grid[0].length;
        // 遍历grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 发现了一个岛屿
                if (grid[i][j] == '1') {
                    result++;
                    // 将和该岛屿相连的陆地变为0，相当于将该岛屿范围扩展到最大，然后全部置为0(意味着全部属于该岛屿)
                    dfs(grid, i, j);
                }
            }
        }
        return result;
    }

    public void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        // 搜索边界超过网格范围
        if (i >= m || i < 0 || j < 0 || j >= n) {
            return;
        }
        // 已经是海水就停止向当前位置四周扩展
        if (grid[i][j] == '0') {
            return;
        }

        // 将扩展到的部分置为海水
        grid[i][j] = '0';  // 相当于一个visited[i][j]=true，防止重复遍历

        // 往四周扩展
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}
