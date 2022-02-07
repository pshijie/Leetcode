import java.util.ArrayList;
import java.util.List;

/**
 * @author psj
 * @date 2022/2/7 18:53
 * @File: subsets.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/subsets/
// 给你一个整数数组nums，数组中的元素互不相同。返回该数组所有可能的子集（幂集）。
//                         [ ]
//                     1/  2|    \3
//                    [1]  [2]   [3]
//                  2/ \3   |3
//              [1,2] [1,3][2,3]
//               3|
//              [1,2,3]
public class subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrce(0, nums, res, new ArrayList<Integer>());
        return res;
    }

    public void backtrce(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> temp) {
        // 不需要判断条件，因为子集都需要记录
        res.add(new ArrayList<>(temp));
        // 起始点为i，不是为0，如果为0子集会重复
        for (int j = i; j < nums.length; j++) {
            // 选择nums[j]
            temp.add(nums[j]);
            backtrce(j + 1, nums, res, temp);
            // 撤销选择
            temp.remove(temp.size() - 1);

        }

    }
}
