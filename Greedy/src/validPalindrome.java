/**
 * @author psj
 * @date 2022/5/1 9:32
 * @File: validPalindrome.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/valid-palindrome-ii/
// 给定一个非空字符串s，最多删除一个字符。判断是否能成为回文字符串

public class validPalindrome {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int low = 0;
        int high = s.length() - 1;
        while (low < high) {
            if (s.charAt(low) == s.charAt(high)) {
                low++;
                high--;
            } else {
                // 删除一个字符即low或者high移动一位
                return validPalindromePart(s, low, high - 1) || validPalindromePart(s, low + 1, high);
            }
        }
        return true;
    }

    public boolean validPalindromePart(String s, int low, int high) {
        for (int i = low, j = high; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
