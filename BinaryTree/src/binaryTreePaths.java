import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/5/27 9:30
 * @File: binaryTreePaths.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/binary-tree-paths/
// 给你一个二叉树的根节点root，按任意顺序，返回所有从根节点到叶子节点的路径

public class binaryTreePaths {
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

    // 方法1:回溯
    public List<String> binaryTreePaths_backtrace(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> path = new ArrayList<>();
        backtrace(root, path, result);
        return result;
    }

    public void backtrace(TreeNode node, List<Integer> path, List<String> result) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size() - 1));
            result.add(sb.toString());
            return;
        }
        if (node.left != null) {
            backtrace(node.left, path, result);
            path.remove(path.size() - 1);
        }
        if (node.right != null) {
            backtrace(node.right, path, result);
            path.remove(path.size() - 1);
        }
    }

    // 方法2:dfs
    public List<String> binaryTreePaths_dfs(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfs(root, "", result);
        return result;
    }

    public void dfs(TreeNode root, String path, List<String> result) {
        if (root != null) {
            StringBuffer sb = new StringBuffer(path);
            sb.append(Integer.toString(root.val));
            // 当前节点是叶子节点
            if (root.left == null && root.right == null) {
                result.add(sb.toString());
            } else {
                sb.append("->");
                dfs(root.left, sb.toString(), result);
                dfs(root.right, sb.toString(), result);
            }
        }
    }

    // 方法3:bfs
    public List<String> binaryTreePaths_bfs(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        if (root == null) {
            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<String> pathQueue = new LinkedList<String>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();
            if (node.left == null && node.right == null) {
                paths.add(path);
            } else {
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    pathQueue.offer(new StringBuffer(path).append("->").append(node.left.val).toString());
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    pathQueue.offer(new StringBuffer(path).append("->").append(node.right.val).toString());
                }
            }
        }
        return paths;
    }
}