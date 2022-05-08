import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/5/8 9:30
 * @File: levelOrderII.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
//请实现一个函数按照之字形顺序打印二叉树，
// 即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推

public class levelOrderII {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isOrderLeft = true;  // 判断是否从左到右
        while (!queue.isEmpty()) {
            // 使用双端队列
            Deque<Integer> list = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                // 如果需要从左到右遍历，就将节点队列所有节点的值从双端队列尾部插入
                if (isOrderLeft) {
                    list.offerLast(curNode.val);
                } else {  // 如果需要从右到左遍历，就将节点队列所有节点的值从双端队列头部插入
                    list.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            result.add(new LinkedList<>(list));
            // 此时要遍历下一层,将遍历顺序标志位置反
            isOrderLeft = !isOrderLeft;
        }
        return result;
    }
}
