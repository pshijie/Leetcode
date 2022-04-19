import java.util.PriorityQueue;

/**
 * @author psj
 * @date 2022/4/19 9:16
 * @File: MedianFinder.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/find-median-from-data-stream/
// 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值

public class MedianFinder {
    // 使用一个小顶堆一个大顶堆
    // large的意思不是大顶堆，而是元素比small堆都要大的堆
    private PriorityQueue<Integer> large;
    private PriorityQueue<Integer> small;

    public MedianFinder() {
        // 小顶堆
        large = new PriorityQueue<>();
        // 小顶堆
        small = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
    }

    public void addNum(int num) {
        // 1.要维护large和small之间的元素个数差不超过1
        // 2.要维护large的堆顶元素大于等于small的堆顶元素

        // 1.如果插入的元素小于small的堆顶元素，则num会留在small中
        // 为了保证两个堆元素数量差不大于1，需要把small的堆顶元素(small中最大的数)插入到large
        // 2.如果插入的元素大于small的堆顶元素，先把该元素插入到large中，再把large的堆顶元素(large中最小的数)给small
        if (small.size() >= large.size()) {
            small.offer(num);
            large.offer(small.poll());
        } else {
            large.offer(num);
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        // 堆中元素个数不一致，则元素多的堆的堆顶元素为中位数
        if (large.size() < small.size()) {
            return small.peek();
        } else if (large.size() > small.size()) {
            return large.peek();
        }
        // 如果元素一样多，则两个堆的堆顶元素的平均数为中位数
        return (double) (large.peek() + small.peek()) / 2;
    }

    public static void main(String[] args) {
        System.out.println(9/4.0);
    }
}
