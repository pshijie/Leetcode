import java.util.Deque;
import java.util.LinkedList;

/**
 * @author psj
 * @date 2022/4/2 10:02
 * @File: removeKdigits.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/remove-k-digits/
// 给你一个以字符串表示的非负整数num和一个整数k ，移除这个数中的k位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字

public class removeKdigits {
    public String removeKdigits(String num, int k) {
        // 维护的一个单调递增栈
        Deque<Character> deque = new LinkedList<>();
        int m = num.length();

        // 每移入一个数字前先判断栈顶元素是否大于该数，大于的话就将栈顶元素移除，
        // 同时记住将k减1，因为移除了一个数字
        // 之所以这样处理是因为假设有数字4252，要移除一个数字：
        // 移除4时剩余252，移除2时剩余452，肯定移除4剩余的数字更小，
        // 所以核心思想是比较当前元素和上一位元素的大小，哪个小就移除哪个
        for (int i = 0; i < m; i++) {
            char curDigit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > curDigit) {
                // 相当于不停的将当前元素和之前元素比较，直至满足移除了k个数字或者队列位空
                deque.pollLast();
                k--;
            }
            deque.offerLast(curDigit);
        }

        // 执行到该步说明当前队列中已经构成了一个单调递增队列，如果还需要移除k个数字就继续移除
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }

        StringBuilder result = new StringBuilder();
        // 记录单调递增队列的开头是否为0
        boolean firstZero = true;
        while (!deque.isEmpty()) {
            char curDigit = deque.pollFirst();
            // 递增队列的开头为0
            if (firstZero && curDigit == '0') {
                continue;
            }
            // 执行到该步说明开头已经不为0了，开始加入到结果中
            firstZero = false;
            result.append(curDigit);
        }

        return result.length() == 0 ? "0" : result.toString();

    }
}
