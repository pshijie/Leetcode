/**
 * @author psj
 * @date 2022/4/28 8:40
 * @File: longestOnes.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/max-consecutive-ones-iii/
// 给定一个二进制数组nums和一个整数k，如果可以翻转(将0变为1)最多k个0 ，则返回数组中连续1的最大个数

public class longestOnes {
    // 将题目转换为找出一个最长子数组，该子数组中最多允许有k个0(因为后续可以将这些0进行翻转)
    public int longestOnes(int[] nums, int k) {
        int result = 0;
        int left = 0;
        int right = 0;
        int zeroNum = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroNum++;
            }
            // 如果[left,right]之间0的个数超过了k，则移动左指针
            while (zeroNum > k) {
                if (nums[left] == 0) {
                    zeroNum--;
                }
                left++;
            }
            result = Math.max(result, right - left + 1);
            // 继续移动右指针
            right++;
        }
        return result;
    }

}
