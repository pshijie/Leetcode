import java.util.HashMap;
import java.util.Map;

/**
 * @author psj
 * @date 2022/5/26 8:37
 * @File: pathSumIII.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/path-sum-iii/
// 给定一个二叉树的根节点root，和一个整数targetSum，求该二叉树里节点值之和等于targetSum的路径的数目
// 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的(只能从父节点到子节点)

public class pathSumIII {
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

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        // 根节点！=起始节点，起始节点必须在路径中包含该节点,根节点不必要
        // 以root节点为起始节点的路径数
        int result = rootSum(root, targetSum);
        // 以root.left节点为根节点
        result += pathSum(root.left, targetSum);
        // 以root.right节点为根节点
        result += pathSum(root.right, targetSum);
        return result;
    }

    // 以node为起始节点的记录数
    public int rootSum(TreeNode node, int targetSum) {
        int result = 0;
        if (node == null) {
            return 0;
        }
        int val = node.val;
        if (val == targetSum) {
            result++;
        }
        result += rootSum(node.left, targetSum - val);
        result += rootSum(node.right, targetSum - val);
        return result;
    }

}