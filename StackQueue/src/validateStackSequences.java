import java.util.Stack;

/**
 * @author psj
 * @date 2022/5/21 9:21
 * @File: validateStackSequences.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/

public class validateStackSequences {
    // 加入pushed栈此时为[1,2,3,4,5],popped栈为[4,3,5,1,2]
    // 当i指针遍历到popped栈中的元素1时,stack.peek()!=popped[i],所以跳出while循环
    // 但此时pushed栈的元素全部遍历完毕,所以stack不为空,返回false
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            // 每次入栈一个元素,就判断栈顶元素和当前pop栈的元素是否相等
            // 如果相等就把栈的栈顶元素弹出,不相等就遍历pop的下一个元素
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
