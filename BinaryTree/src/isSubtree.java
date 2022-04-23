import java.util.List;

/**
 * @author psj
 * @date 2022/4/23 9:03
 * @File: isSubtree.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/subtree-of-another-tree/
// 给你两棵二叉树root和subRoot。检验root中是否包含和subRoot具有相同结构和节点值的子树。如果存在，返回true；否则返回false

public class isSubtree {
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

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        // 依次判断root为根节点的树、root.left为根节点的树以及root.right为根节点的树
        // 其中如果判断的是以root为根节点的树则直接使用isSameTree
        return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // 判断两颗树是否相等(先序遍历)
    public boolean isSameTree(TreeNode s, TreeNode t) {
        // 当两个树都为null时说明变量到底了
        if (s == null && t == null) {
            return true;
        }
        // 执行到该步说明一个没有遍历到底，另一个已经遍历到底了
        if (s == null || t == null) {
            return false;
        }
        // 判断两个树遍历到的当前节点的值是否相等
        if (s.val != t.val) {
            return false;
        }
        // 如果当前节点的值相等,就比较的两个树对应的左子树和对应的右子树
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

}
