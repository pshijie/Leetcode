/**
 * @author psj
 * @date 2022/5/10 8:55
 * @File: wildcardMatch.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/wildcard-matching/
// 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配
// '?' 可以匹配任何单个字符。
// '*' 可以匹配任意字符串（包括空字符串）

public class wildcardMatch {
    // 参考https://leetcode.cn/problems/wildcard-matching/solution/yi-ge-qi-pan-kan-dong-dong-tai-gui-hua-dpsi-lu-by-/
    public boolean wildcardMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // f[i][j]表示s[0...i]和p[0...j]可以匹配
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;

        for (int i = 1; i <= n; i++) {
            f[0][i] = f[0][i - 1] && p.charAt(i - 1) == '*';
        }

        // 把右横轴表示s字符串(i)，下纵轴表示p字符串(j)
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 1.当s[i-1]=p[j-1]时往矩阵右下角移动
                // 2.当p[j-1]='?'时因为可以匹配s[i-1]上的任意字符,所以也可以往右下角移动
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    f[i][j] = f[i - 1][j - 1];
                }
                // 当p[j-1]='*'时
                // 1.*不匹配字符，相当于向下移动
                // 2.*匹配字符,可以匹配当前行的任意个字符
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 1] || f[i - 1][j];
                }
            }
        }
        return f[m][n];
    }
}
