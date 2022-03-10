import java.util.Arrays;
import java.util.Comparator;

/**
 * @author psj
 * @date 2022/3/10 10:07
 * @File: findMinArrowShots.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
// 参考eraseOverlapIntervals

/**
 * 在compare函数中，如果直接使用o1[1]-o2[1]可能会找出溢出，比如o1[1]=2147483647(int类型的最大值),o2[1]=-100
 * 所以最好使用o1[1]>o2[1]的形式
 */
public class findMinArrowShots {
    // 求出最多有多少区间不重叠即为最少的射箭次数(比如第一箭就能把第一个x区间以及和其重叠的区间射爆)
    // 和eraseOverlapIntervals问题不同，如果两个区间边界相等，也可以射爆(即也算重叠)
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        // 还是按end值从小到大进行排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // return o1[1] - o2[1];
                if (o1[1] < o2[1]) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        // 统计最多不重叠区间数
        int count = 1;
        int x_end = points[0][1];
        for (int[] point : points) {
            int start = point[0];
            if (start > x_end) {
                count++;
                // 新的x区间就是和原来x不重叠且最近的一个区间(for循环可以保证是最近的)
                x_end = point[1];
            }
            // 和x区间有重叠的区间已经被跳过(即删除)
        }

        // 这里不是points.length-count
        return count;
    }
}
