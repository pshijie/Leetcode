/**
 * @author psj
 * @date 2022/1/3 19:40
 * @File: reverseList.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/reverse-linked-list/
// 给你单链表的头节点head, 请你反转链表, 并返回反转后的链表

/**
 * tips:
 *      1.完成反转后，新的尾节点的next指针需要指向null
 *      2.在实现递归时，直接考虑函数实现的结果，不需要去思考其中的递归过程
 */
public class reverseList {
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

    // 方法1：递归实现
    public ListNode reverseList(ListNode head) {
        // 1.当head节点为空时返回null; 当整个链表只有head节点，反转后还是head节点
        if (head == null || head.next == null) {
            return head;
        }

        // 2.递归返回反转后链表的头节点last
        // 假设链表为1 → 2 → 3 → null，则执行该步后变为:
        // 1 → 2 ← 3
        //     ⬇
        //    null
        // 注意此时的head指向1，last指针指向3
        ListNode last = reverseList(head.next);

        // 3.head.next即1的next指针指向2，2的next指针指向null，此时将指向null的指针指向1
        // 1 → 2 ← 3
        //   ←
        head.next.next = head;

        // 4.head的next指针指向null，即1的next指针指向null
        // null ← 1 ← 2 ← 3
        head.next = null;

        // 5.返回last指针，即节点3
        return last;

    }

    // 方法2：迭代实现
    public ListNode reverseList_nocur(ListNode head) {
        // 假设链表为1 → 2 → 3 → null
        ListNode pre = null;
        ListNode cur = head;  // 此时cur指针指向1

        while (cur != null) {
            // 1.使用next临时储存下一次要分离的节点,即将next指向2
            ListNode next = cur.next;
            // 2.将要从原链表分离的节点加入到反转链表中
            //  1 2 → 3 → null
            //  ↓
            // null
            cur.next = pre;
            // 3.将pre指针指向被分离的节点,即将pre指针指向1
            pre = cur;
            // 4.将cur指针指向下一个要被分离的节点,即将cur指针指向2
            cur = next;
        }

        // 5.返回pre指针,在上述链表反转完毕后pre指向3,cur指向null
        return pre;
    }
}
