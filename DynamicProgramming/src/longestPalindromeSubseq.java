/**
 * @author psj
 * @date 2022/2/25 10:32
 * @File: longestPalindromeSubseq.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/longest-palindromic-subsequence/
// 给你一个字符串s,找出其中最长的回文子序列，并返回该序列的长度
// 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列

/**
 * 该问题是针对一个字符串的，所以只需要和针对两个字符串一样设置i和j指针，只不过是设置在单个字符串上
 */

public class longestPalindromeSubseq {

    // 假设字符串s：c  b  x  a  b  y  c
    //             i i+1         j-1 j
    // 因为s[i]==s[j]，所以dp[i][j]=dp[i+1][j-1]+2
    // 假设字符串s：a  b  x  a  b  y  b
    //             i i+1         j-1 j
    // 因为s[i]!=s[j]，所以不能以是s[i]和s[j]同时作为最长回文子序列
    // 此时需要比较s[i+1,j]和s[i,j-1]两者的回文子序列的长度
    public int longestPalindromeSubseq(String s) {
        int n = s.length();

        // dp[i][j]表示s[i..j]上的最长回文子序列长度
        int[][] dp = new int[n][n];

        // base case 
        // 对于一个字符，最长回文串长度只能为1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // 这里的i和j是从n-1和i+1开始的，因为i需要小于j，所以dp表只需要遍历一半
        // 1 ? ? ? *   其中*为dp[0][n-1]，表示最终需要的结果
        // 0 1 ? ? ?   dp[i][j-1] → dp[i][j]
        // 0 0 1 ? ?               /     ↑
        // 0 0 0 1 ?   dp[i+1][j-1]  dp[i+1][j]
        // 0 0 0 0 1
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][n-1];
    }
}
