/**
 * @author psj
 * @date 2022/4/10 9:31
 * @File: treeToDoublyList.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
// 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向

public class treeToDoublyList {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    Node pre, head;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        // 执行到该步时，head已经作为双向链表的头节点，pre为尾节点
        // 将头节点和尾节点的相互指向
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void dfs(Node cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.left);
        // pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,
        // 当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
        if (pre != null) {
            pre.right = cur;
        // 当pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur
        } else {
            head = cur;
        }
        cur.left = pre;
        // 将pre节点往下一个节点移动，cur节点也开始向下个节点移动
        pre = cur;
        dfs(cur.right);
    }
}
