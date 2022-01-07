/**
 * @author psj
 * @date 2022/1/7 20:50
 * @File: buildTreeⅢ.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
// 返回与给定的前序和后序遍历匹配的任何二叉树

/**
 * tips:
 *      通过前/中序或者后/中序遍历可以确定一棵原始二叉树，但是通过前/后序遍历结果无法确定原始二叉树,
 *      这是因为可以通过前/后序遍历找到根节点，然后根据中序遍历确定左右子树，所以没有中序就无法确切的知道左右子树有哪些节点
 *       1  和  1    两个树的前/后遍历结果就是一样的
 *      /        \
 *     2          2
 *    /            \
 *   3              3
 */
public class buildTreeⅢ {
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

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // 写一个辅助函数来控制两个数组的索引(因为在当前函数的参数中不能传入需要递归处理数组的上下界索引)
        return build(preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    // preorder:
    //          root     root.left       root.right
    //           - |     --------- - |     -------
    //           ↑       ↑         ↑
    //         rootVal   ↑         ↑
    //              leftRootVal    ↑
    //                     preStart+leftSize
    // postorder:
    //             root.left       root.right   root
    //           ---------  - |     -------   |   -
    //                      ↑                     ↑
    //                     index                rootVal
    //                  leftRootVal
    public TreeNode build(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
        // base case
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }

        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }

        // 1. 找到数组pre[preStart..preEnd]对应的root节点
        int rootVal = preorder[preStart];
        // 2. 把前序遍历的第二个元素作为左子树的根节点的值
        int leftRootVal = preorder[preStart + 1];

        // 3. 在后序遍历结果中寻找左子树根节点的值，从而确定了左子树和右子树的索引边界
        int index = 0;
        for (int i = postStart; i < postEnd; i++) {
            if (postorder[i] == leftRootVal) {
                index = i;
                break;
            }
        }


        // 4. 根据root节点递归构造左右子树
        int leftSize = index - postStart + 1;  // 左子树的元素个数
        TreeNode root = new TreeNode(rootVal);

        // 该步的关键在于preorder和postorder如何确定左右子树的起始/终止索引
        // 对于preorder：
        //      左子树的起始索引为preStart+1,终止索引为preStart+leftSize
        //      右子树的起始索引为preStart+leftSize+1,终止索引为preEnd
        // 对于postorder：
        //      左子树的起始索引为postStart,终止索引为index
        //      右子树的起始索引为index+1,终止索引为postEnd
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                postorder, postStart, index);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                postorder, index + 1, postEnd);

        return root;
    }
}
