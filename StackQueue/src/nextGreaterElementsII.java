import java.util.Stack;

/**
 * @author psj
 * @date 2022/5/9 9:43
 * @File: nextGreaterElementsII.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/next-greater-element-ii/
// 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。

public class nextGreaterElementsII {
    // 对于数组[2,1,2,4,3],对于最后一个元素3，如果不是循环数组就和nextGreaterElementsI的解法一致
    // 但是因为是循环数组，所以3的下一个元素是4
    // trick：将原数组扩展为[2,1,2,4,3,2,1,2,4,3]，后续操作和nextGreaterElementsI一致
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            // 使用i%n就可以不用设置一个两倍大的数组(后半个数组反正是和前半个一样)
            while (!stack.isEmpty() && nums[i % n] >= stack.peek()) {
                stack.pop();
            }
            result[i % n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % n]);
        }
        return result;

    }
}
