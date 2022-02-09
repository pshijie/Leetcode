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
 *      Queue<Node> q;
 *      Set<Node> visited;  // 避免走回头路
 *      // 将起始点加入队列并标记
 *      q.offer(start);
 *      visited.add(start);
 *
 *      int step = 0;  // 记录扩散的步数
 *
 *      while(!q.isEmpty()){
 *          int size = q.size();
 *          // 将当前队列中的所有节点向四周扩散(只扩散一步)
 *          for(int i=0; i<size; i++){
 *              Node cur = q.poll();
 *              // 判断是否到达终点
 *              if(cur is target){
 *                  return step;
 *              }
 *              // 将cur的相邻节点加入队列
 *              for(Node x:cur.adj()){
 *                  if(x not in visited){
 *                      q.offer(x);
 *                      visited.add(x);
 *                  }
 *              }
 *          }
 *          step++;
 *      }
 * }
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

    public int minDepth(TreeNode root) {
        if (root == null){
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;

        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                // 判断是否到达叶子节点，第一个到达的叶子节点到根结点的长度就是最小深度，不需要比较直接返回
                if (cur.left == null && cur.right == null){
                    return depth;
                }
                // 加入cur的相邻节点(即孩子节点)
                if (cur.left != null){
                    q.offer(cur.left);
                }
                if (cur.right != null){
                    q.offer(cur.right);
                }

            }

            depth++;
        }

        return depth;
    }
}
