/**
 * @author psj
 * @date 2022/5/4 9:23
 * @File: partition.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/partition-list/
// 给你一个链表的头节点head和一个特定值x ，请你对链表进行分隔，使得所有小于x的节点都出现在大于或等于x的节点之前

public class partition {
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

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode small = new ListNode(-1);
        ListNode smallHead = small;
        ListNode large = new ListNode(-1);
        ListNode largeHead = large;

        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;  // largeHead.next即为large指针执行的第一个节点

        return smallHead.next;
    }
}
