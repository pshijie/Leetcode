/**
 * @author psj
 * @date 2022/5/1 8:58
 * @File: oddEvenList.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/odd-even-linked-list/
// 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
// 第一个节点的索引被认为是奇数， 第二个节点的索引为偶数 ，以此类推

public class oddEvenList {
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

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 指向奇数链表的头节点
        ListNode oddHead = head;
        // 指向奇数链表的尾节点(用于指向p节点)
        ListNode oddTail = oddHead;
        // 指向偶数链表的头节点(奇数链表头节点的下一位)
        ListNode evenHead = head.next;
        // 指向偶数链表的尾节点(用于指向p节点)
        ListNode evenTail = evenHead;
        // 移动遍历链表的节点
        ListNode p = head.next.next;
        while (p != null) {
            oddTail.next = p;  // p初始指向第三个节点，属于奇数链表
            oddTail = oddTail.next;
            p = p.next;
            // 链表还有元素的前提下，当前p指向的是偶数链表的元素
            if (p != null) {
                evenTail.next = p;
                evenTail = evenTail.next;
                p = p.next;
            }
        }
        // 将奇数链表的尾节点与偶数链表的头节点相连
        oddTail.next = evenHead;
        // 将偶数链表的尾节点指向null
        evenTail.next = null;
        return oddHead;
    }
}
