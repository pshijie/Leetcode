import java.util.List;

/**
 * @author psj
 * @date 2022/1/4 19:32
 * @File: reverseKGroup.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
// 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。k是一个正整数，它的值小于或等于链表的长度。
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
public class reverseKGroup {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 先解决反转区间[a,b)之间的元素，使用的是迭代方法
    // 假设链表为1 → 2 → 3 → 4
    public ListNode reverse(ListNode a, ListNode b) {
        // 1. 定义三个指针
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;


        while (cur != b) {
            // 以反转链表前两个节点为例，此时pre=null,cur指向1
            // 2. nxt指针指向2
            nxt = cur.next;
            // 3. 1的next指针指向pre，即null
            cur.next = pre;
            // 4. pre指针指向1
            pre = cur;
            // 5. cur指针指向2
            cur = nxt;
        }
        // 6. 当cur=b时就跳出while循环，说明节点b前部分的链表已经反转，所以返回pre指针
        return pre;
    }

    // 方法1: 递归，运用反转区间[a,b)之间的元素的代码
    // 假设链表为1 → 2 → 3 → 4，k=2
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // 1. 定义两个指针a,b
        ListNode a, b;
        a = b = head;

        // 2. 将b移动到和a距离k个节点
        for (int i = 0; i < k; i++) {
            // 如果此时b指向null，说明区间[a,b)不足k个节点，返回head即可
            if (b == null) {
                return head;
            }
            b = b.next;
        }

        // 3. 反转前k个元素，即反转区间[a,b)之间的元素
        // 此时的链表变为null ← 1 ← 2   3 → 4。且newHead指向2
        ListNode newHead = reverse(a, b);

        // 4. 将a的next指针,即1的next指针指向后续的链表反转后的newHead，3 → 4反转后的newHead为4
        // 相当于 1 ← 2   4 → 3
        //       ↓       ↑
        //       ---------
        a.next = reverseKGroup(b, k);

        // 5. 返回newHead，即2
        return newHead;

    }

    // 方法2：迭代
    public ListNode reverseKGroup_2(ListNode head, int k) {
        // 1. 定义pre、cur、nex节点以及虚拟节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        ListNode nxt;

        // 2. 计算链表的长度
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }

        // 3. 对整个链表进行分组，在每一组中进行反转
        // length / k为分得的组数
        for (int i = 0; i < length / k; i++) {
            for (int j = 0; j < k - 1; j++) {
                nxt = cur.next;
                cur.next = nxt.next;
                nxt.next = pre.next;
                pre.next = nxt;
            }

            pre = cur;
            cur = pre.next;

        }

        // 4. 返回虚拟节点next指针指向的节点
        return dummy.next;
    }
}
