/**
 * @author psj
 * @date 2022/1/6 19:19
 * @File: flatten.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
// 给你二叉树的根结点root, 请你将它展开为一个单链表：
//    1. 展开后的单链表应该同样使用TreeNode，其中right子指针指向链表中下一个结点，而左子指针始终为 null
//    2. 展开后的单链表应该与二叉树先序遍历顺序相同
//        1        1
//      /  \        \
//     2    5   →    2
//   /   \   \        \
//  3     4   6        3
//                      \
//                       4
//                        \
//                         5
//                          \
//                           6
public class flatten {
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

    public void flatten(TreeNode root) {
        // base case
        if (root == null) {
            return;
        }
        // 1. 将左右子树拉平
        //        1               1
        //      /  \            /   \
        //     2    5   →      2     5
        //   /   \   \          \     \
        //  3     4   6          3     6
        //                        \
        //                         4
        flatten(root.left);
        flatten(root.right);

        // 后序遍历位置
        // 2. 将拉平后的子树变为单链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2. 将root的左子树作为其右子树
        root.left = null;
        root.right = left;

        // 3. 将root原来的右子树接到当前右子树后
        //       1         1
        //        \         \
        //         2         2
        //          \   →     \
        //           3         3
        //            \         \
        //             4         4
        //                        \
        //                         5
        //                          \
        //                           6
        TreeNode p = root;
        // 走到节点4的位置
        while (p.right != null) {
            p = p.right;
        }
        // 将之前的单链表5 → 6接到节点4后面
        p.right = right;

    }
}
