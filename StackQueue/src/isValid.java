import java.util.List;
import java.util.Stack;

/**
 * @author psj
 * @date 2022/3/19 8:58
 * @File: isValid.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/valid-parentheses/
// 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
// 有效字符串需满足：
//      左括号必须用相同类型的右括号闭合
//      左括号必须以正确的顺序闭合

public class isValid {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> left = new Stack<>();

        for (char c : s.toCharArray()) {
            // 如果是左括号就入栈
            if (c == '(' || c == '{' || c == '[') {
                left.push(c);
            } else {
                // 如果栈不为空且字符是右括号
                if (!left.isEmpty() && findLeft(c) == left.peek()) {
                    left.pop();
                }
                // 栈为空但是还有右括号或者当前进栈的右括号和栈顶不匹配
                else {
                    return false;
                }
            }
        }
        return left.isEmpty();
    }

    // 将当前的左括号替换为右括号
    public char findLeft(char c) {
        if (c == '}') {
            return '{';
        } else if (c == ')') {
            return '(';
        } else {
            return '[';
        }
    }
}
