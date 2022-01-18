/**
 * @author psj
 * @date 2022/1/18 21:41
 * @File: searchBST.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
// 给定二叉搜索树（BST）的根节点和一个值。你需要在BST中找到节点值等于给定值的节点。返回以该节点为根的子树。如果节点不存在，则返回NULL。
public class searchBST {
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

    // 不考虑BST的性质的话，就采用普通二叉树性质去处理：
    public TreeNode searchBST_1(TreeNode root, int val) {
        // base case
        if (root == null) {
            return null;
        }

        // 1.判断当前节点的值是否等于目标值
        if (root.val == val) {
            return root;
        }

        // 2.判断左子树是否存在目标值
        TreeNode left = searchBST_1(root.left, val);
        // 3.判断右子树是否存在目标值
        TreeNode right = searchBST_1(root.right, val);

        //  返回找到的值，没有找到就返回null
        return left != null ? left : right;

    }

    // 考虑BST的性质，采取类似二分类的思想
    public TreeNode searchBST(TreeNode root, int val) {
        // base case
        if (root == null) {
            return null;
        }

        // 1.如果目标值小于当前值，就去左子树搜索
        if (root.val > val) {
            return searchBST(root.left, val);
        }

        // 2.如果目标值大于当前值，就去右子树搜索
        if (root.val < val) {
            return searchBST(root.right, val);
        }

        // 3.如果目标值等于当前节点值，返回当前节点
        return root;

    }
}
