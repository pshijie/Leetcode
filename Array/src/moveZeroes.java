/**
 * @author psj
 * @date 2022/3/16 10:24
 * @File: moveZeroes.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/move-zeroes/
// 给定一个数组nums，编写一个函数将所有0移动到数组的末尾，同时保持非零元素的相对顺序

public class moveZeroes {
    public void moveZeroes(int[] nums) {
        // 1.先将所有不为0的数移动到数组开头，并返回这些不为0的数的个数
        int slow = 0, fast = 0;
        while (fast != nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        // 2.将数组剩余部分全部赋值为0，剩余部分的长度即为nums.length-noZeroNum
        int noZeroNum = slow;
        for (; noZeroNum < nums.length; noZeroNum++) {
            nums[noZeroNum] = 0;
        }

    }
}
