import java.util.ArrayList;
import java.util.List;

/**
 * @author psj
 * @date 2022/3/19 9:15
 * @File: reorderList.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/reorder-list/

public class reorderList {
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

    // 方法1：将链表中的元素存储到list中然后首尾遍历
    public void reorderList_twoTraverse(ListNode head) {
        if (head == null) {
            return;
        }
        // 将链表节点存储到list中
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        // 使用首尾指针取元素
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            // 不能在第j个节点的next指针指向i后在判断
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }

        // list中只是保存指向节点的指针
        list.get(i).next = null;
    }

    // 方法2：将原始链表切分为两个链表，然后将第二个链表逆序，
    // 再用第一个链表的元素与第二个链表的元素依次相连
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // 使用快慢指针找到原始链表的中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 在链表中点处断开
        ListNode newHead = slow.next;
        slow.next = null;
        // 将第二个链表进行反转
        newHead = reverseList(newHead);

        // 将两个链表依次连接
        while (newHead != null) {
            ListNode temp = newHead.next;
            newHead.next = head.next;
            head.next = newHead;
            head = newHead.next;
            newHead = temp;
        }

    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        // 创建一个尾指针，作为反转后的链表的头节点
        ListNode tail = head;
        head = head.next;

        tail.next = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = tail;
            tail = head;
            head = temp;
        }

        return tail;
    }
}
