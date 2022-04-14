import java.util.Deque;
import java.util.LinkedList;

/**
 * @author psj
 * @date 2022/4/14 9:14
 * @File: shortestSubarray.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k/
//给你一个整数数组nums和一个整数k，找出 nums中和至少为k的最短非空子数组，并返回该子数组的长度。如果不存在这样的子数组，返回 -1

public class shortestSubarray {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        // 构建前缀和数组
        int[] sumPre = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sumPre[i] = sumPre[i - 1] + nums[i - 1];
        }

        Deque<Integer> q = new LinkedList<>();
        int minLength = Integer.MAX_VALUE;

        // 维护一个递增的单调队列，队首元素为最小值
        // 假设当前元素是j，队列中的元素i1,i2...都是满足sumPre[j]-sumPre[i1]>=k的
        // 由于是单调队列，所以队尾元素的下标离j最近，所以子数组长度最小
        for (int i = 0; i <= n; i++) {
            while (!q.isEmpty() && sumPre[q.peekLast()] >= sumPre[i]) {
                q.pollLast();
            }
            while (!q.isEmpty() && sumPre[i] - sumPre[q.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - q.pollFirst());
            }
            q.addLast(i);
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

}
