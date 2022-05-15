import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author psj
 * @date 2022/5/15 9:09
 * @File: fourSum.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/4sum/
// 给你一个由n个整数组成的数组nums ，和一个目标值target。请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]

public class fourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);
        int n = nums.length;
        // 定义四个指针k,i,j,h;
        // k从0开始遍历，i从k+1开始遍历，留下j和h，j指向i+1，h指向数组最大值
        for (int k = 0; k < n - 3; k++) {
            // 如果k和前面的值相同则跳过
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            // 获取当前最小值,如果最小值比目标值大就无解
            long min = (long) nums[k] + nums[k + 1] + nums[k + 2] + nums[k + 3];
            if (min > target) {
                break;
            }
            // 获取当前最大值,如果最大值比目标值小就无解
            long max = (long) nums[k] + nums[n - 1] + nums[n - 2] + nums[n - 3];
            if (max < target) {
                continue;
            }

            for (int i = k + 1; i < n - 2; i++) {
                // 如果i和前面的值相同则跳过
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int j = i + 1;
                int h = n - 1;
                // 获取当前最小值,如果最小值比目标值大就无解
                long min2 = (long) nums[k] + nums[i] + nums[j] + nums[j + 1];
                if (min2 > target) {
                    break;
                }
                // 获取当前最大值,如果最大值比目标值小就无解
                long max2 = (long) nums[k] + nums[i] + nums[h] + nums[h - 1];
                if (max2 < target) {
                    continue;
                }

                while (j < h) {
                    int cur = nums[k] + nums[i] + nums[j] + nums[h];
                    if (cur == target) {
                        result.add(Arrays.asList(nums[k], nums[i], nums[j], nums[h]));
                        j++;
                        // 通过移动删除重复元素
                        while (j < h && nums[j] == nums[j - 1]) {
                            j++;
                        }
                        h--;
                        while (j < h && i < h && nums[h] == nums[h + 1]) {
                            h--;
                        }
                    } else if (cur > target) {
                        h--;
                    } else {
                        j++;
                    }
                }
            }
        }
        return result;
    }
}
