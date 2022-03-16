/**
 * @author psj
 * @date 2022/3/16 8:44
 * @File: maxArea.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/container-with-most-water/
// 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。返回容器可以储存的最大水量

public class maxArea {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int result = 0;
        while (left < right) {
            // 计算[left,right]之间的矩形面积
            int cur_area = Math.min(height[left], height[right]) * (right - left);
            result = Math.max(result, cur_area);
            // 双指针，移动较低的一边，因为只有移动较低的一边才有机会遇到更高的垂线，
            // 使得Math.min(height[left], height[right])增加，
            // 如果移动较高的一边，即使遇到更高的垂线，因为结果取决于已经确定的最低高度，所以结果不会变
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }
}
