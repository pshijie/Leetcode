import java.util.LinkedList;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/3/23 8:55
 * @File: isSymmetric.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/symmetric-tree/
// 给你一个二叉树的根节点root ， 检查它是否轴对称

public class isSymmetric {
    static class TreeNode {
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

    // 方法1：dfs实现
    public boolean isSymmetric_dfs(TreeNode root) {
        if (root == null) {
            return true;
        }

        return dfs(root.left, root.right);
    }


    public boolean dfs(TreeNode left, TreeNode right) {
        // 两个节点都为null
        if (left == null && right == null) {
            return true;
        }
        // 其中一个节点为null(不可能存在两个节点都为null，因为上面已经做了判断)
        if (left == null || right == null) {
            return false;
        }
        // 两个节点的值不相等
        if (left.val != right.val) {
            return false;
        }
        // 每次递归比较当前节点左孩子的左孩子和当前节点右孩子的右孩子
        // 以及当前节点左孩子的右孩子和当前节点右孩子的左孩子
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    // 方法2：队列实现
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            // 每次从队列中取出两个元素
            // 可能是左孩子的左孩子和右孩子的右孩子
            // 也可能是左孩子的右孩子和右孩子的左孩子
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            // 进行下面两个节点的比较
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            // 将左孩子的左孩子和右孩子的右孩子成对放入
            // 将左孩子的右孩子和右孩子的左孩子成对放入
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }
}
