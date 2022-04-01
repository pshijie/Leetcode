/**
 * @author psj
 * @date 2022/4/1 9:50
 * @File: getKthFromEnd.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
// 输入一个链表，输出该链表中倒数第k个节点。本题从1开始计数，即链表的尾节点是倒数第1个节点
public class getKthFromEnd {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode move = head;
        // 这里判断k>1是因为从1开始计算
        while (k > 1 && move.next != null) {
            move = move.next;
            k--;
        }

        while (move.next != null){
            head = head.next;
            move = move.next;
        }

        return head;


    }
}
