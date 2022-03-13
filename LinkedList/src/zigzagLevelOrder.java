import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/3/13 10:34
 * @File: zigzagLevelOrder.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
// 给你二叉树的根节点root ，返回其节点值的锯齿形层序遍历(即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行)
public class zigzagLevelOrder {
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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        // 是否从左到右的标志位
        boolean left_flag = true;
        // 用于按层遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 利用双端队列保存不同方向的遍历结果
            Deque<Integer> levelList = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (left_flag) {
                    levelList.offerLast(curNode.val);
                }else {
                    levelList.offerFirst(curNode.val);
                }

                if (curNode.left != null){
                    queue.offer(curNode.left);
                }
                if (curNode.right != null){
                    queue.offer(curNode.right);
                }
            }

            result.add(new LinkedList<Integer>(levelList));
            // 进入下一层，调转遍历方向
            left_flag = !left_flag;
        }
        return result;
    }

}
