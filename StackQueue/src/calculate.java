import java.util.Deque;
import java.util.LinkedList;

/**
 * @author psj
 * @date 2022/4/13 9:02
 * @File: calculate.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/basic-calculator/
// 给你一个字符串表达式s ，请你实现一个基本计算器来计算并返回它的值

public class calculate {
    public int calculate(String s) {
        Deque<Character> deque = new LinkedList<>();
        // 将s中的空格排除
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                deque.addLast(s.charAt(i));
            }
        }
        return helper(deque);
    }

    public int helper(Deque<Character> deque) {
        // 用于存储s中所有的解析好的整数(带括号内就算出来，带符号的就将原数变为正/负数)
        Deque<Integer> stack = new LinkedList<>();
        // s中的第一个完整数字一定是正数，所以初始化sign为正号
        char sign = '+';
        // 存储当前遍历的连续数字，将其进行组合存储到num中
        int num = 0;

        while (deque.size() > 0) {
            // 由于是从尾部加入，所以先移除头部的，符合栈的特点
            char c = deque.removeFirst();
            // 如果是数字就进行组合成一个整数
            if (isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            // 遇到左括号就递归将括号中的子串进行处理
            if (c == '(') {
                num = helper(deque);
            }
            // 当前遍历到的字符为运算符或者已经是最后一位数字(最后一位不能是运算符)
            if (!isDigit(c) || deque.size() == 0) {
                // 此时的sign是上一个运算符，该运算符是在当前要处理整数的前面，所以使用此时的sign运算
                if (sign == '+') {
                    stack.addLast(num);
                } else if (sign == '-') {
                    stack.addLast(-num);
                } else if (sign == '*') {
                    // 需要使用到栈中最新放入的整数(考虑到乘除的优先级，需要先计算)
                    int pre = stack.removeLast();
                    stack.addLast(pre * num);
                } else if (sign == '/') {
                    int pre = stack.removeLast();
                    stack.addLast(pre / num);
                }
                // 当前连续的字符已经处理完毕，需要重置为0
                num = 0;
                // 当前的位置的字符由于下次对两个整数的运算
                sign = c;
            }
            // 遇到右括号，说明当前子串已经计算完毕
            // 不能将该判断放在第二个if判断前面，因为需要将num和sign重置
            if (c == ')') {
                break;
            }
        }
        // 将处理好的数全部相加
        int result = 0;
        while (stack.size() > 0) {
            int top = stack.removeLast();
            result += top;
        }
        return result;
    }

    // 判断当前字符是否为数字
    public boolean isDigit(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }
}
