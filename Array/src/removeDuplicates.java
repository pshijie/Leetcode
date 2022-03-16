/**
 * @author psj
 * @date 2022/3/16 9:46
 * @File: removeDuplicates.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
// 给你一个升序排列的数组nums ，请你原地删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度。元素的相对顺序应该保持 一致

public class removeDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            // 如果fast指向的元素等于slow指向的元素，将slow向下移动一位用于存储fast指向的元素(即和当前slow元素不同的元素)
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }

        return slow + 1;
    }
}
