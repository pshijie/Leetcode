import java.util.HashMap;

/**
 * @author psj
 * @date 2022/2/18 17:20
 * @File: findTargetSumWays.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/target-sum/
// 给你一个整数数组nums和一个整数target,向数组中的每个整数前添加'+'或'-'，然后串联起所有整数，可以构造一个表达式：
// 例如，nums=[2, 1],可以在2之前添加'+',在1之前添加'-',然后串联起来得到表达式"+2-1"。返回可以通过上述方法构造的、运算结果等于target的不同表达式的数目。

public class findTargetSumWays {
    // 方法1：回溯法，时间复杂度O(2^n),n为nums的长度
    int result = 0;

    public int findTargetSumWays_backtrace(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        backtarce(nums, 0, 0, target);
        return result;
    }

    public void backtarce(int[] nums, int i, int sum, int target) {
        // base case
        if (i == nums.length) {
            if (sum == target) {
                result++;
            }
            return;
        }

        // 给nums[i]选择+号
        sum += nums[i];
        backtarce(nums, i + 1, sum, target);
        // 撤销选择
        sum -= nums[i];

        // 给nums[i]选择-号
        sum -= nums[i];
        backtarce(nums, i + 1, sum, target);
        // 撤销选择
        sum += nums[i];
    }

    // 方法2：二叉树,时间复杂度O(2^n),n为nums的长度
    public int findTargetSumWays_bTree(int[] nums, int target) {
        backtrace_biTree(nums, target, 0, 0);
        return result;
    }

    public void backtrace_biTree(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                result++;
            }
        } else {
            backtrace_biTree(nums, target, index + 1, sum + nums[index]);
            backtrace_biTree(nums, target, index + 1, sum - nums[index]);
        }
    }

    // 方法3：使用备忘录
    public int findTargetSumWays_memo(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        return dp_memo(nums, 0, target);
    }


    HashMap<String, Integer> memo = new HashMap<>();  // 备忘录
    public int dp_memo(int[] nums, int i, int rest) {
        // base case
        if (i == nums.length) {
            if (rest == 0) {
                return 1;
            }
            return 0;
        }

        // 状态作为哈希表的键
        String key = i + "," + rest;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int result = dp_memo(nums, i + 1, rest - nums[i]) + dp_memo(nums, i + 1, rest + nums[i]);
        // 记入备忘录
        memo.put(key, rest);
        return result;
    }


}
