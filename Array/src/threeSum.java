import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author psj
 * @date 2022/3/12 10:41
 * @File: threeSum.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/3sum/
// 给你一个包含n个整数的数组nums，判断nums中是否存在三个元素a，b，c ，使得a + b + c = 0 ？请你找出所有和为0且不重复的三元组

public class threeSum {
    // 方法1：排序+双指针
    public List<List<Integer>> threeSum_normal(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 3) {
            return res;
        }
        Arrays.sort(nums);

        // 选取元素中的a
        for (int i = 0; i < len; i++) {
            // 因为选取的元组(a,b,c)要保证a<=b<=c(避免重复)，所以如果当前元素已经大于0，
            // 就不可能b和c存在小于0的数，也就凑不出0
            if (nums[i] > 0) {
                break;
            }

            // 遇到重复的a值就跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int L = i + 1;  // 相当于元组中的b
            int R = len - 1;  // 相当于元组中的c
            while (L < R) {
                int sum = nums[L] + nums[R] + nums[i];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++;
                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--;
                    }
                    // 对于跳出while循环的最后一步，只是进行比较，没有移动
                    L++;
                    R--;
                } else if (sum < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }

        return res;
    }

    // 方法2：使用nSum题目框架
    public List<List<Integer>> threeSum(int[] nums) {
        // 对数组从小到大进行排序，这样可以保证lo向右移动后一定是找到大于等于当前元素的值，hi向左移动后一定是小于等于当前元素的值
        Arrays.sort(nums);
        return nSum(nums, 3, 0, 0);
    }

    // 对于一个nums数组，计算从start开始，n数之和为target的列表
    public List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;
        // base case
        if (n < 2 || n > nums.length) {
            return res;
        }

        if (n == 2) {
            // 两数之和为target的情况
            while (lo < hi) {
                // 记录当前元素，后面遍历的元素需要和其进行比较，避免重复
                // 比如数组为1，1，2，4，5。假设此时lo指向第一个位置，经过判断lo要向右移，而第二个位置元素还是1，
                // 没有必要再次计算出sum值和target进行比较，直接再右移即可
                int left = nums[lo], right = nums[hi];
                int sum = left + right;
                // 当sum<target时，说明lo要指向一个更大的值，所以要考虑向右移动，并且移动过程中遇到重复的元素直接跳过即可
                if (sum < target) {
                    while (lo < hi && (nums[lo] == left)) {
                        lo++;
                    }
                    // 当sum>target时，说明hi要指向一个更小的值，所以要考虑向左移动，并且移动过程中遇到重复的元素直接跳过即可
                } else if (sum > target) {
                    while (lo < hi && (nums[hi] == right)) {
                        hi--;
                    }
                    // 匹配到和等于target的时候，将结果加入到集合中，并且将lo和hi同时进行重复元素的判断
                } else {
                    res.add(Arrays.asList(left, right));
                    while (lo < hi && (nums[lo] == left)) {
                        lo++;
                    }
                    while (lo < hi && (nums[hi] == right)) {
                        hi--;
                    }
                }
            }
        } else {  // 当n>2时，常用递归计算
            for (int i = start; i < nums.length; i++) {
                // 要计算n数之和，就是先选择一个元素nums[i]，计算新的target=原始target-选中元素的值，然后计算剩下的n-1数之和为新target的结果
                // 每次只要考虑start后面的元素能否构成target即可(假设当前元素是b考虑a+c=-b和当前元素是a考虑b+c=-a的性质一样,即避免重复)
                List<List<Integer>> sub = nSum(nums, n - 1, i + 1, target - nums[i]);
                // 需要把被选中的元素加入到结果集中
                for (List<Integer> list : sub) {
                    // todo:为什么Leetcode中不能直接在原始list中继续添加元素，而要新建一个list再添加
                    List<Integer> temp = new ArrayList<>(list);
                    temp.add(nums[i]);
                    res.add(temp);
                }
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }

            }
        }
        return res;
    }
}
