/**
 * @author psj
 * @date 2022/5/1 9:15
 * @File: isPalindrome.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/valid-palindrome/
// 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写

public class isPalindromeString {
    // 方法1：使用API
    public static boolean isPalindrome_API(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        StringBuffer sb2 = new StringBuffer(sb).reverse();
        return sb.toString().equals(sb2.toString());
    }

    // 方法2：双指针
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int low = 0;
        int high = s.length() - 1;
        while (low < high) {
            while (low < high && !Character.isLetterOrDigit(s.charAt(low))) {
                low++;
            }
            while (low < high && !Character.isLetterOrDigit(s.charAt(high))) {
                high--;
            }
            if (!(Character.toLowerCase(s.charAt(low)) == Character.toLowerCase(s.charAt(high)))) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}
