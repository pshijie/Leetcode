/**
 * @author psj
 * @date 2022/1/24 22:16
 * @File: maxSumBST.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/maximum-sum-bst-in-binary-tree/
// 给你一棵以root为根的二叉树，该树的子树可能包含二叉搜索树，请找到节点和最大的那棵BST并返回它的节点值之和
// 分析：
//      首先需要知道的信息有：
//          1. 左右子树是否为合法的BST
//          2. 以当前节点为根的树是否为BST（即判断当前节点的值是否大于左子树的最大值，小于右子树的最小值）
//          3. 左右子树节点之和

public class maxSumBST {
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

    class ReturnType {
        public boolean isBST;
        public int max;
        public int min;
        public int sum;

        public ReturnType(boolean isBST, int max, int min, int sum) {
            this.isBST = isBST;  // 该树是否为BST
            this.max = max;  // 该树的最大值
            this.min = min;  // 该树的最小值
            this.sum = sum;  // 该树的节点和
        }

    }

    int res = 0;  // 记录结果

    public int maxSumBST(TreeNode root) {
        helper(root);
        return res;
    }

    public ReturnType helper(TreeNode root) {
        if (root == null) {
            return new ReturnType(true, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        }

        // 1. 递归得到左右子树的信息
        ReturnType left = helper(root.left);
        ReturnType right = helper(root.right);

        // 2. 根据上述提高的信息判断左右子树是否为BST以及以root为根节点的树是否为BST
        if (left.isBST && right.isBST && left.max < root.val && right.min > root.val){
            int curSum = left.sum + right.sum + root.val;
            res = Math.max(res, curSum);
            // 3. 如果左子树为空，则该树的最小值为root的值(因为root为根节点的树是为BST，其右子树的所有值一定大于root的值)
            //    如果右子树为空，则该树的最大值为root的值(因为root为根节点的树是为BST，其左子树的所有值一定小于root的值)
            int min = root.left == null ? root.val : left.min;
            int max = root.right == null ? root.val : right.max;
            return new ReturnType(true, max, min, curSum);
        }

        // 4. 如果以root为根节点的树不是BST，只返回当前节点的信息
        return new ReturnType(false, root.val, root.val, root.val);

    }
}
