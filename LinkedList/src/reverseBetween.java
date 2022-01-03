import java.util.List;

/**
 * @author psj
 * @date 2022/1/3 20:20
 * @File: reverseBetween.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/reverse-linked-list-ii/
// 给你单链表的头指针head和两个整数left和right,其中left<=right。请你反转从位置left到位置right的链表节点，返回反转后的链表。
public class reverseBetween {
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

    // 先处理反转链表前N个节点
    // 假设链表为1 → 2 → 3 → 4 → null,反转前3个节点后链表变为:
    // 1 ←2 ← 3  4 → null
    // ↓         ↑
    // ----------→
    // 并且结果返回节点3

    ListNode successor = null;

    public ListNode reverseN(ListNode head, int n) {
        // 反转一个元素就是本身
        if (n == 1) {
            // 1.记录第n+1个节点，即节点4，因为执行到该步时head为节点3了
            successor = head.next;
            return head;
        }

        // 2.以head.next为起点，即以2为起点，反转2个节点，反转后last指针指向3
        // 1 → 2 ← 3  4 → null
        ListNode last = reverseN(head.next, n - 1);

        // 3.head.next即为2，将2的next指针指向1
        // 1 ← 2 ← 3  4 → null
        head.next.next = head;

        // 4.head的next指针指向之前储存的后继节点，即1的next指针指向4
        // 1 ←2 ← 3  4 → null
        // ↓         ↑
        // ----------→
        head.next = successor;

        // 5.返回last指针，即节点3
        return last;
    }

    // 方法1：递归
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 相当于反转前n个元素
        if (left == 1) {
            return reverseN(head, right);
        }
        // todo:未理解其含义
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    // 方法2：迭代(头插法)
    // 假设链表为1 → 2 → 3 → 4 → null,以left=2,right=4为例
    public ListNode reverseBetween_nocur(ListNode head, int left, int right) {
        // 1.定义虚拟节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 2.初始化两个指针
        ListNode pre = dummy;  // 指向第一个要反转节点的前一个节点(此时为dummy)
        ListNode cur = dummy.next;  // 指向第一个需要反转的节点(此时为1)

        // 3.将两个指针移动到相应位置,假设left=2,两个指针就移动1步
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
            cur = cur.next;
        }

        // 4.使用头插法分别将3和4插入到1和2之间，将3插入1和2之间为例
        for (int i = 0; i < right - left; i++) {
            // removed指向3,即确定要被用来插入的节点
            ListNode removed = cur.next;
            // 2的next指针指向4
            cur.next = cur.next.next;
            // 3的next指针指向2,这里不是直接写为指向cur，
            // 因为cur是固定(节点2)，但是在下一次循环中removed的next指针指向的就是上一轮被插入的节点不是cur了
            removed.next = pre.next;
            // 1的next指针指向3
            pre.next = removed;
        }

        // 5.返回反转后的链表
        return dummy.next;
    }
}
