import java.util.Stack;

/**
 * @author psj
 * @date 2022/3/26 10:00
 * @File: MyQueue.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/implement-queue-using-stacks/
// 请你仅使用两个栈实现先入先出队列
public class MyQueue {
    private Stack<Integer> s1;
    private Stack<Integer> s2;  // s2作为一个中转的栈

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        this.s1.push(x);
    }

    public int pop() {
        this.peek();
        // 只要对s2操作即可
        return s2.pop();
    }

    public int peek() {
        // 当s2为空时才需要将s1的所有元素加入到s2中**
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        // s2的栈顶元素可能是原来的栈顶，也可能是由s1加入的新一轮元素的栈顶
        return s2.peek();
    }

    public boolean empty() {
        // 当两个栈全部为空时才为空
        return s1.isEmpty() && s2.isEmpty();
    }
}
