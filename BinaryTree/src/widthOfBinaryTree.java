import java.util.Deque;
import java.util.LinkedList;

/**
 * @author psj
 * @date 2022/4/3 8:51
 * @File: widthOfBinaryTree.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
// 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树结构相同，但一些节点为空。
// 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度

public class widthOfBinaryTree {
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

    // BFS，每次遍历到一个节点就修改值
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        root.val = 0;
        queue.add(root);
        int result = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            int n = queue.size();
            // 当前队列存储的是这一层的节点，所以队尾节点的值减去队头节点的值再加1为该层的宽度
            result = Math.max(result, queue.getLast().val - queue.getFirst().val + 1);
            for (int i = 0; i < n; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    // 更新当前节点左孩子的值
                    curNode.left.val = curNode.val * 2;
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    // 更新当前节点右孩子的值
                    curNode.right.val = curNode.val * 2 + 1;
                    queue.add(curNode.right);
                }
            }

        }
        return result;
    }
}
