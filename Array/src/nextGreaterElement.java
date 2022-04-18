import java.util.Arrays;

/**
 * @author psj
 * @date 2022/4/18 9:44
 * @File: nextGreaterElement.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/next-greater-element-iii/
// 给你一个正整数n ，请你找出符合条件的最小整数，其由重新排列n中存在的每位数字组成，并且其值大于n。如果不存在这样的正整数，则返回-1

public class nextGreaterElement {
    // 核心思想:一个降序排列的数字num一定找不到大于它的数字，比如4321
    // 1.需要从右向左遍历(因为需要保证最小，所以肯定是交换位数越小的两个数越好)
    // 2.找到第一个不符合降序的数num，比如4132431，从右向左遍历第一个不符合的数为2
    // 3.将num和后续中最接近(大于)num的数交换，即2和后续431中的3进行交换，数字变为4133421
    // 4.将交换后的3的后续数组421进行从小到大排序***

    public int nextGreaterElement(int n) {
        String num = String.valueOf(n);
        char[] arrayNum = num.toCharArray();
        // 记录交换元素的位置,因为需要进行第4步操作
        int index = -1;
        // 如果遍历完一轮发现index=-1说明该整数为递减的数，比如4321
        for (int i = arrayNum.length - 1; i >= 0 && index == -1; i--) {
            int j = i - 1;
            // 从右向左找到比后面一个元素小的当前元素num
            if (j >= 0 && arrayNum[i] > arrayNum[j]) {
                // 在num后续数组中找到刚好大于num的数，
                // 因为已经确定arrayNum[i,arrayNum.length-1]已经是递减的，所以从右到左找到第一个大于num的数即可
                for (int k = arrayNum.length - 1; k >= i; k--) {
                    if (arrayNum[k] > arrayNum[j]) {
                        char temp = arrayNum[j];
                        arrayNum[j] = arrayNum[k];
                        arrayNum[k] = temp;
                        index = i;
                        break;
                    }
                }
            }
        }
        // 说明没有符合条件的元素
        if (index == -1) {
            return -1;
        }
        Arrays.sort(arrayNum, index, arrayNum.length);

        // 不能直接使用Integer.valueOf(s)，因为s可能超过32位
        long result = 0;
        for (int i = 0; i < arrayNum.length; i++) {
            result = result * 10 + (arrayNum[i] - '0');
        }
        // 位数大于Integer的范围(32位整数)就返回-1
        return result > Integer.MAX_VALUE ? -1 : (int) (result);
    }
}
