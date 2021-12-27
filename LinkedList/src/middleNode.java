/**
 * @author psj
 * @date 2021/12/27 21:11
 * @File: middleNode.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/middle-of-the-linked-list/
// 给定一个头结点为 head 的非空单链表，返回链表的中间结点。如果有两个中间结点，则返回第二个中间结点。

/**
 * 注意：
 *     1.不能只判断fast是否到链表尾部，还要判断fast.next是否到链表尾部，
 *       因为执行的是fast.next.next，如果fast.next已经为null了再去执行fast.next.next会报错
 *     2.链表长度为偶数时，代码返回的是两个中点中的后一个，不需要修改代码
 */
public class middleNode {
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

    public ListNode middleNode(ListNode head) {
        // 1.定义快慢指针slow和fast，分别指向head
        ListNode slow = head;  // 慢指针一次走一步
        ListNode fast = head;  // 快指针一次走两步
        // 2.当fast指针走到链表尾部时停止
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 3.慢指针执行中点，返回慢指针
        return slow;
    }

}
