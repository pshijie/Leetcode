/**
 * @author psj
 * @date 2022/1/17 21:03
 * @File: convertBST.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
// 给出二叉搜索树的根节点，该树的节点值各不相同，请你将其转换为累加树(Greater Sum Tree)，使每个节点node的新值等于原树中大于或等于node.val的值之和。

public class convertBST {
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
    // 该题不能简单将当前节点右子树所有节点值相加，因为无法确定其父节点的值是否大于当前节点值
    // 解题思路依旧采用中序遍历，只不过将升序改为降序
    // void traverse(TreeNode root){
    //     if (root == null){
    //         return;
    //     }
    //     /**先遍历右子树，再遍历左子树**/
    //     traverse(root.right);
    //     /**中序遍历代码位置**/
    //     System.out.print(root.val);
    //     traverse(root.left);
    // }

    int sum = 0;  // 记录累加和

    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    public void traverse(TreeNode root) {
        // base case
        if (root == null) {
            return;
        }
        // 采用中序遍历代码框架(降序)
        traverse(root.right);

        // 将框架中打印语句替换为符合题意的代码:
        // sum在未加上root.val时已经将所有大于root.val的值进行了累加
        sum += root.val;
        root.val = sum;

        traverse(root.left);
    }

}
