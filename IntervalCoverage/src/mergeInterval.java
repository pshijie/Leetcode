import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author psj
 * @date 2022/3/22 9:18
 * @File: merge.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/merge-intervals/
// 以数组intervals表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]
// 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间

public class mergeInterval {
    public int[][] merge(int[][] intervals) {
        // 每个数组按起点进行升序排列
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });

        ArrayList<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] compare = result.get(result.size() - 1);
            // 如果当前区间的起点大于合并区间的终点，则当前区间称为新的不重叠区间
            if (intervals[i][0] > compare[1]){
                result.add(intervals[i]);
            // 如果当前区间的起点小于合并区间终点，则更新合并区间的终点值(取合并区间的当前终点值和当前区间的终点值中的最大值)
            }else {
                int[] newInterval = new int[]{compare[0], Math.max(intervals[i][1], compare[1])};
                result.set(result.size()-1, newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}
