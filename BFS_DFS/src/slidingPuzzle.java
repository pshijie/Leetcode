import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/2/14 20:08
 * @File: slidingPuzzle.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/sliding-puzzle/
// 在一个2 x 3的板上有5块砖瓦，用数字1~5来表示, 以及一块空缺用0来表示。一次移动定义为选择0与一个相邻的数字进行交换。
// 最终当板的结果是[[1,2,3],[4,5,0]]谜板被解开。给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回-1

// 涉及到寻路的问题（从start到target），就可以联想到BFS：
//                        2 4 1
//                        5 0 3
//                     /    |    \
//                 2 0 1  2 4 1  2 4 1
//                 5 4 3  0 5 3  5 3 0

public class slidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        int m = 2, n = 3;
        StringBuilder sb = new StringBuilder();
        // 将起点和目标数组采用字符串表示
        String target = "123450";
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();  // 以图中二维数组为例: 241503

        // 记录一维数组的相邻索引
        // 以241503为例，索引0对应数字2，2在二维数组的相邻数字为4和5，而4和5在一维数组上的索引为1和3，所以neighbor[0]={1,3}
        int[][] neighbor = new int[][]{
                {1, 3},
                {0, 4, 2},
                {1, 5},
                {0, 4},
                {3, 1, 5},
                {4, 2}

        };

        Queue<String> q = new LinkedList<>();
        HashSet<String> visited = new HashSet();

        q.offer(start);
        visited.add(start);

        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                // 判断是否到达目标数组
                if (cur.equals(target)) {
                    return step;
                }
                // 找到数字0在字符串cur中的索引
                int index = 0;
                for (; cur.charAt(index) != '0'; index++) ;
                // 将数字0和相邻的数字交换位置
                for (int adj : neighbor[index]) {
                    String newCur = swap(cur.toCharArray(), adj, index);
                    if (!visited.contains(newCur)) {
                        q.offer(newCur);
                        visited.add(newCur);
                    }

                }
            }
            step++;
        }
        return -1;
    }

    // 交换curCharArr的i位置和j位置上的字符
    public String swap(char[] curCharArr, int i, int j) {
        char temp = curCharArr[i];
        curCharArr[i] = curCharArr[j];
        curCharArr[j] = temp;
        return new String(curCharArr);
    }
}
