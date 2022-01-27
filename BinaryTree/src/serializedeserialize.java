import java.util.LinkedList;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/1/27 23:10
 * @File: serializedeserialize.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
// 请设计一个算法来实现二叉树的序列化与反序列化。
// 序列化：将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境
// 反序列化：采取相反方式重构得到原数据
// 假如树的结构如下：
//                  1
//                /   \
//               2     3
//             /   \ /   \
//            #    4 #    #
//               /  \
//              #    #
public class serializedeserialize {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // --------------①前序遍历实现-------------
    String SEP = ",";  // 代表分隔符
    String NULL = "#";  // 代表空指针字符

    // 序列化:[1,2,#,4,#,#,3,#,#]
    public String serialize_pre(TreeNode root) {
        StringBuilder sb = new StringBuilder();  // 用于高效拼接字符串，可以视为列表
        serialize_pre(root, sb);
        return sb.toString();
    }

    public void serialize_pre(TreeNode root, StringBuilder sb) {
        // base case
        if (root == null){
            sb.append(NULL).append(SEP);
            return;
        }

        sb.append(root.val).append(SEP);

        serialize_pre(root.left, sb);
        serialize_pre(root.right, sb);


    }
    // 反序列化
    // [1,     2,#,4,#,#,     3,#,#]
    // root    root.left    root.right
    public TreeNode deserialize_pre(String data) {
        // 1. 将字符串转换为列表
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)){
            nodes.addLast(s);
        }

        return deserialize_pre(nodes);
    }

    public TreeNode deserialize_pre(LinkedList<String> nodes) {
        if (nodes.isEmpty()){
            return null;
        }

        // 根节点root
        String first = nodes.removeFirst();
        if (first.equals(NULL)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));

        // 递归构造当前根节点的左子树和右子树（因为是从后往前取元素，所以是先构造右子树）
        root.left = deserialize_pre(nodes);
        // 执行到该步的nodes只剩[3,#,#]
        root.right = deserialize_pre(nodes);

        return root;

    }
    // --------------①前序遍历实现-------------

    // --------------②后序遍历实现-------------

    // 序列化:[#,#,#,4,2,#,#,3,1]
    public String serialize_post(TreeNode root) {
        StringBuilder sb = new StringBuilder();  // 用于高效拼接字符串，可以视为列表
        serialize_pre(root, sb);
        return sb.toString();
    }
    public void serialize_post(TreeNode root, StringBuilder sb) {
        // base case
        if (root == null){
            sb.append(NULL).append(SEP);
            return;
        }


        serialize_post(root.left, sb);
        serialize_post(root.right, sb);

        sb.append(root.val).append(SEP);

    }
    // 反序列化
    // [#,#,#,4,2,     #,#,3,      1]
    //  root.left     root.right  root
    public TreeNode deserialize_post(String data) {
        // 1. 将字符串转换为列表
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)){
            nodes.addLast(s);
        }

        return deserialize_post(nodes);
    }
    public TreeNode deserialize_post(LinkedList<String> nodes) {
        if (nodes.isEmpty()){
            return null;
        }

        // 根节点root
        String last = nodes.removeLast();
        if (last.equals(NULL)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(last));

        // 递归构造当前根节点的左子树和右子树（因为是从后往前取元素，所以是先构造右子树）
        root.right = deserialize_pre(nodes);
        // 执行到该步的nodes只剩[#,#,#,4,2]
        root.left = deserialize_pre(nodes);

        return root;

    }
    // --------------②后序遍历实现-------------

    // --------------③中序遍历实现-------------
    // 可以实现序列方法，但是无法实现反序列方法，因为不知道root的确切位置
    // --------------③中序遍历实现-------------

    // --------------④层级遍历实现-------------
    // 序列化:[1,     2,3,      #,4,#,#,     #,#]
    //        l1      l2          l3         l4
    public String serialize_level(TreeNode root) {
        if (root == null){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        // 初始化队列
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            TreeNode cur = q.poll();

            if (cur == null){
                sb.append(NULL).append(SEP);
                continue;
            }

            sb.append(cur.val).append(SEP);

            q.offer(root.left);
            q.offer(root.right);
        }

        return sb.toString();
    }

    public TreeNode deserialize_level(String data){
        if (data.isEmpty()){
            return null;
        }

        String[] nodes = data.split(SEP);

        // 第一个元素为root
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));

        // 记录父节点
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        for (int i = 1; i < nodes.length;) {
            TreeNode parent = q.poll();
            // 父节点对应的左孩子
            String left = nodes[i++];
            if (!left.equals(NULL)){
                parent.left = new TreeNode(Integer.parseInt(left));
                q.offer(parent.left);
            }else {
                parent.left = null;
            }

            // 父节点对应的右孩子
            String right = nodes[i++];
            if (!right.equals(NULL)){
                parent.right = new TreeNode(Integer.parseInt(right));
                q.offer(parent.right);
            }else {
                parent.right = null;
            }

        }

        return root;
    }
    // --------------④层级遍历实现-------------

}
