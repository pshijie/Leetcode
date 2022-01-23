import java.util.HashMap;
import java.util.HashSet;

/**
 * @author psj
 * @date 2022/1/23 22:08
 * @File: lowestCommonAncestor.java
 * @Software: IntelliJ IDEA
 */

// https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
// 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先

public class lowestCommonAncestor {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    // 方法1: 使用HashMap和HashSet处理
    public TreeNode lowestCommonAncestor_1(TreeNode root, TreeNode p, TreeNode q) {
        // 用于存储每个节点及其对应的父节点之间的关系
        HashMap<TreeNode, TreeNode> fatherMap = new HashMap<>();

        // 1. root的父节点定义为本身,方便作为while循环的终止条件
        fatherMap.put(root, root);
        // 2. 构建整个树节点与其父节点之间的关系
        process(root, fatherMap);

        // 用于存储节点p到达root的路径上所有节点
        HashSet<TreeNode> set1 = new HashSet<>();

        // 3. 找到节点p到达root的路径上所有节点
        TreeNode cur = p;
        // 如果当前节点等于其在fatherMap存储的父节点(即遍历到root)
        while (cur != fatherMap.get(cur)) {
            set1.add(cur);
            cur = fatherMap.get(cur);
        }
        // 此时set1中没有root(遍历到root不符合条件就跳出while)，需要额外加上
        set1.add(root);

        // 4. 判断q是否在set1中，没有就找q的父节点继续判断
        cur = q;
        while (!set1.contains(cur)) {
            cur = fatherMap.get(cur);
        }

        return cur;
    }

    // 将以root为节点的整棵树中所有节点建立节点及其对应的父节点之间的关系
    public static void process(TreeNode root, HashMap<TreeNode, TreeNode> fatherMap) {
        // base case
        if (root == null) {
            return;
        }
        // 1. 建立root左/右子节点和root之间的关系
        fatherMap.put(root.left, root);
        fatherMap.put(root.right, root);

        // 2. 递归处理root的左子树
        process(root.left, fatherMap);
        // 2. 递归处理root的右子树
        process(root.right, fatherMap);

    }

    // 方法2: 递归
    // 对于p和q之间只有两种关系:
    //      1. p是q的最低公共祖先或者q是p的最低公共祖先
    //          root
    //         /    \
    //        p      x
    //      /   \
    //       ...
    //           q
    //      以上图为例：
    //          ① 节点p作为root的左孩子节点，返回left=p；节点x作为右孩子节点，返回right=null
    //          ②  执行left != null ? left : right，返回left=p
    //      2. p和q不互为最低公共祖先
    //          root
    //         /    \
    //        B      C
    //      /   \
    //     D     E
    //    /       \
    //   p         q
    //      以上图为例：
    //          ① 节点C作为root的右孩子节点，返回right=null
    //          ② 节点B作为root的左孩子节点，不能明显看出返回什么值，继续往下递归
    //          ③ 节点B分别递归左右子树得到left=o1,right=o2
    //          ④ 执行if语句，返回B给以B为root的递归
    //          ⑤ 节点B作为root的左孩子节点，返回left=B
    //          ② 执行left != null ? left : right，返回left=B

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        // 执行到该步说明left和right至少有一个为null
        return left != null ? left : right;
    }
}
