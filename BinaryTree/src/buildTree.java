/**
 * @author psj
 * @date 2022/1/7 19:55
 * @File: buildTree.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// 给定一棵树的前序遍历preorder与中序遍历inorder, 请构造二叉树并返回其根节点
public class buildTree {
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

    // 假设某树的preorder为[1,2,5,4,6,7,3,8,9], inorder为[5,2,6,4,7,1,8,3,9]
    // 首先可以很明确的知道root为preorder[0]，所以将preorder和inorder进行处理后:
    // preorder: 1    2   5   4   6   7   3   8   9
    //           -    -----------------   ---------
    //          root      root.left       root.right
    // inorder:  5    2   6   4   7   1   8   3   9
    //           ------------------   -   ---------
    //                root.left      root root.right
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 写一个辅助函数来控制两个数组的索引(因为在当前函数的参数中不能传入需要递归处理数组的上下界索引)
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);

    }

    public TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        // base case
        if (preStart > preEnd) {
            return null;
        }
        // 1. 找到数组pre[preStart..preEnd]对应的root节点
        int rootVal = preorder[preStart];
        // 2. 在数组inorder[inStart..inEnd]中找到root节点的索引
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }

        // 3. 根据root节点递归构造左右子树
        // 该步的关键在于preorder和inorder如何确定左右子树的起始/终止索引
        // 对于inorder比较容易：
        //      左子树的起始索引为inStart,终止索引为index-1
        //      右子树的起始索引为index+1,终止索引为inEnd
        // 对于preorder，需要先计算出左子树中节点个数(通过inorder确定)才能进一步确定索引位置：
        //      左子树的起始索引为preStart,终止索引为preStart+leftSize
        //      右子树的起始索引为preStart+leftSize+1,终止索引为preEnd

        TreeNode root = new TreeNode(rootVal);
        int leftSize = index - inStart; // root对应的左子树的节点个数

        root.left = build(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, index - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                inorder, index + 1, inEnd);

        return root;

    }
}
