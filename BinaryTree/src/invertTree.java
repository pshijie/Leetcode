/**
 * @author psj
 * @date 2022/1/5 20:28
 * @File: invertTree.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/invert-binary-tree/
// 翻转一棵二叉树，即将每个节点的左右节点都进行交换
public class invertTree {
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

    public TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) {
            return null;
        }

        // 前序遍历位置
        // 1. 先交换root节点的左右子节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        // 2. 让左右子节点继续交换其子节点
        invertTree(root.left);
        invertTree(root.right);

        // 3. 执行到这说明root的左右子树已经完成翻转
        return root;
    }
}
