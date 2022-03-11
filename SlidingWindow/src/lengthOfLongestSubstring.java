import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author psj
 * @date 2022/3/10 11:00
 * @File: lengthOfLongestSubstring.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
// 给定一个字符串s ，请你找出其中不含有重复字符的最长子串的长度

public class lengthOfLongestSubstring {
    // 方法1：滑动窗口，左指针每一轮移动一位
    // 对于字符串abcabcbb
    // 第一轮：a b c a b c b b  最长字符串为(abc)abcbb
    //       start
    // 第二轮：a b c a b c b b  最长字符串为a(bca)bcbb
    //        start
    // 后续每次start移动一位继续遍历
    public int lengthOfLongestSubstring_force(String s){
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为-1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格
                occ.remove(s.charAt(i - 1));
            }
            // 右指针从上一轮出位置往下遍历即可，直到当前右指针指向的字符在set中有重复
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;

    }
    // 方法2:在滑动窗口基础上修改指针跳转规则
    public int lengthOfLongestSubstring(String s) {
        // 记录在当前子串中每一个字母的最大序号(因为当前子串可能有重复的字母)
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int result = 0;
        int start = 0;

        for (int end = 0; end < n; end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                // 直接把start指针指向当前有重复情况的字符在map中的最大位置的下一位
                // 比如a b c f f c b b c
                //      start         end
                // map.containsKey(c)=true时，即s[start...end]肯定有重复的c，
                // 此时仅仅把start向前移动一位到f，肯定s[start...end]还有重复的c,
                // 所以把start直接移动到c在map中的最大位置的下一位即可(此时end位置的c还没加入到map中，所以不是最大)
                // a b c f f c b b c
                //           start end
                start = Math.max(map.get(c) + 1, start);
            }
            result = Math.max(result, end - start + 1);
            // 将当前的位置进行存储，即更新到最大的位置
            map.put(c, end);
        }
        return result;
    }
}
