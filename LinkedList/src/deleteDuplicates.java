/**
 * @author psj
 * @date 2022/3/16 9:53
 * @File: deleteDuplicates.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
// 给定一个已排序的链表的头head ， 删除所有重复的元素，使每个元素只出现一次 。返回已排序的链表

public class deleteDuplicates {
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

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        // 和题26一样如果fast指向的节点值等于slow指向的节点值，将slow的next指针指向fast，并且将slow指针向下移动
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        // 执行到该步时slow指针后面的节点和slow指向的节点值是一样的，所以要断开
        slow.next = null;
        return head;

    }
}
