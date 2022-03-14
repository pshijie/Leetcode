/**
 * @author psj
 * @date 2022/3/14 10:42
 * @File: maxAreaOfIsland.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/max-area-of-island/
// 一个大小为 m x n 的二进制矩阵grid。岛屿是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个1必须在水平或者竖直的四个方向上相邻。
// 你可以假设 grid 的四个边缘都被 0（代表水）包围着。岛屿的面积是岛上值为 1 的单元格的数目。
// 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0

public class maxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1){
                    int area = dfs(grid, i, j);  // 当前陆地构成的岛屿面积
                    result = Math.max(result, area);
                }
            }
        }
        return result;
    }

    // 当前为陆地时，统计与当前陆地相连的陆地数量
    public int dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        // 超过边界
        if (i >= m || i < 0 || j >= n || j < 0) {
            return 0;
        }
        // 已经是海水就停止向当前位置四周扩展
        if (grid[i][j] == 0) {
            return 0;
        }

        // 将当前扩展到的部分置为海水
        grid[i][j] = 0;  // 相当于一个visited[i][j]=true，防止重复遍历

        // 四个方向的陆地数加上本身
        return dfs(grid, i + 1, j)
                + dfs(grid, i, j + 1)
                + dfs(grid, i - 1, j)
                + dfs(grid, i, j - 1)
                + 1;
    }
}
