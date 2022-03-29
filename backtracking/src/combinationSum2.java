import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/3/29 9:17
 * @File: combinationSum2.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/combination-sum-ii/
// 给定一个候选人编号的集合candidates和一个目标数target ，找出candidates中所有可以使数字和为target的组合。
// candidates中的每个数字在每个组合中只能使用一次 

public class combinationSum2 {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    int trackSum = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res;
        }
        // 遇到可以加入重复元素的问题时都是先排序
        Arrays.sort(candidates);
        backtrace(candidates, 0, target);
        return res;
    }

    public void backtrace(int[] candidates, int start, int target) {
        // base case
        if (target == trackSum) {
            res.add(new LinkedList<>(track));
            return;
        }

        // 如果路径上的元素和大于target，直接退出
        if (trackSum > target) {
            return;
        }
        // 起始点为start，不是为0，如果为0子集会重复
        for (int i = start; i < candidates.length; i++) {
            // 和90题一样剪去相邻元素相同的枝条
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // 做选择
            track.addLast(candidates[i]);
            trackSum += candidates[i];
            backtrace(candidates, i+1, target);
            // 撤销选择
            track.removeLast();
            trackSum -= candidates[i];
        }
    }
}
