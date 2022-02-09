import java.util.ArrayList;
import java.util.List;

/**
 * @author psj
 * @date 2022/2/9 20:23
 * @File: generateParenthesis.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/generate-parentheses/
// 数字n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合
// 回溯思路：将题目转换为在2*n个位置上放置左括号或者右括号

public class generateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        backtrace(n, n, temp, result);
        return result;
    }

    public void backtrace(int left, int right, StringBuilder temp, List<String> result) {
        // 如果剩余的左括号多于右括号，不合法
        if (right < left) {
            return;
        }
        // 剩余左括号或者右括号数量小于0，不合法
        if (left < 0 || right < 0) {
            return;
        }
        // 括号恰好全部用完，符合条件
        if (left == 0 && right == 0) {
            result.add(temp.toString());
            return;
        }

        // 尝试放一个左括号
        temp.append('(');
        backtrace(left - 1, right, temp, result);
        temp.deleteCharAt(temp.length() - 1);

        // 尝试放一个右括号
        temp.append(')');
        backtrace(left, right - 1, temp, result);
        temp.deleteCharAt(temp.length() - 1);

    }
}
