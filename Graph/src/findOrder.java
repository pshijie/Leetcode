import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/5/6 8:59
 * @File: findOrder.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/course-schedule-ii/

public class findOrder {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 计算入度
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            indegree[to] += 1;
        }
        // 根据入度初始化队列中的节点
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            // 入度为0
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        int[] result = new int[numCourses];
        int index = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            result[index] = cur;
            index++;
            // 弹出一个节点后把相应节点的入度减1
            for (int next : graph[cur]) {
                indegree[next]--;
                // 减完后如果该节点的入度为0，则入队列
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        if (index != numCourses) {
            return new int[]{};
        }
        return result;

    }

    // 建图
    public List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }
}
