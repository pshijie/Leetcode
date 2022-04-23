import java.util.*;

/**
 * @author psj
 * @date 2022/4/23 9:34
 * @File: topKFrequent.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/top-k-frequent-elements/
// 给你一个整数数组nums和一个整数k，请你返回其中出现频率前k高的元素。你可以按任意顺序返回答案

public class topKFrequent {
    // 方法1:哈希表
    public int[] topKFrequent_hash(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.putIfAbsent(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        int maxTimes = 0;  // 记录最多出现的次数
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxTimes) {
                maxTimes = entry.getValue();
            }
        }
        while (k > 0) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == maxTimes) {
                    result[k - 1] = entry.getKey();
                    k--;
                }
            }
            // 将最大次数递减中会找到第二大、第三大...出现的次数,再根据出现的次数找到对应的key
            maxTimes--;
        }
        return result;
    }

    // 方法2：堆(注意是最小堆而不是最大堆)
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.putIfAbsent(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        // 遍历map,然后使用最小堆保存频率最大的k个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        for (int key : map.keySet()) {
            // 维护一个大小为k的最小堆
            if (pq.size() < k) {
                pq.add(key);
                // 如果新元素的频率比堆顶大就弹出堆顶，并将该元素加入
                // 如果这是一个最大堆,且碰巧此时的堆顶就是map中value最大的，
                // 即使现在要加入第二大的元素也加不进来
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }

        int[] result = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            result[i++] = pq.remove();
        }
        return result;
    }
}
