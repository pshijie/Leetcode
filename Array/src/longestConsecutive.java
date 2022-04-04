import java.util.HashSet;
import java.util.Set;

/**
 * @author psj
 * @date 2022/4/4 8:40
 * @File: longestConsecutive.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/longest-consecutive-sequence/
// 给定一个未排序的整数数组nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

public class longestConsecutive {
    // HashSet
    public int longestConsecutive_hashset(int[] nums) {
        int n = nums.length;
        int result = 0;
        Set<Integer> set = new HashSet<>();

        // 将数组进行去重
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }

        for (int i = 0; i < n; i++) {
            // 当前(num-1)如果存在就不从当前位置遍历，因为从num-1的位置遍历肯定比当前位置开始遍历要长
            // 当前(num-1)如果不存在就从当前位置开始遍历
            int cur = nums[i];
            if (!set.contains(cur-1)) {
                // 判断是否才num+1,num+2,...
                while (set.contains(cur+1)){
                    cur++;
                }
            }
            // 此时肯定[cur,nums[i]]之间是连续的
            result = Math.max(result, cur-nums[i]+1);
        }
        return result;
    }
}
