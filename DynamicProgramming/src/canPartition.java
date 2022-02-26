/**
 * @author psj
 * @date 2022/2/26 9:48
 * @File: canPartition.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/partition-equal-subset-sum/
// 给一个只包含正整数的非空数组nums。请判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等

public class canPartition {
    // 可以将问题转换为给一个可装载sum/2(sum为数组的总和)和N个物品，能否存在装法使得背包装满
    public boolean canPartition_twodim(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        // dp[i][j]表示对于前i个数字进行选择，存在一个方式将容量为j的背包装满
        boolean[][] dp = new boolean[nums.length + 1][sum / 2 + 1];

        // 总和无法均分
        if (sum % 2 != 0) {
            return false;
        }

        // base case
        // 当背包容量为0时，说明被装满了
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }
        // 没有物品时肯定无法装满背包
        for (int i = 0; i <= sum/2; i++) {
            dp[0][i] = false;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                // 背包容量不能装下num[i-1]
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 选择装入或者不装人，如果其中一种有方法装满容量为j的背包即可
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }
        // 子集可以为空，所以即使将所有数组的元素分为一个子集，另一个为空也可
        return dp[nums.length][sum / 2];

    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        // dp[i][j]表示对于前i个数字进行选择，存在一个方式将容量为j的背包装满
        boolean[] dp = new boolean[sum / 2 + 1];

        // 总和无法均分
        if (sum % 2 != 0) {
            return false;
        }

        // base case
        dp[0] = true;

        // 从dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]]可以看出每一行只由上一行决定，所以可以转为一位数组
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum/2; j >= 0; j--) {
                if (j - nums[i] >= 0){
                    dp[j] = dp[j] || dp[j-nums[i]];
                }
            }
        }
        return dp[sum / 2];

    }
}
