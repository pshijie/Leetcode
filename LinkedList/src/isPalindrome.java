/**
 * @author psj
 * @date 2022/1/4 20:39
 * @File: isPalindrome.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/palindrome-linked-list/
// 给你一个单链表的头节点head, 请你判断该链表是否为回文链表。如果是，返回true;否则返回false。
public class isPalindrome {
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

    // 先理解如何反转链表
    public ListNode reverse(ListNode head) {
        // 1. 创建pre、cur、nxt三个指针
        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt;

        while (cur != null) {
            // 以反转链表1 → 2为例，此时pre=null,cur指向1
            // 2. nxt指针指向2
            nxt = cur.next;
            // 3. 1的next指针指向pre，即null
            cur.next = pre;
            // 4. pre指针指向1
            pre = cur;
            // 5. cur指针指向2
            cur = nxt;
        }

        // 6. 当cur=null时就跳出while循环，说明链表已经反转，所以返回pre指针
        return pre;

    }

    // 使用快慢指针
    public boolean isPalindrome(ListNode head) {
        // 1. 定义快慢指针, 慢指针用于找到链表的中点(严格来说是找到后半段需要反转的链表头)
        ListNode slow, fast;
        slow = fast = head;

        // 2.快指针走两步，慢指针走一步
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 3. 将slow指针指向需要反转的链表的头节点
        // 当偶数链表为1 → 2 → 3 → 4,则需要反转的后半段链表为3 → 4。
        // 此时的slow指向3，fast指向null，slow指向的节点满足条件
        // 当奇数链表为1 → 2 → 3 → 4 → 5,则需要反转的后半段链表为4 → 5。
        // 此时的slow指向3，fast指向5，slow指向的节点应该是4，所以需要再向前移动一个为位置
        if (fast != null){
            slow = slow.next;
        }

        // 4. 将后半段链表进行反转，然后将反转的链表和前半段链表进行比较
        ListNode left = head;
        ListNode right = reverse(slow);
        while (right != null){
            // 5. 前半段和后半段节点值出现不一致的情况就返回false
            if (left.val != right.val){
                return false;
            }
            left = left.next;
            right = right.next;
        }

        // 5. 前半段和后半段节点值都一样就返回true
        return true;
    }
}
