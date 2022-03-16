/**
 * @author psj
 * @date 2022/3/16 9:28
 * @File: longestPalindrome.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/longest-palindromic-substring/
// 给你一个字符串 s，找到 s 中最长的回文子串

public class longestPalindrome {
    // 双指针从中间向两边发散
    public String longestPalindrome(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            // 变量字符串s中的不同元素，以不同位置的元素作为中心点
            // l和r指针开始指向相同的元素是为了找以s[i]为起点，长度为奇数的回文串
            String s1 = palindrome(s, i,i);
            // l和r指针开始指向相邻的元素是为了找以s[i]和s[i+1]为起点，长度为偶数的回文串
            String s2 = palindrome(s, i,i+1);
            result = result.length() > s1.length() ? result : s1;
            result = result.length() > s2.length() ? result : s2;
        }
        return result;
    }

    // 在字符串s中寻找以s[l]和s[r]为中心的最长回文串
    public String palindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            // 在指针不越界的情况下，如果两个指针指向的元素相等，就向两边扩散
            l--;
            r++;
        }
        // l+1的原因是上面的while循环已经进行了加1操作，r不加是因为substring取的是开区间
        return s.substring(l+1,r);
    }
}
