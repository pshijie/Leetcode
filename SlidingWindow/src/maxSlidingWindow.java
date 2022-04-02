import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author psj
 * @date 2022/4/2 8:44
 * @File: maxSlidingWindow.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/sliding-window-maximum/
// 给你一个整数数组nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的k个数字。滑动窗口每次只向右移动一位
// 返回滑动窗口中的最大值

public class maxSlidingWindow {
    // 单调队列
    class MonotonicQueue {
        LinkedList<Integer> q = new LinkedList<>();

        // 将队列中小于n的元素全部移除，再将n加入队列
        public void push(int n) {
            // 将队列中小于n的元素全部移除
            while (!q.isEmpty() && q.getLast() < n) {
                q.pollLast();
            }
            // 将n加入队列
            q.addLast(n);
        }

        // 在push方法中将新元素加入到对尾，说明在队头到队尾是递减的
        public int max() {
            return q.getFirst();
        }

        // 将队列的队头元素删除
        public void pop(int n) {
            // 需要判断以下要删除的元素是否还在队列中，因为可能在加入新元素时已经被移除
            // 正常来说，如果每次加入到队列的元素是单调递减的，此时队头的元素肯定是第一个添加的
            if (n == q.getFirst()) {
                q.pollFirst();
            }
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                // 将窗口填满前nums的前k-1个元素
                window.push(nums[i]);
            } else {
                // 向前滑动窗口
                window.push(nums[i]);
                // 记录当前窗口的最大值
                result.add(window.max());
                // 移除旧数字
                window.pop(nums[i - k + 1]);
            }
        }

        // 转换为int数组
        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }
}
