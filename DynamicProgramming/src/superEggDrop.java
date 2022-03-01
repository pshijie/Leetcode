import java.util.HashMap;
import java.util.Map;

/**
 * @author psj
 * @date 2022/3/1 8:56
 * @File: superEggDrop.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/super-egg-drop/
// 已知存在楼层f ，满足0 <=f<= n,任何从高于f的楼层落下的鸡蛋都会碎，从f楼层或比它低的楼层落下的鸡蛋都不会破
// 每次操作可以取一枚没有碎的鸡蛋并把它从任一楼层x扔下（满足1<=x<=n）。如果鸡蛋碎了就不能再次使用它。如果没有摔碎则可以在之后的操作中重复使用这枚鸡蛋
// 给你k枚相同的鸡蛋，并可以使用一栋从第1层到第n层共有n层楼的建筑请计算并返回要确定f确切的值的最小操作次数是多少

public class superEggDrop {

    Map<String, Integer> memo = new HashMap<String, Integer>();

    public int superEggDrop(int k, int n) {
        return dp_2(k, n);
    }

    // 方法1：备忘录(超时)
    public int dp(int k, int n) {
        // 鸡蛋数为1时，要确切返回楼层数只能一层一层试
        if (k == 1) {
            return n;
        }
        // 楼层数为0就不用鸡蛋了
        if (n == 0) {
            return 0;
        }

        if (memo.containsKey(k + ":" + n)) {
            return memo.get(k + ":" + n);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            // dp(k,n-i)表示没碎的情况，就去看剩余楼上n-i层
            // dp(k-1,i-1)表示碎的情况，就去看剩余楼下i-1层
            // 不管是楼下还是楼上都操作都是一样的
            // 加1代表丢了一次鸡蛋
            res = Math.min(res, Math.max(dp(k, n - i), dp(k - 1, i - 1)) + 1);
        }

        memo.put(k + ":" + n, res);
        return memo.get(k + ":" + n);

    }

    // 方法2：二分法
    public int dp_2(int k, int n) {
        // 鸡蛋数为1时，要确切返回楼层数只能一层一层试
        if (k == 1) {
            return n;
        }
        // 楼层数为0就不用鸡蛋了
        if (n == 0) {
            return 0;
        }

        if (memo.containsKey(k + ":" + n)) {
            return memo.get(k + ":" + n);
        }
        int res = Integer.MAX_VALUE;

        int lo = 1, hi = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // 打碎的情况
            int broken = dp(k - 1, mid - 1);
            // 没打碎的情况
            int not_broken = dp(k, n - mid);
            // 因为是确保能检查出楼层，所以需要选择最小操作次数较大的情况
            if (broken > not_broken) {
                hi = mid - 1;
                res = Math.min(res, broken + 1);
            }else {
                lo = mid + 1;
                res = Math.min(res, not_broken + 1);
            }
        }

        memo.put(k + ":" + n, res);
        return memo.get(k + ":" + n);
    }
}
