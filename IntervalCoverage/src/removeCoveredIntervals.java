import java.util.Arrays;

/**
 * @author psj
 * @date 2022/3/22 8:40
 * @File: removeCoveredIntervals.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/remove-covered-intervals/
// 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
// 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
// 在完成所有删除操作后，请你返回列表中剩余区间的数目

public class removeCoveredIntervals {
    // 方法1：暴力法
    public int removeCoveredIntervals_force(int[][] intervals) {
        int result = 0;
        // 检查第i个区间是否被其他区间包含
        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < intervals.length; j++) {
                if (i != j && intervals[j][0] <= intervals[i][0] && intervals[i][1] <= intervals[j][1]) {
                    result++;
                    break;
                }
            }
        }
        return intervals.length - result;
    }

    // 方法2：扫描线法
    public int removeCoveredIntervals(int[][] intervals) {
        // 将每个线段以起点升序的方式排列，如果起点值一样就按终点降序的方式排列
        // 为什么终点要降序?
        // intervals[0]：-------
        // intervals[1]：----------
        // 此时intervals[0]是被intervals[1]覆盖的但是执行判断代码时
        // 由于intervals[1][1]>intervals[0][1](此时为right)，不会执行result++
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int result = 0;

        // 记录合并区间的起点和终点,初始化为第一个区间的值
        int left = intervals[0][0];
        int right = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int[] intv = intervals[i];
            // 情况1：当前区间被合并区间覆盖
            if (left <= intv[0] && right >= intv[1]) {
                result++;
            }

            // 情况2：两个区间相交，将它们进行合并
            // intervals[0]：-----------
            // intervals[1]：  -----------
            // intervals[2]：    --------
            // 将前两个区间合并后,intervals[2]被合并区间覆盖也就意味着被intervals[1]覆盖
            // 合并区间起点不需要修改，因为已经按起点进行升序排列
            if (right >= intv[0] && right <= intv[1]) {
                right = intv[1];
            }
            // 情况3：完全不相交，更新起点和终点
            if (right < intv[0]) {
                left = intv[0];
                right = intv[1];
            }
        }
        return intervals.length - result;
    }

}
