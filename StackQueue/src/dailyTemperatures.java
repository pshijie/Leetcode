import java.util.Stack;

/**
 * @author psj
 * @date 2022/4/7 9:12
 * @File: dailyTemperatures.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/daily-temperatures/
// 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指在第 i 天之后，才会有更高的温度。
// 如果气温在这之后都不会升高，请在该位置用 0 来代替

public class dailyTemperatures {
    // 单调栈
    // 每个元素进栈首先将小于该元素的值全部踢出，碰到的第一个大于它的元素就是符合条件的温度，因为栈记录了每个入栈元素在数组的下标，
    // 因为栈中记录了每个元素的下标，所以可以很快计算它们减的距离
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        // **倒序遍历**,每次就可以很快找到第一个比它大的元素
        for (int i = temperatures.length - 1; i >= 0; i--) {
           // 将小于该元素的值全部踢出
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            // 栈为空的说明前面没有比它大的数
            result[i] = stack.isEmpty() ? 0 : (stack.peek() - i);
            stack.push(i);
        }
        return result;
    }
}
