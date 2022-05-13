import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author psj
 * @date 2022/5/13 9:21
 * @File: missingNumber.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/missing-number/
// 给定一个包含[0, n]中n个数的数组nums ，找出[0, n]这个范围内没有出现在数组中的那个数

public class missingNumber {
    // 方法1:排序
    public int missingNumber_sort(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }

    // 方法2:hash
    public int missingNumber_hash(int[] nums) {
        int n = nums.length;
        boolean[] hash = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            hash[nums[i]] = true;
        }
        for (int i = 0; i < n; i++) {
            if (!hash[i]) {
                return i;
            }
        }
        return n;
    }

    // 方法3:差值
    // 计算[1,n]的数值总和,再计算nums的数值总和,二者的差即为缺失的数
    public int missingNumber_sub(int[] nums) {
        int n = nums.length;
        int cur_sum = 0;
        int org_sum = (n + 1) * n / 2;
        for (int i : nums) {
            cur_sum += i;
        }
        return org_sum - cur_sum;
    }

    // 方法4:异或运算
    // 一个数和它本身做异或运算结果为0，一个数和0做异或运算还是它本身
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int result = 0;
        for (int i = 0; i <= n; i++) {
            result ^= i;
        }
        // 正常而言遍历两遍一样的数组最终结果为0,但是由于nums中缺失一个值
        // 所以结果会多出缺失的值
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
