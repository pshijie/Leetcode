import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/5/22 8:53
 * @File: levelOrderBottom.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
// 给你二叉树的根节点root，返回其节点值自底向上的层序遍历。即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历

public class levelOrderBottom {
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

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        // 前部分代码和正常层次遍历一致,只不过将最终结果进行反向输出
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode peek = queue.peekFirst();
                levelList.add(queue.pollFirst().val);
                if (peek.left != null) {
                    queue.offerLast(peek.left);
                }
                if (peek.right != null) {
                    queue.offerLast(peek.right);
                }
            }
            list.add(levelList);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }
}
