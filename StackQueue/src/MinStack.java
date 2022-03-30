import java.util.Stack;

/**
 * @author psj
 * @date 2022/3/30 9:28
 * @File: MinStack.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/min-stack/
// 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈

public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> min_stack;  // 原栈每存入一个数字就存储当前所有元素的最小值
    public MinStack() {
        stack = new Stack<>();
        min_stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        // 每次都将原栈的栈顶和最小栈的栈顶进行比较，如果比最小栈的栈顶还小就
        if (min_stack.isEmpty() || val <= min_stack.peek()){
            min_stack.push(val);
        }
    }

    public void pop() {
        // 先将stack进行pop，如果pop出来的值等于最小栈的栈顶元素，最小栈的栈顶元素也需要pop
        Integer popNum = stack.pop();
        if (popNum.equals(min_stack.peek())){
            min_stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min_stack.peek();
    }
}
