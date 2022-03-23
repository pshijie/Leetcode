import java.util.List;

/**
 * @author psj
 * @date 2022/3/23 9:33
 * @File: sortList.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/sort-list/
// 给你链表的头结点head ，请将其按升序排列并返回排序后的链表

public class sortList {
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

    // 归并排序
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    // 这里的tail可以视为需要排序的数组最后一个元素的下标
    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }

        // 利用快慢指针找链表的中间节点
        ListNode slow = head;
        ListNode fast = head;

        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }

        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sortList = merge(list1, list2);
        return sortList;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(-1);
        // 相对数组归并排序中用于在两个部分数组遍历的指针
        ListNode temp = dummyHead;
        ListNode temp1 = head1;
        ListNode temp2 = head2;

        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }

            temp = temp.next;
        }
        // 处理剩下的链表
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }

        return dummyHead.next;
    }

}
