/**
 * @author psj
 * @date 2022/1/21 20:19
 * @File: insertIntoBST.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
// 给定二叉搜索树的根节点和要插入树中的值，将值插入二叉搜索树。返回插入后二叉搜索树的根节点。输入数据保证新值和原始二叉搜索树中的任意节点值都不同。

public class insertIntoBST {
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

    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 1.找到空位置插入新节点
        if (root == null){
            return new TreeNode(val);
        }

        // 2. 当需要插入节点值大于root的值，说明要插入到root的右子树
        if (val > root.val){
            root.right = insertIntoBST(root.right, val);
        }
        // 3. 当需要插入节点值小于root的值，说明要插入到root的左子树
        if (val < root.val){
            root.left = insertIntoBST(root.left, val);
        }

        // 4. 返回插入完后的root节点
        return root;
    }
}
