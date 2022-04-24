import java.util.HashSet;
import java.util.Map;

/**
 * @author psj
 * @date 2022/4/24 8:48
 * @File: isStraight.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
// 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
// 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0，可以看成任意数字

public class isStraight {
    // 因为大小王可以替换任何一张牌，所以只需要除了大小王之外的牌最大值和最小值之间的差值小于5皆可
    // 并且顺子中不能包含重复的牌
    public boolean isStraight(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int max = 0, min = 14;
        for (int num : nums) {
            // 0表示大小王，遍历到这跳过
            if (num == 0) {
                continue;
            }
            max = Math.max(max, num);
            min = Math.min(min, num);
            // 顺子中包含重复的牌
            if (set.contains(num)) {
                return false;
            }
            set.add(num);
        }
        return max - min < 5;

    }
}
