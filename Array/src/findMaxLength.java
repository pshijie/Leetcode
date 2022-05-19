import java.util.HashMap;

/**
 * @author psj
 * @date 2022/5/19 9:18
 * @File: findMaxLength.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/contiguous-array/
// 给定一个二进制数组nums, 找到含有相同数量的0和1的最长连续子数组，并返回该子数组的长度

public class findMaxLength {
    // 给出一组测试用例：[0,0,0,1,1,1,0,0,1], 用指针 i 扫描一遍数组, 来观察每个位置上的可能情况
    // [0,0,0,1,1,1,0,0,1]
    //  i                 不符合条件, 0 1 数量不同
    // [0,0,0,1,1,1,0,0,1]
    //    i               不符合条件, 0 1 数量不同
    // [0,0,0,1,1,1,0,0,1]
    //      i             不符合条件, 0 1 数量不同
    // [0,0,0,1,1,1,0,0,1]
    //        i           此时与前一个 0 构成 [0,1] 满足条件, 此时的子数组长度为 2
    // [0,0,0,1,1,1,0,0,1]
    //          i         此时 [1,4] 区间满足条件, 子数组长度为 4
    // [0,0,0,1,1,1,0,0,1]
    //            i       此时 [0,5] 区间满足条件, 子数组长度为 6
    // [0,0,0,1,1,1,0,0,1]
    //              i     不符合条件
    // [0,0,0,1,1,1,0,0,1]
    //                i   不符合条件
    // [0,0,0,1,1,1,0,0,1]
    //                  ^ 此时 [1,8] 区间满足条件, 子数组长度为 8
    // 让 0 变为 -1, 那么当区间内 -1 和 1 的数量相同时, 这区间和就是 0 。当计算的前缀和为 0 时, 就说明[0,i] 区间是满足题目要求的一个子数组。
    // 前缀和：
    // [0,0,1,0,0,0,1,1]
    //  i               preSum = -1, (用 -1 替换 0);
    // [0,0,1,0,0,0,1,1]
    //    i             preSum = -2
    // [0,0,1,0,0,0,1,1]
    //      i           preSum = -1
    // [0,0,1,0,0,0,1,1]
    //        i         preSum = -2
    // [0,0,1,0,0,0,1,1]
    //          i       preSum = -3
    // [0,0,1,0,0,0,1,1]
    //            i     preSum = -4
    // [0,0,1,0,0,0,1,1]
    //              i   preSum = -3
    // [0,0,1,0,0,0,1,1]
    //                i preSum = -2
    // 观察可以发现, 当前缀和相同时, 前一个 i1 后面一个位置开始一直到 i2 的区间是满足题目要求的子数组, 即 nums[i1+1...i2] 满足题
    // 目要求, 并且 i2 - i1 = 子数组长度, 所以我们只需要计算出 nums[0...n-1] 每一个位置的前缀和, 一旦发现当前的计算出的前缀和在
    // 之前已经出现过, 就用当前的索引 i2 - 之前的索引 i1 即可求出本次得到的子数组长度,
    // 一、用变量 sum 来纪录[0...i]区间的和:
    //    1.当 nums[i] == 0 时, sum += -1
    //    2.当 nums[i] == 1 时, sum += 1
    // 二、接着判断 sum 是否已经存在于 HashMap 中:
    //    1. 如果存在, 则取出 sum 所对应的索引 j, 那么 nums[j+1,i] 就是一个满足答案的子区间, 用
    //       maxLength = Math.max(maxLengnth, i - j); 来纪录最长子数组。
    //    2. 如果不存在, 则将 {sum:i} 存放在 HashMap 中作为纪录。
    // 当数组遍历完毕时, maxLength 中保存的即为答案数组的长度。
    public int findMaxLength(int[] nums) {
        // key:当前位置的前缀和(包括当前位置元素值)  value:key值对应的索引
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : nums[i];
            if (map.containsKey(sum)) {
                int j = map.get(sum);
                maxLength = Math.max(maxLength, i - j);
            } else {
                map.put(sum, i);
            }
        }
        return maxLength;

    }
}
