import java.util.Arrays;

/**
 * @author psj
 * @date 2022/5/6 8:50
 * @File: majorityElement.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
// 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字

public class majorityElement {
    // 方法1：排序
    public int majorityElement_sort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // 方法2：摩尔投票
    public int majorityElement(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) {
                x = num;
            }
            votes += num == x ? 1 : -1;
        }
        return x;
    }
}
