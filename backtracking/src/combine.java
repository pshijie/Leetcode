import java.util.ArrayList;
import java.util.List;

/**
 * @author psj
 * @date 2022/2/7 20:30
 * @File: combine.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/combinations/
// 给定两个整数n和k，返回范围[1, n]中所有可能的k个数的组合

public class combine {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtrace(n, k, 1, res, temp);
        return res;
    }

    public void backtrace(int n, int k, int start, List<List<Integer>> res, List<Integer> temp) {
        // base case（和题目子集类似，但是加入到结果集的需要满足长度条件）
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }

        // i是从start开始遍历，这样不会出现重复的组合
        for (int i = start; i <= n; i++) {
            // 选择数字i到temp
            temp.add(i);
            backtrace(n, k, i + 1, res, temp);
            temp.remove(temp.size() - 1);
            // 撤销选择
        }
    }

}
