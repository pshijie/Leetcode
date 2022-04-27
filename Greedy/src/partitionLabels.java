import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/4/27 8:38
 * @File: partitionLabels.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/partition-labels/
// 字符串S由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表

public class partitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new LinkedList<>();
        int[] lastAppear = new int[26];
        char[] chars = s.toCharArray();
        // 统计每个字符最后出现的位置
        for (int i = 0; i < chars.length; i++) {
            lastAppear[chars[i] - 'a'] = i;
        }
        int left = 0;
        int right = 0;
        for (int i = 0; i < chars.length; i++) {
            // right记录遍历到当前字符的最大右边界
            right = Math.max(right, lastAppear[chars[i] - 'a']);
            // 当i走到右边界时获取左边界到右边界的长度
            if (i == right) {
                result.add(right - left + 1);
                // 更新左边界为i的下一个位置
                left = i + 1;
            }
        }
        return result;
    }
}
