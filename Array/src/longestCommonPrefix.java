/**
 * @author psj
 * @date 2022/4/13 9:47
 * @File: longestCommonPrefix.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/longest-common-prefix/solution/
// 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""

public class longestCommonPrefix {
    // 两两比较
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return "";
        }
        // 将前两个字符串的前缀pre找出，后续的字符串和前面的字符串的最长前缀肯定是pre的子集
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            result = longestCommonPrefix(result, strs[i]);
            // 如果前面字符串的最长前缀为空串，就没有必要比较后面的字符串了
            if (result.length() == 0){
                break;
            }
        }
        return result;
    }

    // 寻找两个子串的公共前缀
    public String longestCommonPrefix(String s1, String s2) {
        int minLen = Math.min(s1.length(), s2.length());
        int index = 0;
        while (index < minLen && s1.charAt(index) == s2.charAt(index)) {
            index++;
        }
        return s1.substring(0, index);
    }
}
