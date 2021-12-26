import java.util.PriorityQueue;

/**
 * @author psj
 * @date 2021/12/26 21:17
 * @File: mergeKLists.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/merge-k-sorted-lists/
// 给你一个链表数组，每个链表都已经按升序排列。将所有链表合并到一个升序链表中，返回合并后的链表。

/**
 * 1.比较器的使用: 参考博客https://blog.csdn.net/qq_41398418/article/details/121326284
 * 2.优先队列(大小最大为K)poll/add一次时间复杂度为O(logK),
 *   假设所有链表节点个数为N, 因为要将所有节点假如且弹出，所以该题算法的时间复杂度为O(NlogK)
 */
public class mergeKLists {
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

    public ListNode mergeKLists(ListNode[] lists) {
        // 对输入做判断
        if (lists.length == 0) {
            return null;
        }

        // 虚拟头节点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;

        // 使用最小堆(即优先级队列)
        // 该最小堆储存的元素最多为lists.length(即K), 后面参数为自定义的比较器
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                lists.length, (a, b) -> (a.val - b.val)
        );

        // 将K个链表的头节点放入最小堆中
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        while (!pq.isEmpty()) {
            // 每次获取队列中K个节点中值最小的节点，接到p指针
            ListNode node = pq.poll();
            p.next = node;

            // 将上面取得节点所在链表的下一个节点放入最小堆中(如果存在)
            if (node.next != null) {
                pq.add(node.next);
            }

            p = p.next;
        }

        return dummy.next;
    }
}
