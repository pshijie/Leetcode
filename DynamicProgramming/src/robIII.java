import java.util.HashMap;
import java.util.Map;

/**
 * @author psj
 * @date 2022/3/7 9:09
 * @File: robIII.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/house-robber-iii/

public class robIII {
    Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (memo.containsKey(root)){
            return memo.get(root);
        }

        // 抢当前家，然后去下下家
        int do_it = root.val
                    + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                    + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
        // 不抢当前家，然后去下家
        int not_do = rob(root.left) + rob(root.right);

        int res = Math.max(do_it, not_do);
        memo.put(root, res);

        return res;
    }
}

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
