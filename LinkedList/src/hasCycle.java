/**
 * @author psj
 * @date 2021/12/29 21:13
 * @File: hasCycle.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/linked-list-cycle/
// 给你一个链表的头节点head, 判断链表中是否有环。

/**
 * 注意：
 * 不能只判断fast是否到链表尾部，还要判断fast.next是否到链表尾部，
 * 因为执行的是fast.next.next，如果fast.next已经为null了再去执行fast.next.next会报错
 */
public class hasCycle {
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

    public boolean hasCycle(ListNode head) {
        // 1.定义快慢指针
        ListNode fast = head;
        ListNode slow = head;

        // 2.快指针走一步，慢指针走两步
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // 3.如果快慢指针相遇，就说明有环
            if (slow == fast) {
                return true;
            }
        }

        // 4.执行到该步说明不包含环
        return false;
    }
}
