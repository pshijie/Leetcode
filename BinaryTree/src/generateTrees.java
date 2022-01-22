import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/1/22 17:12
 * @File: generateTrees.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
// 给你一个整数n ，请你生成并返回所有由n个节点组成且节点值从1到n互不相同的不同二叉搜索树。可以按任意顺序返回答案。

public class generateTrees {
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

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }

        return build(1, n);
    }

    List<TreeNode> build(int lo, int hi) {
        List<TreeNode> res = new LinkedList<>();

        // base case
        if (lo > hi) {
            res.add(null);
            return res;
        }

        // 1. 穷举root节点
        for (int i = lo; i <= hi; i++) {
            // 2. 递归构造左子树所有BST
            List<TreeNode> leftTree = build(lo, i - 1);
            // 3. 递归构造右子树所有BST
            List<TreeNode> rightTree = build(i + 1, hi);

            // 4. 给当前值为i的节点分配左右子树
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }

        }

        return res;
    }
}
