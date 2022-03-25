import java.util.*;

/**
 * @author psj
 * @date 2022/3/25 10:06
 * @File: pathSum.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/path-sum-ii/
// 给你二叉树的根节点root和一个整数目标和targetSum，找出所有从根节点到叶子节点路径总和等于给定目标和的路径

public class pathSum {
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

    // 方法1：DFS
    List<List<Integer>> result = new LinkedList<>();
    public List<List<Integer>> pathSum_DFS(TreeNode root, int targetSum) {
        if (root == null){
            return null;
        }
        int curSum = 0;
        List<Integer> tmp = new LinkedList<>();

        dfs(root, curSum, targetSum, tmp);
        return result;
    }

    public void dfs(TreeNode root, int curSum, int targetSum ,List<Integer> tmp) {
        if (root == null){
            return;
        }
        tmp.add(root.val);
        curSum += root.val;
        if (root.left == null && root.right == null){
            if (curSum == targetSum){
                result.add(new LinkedList<>(tmp));
            }
        }

        dfs(root.left, curSum, targetSum, tmp);
        dfs(root.right, curSum, targetSum, tmp);
        tmp.remove(tmp.size()-1);
    }

    // 方法2：BFS
    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    // 记录每一个节点的父节点
    Map<TreeNode, TreeNode> map = new HashMap<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueSum = new LinkedList<>();
        queueNode.offer(root);
        queueSum.offer(0);

        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int rec = queueSum.poll() + node.val;

            if (node.left == null && node.right == null) {
                if (rec == targetSum) {
                    getPath(node);
                }
            } else {
                if (node.left != null) {
                    map.put(node.left, node);
                    queueNode.offer(node.left);
                    queueSum.offer(rec);
                }
                if (node.right != null) {
                    map.put(node.right, node);
                    queueNode.offer(node.right);
                    queueSum.offer(rec);
                }
            }
        }

        return ret;
    }

    public void getPath(TreeNode node) {
        List<Integer> temp = new LinkedList<>();
        // 根据hashMap记录的每个节点的父节点一直向上遍历
        while (node != null) {
            temp.add(node.val);
            node = map.get(node);
        }
        Collections.reverse(temp);
        ret.add(new LinkedList<>(temp));
    }

}
