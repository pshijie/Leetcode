/**
 * @author psj
 * @date 2022/4/25 8:53
 * @File: compress.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/string-compression/solution/

public class compress {
    // 双指针
    public int compress(char[] chars) {
        int n = chars.length;
        // i用于记录当前字符的位置，j为存储新字符的位置
        int i = 0, j = 0;
        while (i < n) {
            int idx = i;
            while (idx < n && chars[idx] == chars[i]) {
                idx++;
            }
            chars[j++] = chars[i];
            // cnt表示当前字符在数组中出现的次数
            int cnt = idx - i;
            if (cnt > 1) {
                int start = j, end = start;
                while (cnt != 0) {
                    chars[end] = (char) ((cnt % 10) + '0');
                    end++;
                    cnt /= 10;
                }
                // 假设字符a出现的次数为12，此时在数组中的顺序为'2','1'
                // 需要将该部分进行反转
                reverse(chars, start, end - 1);
                j = end;
            }
            i = idx;
        }
        return j;
    }

    // 将字符数组中[start,end]部分进行反转
    public void reverse(char[] cs, int start, int end) {
        while (start < end) {
            char temp = cs[start];
            cs[start] = cs[end];
            cs[end] = temp;
            start++;
            end--;
        }
    }
}
