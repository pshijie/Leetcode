import java.util.HashMap;

/**
 * @author psj
 * @date 2022/3/11 12:38
 * @File: minWindow.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/minimum-window-substring/
// 给你一个字符串s一个字符串t 。返回s中涵盖t所有字符的最小子串。如果s中不存在涵盖t所有字符的子串，则返回空字符串""
// 滑动窗口框架：总结就是先移动右窗口找到可行解，在移动左窗口找到最优解
//    map<char, int> need, window;
//    for(char c : t) need[c]++  // 对字符串t的字母进行统计
//    int left=0, right=0
//    while(right < s.size()){
//          char c = s[right]  // c为即将移入窗口的字符
//          right++  // 移动右窗口
//          // 进行窗口内的数据更新
//          ...
//
//          // 判断左侧窗口是否要收缩
//          while(窗口需要收缩){
//              char d = s[left];  // d为即将移出窗口的字符
//              // 移动左窗口
//              left++;
//              // 进行窗口内的数据更新
//              ...
//          }
//    }
public class minWindow {
    public String minWindow(String s, String t) {
        // 用于记录字符串t中每个字符和出现次数的映射
        HashMap<Character, Integer> need = new HashMap<>();
        // 用于记录当前窗口每个字符和出现次数的映射
        HashMap<Character, Integer> window = new HashMap<>();

        // 统计t中字符出现的次数
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;  // 记录左窗口和右窗口的位置
        int vaild = 0;  // 存储当前窗口达到字符要求的字符数目
        int start = 0, len = Integer.MAX_VALUE;  // 记录最小覆盖子串的起始位置和其长度
        while (right < s.length()) {
            char c = s.charAt(right);  // 移入窗口的字符
            right++;
            // 进行数据更新
            if (need.containsKey(c)) {  // 注意不要用==，因为此时比较的是Integer对象
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    vaild++;
                }
            }

            // 判断左窗口是否要收缩(当前窗口每个字符数目达到要求数目开始收缩，当其中某个字符的数目由于减少没达到要求数则停止收缩)
            while (vaild == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                char d = s.charAt(left);  // 移除窗口的字符
                left++;  // 移动左窗口
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        vaild--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
