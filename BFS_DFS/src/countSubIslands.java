/**
 * @author psj
 * @date 2022/3/14 10:57
 * @File: countSubIslands.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/count-sub-islands/
// 给你两个m x n的二进制矩阵grid1和grid2，它们只包含0(表示水域)和1(表示陆地)。一个岛屿是由四个方向(水平或者竖直)上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。
// 如果grid2 的一个岛屿，被 grid1 的一个岛屿完全包含，也就是说grid2中该岛屿的每一个格子都被grid1中同一个岛屿完全包含，那么我们称 grid2 中的这个岛屿为子岛屿 。
// 请你返回grid2中子岛屿的数目

public class countSubIslands {
    // 和题1254一样先将不符合条件的岛屿全置为海水，剩下的就是符合条件的岛屿
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        // 假设grid2[i][j]=1，但是grid1[i][j]=0，说明在grid2以(i,j)扩展形成的岛屿肯定不是grid1的子岛屿
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 将扩展形成的岛屿置为海水
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    dfs(grid2, i, j);
                }
            }
        }

        // 在grid2中剩下的就是符合条件的岛屿
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    result++;
                    // 将和该岛屿相连的陆地变为0，相当于将该岛屿范围扩展到最大，然后全部置为0(意味着全部属于该岛屿)
                    dfs(grid2, i, j);
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
        if (grid[i][j] == 0) {
            return;
        }

        // 将扩展到的部分置为海水
        grid[i][j] = 0;  // 相当于一个visited[i][j]=true，防止重复遍历
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}
