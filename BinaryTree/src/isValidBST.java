/**
 * @author psj
 * @date 2022/1/18 21:09
 * @File: isValidBST.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/validate-binary-search-tree/
// 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树(当前节点的左子树只包含小于当前节点值的节点，右子树同理)
public class isValidBST {
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

    // BST相关问题会经常出现以下框架：
    // if(root.val == target)
    //    do something
    // if(root.val < target)
    //    BST(root.right, target)
    // if(root.val > target)
    //    BST(root.left, target)
    // 如果使用以上框架写该题代码：
    // if (root = null) return true;
    // if (root.left != null && root.val <= root.left.val)
    //     return false;
    // if (root.right != null && root.val <= root.right.val)
    //     return false;
    // return isValidBST(root.left) && isValidBST(root.right);
    // 但是上述写法只检查每个节点的左右孩子节点是否符合左小右大，无法判断整个左子树和右子树是否符合该条件

    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        // base case
        if (root == null){
            return true;
        }

        // 1.遍历左子树
        if (!isValidBST(root.left)){
            return false;
        }

        // 2.访问当前节点，如果当前节点小于等于中序遍历的前一个节点，就不满足BST的性质
        if (root.val <= pre){
            return false;
        }
        // 记录当前节点值作为下一次比较的值***
        pre = root.val;

        // 3.遍历右子树
        if (!isValidBST(root.right)){
            return false;
        }

        // 4.左右子树均符合条件
        return true;
    }

}
