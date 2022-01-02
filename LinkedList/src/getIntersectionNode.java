/**
 * @author psj
 * @date 2022/1/2 20:32
 * @File: getIntersectionNode.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
// 给你两个单链表的头节点headA和headB，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回null（整个链式结构中不存在环）。

/**
 * 1.博客https://blog.csdn.net/qq_41398418/article/details/121706730分析了链表存在环的情况下如何找出两个链表的相交起始节点
 * 2.方法1的解析参考https://labuladong.gitee.io/algo/2/17/16/
 */
public class getIntersectionNode {
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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 判断两个链表是否存在空链表
        if (headA == null || headB == null) {
            return null;
        }

        return getIntersectionNode_1(headA, headB);
    }

    // 方法1
    public ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        ListNode cur1 = headA;
        ListNode cur2 = headB;

        int len1 = 0; // 记录headA的长度
        int len2 = 0; // 记录headB的长度

        // 1.分别计算两个链表的长度
        while (cur1.next != null) {
            len1++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            len2++;
            cur2 = cur2.next;
        }

        // 2.长的链表的头指针先移动差值步(差值步为两个链表的长度差)
        //   这样做的目的是为了两个指针到链表尾部的距离相同
        int n = Math.abs(len1 - len2);
        cur1 = headA;
        cur2 = headB;

        if (len1 > len2) {  // headA较长则移动headA的头指针
            for (int i = 0; i < n; i++) {
                cur1 = cur1.next;
            }
        } else {    // headB较长则移动headB的头指针
            for (int i = 0; i < n; i++) {
                cur2 = cur2.next;
            }
        }

        // 3.此时两个链表的头指针同时移动
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        // 4.如果满足上述while循环则说明存在相交的节点，返回即可
        //   不满足则最后cur1和cur2都为null，返回null满足题意
        return cur1;
    }

    // 方法2
    public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        ListNode cur1 = headA;
        ListNode cur2 = headB;

        while (cur1 != cur2) {
            // 1. cur1向前走一步，如果到headA末尾就转到headB继续走
            if (cur1 == null) {
                cur1 = headB;
            } else {
                cur1 = cur1.next;
            }

            // 2. cur2向前走一步，如果到headB末尾就转到headA继续走
            if (cur2 == null) {
                cur2 = headA;
            } else {
                cur2 = cur2.next;
            }
        }

        // 3.如果满足上述while循环则说明存在相交的节点，返回即可
        //   不满足则最后cur1和cur2都为null，返回null满足题意
        return cur1;
    }
}
