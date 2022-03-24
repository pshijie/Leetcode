import java.util.LinkedList;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/3/24 8:49
 * @File: hasPathSum.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/path-sum/
// 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum。判断该树中是否存在 根节点到叶子节点的路径，
// 这条路径上所有节点值相加等于目标和targetSum 。如果存在返回true ；否则返回false

public class hasPathSum {
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

    // 方法1：dfs
    public boolean hasPathSum_DFS(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        int curSum = 0;
        return dfs(root, curSum, targetSum);
    }

    public boolean dfs(TreeNode root, int curSum, int targetSum) {
        if (root == null) {
            return false;
        }
        curSum += root.val;
        if (root.left == null && root.right == null) {
            if (curSum == targetSum) {
                return true;
            } else {
                return false;
            }
        }

        return dfs(root.left, curSum, targetSum) || dfs(root.right, curSum, targetSum);
    }

    // 方法2：BFS
    public boolean hasPathSum_BFS(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueNum = new LinkedList<>();

        queueNode.add(root);
        queueNum.add(root.val);

        while (!queueNode.isEmpty()) {
            TreeNode curNode = queueNode.poll();
            int curNum = queueNum.poll();
            if (curNode.left == null && curNode.right == null) {
                if (curNum == targetSum) {
                    return true;
                }
            }
            if (curNode.left != null) {
                queueNode.add(curNode.left);
                queueNum.add(curNode.left.val + curNum);
            }
            if (curNode.right != null) {
                queueNode.add(curNode.right);
                queueNum.add(curNode.right.val + curNum);
            }
        }
        return false;
    }
}
