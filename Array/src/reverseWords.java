/**
 * @author psj
 * @date 2022/5/11 9:12
 * @File: reverseWords.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/reverse-words-in-a-string-iii/
// 给定一个字符串s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序

public class reverseWords {
    public String reverseWords(String s) {
        StringBuffer result = new StringBuffer();
        int n = s.length();
        int i = 0;  // 记录新单词的下标
        while (i < n) {
            int start = i;  // 当前单词起始位置
            // 当前位置元素不为空
            while (i < n && s.charAt(i) != ' ') {
                i++;
            }
            // 将当前单词反转，start+(i-p)-1表示从当前单词末尾进行定位
            for (int p = start; p < i; p++) {
                result.append(s.charAt(start + i - 1 - p));
            }
            // 可能遇到多个空格
            while (i < n && s.charAt(i) == ' ') {
                i++;
                result.append(' ');
            }
        }
        return result.toString();
    }
}
