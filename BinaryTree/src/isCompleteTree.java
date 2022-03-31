import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/3/31 8:44
 * @File: isCompleteTree.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree/
// 给定一个二叉树的 root ，确定它是否是一个完全二叉树

public class isCompleteTree {
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

    // 方法1：层次遍历
    public boolean isCompleteTree_BFS(TreeNode root) {
        boolean flag = true;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.pollFirst();
                // 当前节点的左孩子不为空时
                if (curNode.left != null) {
                    // 上一个遍历节点的右孩子为空，当前节点应该没有节点的
                    // 现在有一个左孩子，说明不是完全二叉树
                    if (!flag) {
                        return false;
                    }
                    queue.addLast(curNode.left);
                    // 当前节点没有左孩子，将flag置为flase，为了下一个遍历的节点进行验证
                } else {
                    flag = false;
                }
                // 当前节点的右孩子不为空时
                if (curNode.right != null) {
                    // 当前节点的左孩子应该不为空，如果为空在上面的if判断中会置为false
                    if (!flag) {
                        return false;
                    }
                    queue.addLast(curNode.right);
                    // 当前节点没有右孩子，将flag置为flase，为了下一个遍历的节点进行验证
                } else {
                    flag = false;
                }
            }
        }

        return true;
    }

    // 方法2：DFS
    int size = 0;  // 树的节点总数
    int finalIndex = 0;  // 最后一个节点的下标

    public boolean isCompleteTree(TreeNode root) {
        // 即判断树的节点总数是否等于最后一个节点的下标(从1开始)
        if (root == null) {
            return true;
        }

        dfs(root, 1);
        return size == finalIndex;
    }

    public void dfs(TreeNode root, int index) {
        if (root == null) {
            return;
        }

        size++;
        finalIndex = Math.max(finalIndex, index);
        // 遍历当前节点的左子树，左子树的根节点下标为index*2
        dfs(root.left, index * 2);
        // 遍历当前节点的右子树，右子树的根节点下标为index*2+1
        dfs(root.right, index * 2 + 1);
    }

}
