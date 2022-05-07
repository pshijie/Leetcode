import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/5/7 9:34
 * @File: findDuplicates.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
// 一个长度为n的整数数组nums ，其中nums的所有整数都在范围[1, n] 内，且每个整数出现一次或两次 。请你找出所有出现两次的整数，并以数组形式返回

public class findDuplicates {
    // 1.找到数字i时，将位置num[i]-1处的数字翻转为负数。
    // 2.如果位置i-1上的数字已经为负，则i是出现两次的数字
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1; // 相当于做个hash
            if (nums[index] < 0) {
                result.add(Math.abs(index + 1));  // 加回1相等于反hash
            }
            nums[index] = -nums[index];
        }
        return result;
    }
}
