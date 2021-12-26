/**
 * @author psj
 * @date 2021/12/26 20:52
 * @File: mergeTwoLists.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/merge-two-sorted-lists/
// 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

/**
 * 使用dummy节点的意义：
 * - 如果当链表为空的时候,单链表没有带头结点,那么它的头指针就为NULL.
 * - 方便单链表的操作，当对头节点进行操作时可以保持和后续节点一致的操作
 */


public class mergeTwoLists {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = list1, p2 = list2;

        while (p1 != null && p2 != null) {
            // 比较p1和p2两个指针的值，值较小的连接到p指针
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }

        // 假如p1还没有遍历完（p2遍历完）,将剩余部分直接连到p指针
        if (p1 != null) {
            p.next = p1;
        }
        // 假如p2还没有遍历完（p2遍历完）,将剩余部分直接连到p指针
        if (p2 != null) {
            p.next = p2;
        }

        return dummy.next;

    }
}
