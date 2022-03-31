/**
 * @author psj
 * @date 2022/3/12 8:42
 * @File: canJump.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/jump-game/
// 给定一个非负整数数组nums ，你最初位于数组的第一个下标。数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个下标

public class canJump {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = 0;  // 此前能跳到的最远距离
        for (int i = 0; i < n ; i++) {
            // 如果当前能跳到的最大距离小于当前位置
            if (farthest < i) {
                return false;
            }
            // 计算当前能跳到的最远距离
            farthest = Math.max(farthest, i + nums[i]);

        }

        return true;
    }
}
