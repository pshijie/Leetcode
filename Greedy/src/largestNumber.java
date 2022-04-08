import java.util.Arrays;

/**
 * @author psj
 * @date 2022/4/8 9:18
 * @File: largestNumber.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/largest-number/
// 给定一组非负整数nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数

public class largestNumber {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] temp = new String[n];
        // 将nums中每一个元素都转换为字符串数组
        for (int i = 0; i < n; i++) {
            temp[i] = "" + nums[i];
        }
        // 如果拼接结果ab要比ba好(数值大)，则认为a应该放在b前面
        Arrays.sort(temp, (a, b) -> {
            String ab = a + b;
            String ba = b + a;
            return ba.compareTo(ab);
        });

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < temp.length; i++) {
            sb.append(temp[i]);
        }

        int k = 0;
        // 将前面的0删除(如果全是0的话需要保留最后一个0，所以遍历到temp.length - 1)
        while (k < sb.length() - 1) {
            if (sb.charAt(k) == '0'){
                k++;
            }else {
                break;
            }
        }
        return sb.substring(k);
    }
}
