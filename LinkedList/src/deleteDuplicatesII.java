import java.net.URLEncoder;
import java.util.*;

/**
 * @author psj
 * @date 2022/3/25 8:38
 * @File: deleteDuplicatesII.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
// 给定一个已排序的链表的头head，删除原始链表中所有重复数字的节点，只留下不同的数字(所有重复的不会保留一个)。返回已排序的链表

public class deleteDuplicatesII {
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

    // 方法1：递归
    public ListNode deleteDuplicates_recursion(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }

        // 当前节点的值和下一个节点的值不相等，当前节点保留
        // 当前节点的next指针需要指向后续链表删除所有重复元素后的头节点
        if (head.val != head.next.val) {
            head.next = deleteDuplicates_recursion(head.next);
            return head;
        }else {
            // 当前节点和下一个节点的值相等，则两个节点都不保留
            // notDup指针用于和当前节点不重复的节点
            ListNode notDup = head.next.next;
            while (notDup != null && notDup.val == head.val){
                notDup = notDup.next;
            }

            // 找到第一个和当前节点不重复的节点后
            return deleteDuplicates_recursion(notDup);
        }
    }

    // 方法2：遍历
    public ListNode deleteDuplicates_traverse(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null){
            // 出现和当前节点相同的值时就选择跳过
            while (cur.next != null && cur.val == cur.next.val){
                cur = cur.next;
            }
            // 如果pre和cur之间没有重复节点
            if (pre.next == cur){
                pre = pre.next;
            }else {  // pre和cur之间有重复的节点,将pre的next指针指向cur的next指针指向的节点(该节点肯定与cur不同)
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    // 方法3：使用HashMap
    public ListNode deleteDuplicates_HashMap(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode cur = head;
        // 存储每个节点出现的次数
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        while (cur != null){
            hashMap.put(cur.val, hashMap.getOrDefault(cur.val, 0)+1);
            cur = cur.next;
        }

        // 存储出现次数大于1的节点的值
        List<Integer> beyond_one = new ArrayList<>();
        Iterator<Map.Entry<Integer, Integer>> iterator = hashMap.entrySet().iterator();
        // 找出出现次数大于1的节点的值
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> curEntry = iterator.next();
            if (curEntry.getValue() > 1){
                beyond_one.add(curEntry.getKey());
            }
        }

        // 第二次遍历，将出现次数大于1的节点删除
        ListNode pre = new ListNode(-1);
        cur = head;
        pre.next = head;

        while (cur != null){
            if (beyond_one.contains(cur.val)){
                // 如果一开始就出现重复的节点，不移动head节点的话head节点就被移除，最后返回不了head
                if (cur.val == head.val){
                    head = head.next;
                }
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }
}
