/**
 * @author psj
 * @date 2022/3/8 10:28
 * @File: maxProfitWithFee.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
// 给定一个整数数组prices，其中prices[i]表示第i天的股票价格；整数fee代表了交易股票的手续费用。
// 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。返回获得利润的最大值。

// 1.确定三种状态：天数、允许交易的最大次数、当前持有状态(0为未持有,1为持有)
// 2.确定选择：买入、卖出、不操作
// 3.定义dp[i][j][0]：表示当前为第i+1天，现在手上没有股票，当天截止最多进行了j次交易，此时能获得的最大利润
//   定义dp[i][j][1]：表示当前为第i+1天，现在手上有股票，当天截止最多进行了j次交易，此时能获得的最大利润
//   最后结果要求得dp[n-1][K][0]:到最后一天，手上没有股票了，当天截止最多进行了K次交易，此时能获得的最大利润,并且dp[n-1][K][1]肯定小于dp[n-1][K][0],因为最后一天还持有股票未卖,肯定利润小
// 4.状态转移方程：
//      dp[i][j][0]=max(dp[i-1][j][0], dp[i-1][j][1]+prices[i])
//          解析：
//              1.dp[i-1][j][0]：昨天没有股票，因为今天手上也没有股票，所以没有进行操作，所以到今天最多进行了还是j次操作
//              2.dp[i-1][j][1]：昨天有股票，因为今天手上没有股票，所以进行了卖出操作，把卖的钱加上。但是买入和卖出算一次操作，所以到昨天截止最多进行了还是j次操作
//      dp[i][j][1]=max(dp[i-1][j][1], dp[i-1][j-1][0]-prices[i])
//          解析：
//              1.dp[i-1][j][1]：昨天有股票，因为今天手上也有股票，所以没有进行操作，所以今天截止最多进行了还是j次操作
//              2.dp[i-1][j-1][0]：昨天没有股票，因为今天手上有股票，所以进行了买入操作，把买的钱减去。买入开始算一次新的操作，所以昨天截止最多进行了j-1次操作
// 5.base case:
//      dp[0][j][0]=0: i=0表示在第一天截止时,最多进行了j次交易，手上未持有股票，所以利润为0
//      dp[0][j][1]=-prices[i]: i=0表示在第一天截止时,最多进行了j次交易，手上持有股票，所以利润为-prices[i]
//      dp[...][0][0]=0: j=0表示不允许交易,所以利润为0
//      dp[...][0][1]=-infinity: j=0表示不允许交易,不可能持有股票,所以利润为-infinity

public class maxProfitWithFee {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // 套用框架,K=正无穷,所以状态转移方程为:
        //     dp[i][k][0]=max(dp[i-1][k][0],dp[i-1][k][1]+prices[i])
        //     dp[i][k][1]=max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i]-fee)  // 因为每次交易要手续费，所以要减去fee
        //                =max(dp[i-1][k][1],dp[i-1][k][0]-prices[i]-fee) // k为无穷,所以减去1后还是无穷，相当于还是k
        //     因为k值始终不变,所以和k值无关,所以可以将三维dp改为二维dp:
        //     dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i])
        //     dp[i][1]=max(dp[i-1][1],dp[i-1][0]-prices[i])
        int[][] dp = new int[n][2];

        dp[0][1] = -prices[0]-fee;
        dp[0][0] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i] - fee);
        }

        return dp[n-1][0];
    }
}
