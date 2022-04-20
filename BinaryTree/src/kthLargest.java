import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author psj
 * @date 2022/4/20 9:44
 * @File: kthLargest.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
// 给定一棵二叉搜索树，请找出其中第k大的节点的值

public class kthLargest {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 方法1：中序遍历(额外数据结构)
    List<Integer> list = new ArrayList<>();

    public int kthLargest_list(TreeNode root, int k) {
        inOrder_list(root);
        return list.get(list.size() - k);
    }

    public void inOrder_list(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder_list(root.left);
        list.add(root.val);
        inOrder_list(root.right);
    }

    // 方法2:中序遍历(不使用额外数据结构)
    int result, k;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        inOrder(root);
        return result;
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        // 采用先遍历右子树后遍历左子树，这样产生递减数组，则第k个最大数就是数组中的第k个数
        inOrder(root.right);
        if (k == 0) {
            return;
        }
        k--;
        // 当前节点为第k大的数
        if (k == 0) {
            result = root.val;
        }
        inOrder(root.left);
    }
}
