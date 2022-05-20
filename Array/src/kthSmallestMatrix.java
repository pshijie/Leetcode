import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author psj
 * @date 2022/5/20 9:03
 * @File: kthSmallest.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/
// 给你一个nxn矩阵matrix，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素

public class kthSmallestMatrix {
    // 方法1:转为一维数组
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] array = new int[m * n];
        int index = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                array[index] = num;
                index++;
            }
        }
        Arrays.sort(array);
        return array[k - 1];
    }

    // 方法2:堆
    // 1.把每一行的第一列元素放入堆中形成初始堆
    // 2.每次弹出小根堆的堆顶元素(记录了该元素所在行和列),并将该元素所在行的下一个元素入堆
    // 3.弹出的个数为k时停止，此时弹出的元素就是第k小的元素
    public int kthSmallest_heap(int[][] matrix, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // o1/o2数组存储两个元素，分别为行数和列数(就是每一行的最小值坐标)
                return matrix[o1[0]][o1[1]] - matrix[o2[0]][o2[1]];
            }
        });
        int m = matrix.length;
        for (int i = 0; i < m; i++) {
            queue.offer(new int[]{i, 0});
        }
        while (--k > 0) {
            int[] curMin = queue.poll();
            // x和y是min所在行的下一个元素坐标
            int x = curMin[0], y = curMin[1] + 1;
            if (y < m) {
                queue.offer(new int[]{x, y});
            }
        }
        int[] min = queue.poll();
        return matrix[min[0]][min[1]];
    }

}