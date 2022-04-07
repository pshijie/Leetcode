import com.sun.source.tree.Tree;

/**
 * @author psj
 * @date 2022/4/7 8:40
 * @File: isBalanced.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/balanced-binary-tree/
// 给定一个二叉树，判断它是否是高度平衡的二叉树。
// 一棵高度平衡二叉树定义为：一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1 。

public class isBalanced {
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

    public boolean isBalanced(TreeNode root) {
        return getMaxHeight(root) != -1;
    }

    // 判断以node为根的数是否为高度平衡二叉树，是的话就返回该数的高度，不是的话就返回-1
    public int getMaxHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getMaxHeight(node.left);
        if (left == -1) {
            return -1;
        }
        int right = getMaxHeight(node.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) >= 2 ? -1 : Math.max(left, right) + 1;
    }
}
