import java.util.LinkedList;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/3/21 10:08
 * @File: sumNumbers.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
// 给你一个二叉树的根节点 root ，树中每个节点都存放有一个0到 9之间的数字。
// 每条从根节点到叶节点的路径都代表一个数字.计算从根节点到叶节点生成的所有数字之和

public class sumNumbers {
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

    // 方法1:DFS,将左右子树的总和分别算出来
    public int sumNumbers_dfs(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int preSum) {
        if (root == null) {
            return 0;
        }

        // 该步已经将当前节点计算在内
        int sum = preSum * 10 + root.val;

        if (root.left == null && root.right == null) {
            return sum;
        }

        return dfs(root.left, sum) + dfs(root.right, sum);

    }

    // 方法2：BFS，每次遍历一个节点就把当前的值加到之前的和中
    public int sumNumbers_Bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 0;
        // 下面两个队列的元素是一一对应的
        // 该队列存储BFS遍历的节点
        Queue<TreeNode> queueTreeNode = new LinkedList<>();
        // 该队列由于存储遍历到当前节点后的当前节点的累加和
        Queue<Integer> queueNum = new LinkedList<>();
        queueTreeNode.offer(root);
        queueNum.offer(root.val);

        while (!queueTreeNode.isEmpty()) {
            TreeNode curNode = queueTreeNode.poll();
            int curNum = queueNum.poll();
            TreeNode left = curNode.left;
            TreeNode right = curNode.right;
            if (left == null && right == null) {
                sum += curNum;
            }
            if (left != null) {
                queueTreeNode.offer(left);
                queueNum.offer(left.val + curNum * 10);
            }
            if (right != null) {
                queueTreeNode.offer(right);
                queueNum.offer(right.val + curNum * 10);
            }

        }

        return sum;

    }
}
