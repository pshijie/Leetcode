import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/3/18 8:52
 * @File: rightSideView.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/binary-tree-right-side-view/
// 给定一个二叉树的根节点root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值
public class rightSideView {
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

    // 方法1：BFS
    public List<Integer> rightSideView_bfs(TreeNode root) {
        // 存储每一层的最后一个节点
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }

                // 即当前遍历到该层的最后一个节点
                if (i == size - 1) {
                    result.add(curNode.val);
                }
            }
        }
        return result;
    }

    // 方法2：DFS
    List<Integer> result = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    // 将先序遍历root->root.left->root.right改为root->root.right->root.left
    // 这样在遍历每一层的时候第一个都是最右侧的节点
    public void dfs(TreeNode root, int depth){
        if (root == null){
            return;
        }
        // 先访问当前节点，再递归地访问右子树和左子树
        // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中
        if (depth == result.size()){
            result.add(root.val);
        }

        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }
}
