import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author psj
 * @date 2022/5/26 9:11
 * @File: permutation.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/
// 输入一个字符串，打印出该字符串中字符的所有排列(但里面不能有重复元素)

public class permutation {
    List<String> result = new ArrayList<>();  // 存放结果
    StringBuilder tmp = new StringBuilder();
    boolean[] visited;

    public String[] permutation(String s) {
        visited = new boolean[s.length()];
        char[] cs = s.toCharArray();
        Arrays.sort(cs); // 排序后才能进行后面cs[i-1]和cs[i]的比较
        dfs(cs, 0);
        return result.toArray(new String[0]);  // 表示将所有字符串存入一个字符数组中
    }

    public void dfs(char[] cs, int depth) {
        if (depth == cs.length) {
            result.add(tmp.toString());
            return;
        }
        // cs中一个字符代表树的一层
        for (int i = 0; i < cs.length; i++) {
            // 当前字符已经被遍历过就不再重复遍历
            if (visited[i]) {
                continue;
            }
            // 如果同一层中相邻节点值一样,且上一个节点并没有被访问
            // 说明如果将当前节点加入tmp中肯定和上一个节点加入tmp中重复(因为上一个节点肯定先被遍历)
            // !!visited[i - 1]表示第i-1个节点已经遍历完毕并且被撤销选择
            // 如果没有被撤销选择说明cs[i]是在cs[i-1]的子树上，不是在同一层
            if (i > 0 && cs[i - 1] == cs[i] && !visited[i - 1]) {
                continue;
            }
            tmp.append(cs[i]);
            visited[i] = true;
            dfs(cs, depth + 1);
            visited[i] = false;
            tmp.deleteCharAt(tmp.length() - 1);
        }

    }
}
