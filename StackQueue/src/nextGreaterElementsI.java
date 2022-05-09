import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author psj
 * @date 2022/5/9 9:12
 * @File: nextGreaterElement.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/next-greater-element-i/
// 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。
// 如果不存在下一个更大元素，那么本次查询的答案是 -1

public class nextGreaterElementsI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        // **倒序向栈中存放元素**
        // 因为前面的元素也是向后找比其大的元素,不如先将后面大的元素存入栈中
        for (int i = nums2.length - 1; i >= 0; i--) {
            // 将栈中比nums2[i]小的元素移除
            while (!stack.isEmpty() && nums2[i] >= stack.peek()) {
                stack.pop();
            }
            // 直到遇到比nums2[i]大的元素a，就将num2[i]和a建立映射
            // 如果栈没有元素，表示nums[i]后面没有比它大的元素
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }
        // 根据num1中的元素找到num2[i]中对应的下一个大值
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }
}
