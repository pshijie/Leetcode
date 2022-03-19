import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/3/19 9:43
 * @File: levelOrder.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
// 给你二叉树的根节点root ，返回其节点值的层序遍历（即逐层地，从左到右访问所有节点）

public class levelOrder {
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();


        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                list.add(curNode.val);
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }

            result.add(list);
        }
        return result;
    }
}
