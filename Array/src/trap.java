/**
 * @author psj
 * @date 2022/3/15 11:07
 * @File: trap.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/trapping-rain-water/
// 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水

// 核心思想：首先要拆分为每一个柱子能装多少水，对于位置i来说，能装多少水取决于min(左侧最高柱子高度，右侧最高柱子高度）-当前柱子高度
// 如下图所示，假设左侧最高柱子高度为6，右侧最高柱子高度为4，当前柱子高度为2，当前柱子最后能接水量为j=min(6,4)-2=2
// ----
// |  |
// |  |       ----  ———
// |  |   i   |  |   | 结果j=2
// |  |  ---- |  |  ———
// |  |  |  | |  |

public class trap {
    // 方法1：暴力
    public int trap_force(int[] height) {
        int n = height.length;
        int result = 0;
        // i表示当前要计算的柱子，第一个柱子和最后一个柱子都不用计算，无论高度是多少都接不了水
        for (int i = 1; i < n - 1; i++) {
            int l_max = 0, r_max = 0;
            // 找右侧最高柱子
            for (int j = i; j < n; j++) {
                r_max = Math.max(r_max, height[j]);
            }
            // 找右侧最高柱子
            for (int j = i; j >= 0; j--) {
                l_max = Math.max(l_max, height[j]);
            }
            // 假如当前柱子最高(因为在比较是j下标是从i开始的，所以把当前柱子的高度也进行了比较),肯定接不了水
            // l_max=r_max=height[i],所以Math.min(l_max, r_max) - height[i] = 0
            result += Math.min(l_max, r_max) - height[i];
        }
        return result;
    }

    // 方法2:备忘录，保存当前柱子左/右侧的最高柱子高度(包括当前柱子)
    public int trap_memo(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int n = height.length;
        int result = 0;
        // 备忘录，分别记录当前柱子左/右侧最高高度(包括当前柱子)
        int[] l_max = new int[n];
        int[] r_max = new int[n];
        // 第一个柱子没有左侧，所以左侧最高高度就是自己
        l_max[0] = height[0];
        // 最后一个柱子没有右侧，所以右侧最高高度就是自己
        r_max[n - 1] = height[n - 1];

        for (int i = 1; i < n; i++) {
            l_max[i] = Math.max(height[i], l_max[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            r_max[i] = Math.max(height[i], r_max[i + 1]);
        }

        for (int i = 1; i < n - 1; i++) {
            result += Math.min(r_max[i], l_max[i]) - height[i];
        }
        return result;
    }

}
