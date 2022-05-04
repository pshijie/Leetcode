/**
 * @author psj
 * @date 2022/5/4 8:54
 * @File: minNumber.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
// 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个

public class minNumber {
    // 方法1：快排
    public String minNumber(int[] nums) {
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        quickSort(strs, 0, n - 1);
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }
        return res.toString();
    }

    public void quickSort(String[] strs, int l, int r) {
        if (l < r) {
            int mid = getMiddle(strs, l, r);
            quickSort(strs, l, mid - 1);
            quickSort(strs, mid + 1, r);
        }
    }

    public int getMiddle(String[] strs, int l, int r) {
        // 以数组第一个元素为基准
        String temp = strs[l];
        while (l < r) {
            // 从后向前找比基准元素小的数
            while (l < r && (strs[r] + temp).compareTo(temp + strs[r]) >= 0) {
                r--;
            }
            // 把比基准元素小的数移到低端
            strs[l] = strs[r];
            while (l < r && (strs[l] + temp).compareTo(temp + strs[l]) <= 0) {
                l++;
            }
            // 把比基准元素大的数移到高端
            strs[r] = strs[l];
        }
        strs[l] = temp;
        return l;
    }

    // 方法2：冒泡
    public String minNumber_bubble(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs.length - 1; j++) {
                if ((strs[j] + strs[j + 1]).compareTo(strs[j + 1] + strs[j]) > 0) {
                    String tmp = strs[j + 1];
                    strs[j + 1] = strs[j];
                    strs[j] = tmp;
                }
            }
        }
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            res.append(strs[i]);
        }
        return res.toString();
    }
}
