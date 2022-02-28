import java.util.*;

/**
 * @author psj
 * @date 2022/2/28 8:45
 * @File: findRotateSteps.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/freedom-trail/

public class findRotateSteps {

    Map<Character, List<Integer>> charMap;
    int[][] memo;

    public int findRotateSteps(String ring, String key) {
        int m = ring.length();
        int n = key.length();

        // 记录每一个字符在ring中的位置
        charMap = new HashMap<>();
        for (int i = 0; i < ring.length(); i++) {
            char ch = ring.charAt(i);
            List<Integer> list = new ArrayList<>();
            charMap.putIfAbsent(ch, list);
            charMap.get(ch).add(i);
        }

        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
            return dp(ring, 0, key, 0);
        }

        return dp(ring, 0, key, 0);
    }

    // 当圆盘指针在ring[i]上时，输入key[j...]的最少操作数
    public int dp(String ring, int i, String key, int j) {
        // base case
        if (j == key.length()) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int n = ring.length();
        int res = Integer.MAX_VALUE;

        for (int k : charMap.get(key.charAt(j))) {
            // 指针转动的次数
            int delta = Math.abs(k - i);
            // 可以选择顺时针还是逆时针
            delta = Math.min(delta, n - delta);
            // 将指针转动到ring[k],输入key[j+1]
            int subproblem = dp(ring, k, key, j + 1);
            res = Math.min(res, 1 + delta + subproblem);

        }
        memo[i][j] = res;

        return memo[i][j];
    }

}

