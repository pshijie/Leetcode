/**
 * @author psj
 * @date 2022/3/27 10:08
 * @File: addTwoNumbers.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/add-two-numbers/
// 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字
// 请你将两个数相加，并以相同形式返回一个表示和的链表

public class addTwoNumbers {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;
        // 如果有一个链表遍历完毕，就可以进行补零操作
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 最后需要检查是否还有进位
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }
}
