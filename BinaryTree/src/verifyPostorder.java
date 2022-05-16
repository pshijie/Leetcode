/**
 * @author psj
 * @date 2022/5/16 9:15
 * @File: verifyPostorder.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
// 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回true，否则返回false。假设输入的数组的任意两个数字都互不相同

public class verifyPostorder {
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    public boolean recur(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        int p = i;
        // j为postorder[i,j]这棵树的根节点下标
        while (postorder[p] < postorder[j]) {
            p++;
        }
        // m为第一个大于根节点的节点
        // 此时[i,m-1]内所有节点为左子树区间,右子树区间为[m,j-1]
        int m = p;
        // p继续移动直到找到等于根节点值的下标,如果该下标就是根节点的下标
        // 说明在不遍历左右子树的情况下,该树满足BST的条件
        while (postorder[p] > postorder[j]) {
            p++;
        }
        // 判断完上述条件还要判断左右子树是否为BST
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }
}
