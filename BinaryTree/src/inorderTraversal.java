import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author psj
 * @date 2022/3/26 9:01
 * @File: inorderTraversal.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
// 给定一个二叉树的根节点root ，返回它的中序遍历

public class inorderTraversal {
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

    // 方法1：递归实现
    public List<Integer> inorderTraversal_recursion(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;

    }

    public void dfs(TreeNode root, List<Integer> result) {
        if (root == null){
            return;
        }

        dfs(root.left, result);
        result.add(root.val);
        dfs(root.right, result);

    }

    // 方法2：迭代
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();  // 用于保存每个节点左路径上的所有节点(包括节点自己)

        while (!stack.isEmpty() || root != null){
            // 不断往左子树走，将其全部保存到stack中(模拟递归实现的栈)
            if (root != null){
                stack.push(root);
                root = root.left;
            }else {  // 左子树走到头了
                TreeNode cur = stack.pop();
                result.add(cur.val);
                root = cur.right;  // 下一次就是将cur的右孩子向左走到头
            }
        }
        return result;
    }
}
