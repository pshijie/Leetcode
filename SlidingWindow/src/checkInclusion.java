import java.util.HashMap;

/**
 * @author psj
 * @date 2022/3/12 9:58
 * @File: checkInclusion.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/permutation-in-string/
// 给你两个字符串s1和s2 ，写一个函数来判断s2是否包含s1的排列。如果是返回true ；否则返回false。换句话说，s1的排列之一是s2的子串

public class checkInclusion {
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int vaild = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right);  // 要移入窗口的值
            right++;  // 右窗口向右移动
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    vaild++;
                }
            }

            // 判断窗口是否要收缩
            // 当窗口的长度(之所以是right-left不是right-left+1是因为在上面right已经加1，实际的窗口还是[left,right-1])
            // 为s1的长度时，就要进行收缩(假设s1="ab",s2="cbcae"，即使能找到"bca"中包含s1的所有字母，但是不是子串，所以不符合条件)
            while (right - left >= s1.length()) {
                if (vaild == need.size()) {
                    return true;
                }

                char d = s2.charAt(left);  // 要移出窗口的值
                left++;  // 左窗口向右移动

                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        vaild--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }

        return false;

    }
}
