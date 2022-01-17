/**
 * @author psj
 * @date 2022/1/17 20:25
 * @File: kthSmallest.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
// 给定一个二叉搜索(BST)树的根节点root和一个整数k，请你设计一个算法查找其中第k个最小元素（从1开始计数）
// BST的特性：
//      1.对于BST的每一个节点node，其左子树的所有节点的值都比node的值要小，其右子树的所有节点的值都比node的值要大
//      2.对于BST的每一个节点node，它的左子树和右子树都是BST
//      3.BST的中序遍历结果是升序的

public class kthSmallest {
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

    // 该题思路直接利用特性3即可
    // void traverse(TreeNode root){
    //     if (root == null){
    //         return;
    //     }
    //     traverse(root.left);
    //     /**中序遍历代码位置**/
    //     System.out.print(root.val);
    //     traverse(root.right);
    // }


    int res = 0;  // 记录最终结果
    int rank = 0;  // 记录当前元素在升序序列中的排名

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    public void traverse(TreeNode root, int k) {
        // base case
        if (root == null) {
            return;
        }
        // 采用中序遍历代码框架
        traverse(root.left, k);

        // 将框架中打印语句替换为符合题意的代码:
        // 可以理解为当前打印的节点值排在升序序列的第rank位(rank++后的值)
        rank++;
        if (k == rank) {
            // 当前打印的节点值排在第k位
            res = root.val;
            return;
        }

        traverse(root.right, k);
    }
}
