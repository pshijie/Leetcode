/**
 * @author psj
 * @date 2022/1/5 20:44
 * @File: connect.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
// 给定一个完美二叉树,其所有叶子节点都在同一层,每个父节点都有两个子节点。二叉树定义如下：
// struct Node{
//    int val;
//    Node *left;
//    Node *right;
//    Node *next;
// }
// 填充它的每个next指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将next指针设置为NULL。
//      1            1 → null
//     / \        /     \
//    2   3      2   →   3 → null
//   / \ / \    /  \    /  \
//  4  5 6 7    4 → 5 → 6 → 7 → null
//  从上图中可以看出不仅需要将节点的左右孩子连接，父节点不同的孩子节点之间也需要连接

/**
 * 如果将connect函数简单写为:
 *      root.left.next = root.right;
 *      connect(root.left);
 *      connect(root.right);
 *      return root;
 *  这样只能将相同父节点的两个节点进行相连
 */
public class connect {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        connectTwoNode(root.left, root.right);

        return root;
    }

    public void connectTwoNode(Node node1, Node node2) {
        // base case
        if (node1 == null || node2 == null) {
            return;
        }

        // 前序遍历位置
        // 1. 将传入的两个节点连接
        node1.next = node2;

        // 2. 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);

        // 3. 连接父节点不同的两个子节点
        connectTwoNode(node1.right, node2.left);

    }
}
