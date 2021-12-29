/**
 * @author psj
 * @date 2021/12/29 21:22
 * @File: detectCycle.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/linked-list-cycle-ii/
// 给定一个链表，返回链表开始入环的第一个节点。如果链表无环, 则返回null

/**
 * tips:
 *      具体算法推导参考 https://labuladong.gitee.io/algo/2/21/55/
 */
public class detectCycle {
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

    public ListNode detectCycle(ListNode head) {
        // 1.定义两个快慢指针
        ListNode fast = head;
        ListNode slow = head;

        // 2.快指针走一步，慢指针走两步
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // 3. 如果两个指针相遇，则不再往下走
            if (fast == slow) {
                break;
            }
        }

        // 3.判断一下跳出while是因为无环还是因为指针相遇
        if (fast == null || fast.next == null) {  // 满足其中任何一个说明无环
            return null;
        }

        // 4.将两个指针中的其中一个重新指向头节点
        slow = head;

        // 5.两个指针同时移动一步,直至指针相交
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }

        // 6.相交的节点就是环的起始点
        return slow;
    }
}
