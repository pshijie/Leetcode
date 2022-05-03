import com.sun.source.tree.ReturnTree;

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
    // 方法1：trick
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        // start和minSum相当于图像中最小值对应的坐标点和最小值
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

    // 方法2：暴力+优化
    public int canCompleteCircuit_force(int[] gas, int[] cost) {
        int n = gas.length;
        // 考虑从每个点出发
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            // 判断当前的油是否能到达下一个点
            while (remain - cost[j] >= 0) {
                // 能到达下个点就可以在下个点加油
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
                // 如果j回到出发点
                if (j == i) {
                    return i;
                }
            }
            // 执行到该步说明可以走到j但是无法走到出发点，j在[0,i)之间的加油站停止
            if (j < i) {
                return -1;
            }
            // 执行到该步说明j在i的后面，并且油不够
            // 此时的j在[i,length-1]之间.
            // 说明(j,length-1]之间的加油站还存在可能可以符合要求的出发点，
            // 而[i,j]之间的加油站出发一定不能走完一圈，因为走到j已经没油了，
            // 如果从[i,j]中选个加油站k出发,因为没有[i,k]的剩余油更不可能往下走
            i = j;
        }
        return -1;
    }
}
