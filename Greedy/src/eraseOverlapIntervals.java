/**
 * @author psj
 * @date 2022/3/10 9:37
 * @File: eraseOverlapIntervals.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/non-overlapping-intervals/
// 给定一个区间的集合intervals ，其中intervals[i]=[starti, endi]。返回需要移除区间的最小数量，使剩余区间互不重叠

import java.util.Arrays;
import java.util.Comparator;

/**
 * 贪心算法即从局部最优最后到全局最优，因为贪心需要满足更多的条件，所以效率要比动态规划高(动态规划本质就是消去重叠子问题)
 */
public class eraseOverlapIntervals {
    // 要计算出需要移除区间的最小数量，即计算出最多有多少区间不重叠
    // 思路：
    //     1.从区间中选择一个在所有区间结束最早的区间x(即end最小)
    //     2.把所有和x区间相交的区间从集合中删除，并将x区间记录下来
    //     3.重复1和2(再次执行步骤1时是考虑除原来x和被删除区间外的区间)，直到集合为空，
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 按照每个区间的end值从小到大排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        // 假设所有和x区间有重叠，则最后结果为1
        int count = 1;
        int x_end = intervals[0][1];
        for (int[] interval : intervals) {
            // 如果被比较的区间的start值小于x_end，说明重叠(不可能存在被比价区间的end值还小于x_start的情况，因为已经按end的值排序了)
            int start = interval[0];
            if (start >= x_end) {
                count++;
                // 新的x区间就是和原来x不重叠且最近的一个区间(for循环可以保证是最近的)
                x_end = interval[1];
            }
            // 和x区间有重叠的区间已经被跳过(即删除)
        }

        return intervals.length - count;

    }
}
