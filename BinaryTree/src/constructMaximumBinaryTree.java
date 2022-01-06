/**
 * @author psj
 * @date 2022/1/6 19:48
 * @File: constructMaximumBinaryTree.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/maximum-binary-tree/
// 给定一个不含重复元素的整数数组nums。一个以此数组直接递归构建的最大二叉树定义如下：
//    1. 二叉树的根是数组nums中的最大元素。
//    2. 左子树是通过数组中最大值左边部分递归构造出的最大二叉树。
//    3. 右子树是通过数组中最大值右边部分递归构造出的最大二叉树。
// 假如数组为nums=[3,2,1,6,0,5],最大值为6，所以构建的树为:
//               6
//             /   \
//            3     5
//             \   /
//              2 0
//               \
//                1
public class constructMaximumBinaryTree {
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


    // 伪代码
    // 1. 先找到当前数组中的最大值maxVal及其对应的下标index
    // 2. 递归构造左右子树
    //    TreeNode root = new TreeNode(maxVal)
    //    root.left = constructMaximumBinaryTree(nums[0..index-1])
    //    root.right = constructMaximumBinaryTree(nums[index+1..num.length-1])
    // 3. 返回root节点

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // 写一个辅助函数来控制nums的索引(因为在当前函数的参数中不能传入需要递归处理数组的上下界索引)
        return build(nums, 0, nums.length - 1);
    }

    public TreeNode build(int[] nums, int lo, int hi) {
        // base case
        if (lo > hi) {
            return null;
        }

        // 1. 找到数组[lo...hi]中的最大值maxVal及其对应的下标index
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                maxVal = nums[i];
                index = i;
            }
        }

        // 2. 递归调用函数本身构造左右子树
        TreeNode root = new TreeNode(maxVal);
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);

        // 3. 返回root节点
        return root;

    }
}
