import java.util.List;

/**
 * @author psj
 * @date 2022/3/27 9:40
 * @File: sortOddEvenList.java
 * @Software: IntelliJ IDEA
 */
// https://mp.weixin.qq.com/s/0WVa2wIAeG0nYnVndZiEXQ
// 给定一个奇数位升序，偶数位降序的链表，将其重新排序

public class sortOddEvenList {
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

    // 1. 按奇偶位置拆分链表，得1->3->5->7->NULL和8->6->4->2->NULL
    // 2. 反转偶链表，得1->3->5->7->NULL和2->4->6->8->NULL
    // 3. 合并两个有序链表，得1->2->3->4->5->6->7->8->NULL
    public ListNode sortOddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode evenHead = partition(head);
        evenHead = reverse(evenHead);
        return merge(head, evenHead);
    }

    public ListNode merge(ListNode oddHead, ListNode evenHead) {
        ListNode dummy = new ListNode(-1);
        ListNode move = dummy;
        while (oddHead != null && evenHead != null) {
            if (oddHead.val <= evenHead.val) {
                move.next = oddHead;
                oddHead = oddHead.next;
            } else {
                move.next = evenHead;
                evenHead = evenHead.next;
            }
            move = move.next;
        }

        if (oddHead != null) {
            move.next = oddHead;
        }
        if (evenHead != null) {
            move.next = evenHead;
        }
        return dummy.next;

    }

    public ListNode reverse(ListNode evenHead) {
        ListNode dummy = new ListNode(-1);
        ListNode p = evenHead;
        // 头插法
        while (p != null) {
            ListNode temp = p.next;
            p.next = dummy.next;
            dummy.next = p;
            p = temp;
        }
        return dummy.next;
    }

    // 按奇偶位置拆分链表
    public static ListNode partition(ListNode head) {
        ListNode evenHead = head.next;
        ListNode odd = head;
        ListNode even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = null;
        return evenHead;  // head此时就是oddHead
    }

}

