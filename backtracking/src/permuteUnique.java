import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/3/29 9:28
 * @File: permuteUnique.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/permutations-ii/
// 给定一个可包含重复数字的序列nums ，按任意顺序返回所有不重复的全排列
// 子集可以重复，比如2，1'，1和1'，1，2是可以的，但是元素不能重复,如2，1，1和1，1，2是不行的

public class permuteUnique {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;  // 记录元素是否被使用过

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 遇到可以加入重复元素的问题时都是先排序
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtrace(nums);
        return res;

    }

    public void backtrace(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        // 因为是全排列，所以每次从0开始
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            //
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            track.addLast(nums[i]);
            used[i] = true;
            backtrace(nums);
            track.removeLast();
            used[i] = false;

        }
    }
}
