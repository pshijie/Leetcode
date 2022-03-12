import java.util.Arrays;

/**
 * @author psj
 * @date 2022/3/12 8:55
 * @File: jump.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/jump-game-ii/
// 给你一个非负整数数组nums ，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。假设你总是可以到达数组的最后一个位置

public class jump {
    // 方法1:备忘录(内存溢出，因为要计算所有值然后再从所有值中找出最小值)
    int[] memo;

    public int jump_memo(int[] nums) {
        int n = nums.length;
        memo = new int[n];
        Arrays.fill(memo, n);  // 初始化为n是因为从0到n-1最多只要n-1步，找一个比这大的值即可

        return dp(nums, 0);
    }

    // 从索引i跳到最后一格，至少需要dp(nums,i)步
    public int dp(int[] nums, int i) {
        int n = nums.length;
        // base case
        // 已经在最后一格就不需要跳了
        if (i >= n - 1) {
            return 0;
        }

        if (memo[i] != n) {
            return memo[i];
        }

        for (int j = 0; j <= nums[i]; j++) {
            // 穷举所有可能跳的步数[0,nums[i]]
            int sub = dp(nums, i + j);
            memo[i] = Math.min(memo[i], sub + 1);  // sub+1表示如果选择跳j步，意味着一次跳跃
        }
        return memo[i];
    }

    // 方法2：贪心
    // 假设当前在位置0，nums[0]=2,所以当前可以跳到i=1和i=2，如何选择跳到i=1还是i=2？
    // 此时看nums[1]+1和nums[2]+2的值即可，选择一个较大的值。假设nums[1]+1>nums[2]+1，
    // 说明选择跳到1能跳跃的范围是能覆盖跳到2后能跳到的最大范围，那就选择1
    public int jump(int[] nums) {
        int n = nums.length;
        int end = 0;  // 表示在当前i位置时可以跳到的最远距离
        int farthest = 0;  // 表示选择跳到[i...end]中任意一个位置后能跳到的最远距离
        int jump_num = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, nums[i] + i);
            // 已经遍历到当前能跳到的最远距离时
            if (end == i) {
                jump_num++;
                end = farthest;  // end被赋予farthest相当于例子中选择了从0跳到位置1
            }
        }

        return jump_num;
    }
}
