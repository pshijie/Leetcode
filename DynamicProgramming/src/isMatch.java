import java.util.HashMap;

/**
 * @author psj
 * @date 2022/3/6 8:33
 * @File: isMatch.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/regular-expression-matching/
// 给你一个字符串s和一个字符规律p，请你来实现一个支持'.'和'*'的正则表达式匹配
public class isMatch {
    HashMap<String, Boolean> memo = new HashMap<>();

    public boolean isMatch(String s, String p) {
        return dp(s, 0, p, 0);
    }

    // dp(s,i,p,j)表示s[i,..]可以匹配p[j..]
    public boolean dp(String s, int i, String p, int j) {
        int m = s.length();
        int n = p.length();
        // 当p字符串已经遍历完毕时，此时只要判断s字符串是否遍历完毕即可
        if (j == n) {
            return i == m;
        }
        // 当s字符串遍历完毕时，不能简单看p字符串是否遍历完毕
        // 比如s="a",p="ab*c*"。此时s遍历完，p没有，但是也可以匹配
        if (i == m) {
            // p字符串剩余的字符数为奇数时，肯定是无法匹配的
            if ((n - j) % 2 == 1) {
                return false;
            }
            // 剩余的p字符串如果出现连续的两个字母，肯定无法匹配，只有确保出现一个字母一个*的形式才可
            for (; j + 1 < n; j += 2) {
                if (p.charAt(j + 1) != '*') {
                    return false;
                }
            }
            return true;
        }

        String key = s.charAt(i) + "," + p.charAt(j);
        boolean res = false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                return dp(s, i, p, j + 2) || // 通配符*匹配0次
                        dp(s, i + 1, p, j);  // 通配符*匹配多次
            } else {
                return dp(s, i + 1, p, j + 1);  // 正常字母的匹配
            }
        } else {  // s[i]!=p[j]且p[j]!='.'
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                // 通配符*只能匹配0次
                res = dp(s, i, p, j + 2);
            } else {  // 没有通配符*,字母也无法正常匹配
                res = false;
            }
        }

        memo.put(key, res);

        return res;
    }
}
