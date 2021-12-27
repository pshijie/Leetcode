/**
 * @author psj
 * @date 2021/12/27 20:37
 * @File: removeNthFromEnd.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
// 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

/**
 * 注意:
 *      传入findFromEnd函数的是虚拟节点dummy，并不是head节点。
 *      因为如果是从head节点开始走K步后，就是走到第K+1个节点上，距离链表尾部就为n-K-1个节点
 *      指针p2最后移动到的位置就是第n-K-1个节点，不是第n-K个位置了
 */
public class removeNthFromEnd {
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 1.找到倒数第n+1个节点，再去删除倒数第n个节点
        ListNode remove = findFromEnd(dummy, n + 1);
        // 2.删除倒数第n个节点
        remove.next = remove.next.next;
        return dummy.next;

    }

    // 返回链表倒数第k个节点
    public ListNode findFromEnd(ListNode head, int k) {
        // 1.定义两个指针p1和p2分别指向head
        ListNode p1 = head;
        ListNode p2 = head;

        // 2.p1先走k步,此时p1距离链表尾部还有n-k个节点
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }

        // 3.p1和p2同时走n-k步(即p1走到链表尾部)
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }

        // 4.此时p2指向的就是第n-k个节点
        return p2;

    }
}
