/**
 * @author psj
 * @date 2022/4/5 10:15
 * @File: swapPairs.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/
// 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题

public class swapPairs {
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
    // 方法1:递归
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        // 参考reverseKGroup
        ListNode newHead = head.next;

        head.next = swapPairs(newHead.next);
        // 因为head.next与newHead断开，所以要将newHead与head重新连接
        newHead.next = head;
        return newHead;
    }

    // 方法2：迭代
    public ListNode swapPairs_iterate(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode temp = dummy;
        // 每次交换temp的下两个节点
        while (temp.next != null && temp.next.next != null){
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;  // 因为node1已经和node2进行了交换，所以node1距离下一组要交换的节点较近
        }

        return dummy.next;
    }
}
