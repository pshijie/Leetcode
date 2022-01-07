/**
 * @author psj
 * @date 2022/1/7 20:34
 * @File: buildTreeⅡ.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
// 根据一棵树的中序遍历与后序遍历构造二叉树
public class buildTreeⅡ {
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
    // 假设某树的inorder为[5,2,6,4,7,1,8,3,9], postorder为[5,6,7,4,2,8,9,3,1]
    // 首先可以很明确的知道root为postorder的最后一个元素，所以将preorder和inorder进行处理后:
    // postorder:5    6   7   4   2   8   9   3   1
    //          -------------------   ---------   -
    //                root.left       root.right root
    // inorder:  5    2   6   4   7   1   8   3   9
    //           ------------------   -   ---------
    //                root.left      root root.right
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 写一个辅助函数来控制两个数组的索引(因为在当前函数的参数中不能传入需要递归处理数组的上下界索引)
        return build(inorder, 0, inorder.length - 1,
                     postorder, 0, postorder.length - 1);
    }

    public TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        // base case
        if (inStart > inEnd) {
            return null;
        }
        // 1. 找到数组postorder[postStart..postEnd]对应的root节点
        int rootVal = postorder[postEnd];
        // 2. 在数组inorder[inStart..inEnd]中找到root节点的索引
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }

        // 3. 根据root节点递归构造左右子树
        // 该步的关键在于postorder和inorder如何确定左右子树的起始/终止索引
        // 对于inorder比较容易：
        //      左子树的起始索引为inStart,终止索引为index-1
        //      右子树的起始索引为index+1,终止索引为inEnd
        // 对于postorder，需要先计算出左子树中节点个数(通过inorder确定)才能进一步确定索引位置：
        //      左子树的起始索引为postStart,终止索引为postStart+leftSize-1
        //      右子树的起始索引为postStart+leftSize,终止索引为postEnd-1

        TreeNode root = new TreeNode(rootVal);
        int leftSize = index - inStart; // root对应的左子树的节点个数

        root.left = build(inorder, inStart, index-1,
                          postorder, postStart,postStart+leftSize-1);
        root.right = build(inorder, index + 1, inEnd,
                          postorder, postStart + leftSize, postEnd -1);

        return root;

    }
}
