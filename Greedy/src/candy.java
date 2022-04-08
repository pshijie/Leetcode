import java.util.Arrays;

/**
 * @author psj
 * @date 2022/4/8 8:51
 * @File: candy.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/candy/
// n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。你需要按照以下要求，给这些孩子分发糖果：
//  1.每个孩子至少分配到 1 个糖果。
//  2.相邻两个孩子评分更高的孩子会获得更多的糖果。
// 请你给每个孩子分发糖果，计算并返回需要准备的最少糖果数目

public class candy {
    // 左规则： 当 ratings_B>ratings_A,BB的糖比AA的糖数量多。
    // 右规则： 当 ratings_A>ratings_B，AA的糖比BB的糖数量多
    public int candy(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        // 先从左到右遍历，如果右边的大于左边的，则将右边的数在左边数的基础上加1，否则还是1
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        int result = left[ratings.length - 1];  // 置为1是因为最后一个数只能为1

        // 再从右到左遍历，如果左边的大于右边的，则将左边的数在右边数的基础上加1，否则还是1
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            result += Math.max(right[i], left[i]);  // 这里没有必要比较最后一位，因为已经加上了(result初始化为1)
        }

        return result;
    }
}
