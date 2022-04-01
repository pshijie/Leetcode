/**
 * @author psj
 * @date 2022/4/1 10:01
 * @File: backToOrigin.java
 * @Software: IntelliJ IDEA
 */
// https://mp.weixin.qq.com/s/NZPaFsFrTybO3K3s7p7EVg
// 圆环上有num个点，编号为0~num-1。从0点出发，每次可以逆时针和顺时针走一步，问走n步回到0点共有多少种走法
public class backToOrigin {
    public int backToOrigin(int num, int n) {
        // 参考爬楼梯，所有走法等于走到1和走到9的走法之和
        // dp[i][j]表示从0出发，走n步走到j的走法
        int[][] dp = new int[n + 1][num];
        // base case:从0出发，走0步到0点为1种走法
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < num; j++) {
                // 走i-1步走到j-1的走法和走i-1步走到j+1的走法之和
                // 取余是因为+1或-1后可能会超过0-num-1的范围
                dp[i][j] = dp[i - 1][(j - 1) % num] + dp[i - 1][(j + 1) % num];
            }
        }
        return dp[n][0];
    }

}
