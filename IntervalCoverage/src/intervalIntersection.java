import java.util.ArrayList;
import java.util.List;

/**
 * @author psj
 * @date 2022/3/22 9:43
 * @File: intervalIntersection.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/interval-list-intersections/
// 给定两个由一些闭区间组成的列表firstList和secondList,返回这两个区间列表的交集
public class intervalIntersection {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // 设置两个指针分别在两个列表中遍历
        int i = 0, j = 0;

        List<int[]> result = new ArrayList<>();
        while (i < firstList.length && j < secondList.length) {
            int begin_i = firstList[i][0];
            int end_i = firstList[i][1];
            int begin_j = secondList[j][0];
            int end_j = secondList[j][1];
            // 如果两个区间存在交集
            if (!(end_j < begin_i || begin_j > end_i)) {
                // 取两个区间起点的最大值和终点的最小值作为相交区间的端点
                result.add(new int[]{Math.max(begin_i, begin_j), Math.min(end_i, end_j)});
            }
            // 一个区间可能会包含另一个列表的多个区间
            // firstList的当前区间包含了secondList的当前区间，则移动secondList上的指针
            if (end_j < end_i){
                j++;
            }else {
                i++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
