import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/3/1 9:34
 * @File: maxCoins.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/burst-balloons/
// 有n个气球，编号为0到n - 1，每个气球上都标有一个数字，这些数字存在数组nums中
// 现在要求戳破所有的气球。戳破第i个气球，你可以获得nums[i - 1] * nums[i] * nums[i + 1]枚硬币,求所能获得硬币的最大数量
// 可以视num[-1]=num[n]=1

public class maxCoins {
    // 方法1:回溯（超时）
    int res = Integer.MIN_VALUE;

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 使用list集合便于删除和
        List<Integer> list = new LinkedList<>();
        for (int i : nums) {
            list.add(i);
        }

        backtrace(list, 0);
        return res;
    }

    public void backtrace(List<Integer> list, int score) {
        if (list.size() == 0) {
            res = Math.max(res, score);
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            // 预防i=0出现的num[-1]
            int left = (i - 1 >= 0) ? list.get(i - 1) : 1;
            // 预防i=(list.size-1)出现的num[list.size]
            int right = (i + 1 < list.size()) ? list.get(i + 1) : 1;
            // 选择
            int total = left * list.get(i) * right;
            int temp = list.remove(i);
            backtrace(list, score + total);
            // 撤销选择
            list.add(i, temp);
        }
    }

    // 方法2：动态规划
    // ***构建两个虚拟气球，即将nums左右边界各扩展一个气球
    public int maxCoins_dp(int[] nums) {
        int n = nums.length;
        int[] points = new int[n + 2];
        points[0] = points[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            points[i] = nums[i - 1];
        }

        // base case
        // dp[i][j]表示戳破point[i]和point[j]之间的气球可获得的最高分数
        int[][] dp = new int[n + 2][n + 2];

        // 当i==j时，最高分数为0
        for (int i = 0; i < points.length; i++) {
            dp[i][i] = 0;
        }

        // 对于i>j的情况是无解的，所以只要求矩阵的一半即可
        for (int i = n; i >= 0; i--) {
            for (int j = i + 1; j < n + 2; j++) {
                // 考虑戳破(i,j)之间的哪个气球
                for (int k = i + 1; k < j; k++) {
                    // dp[i][k]表示将(i,k)间的气球全部戳破得到的最高分数
                    // dp[k][j]表示将(k,j)间的气球全部戳破得到的最高分数
                    // 经过了上述两个步骤，k的相邻气球就为i和j了，所以加上这三个气球的分数
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + points[i] * points[k] * points[j]);
                }
            }
        }
        return dp[0][n + 1];

    }
}
