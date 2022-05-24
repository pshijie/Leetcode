/**
 * @author psj
 * @date 2022/2/1 23:09
 * @File: countNodes.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/count-complete-tree-nodes/
// 给你一棵完全二叉树的根节点root，求出该树的节点个数
// 完全二叉树：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第h层，则该层包含1~ 2^h个节点。
//                  #               #
//                /   \           /   \
//               #     #         #     #
//             /   \ /   \     /   \  /
//            #    # #    #   #     # #
//           /
//          #
public class countNodes {
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

    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }

        TreeNode l = root.left;
        TreeNode r = root.right;
        // 记录左右子树高度
        // 这是针对完全二叉树特点左子树高度一定大于等于右子树，对于普通二叉树计算高度代码如下：
        // int countLevel(TreeNode root){
        //     if (root == null) {
        //         return 0;
        //     }
        //     return Math.max(countLevel(root.left), countLevel(root.right)) + 1;
        // }

        int hl = 0, hr = 0;
        // 左子树高度一定大于等于右子树，所以只要一直往左边走
        while (l != null){
            l = l.left;
            hl ++;
        }

        while (r != null){
            r = r.left;  // 右子树的也是需要一直往左走，才能计算出右子树的最大深度
            hr ++;
        }

        // 参考上述两个图：
        // 如果左右子树高度相同，则左子树一定为满二叉树
        if (hl == hr){
            return (int)Math.pow(2, hl) + countNodes(root.right); // 左子树的个数为2^hl-1，要加上根节点，所以为2^hl
        }else {
            // 如果高度不同，则此时右子树一定为满二叉树
            return (int)Math.pow(2, hr) + countNodes(root.left);
        }

    }

    // 采用普通二叉树的解法也可
    public int countNodes_2(TreeNode root) {
        if (root == null){
            return 0;
        }

        return 1 + countNodes_2(root.left) + countNodes_2(root.right);
    }
}
