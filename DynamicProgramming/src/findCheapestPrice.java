import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/2/28 9:30
 * @File: findCheapestPrice.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/
// 有n个城市通过一些航班连接。给你一个数组flights，其中flights[i] = [fromi, toi, pricei]，表示该航班都从城市fromi开始，以价格pricei抵达toi
// 现在给定所有的城市和航班，以及出发城市src和目的地dst，找到出一条最多经过k站中转的路线，使得从src到dst的价格最便宜 ，并返回该价格。如果不存在这样的路线，则输出-1

public class findCheapestPrice {

    // 方法1：动态规划(备忘录)
    //     --.....s1
    //    /       \w1     dp(s,k)表示从src到s在k步内的最便宜的价格
    // src         dest   题目转换为求dp(s1,k-1)+w1和dp(s2,k-1)+w2的大小
    //    \       /w2
    //     --.....s2
    // 记录每一个点的入度节点和边的权重[from, price]
    HashMap<Integer, List<int[]>> indegree;
    int[][] memo;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // k个中转站表示k+1条边
        k++;
        memo = new int[n][k + 1];
        indegree = new HashMap<>();
        for (int[] f : flights) {
            int from = f[0];
            int to = f[1];
            int price = f[2];
            indegree.putIfAbsent(to, new LinkedList<>());
            indegree.get(to).add(new int[]{from, price});
        }

        for (int[] row : memo) {
            Arrays.fill(row, -2);
        }

        return dp(src, dst, k);
    }

    // dp(s,k)表示从src到s在k步内的最便宜的价格
    public int dp(int src, int s, int k) {
        // base case
        if (s == src) {
            return 0;
        }

        if (k == 0) {
            return -1;
        }

        if (memo[s][k] != -2) {
            return memo[s][k];
        }
        int res = Integer.MAX_VALUE;
        if (indegree.containsKey(s)) {
            for (int[] v : indegree.get(s)) {
                // s的相邻节点
                int from = v[0];
                int price = v[1];
                // 从src到s的相邻节点的最便宜价格
                int subProblem = dp(src, from, k - 1);
                // 无解就不需要考虑
                if (subProblem != -1) {
                    res = Math.min(res, subProblem + price);
                }
            }
        }


        memo[s][k] = res == Integer.MAX_VALUE ? -1 : res;
        // 如果值还是Integer.MAX_VALUE说明找不到路径
        return memo[s][k];
    }

}
