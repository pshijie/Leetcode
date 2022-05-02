import java.util.Stack;

/**
 * @author psj
 * @date 2022/5/2 8:48
 * @File: largestRectangleArea.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
// 给定n个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为1。
// 求在该柱状图中，能够勾勒出来的矩形的最大面积

public class largestRectangleArea {
    // 方法1：暴力(超时)
    // 每个柱子的高度为标准，向左右扩展计算当前柱子能形成的最大矩形
    public int largestRectangleArea_force(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            int curHeight = heights[i];
            // 找出最左边的一个高度大于等于当前柱子的索引(遇到比它低的就停止)
            int left = i;
            while (left > 0 && heights[left - 1] >= curHeight) {
                left--;
            }
            // 找出最右边的一个高度大于等于当前柱子的索引(遇到比它低的就停止)
            int right = i;
            while (right < len - 1 && heights[right + 1] >= curHeight) {
                right++;
            }
            // 因为都是先比较移动后的数值再移动，说明最后right和left都是满足高度条件的
            int width = right - left + 1;
            result = Math.max(result, width * curHeight);

        }
        return result;
    }

    // 方法2：单调栈
    public int largestRectangleArea(int[] heights) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        // 将数组左右两侧各添加一个元素0
        int[] newHeights = new int[heights.length + 2];
        newHeights[0] = 0;
        for (int i = 1; i < heights.length + 1; i++) {
            newHeights[i] = heights[i - 1];
        }

        for (int i = 0; i < newHeights.length; i++) {
            // 如果栈不为空且当前考察的元素值小于栈顶元素值，则表示以栈顶元素值为高的矩形面积可以确定
            // 如果不小于就将当前考察元素索引入栈，保证栈中这一串索引对应的高度为递增的
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                int cur = stack.pop();
                // 获取栈顶元素对应的高度
                int curHeight = newHeights[cur];
                // 栈顶弹出后，新的栈顶元素就是其左侧边界
                int left = stack.peek();
                // 当前考察的索引为右侧边界
                int right = i;
                int curWidth = right - left - 1;
                result = Math.max(result, curWidth * curHeight);

            }
            // 当前元素的索引入栈
            stack.push(i);
        }

        return result;
    }
}
