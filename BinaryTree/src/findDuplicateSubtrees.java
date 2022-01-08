import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/1/8 19:57
 * @File: findDuplicateSubtrees.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/find-duplicate-subtrees/
// 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
// 两棵树重复是指它们具有相同的结构以及相同的结点值, 比如:
//       1    这棵树的重复子树为  2  和  4
//     /   \                  /
//    2     3                4
//   /    /   \
//  4    2     4
//      /
//     4
public class findDuplicateSubtrees {
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

    // 记录所有子树以及每个子树出现的次数
    HashMap<String, Integer> memo = new HashMap<>();
    // 记录重复的子树的根节点
    LinkedList<TreeNode> res = new LinkedList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;

    }

    // 将树的结构用字符串进行表示(序列化)，#表示空节点
    public String traverse(TreeNode root) {
        // base case
        if (root == null) {
            return "#";
        }

        // 2. 使用后序遍历对子树进行序列化
        // 为什么使用后序遍历?要知道以自己为根的树是什么样子，要先清楚左/右子树后再加上自己本身就构成了完整的树
        String left = traverse(root.left);
        String right = traverse(root.right);
        String subTree = left + "," + right + "," + root.val;

        // 3. 判断该子树是否出现过
        int freq = memo.getOrDefault(subTree, 0);
        // freq = 1说明已经出现过，符合条件
        if (freq == 1) {
            res.add(root);
        }
        // 这里继续累加次数的目的在于:如果某一个子树出现2次以上也不会重复加入res中，因为freq>1了
        memo.put(subTree, freq + 1);

        // 4. 得到完整的子树序列表示后返回
        return subTree;

    }
}
