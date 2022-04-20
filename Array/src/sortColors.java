/**
 * @author psj
 * @date 2022/4/20 9:24
 * @File: sortColors.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/sort-colors/
// 给定一个包含红色、白色和蓝色、共n个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色(0,1,2)顺序排列

public class sortColors {
    // 方法1：单指针
    // 第一次遍历将0全部放在数组前面，第二次遍历将1放在0的后面
    public void sortColors_one(int[] nums) {
        int n = nums.length;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }

        for (int i = index; i < n; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }
    }

    // 方法2：双指针
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0;
        int p2 = n - 1;
        for (int i = 0; i <= p2; i++) {
            // 为什么使用while循环?假设nums[p2]=2,nums[i]=2,此时将这两个元素及逆行了交换
            // 两个位置数值没变，但是由于下一轮for循环i=i+1，当前nums[i]=2则被忽略了
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                p0++;
            }
        }
    }

}
