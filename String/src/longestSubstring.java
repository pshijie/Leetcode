import java.util.HashMap;
import java.util.Map;

/**
 * @author psj
 * @date 2022/5/28 9:07
 * @File: longestSubstring.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/
// 给你一个字符串s和一个整数k，请你找出s中的最长子串，要求该子串中的每一字符出现次数都不少于k。返回这一子串的长度

public class longestSubstring {
    public int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        Map<Character, Integer> counter = new HashMap<>();
        // 统计每个字符总出现次数
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (char c : counter.keySet()) {
            // 如果当前子串中包含总出现次数小于k的字符,就根据该字符将s进行划分
            // 在划分后的多个子串中继续寻找满足条件的子串
            if (counter.get(c) < k){
                int res = 0;
                for (String t : s.split(String.valueOf(c))) {
                    res = Math.max(res, longestSubstring(t, k));
                }
                return res;
            }
        }
        // 执行到该步说明原字符串s中每个字符总出现次数都大于k
        return s.length();
    }
}
