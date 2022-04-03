import java.util.List;

/**
 * @author psj
 * @date 2022/4/3 9:17
 * @File: wordBreak.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/word-break/
// 给你一个字符串s 和一个字符串列表wordDict作为字典。请你判断是否可以利用字典中出现的单词拼接出s

public class wordBreak {
    // 方法1：动态规划
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp[i]表示s[0...i-1]经过拆分后的单词是否都在字典中
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            // 假设s=abcdef,此时i指向下标5(字母e),要判断dp[5]需要知道
            // 1.dp[0]是否为true，abcd是否在wordDict中(相当于拆分为""和abcd)
            // 2.dp[1]是否为true，bcd是否在wordDict中(相当于拆分为a和bcd)
            // 3.dp[2]是否为true，cd是否在wordDict中(相当于拆分为ab和cd)
            // 4.dp[3]是否为true，d是否在wordDict中(相当于拆分为abc和d)
            // 5.dp[4]是否为true，""是否在wordDict中(相当于拆分为abcd和"")
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    // 方法2：DFS
    public boolean wordBreak_DFS(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0);
    }

    public boolean dfs(String s, List<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            // 当前截取的子串不在字典就截取更大的子串
            if (!wordDict.contains(s.substring(start, i))) {
                continue;
            }
            // 当前截取的子串在字典中，继续拆分剩下子串并判断
            if (dfs(s, wordDict, i)){
                return true;
            }
        }
        return false;
    }
}
