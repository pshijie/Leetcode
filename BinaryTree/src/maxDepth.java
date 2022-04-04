import java.util.LinkedList;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/4/4 9:00
 * @File: maxDepth.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
// 给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数

public class maxDepth {
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

    // 方法1：DFS
    public int maxDepth_dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return dfs(root);
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(dfs(root.left), dfs(root.right));
    }

    // 方法2:BFS
    public int c_bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int maxDepth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
                size--;
            }
            maxDepth++;
        }
        return maxDepth;
    }
}
