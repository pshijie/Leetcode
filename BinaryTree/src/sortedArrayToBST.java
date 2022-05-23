/**
 * @author psj
 * @date 2022/5/23 9:20
 * @File: sortedArrayToBST.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
// 给你一个整数数组nums ，其中元素已经按升序排列，请你将其转换为一棵高度平衡二叉搜索树。
// 高度平衡二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过1」的二叉树

public class sortedArrayToBST {
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

    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    public TreeNode dfs(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        // BST转换为数组后正好为升序数组,所以取数组中点为当前树的根节点
        // 同时还保证了左右子树的高度满足高度平衡树的条件
        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, lo, mid - 1);
        root.right = dfs(nums, mid + 1, hi);
        return root;
    }
}
