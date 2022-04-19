import java.util.Stack;

/**
 * @author psj
 * @date 2022/4/19 8:51
 * @File: removeDuplicates.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
// 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
// 在S上反复执行重复项删除操作，直到无法继续删除

public class removeDuplicatesAdj {
    public static String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        char[] arrCh = s.toCharArray();

        stack.push(arrCh[0]);
        for (int i = 1; i < arrCh.length; i++) {
            if (!stack.isEmpty() && stack.peek() == arrCh[i]) {
                stack.pop();
                continue;
            }
            stack.push(arrCh[i]);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        String result = sb.reverse().toString();
        return result;
    }

}
