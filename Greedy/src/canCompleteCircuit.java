/**
 * @author psj
 * @date 2022/3/12 9:28
 * @File: canCompleteCircuit.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/gas-station/
// 在一条环路上有n个加油站，其中第i个加油站有汽油gas[i]升。
// 你有一辆油箱容量无限的的汽车，从第i个加油站开往第i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
// 给定两个整数数组gas和cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回-1

public class canCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        // 相当于图像中坐标点和最低点
        int sum = 0, minSum = 0;
        int start = 0;
        // 每次经过一个加油站就需要选择停下来加油，因为最后要看能否绕一周，没必须等到油耗尽了再加
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            // 假设达到第i个加油站时sum已经为负数，且为最小值，那么就选择从第i个加油站出发(相当于排除最差情况)
            if (sum < minSum) {
                start = i + 1;
                minSum = sum;
            }
        }

        if (sum < 0) {
            return -1;
        }
        // 考虑到是环形数组，所以要对start=n进行处理
        return start == n ? 0 : start;
    }
}
