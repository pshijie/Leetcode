import java.util.Collections;
import java.util.LinkedList;

/**
 * @author psj
 * @date 2022/3/31 9:10
 * @File: decodeString.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/decode-string/
// 给定一个经过编码的字符串，返回它解码后的字符串。
// 编码规则为: k[encoded_string]，表示其中方括号内部的encoded_string 正好重复k次。注意k保证为正整数

public class decodeString {
    // 栈
    int ptr = 0; // 记录遍历字符串的当前位置下标

    public String decodeString(String s) {
        LinkedList<String> stack = new LinkedList<>();
        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            // 如果是一个/连续的数字(可以不只是个位数，可能是十位数等)
            if (Character.isDigit(cur)) {
                String digit = getDigits(s);
                stack.addLast(digit);
                // 如果当前字符是一个字母或者是一个左括号，直接入栈
            } else if (Character.isLetter(cur) || cur == '[') {
                stack.addLast(String.valueOf(s.charAt(ptr)));
                ptr++;
                // 如果当前字符是右括号，开始处理之前入栈的字符
            } else {
                ptr++;
                LinkedList<String> sub = new LinkedList<>();
                // 如果stack队尾(视为栈顶)不是左括号的字符依次放入sub的队尾
                while (!"[".equals(stack.peekLast())) {
                    sub.addLast(stack.removeLast());
                }

                // 此时的sub假设为["b",a"]，应该调整为["a","b"]才是原始字符串的顺序
                Collections.reverse(sub);

                // 执行到该步说明s的当前位置是左括号，需要移除
                stack.removeLast();
                // 移除左括号后当前stack的队尾(视为栈顶)就是sub出现的次数
                int repNum = Integer.parseInt(stack.removeLast());
                StringBuffer o = new StringBuffer();
                String temp = getString(sub);
                while (repNum > 0) {
                    o.append(temp);
                    repNum--;
                }
                // 将组建好的字符串入栈
                stack.addLast(o.toString());
            }
        }
        return getString(stack);
    }

    // 如果s当前位置是一个(一串)数字，将这些数字转换为一个字符串
    public String getDigits(String s) {
        StringBuffer sb = new StringBuffer();
        // 如果当前位置在s中是一个数字，将该数字入栈
        while (Character.isDigit(s.charAt(ptr))) {
            sb.append(s.charAt(ptr));
            ptr++;
        }

        return sb.toString();
    }

    // 将当前的sub中的每个String进行拼接
    public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }

}
