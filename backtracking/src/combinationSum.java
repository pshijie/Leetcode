import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/3/29 9:44
 * @File: combinationSum.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/combination-sum/
// 你一个无重复元素的整数数组candidates和一个目标整数target，找出candidates中可以使数字和为目标数target的所有不同组合，
// 并以列表形式返回。你可以按任意顺序返回这些组合
// candidates中的同一个数字可以无限制重复被选取
//                         [ ]                                 []
//                     1/  2|    \3                  1/        2|    \3
//                    [1]  [2]   [3]   -->          [1]        [2]    [3]
//                  2/ \3   |3                   1/ 2|  \3    2/ \3     \3
//              [1,2] [1,3][2,3]             [1,1][1,2][1,3] [2,2][2,3] [3,3]
//               3|                                         ...........
//              [1,2,3]
public class combinationSum {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    int trackSum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0){
            return res;
        }
        backtrace(candidates, 0, target);
        return res;
    }

    public void backtrace(int[] candidates, int start, int target) {
        if (target == trackSum){
            res.add(new LinkedList<>(track));
            return;
        }

        if (trackSum > target){
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 选择
            track.addLast(candidates[i]);
            trackSum += candidates[i];
            // 注意：这里不是i+1而是i，是因为可以重复选择当前的元素
            // 为什么for循环的条件不直接设置为i=0，如果这样设置会导致重复的子集
            backtrace(candidates, i, target);
            // 撤销选择
            track.removeLast();
            trackSum -= candidates[i];
        }

    }
}
