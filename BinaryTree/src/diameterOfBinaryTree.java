/**
 * @author psj
 * @date 2022/4/1 9:03
 * @File: diameterOfBinaryTree.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/diameter-of-binary-tree/
// 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点
public class diameterOfBinaryTree {
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

    // 计算每个节点的左右孩子节点的各自最大深度的和即可
    int result = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return result;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        // 深度的和不需要加1，直径为这条直径经过的节点总数-1
        result = Math.max(result, (leftDepth + rightDepth));
        dfs(root.left);
        dfs(root.right);
    }

    // 计算当前节点为根节点的二叉树最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return 1 + Math.max(leftDepth, rightDepth);

    }
}
