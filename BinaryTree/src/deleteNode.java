/**
 * @author psj
 * @date 2022/1/21 20:44
 * @File: deleteNode.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/delete-node-in-a-bst/
// 给定一个二叉搜索树的根节点root和一个值key，删除二叉搜索树中的key对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

public class deleteNode {
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

    public TreeNode deleteNode(TreeNode root, int key) {
        // 未找到需要删除的节点
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            // 情况1：root左右子树都为空
            if (root.left == null && root.right == null) {
                return null;
            }
            // 情况2：root只有一个非空子节点，则让其接替自己的位置(不符合情况1才会执行到该步)
            if (root.left == null) {  // 左子树为空就让右子树替换
                return root.right;
            }
            if (root.right == null) {  // 右子树为空就让左子树替换
                return root.left;
            }
            // 情况3：root的两个子节点都存在，为了不破坏BST的性质，需要找到左子树最大节点或者右子树最小节点替换自己
            // 获取右子树值最小的节点
            TreeNode minNode = getMin(root.right);
            // 在右子树中删除minNode
            root.right = deleteNode(root.right, minNode.val);
            // 将minNode替换root(最好不要直接简单写为root.val = minNode.val)
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        } else if (root.val > key) {
            // 2. 当需要删除节点值大于root的值，说明要到root的右子树去寻找节点删除
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            // 3. 当需要删除节点值小于root的值，说明要到root的左子树去寻找节点删除
            root.right = deleteNode(root.right, key);
        }

        // 4. 返回删除完后的root节点
        return root;
    }

    public TreeNode getMin(TreeNode node) {
        // 在BST中沿着左边一直走到底就能找到最小值的节点
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
