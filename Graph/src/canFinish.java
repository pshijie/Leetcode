import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/4/18 8:47
 * @File: canFinish.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/course-schedule/
// 在选修某些课程之前需要一些先修课程。先修课程按数组prerequisites给出，其中prerequisites[i]=[ai, bi]，表示如果要学习课程ai则必须先学习课程 bi
// 请你判断是否可能完成所有课程的学习？如果可以，返回true；否则，返回false

public class canFinish {
    // 方法1：dfs遍历
    // 记录每一次递归后栈中的节点，用于判断是否有环(记录当前traverse经过的路径)
    boolean[] onPath;
    // 标记走过的节点，防止回头
    boolean[] visited;
    // 是否有环
    boolean hasCycle = false;

    public boolean canFinish_dfs(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            // 遍历图中所有节点，判断以该节点出发是否有环
            travserse(graph, i);
        }
        return !hasCycle;
    }

    public void travserse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            hasCycle = true;
        }

        // 已经遍历过了当前节点，或者已经找到环就不需要再往下遍历
        if (visited[s] || hasCycle) {
            return;
        }
        visited[s] = true;
        onPath[s] = true;
        for (int t : graph[s]) {
            travserse(graph, t);
        }
        onPath[s] = false;
        // visited不需要再置为false

    }

    // 构建邻接表
    public static List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
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

    // 方法2：BFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 构建入度数组
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            indegree[to]++;
        }
        // 存储入度为0的节点的下标
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        // 记录被遍历的节点个数(即处理后所有节点的入度均为0)
        int count = 0;
        while (!queue.isEmpty()) {
            // 将当前入度为0的节点弹出并将其指向的节点入度减1
            int cur = queue.poll();
            count++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0){
                    queue.offer(next);
                }
            }
        }

        // 判断所有节点是否都被遍历一遍(即所有节点处理后入度均为0)
        return count == numCourses;
    }

}
