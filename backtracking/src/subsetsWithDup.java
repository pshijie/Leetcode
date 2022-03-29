import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/3/29 9:00
 * @File: subsetsWithDup.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/subsets-ii/
// 给你一个整数数组nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
// 解集不能包含重复的子集。返回的解集中子集可以按任意顺序排列
//                        []
//             1/         2|         \2'
//            [1]         [2]        [2']
//          2/  \2'(×)        |2'
//         [1,2] [1,2']   [2,2']
//         2'|
//         [1,2,2']
public class subsetsWithDup {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先排序，让相同的元素靠在一起**(为了后续的剪枝)
        Arrays.sort(nums);
        backtrace(nums, 0);
        return res;
    }

    public void backtrace(int[] nums, int start) {
        // 子集没有长度限制，所以都可以加入到res中
        res.add(new LinkedList<>(track));
        // 以start开头是为了不出现重复的子集
        for (int i = start; i < nums.length; i++) {
            // 值相同的相邻树枝只遍历一条(因为进行了排序，所以可以确保如果有相同的就一定相邻)
            // 这里要求i>start是因为当前节点衍生出的第一条树枝是需要的
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            track.addLast(nums[i]);
            backtrace(nums, i + 1);
            track.removeLast();
        }
    }
}
