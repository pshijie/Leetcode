import java.util.Stack;

/**
 * @author psj
 * @date 2022/4/6 9:50
 * @File: CQueue.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
// 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail和deleteHead
// 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
public class CQueue {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;
    public CQueue() {
        this.inStack = new Stack<>();
        this.outStack = new Stack<>();
    }

    public void appendTail(int value) {
        inStack.push(value);
    }

    public int deleteHead() {
        // 如果出栈不为空，就直接pop出栈
        if (!outStack.isEmpty()){
            return outStack.pop();
        }else {
            // 如果出栈为空，此时需要将入栈的所有元素加入到出栈中
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }

            // 此时出栈可能没有元素，需要进行判断
            return !outStack.isEmpty() ? outStack.pop() : -1;
        }
    }
}
