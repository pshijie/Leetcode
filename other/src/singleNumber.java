import java.util.HashMap;

/**
 * @author psj
 * @date 2022/4/24 9:06
 * @File: singleNumber.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/single-number/
// 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素

public class singleNumber {
    // 方法1:位运算
    // ^表示异或运算:a^a=0 a^0=a
    public int singleNumber(int[] nums) {
        int a = nums[0];
        // 因为重复的元素每个均出现两次，所以重复的元素为b，a^b=c;c^b=a,最后剩下的就是只出现一次的元素
        // 如果重复的元素出现奇数次(如3次)就不能使用该方法
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                a = a ^ nums[i];
            }
        }
        return a;
    }

    // 方法2：哈希表
    public int singleNumber_hash(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        for (int key : map.keySet()) {
            if (map.get(key) == 1){
                return key;
            }
        }
        return -1;
    }
}
