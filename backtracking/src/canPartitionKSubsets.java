import java.util.Arrays;

/**
 * @author psj
 * @date 2022/2/6 20:50
 * @File: canPartitionKSubsets.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/
// 给定一个整数数组nums和一个正整数k，找出是否有可能把这个数组分成k个非空子集，其总和都相等

/**
 * 使用for循环遍历数组：
 *      for(int index=0; index<nums.length;index++){
 *          print(nums[index]);
 *      }
 *  递归遍历数组：
 *      void traverse(int[] nums, int index){
 *          if(index == nums.length){
 *              return;
 *          }
 *
 *          print(nums[index])
 *
 *          traverse(nums, index+1)
 *      }
 */
public class canPartitionKSubsets {

    // 以nums中的数字为视角考虑，即每个数字要放到k个桶中的一个：该方法超时
    // 不使用递归表示for循环为：
    //    int[] bucket = new int[k];
    //    // 穷举nums中的每个数字
    //    for(int index : nums){
    //        // 穷举每个桶
    //        for(int i : k){
    //            // 考虑index是否要放入第i个桶
    //        }
    //    }
    // 改为递归为
    //    int[] bucket = new int[k];
    //    void backtrack(int[] nums, int index){
    //        // base case
    //        if (index == nums.length){
    //            return;
    //        }
    //        // 穷举每个桶
    //        for (int i : k) {
    //            // 选择装进第i个桶
    //            bucket[i] += nums[index];
    //            // 递归穷举下一个数字的选择
    //            backtrack(nums, index+1);
    //            // 撤销选择
    //            bucket[i] -= nums[index];
    //        }
    //    }

    public boolean canPartitionKSubsets_1(int[] nums, int k) {
        // 排除情况1：k > 数组长度
        if (k > nums.length){
            return false;
        }

        // 排除情况2：数字总和与k不成倍数
        int sum = 0;
        for (int a : nums) {
            sum += a;
        }
        if (sum % k != 0){
            return false;
        }

        // 将nums数组进行降序排列(先升序排序，后交换首尾)
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        // 记录每个桶的存储数字总和
        int[] bucket = new int[k];
        // 每个桶中数字和应满足的值
        int target = sum / k;

        return backtrace(nums, 0, bucket, target);

    }

    public boolean backtrace(int[] nums, int index, int[] bucket, int target) {
        if (index == nums.length){
            // 检查每个桶的数字和是否为target
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != target){
                    return false;
                }
            }

            return true;
        }

        // 穷举nums[index]可能装入的桶
        for (int i = 0; i < bucket.length; i++) {
            // 如果桶装满，剪枝
            if (bucket[i] + nums[index] > target){
                continue;
            }

            // 将nums[index]装入bucket[i]
            bucket[i] += nums[index];
            // 递归下一个数字的选择
            if (backtrace(nums, index+1, bucket, target)){
                return true;
            }

            // 撤销选择
            bucket[i] -= nums[index];

        }

        // nums[index]装入哪个桶都不行
        return false;
    }

    // 以桶为视角考虑，即每个桶要遍历nums中每一个数字，决定是否把当前数字装入桶中，装满一个桶就装下一个桶：
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // 排除情况1：k > 数组长度
        if (k > nums.length){
            return false;
        }

        // 排除情况2：数字总和与k不成倍数
        int sum = 0;
        for (int a : nums) {
            sum += a;
        }
        if (sum % k != 0){
            return false;
        }

        int target = sum / k;
        // 标志元素是否已经被装入桶中
        boolean[] used = new boolean[nums.length];

        return backtrace(k, 0, nums, 0, used, target);

    }

    public boolean backtrace(int k, int bucket, int[] nums, int start, boolean[] used, int target) {
        /**
         * 目前第k个桶已经装的数字和为bucket，start表示当前考虑是否装入数字的下标，used标志元素是否已经被装入桶中
         */
        // base case
        if (k == 0){
            // 所有桶都被装满
            return true;
        }

        if (bucket == target){
            // 当前桶装满了就准备装下一个桶
            return backtrace(k-1, 0, nums, 0, used, target);
        }

        // 从start位置开始想后探索nums的数字装入当前桶
        for (int i = start; i < nums.length; i++) {
            // 已被装入其他桶
            if (used[i]){
                continue;
            }
            // 装入后溢出
            if (nums[i] + bucket > target){
                continue;
            }
            // 选择装入nums[i]
            used[i] = true;
            bucket += nums[i];

            // 递归穷举下一个数字是否装入当前桶
            if (backtrace(k, bucket, nums, i+1, used, target)){
                return true;
            }

            // 撤销选择
            used[i] = false;
            bucket -= nums[i];
        }

        return false;
    }
}
