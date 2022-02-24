import java.util.Map;

/**
 * @author psj
 * @date 2022/2/24 9:42
 * @File: minDistance.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/edit-distance/
// 给你两个单词word1和word2，请返回将word1转换成 word2所使用的最少操作数
// 可以的操作：插入一个字符;删除一个字符;替换一个字符

public class minDistance {
    // dp(i,j)表示s1[0...i]和s2[0...j]的最小编辑距离
    // 伪代码(存在重叠子问题)：
    // if(s1[i]==s2[j]){
    //      return dp(i-1,j-1)  # 不做处理，直接判断s1[0...i-1]和s2[0...j-1]的最小编辑距离
    // }else{
    //      return min(dp(i,j-1)+1,  # 插入(被插入的字符串的指针不动，因为向后插入，当前的字符还需要比较)
    //                  dp(i-1,j)+1,  # 删除(被删除的字符串的指针向前移动)
    //                  dp(i-1,j-1)+1)  # 替换(两个指针一起向前移动)
    // }
    // return dp(len(s1)-1,len(s2)-1)
    // 插入前：
    //      s1: r a d l e    s2: a p p l e
    //              i                j
    // 插入后：
    //      s1: r a d p l e  s2: a p p l e
    //              i              j
    // 删除前：
    //      s1: r a p p l e    s2: a p p l e
    //          i                 j
    // 删除后：
    //      s1: a p p l e  s2: a p p l e
    //         i              j
    // 替换前：
    //      s1: r a d p l e    s2: a p p l e
    //              i                j
    // 替换后：
    //      s1: r a p p l e  s2: a p p l e
    //            i              j

    // 方法1：备忘录
    int memo[][];

    public int minDistance_memo(String word1, String word2) {
        memo = new int[word1.length()][word2.length()];
        return dp_memo(word1, word2, word1.length() - 1, word2.length() - 1);
    }

    public int dp_memo(String s1, String s2, int i, int j) {
        // base case
        // 假设其中一个字符串的下标走到-1，则说明已经全部遍历完毕，另一个字符串要做的操作次数就只能是剩余字符串的个数
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp_memo(s1, s2, i - 1, j - 1);
        } else {
            memo[i][j] = Math.min(dp_memo(s1, s2, i, j - 1) + 1,
                            Math.min(dp_memo(s1, s2, i - 1, j - 1) + 1, dp_memo(s1, s2, i - 1, j) + 1));
        }
        return memo[i][j];
    }

    // 方法2：动态规划
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // s1[0...i]和s2[0...j]的最小编辑距离为dp[i-1][j-1]
        int dp[][] = new int[m + 1][n + 1];
        // base case
        // 假设word1的长度为5，则dp[5][0]表示整个字符串转为空串的最小编辑距离为5
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }

        // 自底向上
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }
        return dp[m][n];
    }
}
