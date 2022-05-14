import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author psj
 * @date 2022/5/14 9:08
 * @File: nthUglyNumber.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/ugly-number-ii/
// 给你一个整数n，请你找出并返回第n个丑数。丑数就是只包含质因数2、3或5的正整数

public class nthUglyNumber {
    // 1是最小的丑数。对于任意一个丑数x，其与任意的质因数（2、3、5）相乘，结果（2x、3x、5x）仍为丑数

    // 优先队列(小根堆)
    // 1.起始先将最小丑数 11 放入队列
    // 2.每次从队列取出最小值x，然后将x所对应的丑数2x、3x和5x进行入队
    // 3.对2循环多次，第n次出队的值即是答案
    public int nthUglyNumber(int n) {
        int[] nums = new int[]{2, 3, 5};
        // 为了防止同一丑数多次进队，需要使用数据结构Set来记录入过队列的丑数
        HashSet<Long> set = new HashSet<>();
        Queue<Long> pq = new PriorityQueue<>();
        set.add(1L);
        pq.add(1L);
        for (int i = 1; i <= n; i++) {
            long x = pq.poll();
            if (i == n) {
                return (int) x;
            }
            for (int num : nums) {
                long t = num * x;
                if (!set.contains(t)) {
                    pq.add(t);
                    set.add(t);
                }
            }
        }
        return -1;
    }
}
