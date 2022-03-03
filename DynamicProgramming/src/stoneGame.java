/**
 * @author psj
 * @date 2022/3/3 9:00
 * @File: stoneGame.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/stone-game/

public class stoneGame {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        // dp[i][j].first表示对于数组piles[i..j]，先手得到的分数
        // dp[i][j].second表示对于数组piles[i..j]，后手得到的分数
        Pair[][] dp = new Pair[n][n];

        // base case
        // 不同于数组默认值为0，这里默认为null，所以需要将初始化
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }
        // 当i==j时，即只有一堆石头，先手肯定获得该值,后手没值
        for (int i = 0; i < n; i++) {
            dp[i][i].first = piles[i];
            dp[i][i].second = 0;
        }

        // 矩阵要求右上角的值，需要由左下到右上遍历，且只需要遍历矩阵的一半
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 该轮的先手如果选择piles[i..j]最左边，则它在上一轮就是piles(i+1..j)的后手
                int left = piles[i] + dp[i + 1][j].second;
                // 该轮的先手如果选择piles[i..j]最右边，则它在上一轮就是piles(i..j-1)的后手
                int right = piles[j] + dp[i][j - 1].second;

                // 先手会选择最大的结果，后手的选择随着先手的结果而改变
                // 如果先手选择piles[i..j]最左边，则后手此时的分数为piles(i+1..j)的先手
                if (left > right) {
                    dp[i][j].first = left;
                    dp[i][j].second = dp[i + 1][j].first;
                // 如果先手选择piles[i..j]最右边，则后手此时的分数为piles(i..j-1)的先手
                } else {
                    dp[i][j].first = right;
                    dp[i][j].second = dp[i][j - 1].first;
                }
            }
        }

        Pair res = dp[0][n - 1];
        // 计算dp[0][n-1]中先手和后手的差值即可
        if (res.first - res.second > 0) {
            return true;
        }
        return false;
    }

    class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
