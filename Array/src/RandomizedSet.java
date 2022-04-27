import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author psj
 * @date 2022/4/27 9:01
 * @File: RandomizedSet.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/insert-delete-getrandom-o1/

public class RandomizedSet {
    Map<Integer, Integer> map;  // 记录RandomizedSet中的值和其下标的关系
    Random random;
    int[] nums;  // RandomizedSet实际存储元素的数据结构
    int idx;

    public RandomizedSet() {
        map = new HashMap<>();
        random = new Random();
        nums = new int[200010];
        idx = -1;
    }

    public boolean insert(int val) {
        // 如果map中存储了要插入的值则返回false
        if (map.containsKey(val)) {
            return false;
        }
        // 该值可以插入到数组末尾
        nums[++idx] = val;
        map.put(val, idx);
        return true;
    }

    public boolean remove(int val) {
        // 如果map没有存储要移除的元素则返回false
        if (!map.containsKey(val)) {
            return false;
        }
        // 返回移除元素在数组中的下标
        int loc = map.remove(val);
        // idx表示数组的最后一个元素的下标
        if (loc != idx) {
            // 将最后一个元素在map上的映射改为loc
            map.put(nums[idx], loc);
        }
        // 把最后一个元素赋值到移除元素的下标位置
        // 保证了[0,idx]为有效值,用于后续随机取值
        nums[loc] = nums[idx--];

        return true;
    }

    public int getRandom() {
        // 能够随机取值的原因：
        // 1.使用map和数组相结合
        // 2.remove保证了[0,idx]为有效值，相当于记录了存储元素部分的数组长度
        return nums[random.nextInt(idx + 1)];
    }
}
