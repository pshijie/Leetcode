import java.util.LinkedList;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/4/16 9:51
 * @File: mirrorTree.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
// 请完成一个函数，输入一个二叉树，该函数输出它的镜像

public class mirrorTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 方法1：dfs
    public TreeNode mirrorTree_dfs(TreeNode root) {
        if (root == null) {
            return root;
        }

        // 就是交换镜像处理后的左右子树
        TreeNode temp = mirrorTree(root.right);
        root.right = mirrorTree(root.left);
        root.left = temp;
        return root;

    }

    // 方法2：bfs
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode.left != null) {
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
            }
            // 这里把指针交换了，所以下一轮while循环最先poll的实际是curNode的右孩子节点
            TreeNode temp = curNode.left;
            curNode.left = curNode.right;
            curNode.right = temp;
        }
        return root;
    }

}
