/**
 * @author psj
 * @date 2022/5/25 8:47
 * @File: MyCircularQueue.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/design-circular-queue/

public class MyCircularQueue {
    private int[] queue;  // 固定大小的数组,用于保存循环队列的元素
    private int headIndex;  // 保存队首head的索引，用于取队头元素
    private int count;  // 循环队列当前的长度,用于判断是否继续操作
    private int capacity;  // 循环队列的容量，用于判断队列是否存满

    public MyCircularQueue(int k) {
        this.capacity = k;
        this.queue = new int[k];
        this.headIndex = 0;
        this.count = 0;
    }

    public boolean enQueue(int value) {
        if (this.count == this.capacity) {
            return false;
        }
        // (headIndex+count)%capacity表示当前元素要存储在队列中的下标
        this.queue[(this.headIndex + this.count) % this.capacity] = value;
        this.count += 1;
        return true;

    }

    public boolean deQueue() {
        if (this.count == 0) {
            return false;
        }
        // 删除元素是从队头删除,所以队头需要移动一格
        this.headIndex = (this.headIndex + 1) % this.capacity;
        this.count -= 1;
        return true;
    }

    public int Front() {
        if (this.count == 0) {
            return -1;
        }
        return this.queue[this.headIndex];
    }

    public int Rear() {
        if (this.count == 0) {
            return -1;
        }
        int tailIndex = (this.headIndex + this.count - 1) % this.capacity;
        return this.queue[tailIndex];
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean isFull() {
        return this.capacity == this.count;
    }
}
