import java.util.LinkedList;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/3/26 10:15
 * @File: MyStack.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/implement-stack-using-queues/
// 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作

public class MyStack {
    // 方法1：使用两个队列
//    Queue<Integer> queue1;
//    Queue<Integer> queue2;
//    public MyStack() {
//        queue1 = new LinkedList<>();
//        queue2 = new LinkedList<>();
//    }
//
//    public void push(int x) {
//        // 1.先将每次新加入的元素入queue2(需要满足队列前端的元素是最后入栈的元素)
//        // 2.再将queue1中所有元素加入到queue2中(保证满足栈的后进先出)
//        // 3.最后将queue2中的元素全部赋值给queue1
//        queue2.offer(x);  // 步骤1
//        // 步骤2
//        while (!queue1.isEmpty()){
//            queue2.offer(queue1.poll());
//        }
//        // 到该步时queue1为空
//        queue1 = queue2;
//        queue2 = new LinkedList<>();
//
//    }
//
//    // 下面判断全部使用queue1即可
//    public int pop() {
//        return queue1.poll();
//    }
//
//    public int top() {
//        return queue1.peek();
//    }
//
//    public boolean empty() {
//        return queue1.isEmpty();
//    }

    // 方法2:使用一个队列
    Queue<Integer> queue;
    public MyStack(){
        queue = new LinkedList<>();
    }

    public void push(int x){
        // 1.先记录下没入新元素前的队列大小
        int n = queue.size();
        // 2.将新元素入队列(此时在队尾)
        queue.offer(x);
        // 3.将队头的元素移入队尾(除了新加入的元素)
        for (int i = 0; i < n; i++) {
            queue.offer(queue.poll());
        }
    }

    public int top(){
        return queue.peek();
    }

    public int pop(){
        return queue.poll();
    }

    public boolean empty(){
        return queue.isEmpty();
    }

}

