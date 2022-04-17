import java.util.List;

/**
 * @author psj
 * @date 2022/4/17 9:27
 * @File: rotateRight.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/rotate-list/
// 给你一个链表的头节点head,旋转链表，将链表每个节点向右移动k个位置

public class rotateRight {
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

    // 假设链表长度为n:
    // 1.为了防止k过大，需要k=k%n
    // 2.找到第n-k个节点，将后k个节点与前n-k个节点断开
    // 3.将后k个节点中的第一个节点作为新链表的头节点
    // 4.将原链表的末尾元素的next指向原head,原链表的第n-k个节点的next指向null
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        int len = 0;
        // 计算链表的长度
        ListNode temp = head;
        ListNode tail = null;
        while (temp != null) {
            len++;
            tail = temp;
            temp = temp.next;
        }

        k = k % len;
        // 找到链表的第n-k个节点，temp指向该节点
        temp = head;
        for (int i = 0; i < len - k - 1; i++) {
            temp = temp.next;
        }
        // 原链表的末尾元素的next指向原head
        tail.next = head;
        // 将后k个节点的第一个元素作为新链表的头节点
        ListNode newHead = temp.next;
        temp.next = null;
        return newHead;
    }
}
