import java.util.*;

/**
 * @author psj
 * @date 2022/4/5 9:40
 * @File: haveCircularDependency.java
 * @Software: IntelliJ IDEA
 */
// https://mp.weixin.qq.com/s/pCRscwKqQdYYN7M1Sia7xA
// 现有n个编译项，编号为0 ~ n-1。给定一个二维数组，表示编译项之间有依赖关系。如[0, 1]表示1依赖于0。
// 若存在循环依赖则返回空；不存在依赖则返回可行的编译顺序

public class haveCircularDependency {
    // 实现拓扑排序
    public int[] haveCircularDependency(int n, int[][] prerequisites) {
        // 使用邻接表存储图结构
        List<Integer>[] g = new LinkedList[n];
        // 存储每个点的入度
        int[] indegree = new int[n];

        List<Integer> result = new LinkedList<Integer>();

        for (int i = 0; i < prerequisites.length; i++) {
            int inNode = prerequisites[i][0];
            int outNode = prerequisites[i][1];
            // 构建邻接表
            g[inNode].add(outNode);
            // 修改入度
            indegree[outNode]++;
        }

        // 存储入度为0的节点
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.push(i);
            }
        }

        // 将入度为0的节点依次移除，每移除一个就将相连节点的入度减1，然后再判断是否入度为0
        // 为0的话加入到queue中
        while (!queue.isEmpty()) {
            int curNUum = queue.poll();
            result.add(curNUum);
            for (int i = 0; i < g[curNUum].size(); i++) {
                int tempOut = g[curNUum].get(i);
                indegree[tempOut]--;
                if (indegree[tempOut] == 0) {
                    queue.push(tempOut);
                }
            }
        }

        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        // 如果结果集中节点个数不为n，说明有节点存在环
        if (arr.length == n) {
            return arr;
        } else {
            return null;
        }

    }
}
