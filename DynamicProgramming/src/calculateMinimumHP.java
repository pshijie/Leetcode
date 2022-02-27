import java.util.Arrays;

/**
 * @author psj
 * @date 2022/2/27 9:26
 * @File: calculateMinimumHP.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/dungeon-game/

public class calculateMinimumHP {
    // 方法1：备忘录
    int[][] memo;

    public int calculateMinimumHP_memo(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp_memo(dungeon, 0, 0);

    }

    // 从(i,j)到右下角需要的初始血量
    public int dp_memo(int[][] dungeon, int i, int j) {
        // base case
        // 初始血量至少为1
        if (i == dungeon.length - 1 && j == dungeon[0].length - 1) {
            return dungeon[i][j] >= 0 ? 1 : -dungeon[i][j] + 1;
        }
        if (i == dungeon.length || j == dungeon[0].length) {
            return Integer.MAX_VALUE;
        }

        int res = Math.min(dp_memo(dungeon, i + 1, j), dp_memo(dungeon, i, j + 1)) - dungeon[i][j];
        memo[i][j] = res <= 0 ? 1 : res;
        return memo[i][j];
    }

    // 方法2：自底向上
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // dp[i][j]表示从(i,j)到右小角的最少血量
        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] < 0 ? -dungeon[m - 1][n - 1] + 1 : 1;

        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i == m || j == n) {
                    dp[i][j] = Integer.MAX_VALUE;
                    continue;
                }
                if (i == m - 1 && j == n - 1) {
                    continue;
                }
                int res = Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j];
                dp[i][j] = res < 0 ? 1 : res;
            }
        }
        return dp[0][0];
    }
}
