/**
 * @author psj
 * @date 2022/2/9 20:59
 * @File: minDepth.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
// 给定一个二叉树，找出其最小深度。最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS的框架：计算从start到target的最短距离
 * int BFS(int start, int target){
 * Queue<Node> q;
 * Set<Node> visited;  // 避免走回头路
 * // 将起始点加入队列并标记
 * q.offer(start);
 * visited.add(start);
 * <p>
 * int step = 0;  // 记录扩散的步数
 * <p>
 * // 每执行一次while循环相当于向下一层
 * while(!q.isEmpty()){
 * int size = q.size();
 * <p>
 * // 每执行一次for循环相当于将遍历当前层所有节点
 * // 将当前队列中的所有节点向四周扩散(只扩散一步)
 * for(int i=0; i<size; i++){
 * Node cur = q.poll();
 * // 判断是否到达终点
 * if(cur is target){
 * return step;
 * }
 * // 将cur的相邻节点加入队列
 * for(Node x:cur.adj()){
 * if(x not in visited){
 * q.offer(x);
 * visited.add(x);
 * }
 * }
 * }
 * step++;
 * }
 * }
 * <p>
 * tips:
 * 1. DFS也可以找到最短距离，但是时间复杂度高，需要把二叉树中所有树叉遍历完进行对比才知道结果，而BFS相当于一次走一圈节点，可以不遍历完整棵树
 * 2. DFS空间复杂度低，最坏情况为树的高度O(logN)；而BFS中的队列每次会存储当前层的节点总数，空间复杂度为O(N)
 */
public class minDepth {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 方法1：BFS
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                // 判断是否到达叶子节点，第一个到达的叶子节点到根结点的长度就是最小深度，不需要比较直接返回
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                // 加入cur的相邻节点(即孩子节点)
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }

            }

            depth++;
        }

        return depth;
    }

    // 方法2：DFS
    public int minDepth_dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 当前节点左右孩子都为空
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 执行到该步说明至少有一个不为空
        int m1 = minDepth(root.left);
        int m2 = minDepth(root.right);
        if (root.left != null && root.right == null) {
            return m1 + 1;
        }
        if (root.right != null && root.left == null){
            return m2 + 1;
        }
        // 执行到该步说明当前节点左右孩子都不为空
        return Math.min(m1, m2) + 1;
    }
}
