import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author psj
 * @date 2022/3/25 9:15
 * @File: longestValidParentheses.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/longest-valid-parentheses/
// 给你一个只包含'(' 和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长

public class longestValidParentheses {
    // 方法1：动态规划
    public int longestValidParentheses(String s) {
        if (s.length() <= 1 || s == null) {
            return 0;
        }
        int result = 0;
        // dp[i]表示以s[i]结尾的字符串的最长有效子串长度
        int[] dp = new int[s.length()];
        dp[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // 注意：要判断的i-2是否在合理范围内
                    if (i - 2 >= 0) {
                        dp[i] = dp[i - 2] + 2;
                    } else {
                        dp[i] = 2;
                    }
                    // 此时s[i-1]=')'
                    // dp[i-1]表示以s[i-1]的最长字符串的长度，当前位置i减去该长度再减去1得到位置k
                    // 然后判断s[k]是否能匹配s[i](即是否为'(')
                } else if (s.charAt(i - dp[i - 1] - 1) == '(') {
                    // 同样需要考虑i-dp[i-1]-2是否在合理范围内
                    if (i - dp[i - 1] - 2 >= 0) {
                        dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
                    } else {
                        dp[i] = dp[i - 1] + 2;
                    }
                }
                result = Math.max(result, dp[i]);
            }
            // 如果s[i]='('，则肯定找不到以s[i]结尾的有效子串，所以dp[i]=0(默认初始化就为0，不需要改变)
        }
        return result;
    }

    // 方法2:使用栈
    public int longestValidParentheses_stack(String s) {
        Stack<Integer> stack = new Stack<>();

        int result = 0;
        int start = 0;  // start是记录符合的子串的起始位置，之所以要声明是因为子串的起始字符会被pop出去，所以要记录位置
        for (int i = 0; i < s.length(); i++) {
            // 当前遍历元素为'('就进栈
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {  // 当前为')'
                // 当前栈为空,说明s[i]=')'无法匹配有效的'('
                if (stack.isEmpty()) {
                    // 开始找以start为起始点的字符串是否符合
                    start = i + 1;
                } else {
                    stack.pop();
                    // 栈为空说明以start为起始点的子串符合条件
                    // 栈不为空说明以当前右括号为右端点的合法括号序列的左端点为栈顶元素的下一个元素
                    result = Math.max(result, stack.isEmpty() ? i - start + 1 : i - stack.peek());
                }
            }
        }
        return result;
    }

}
