/**
 * @author psj
 * @date 2022/3/7 10:35
 * @File: maxProfitII.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
// 给定一个数组prices,其中prices[i]表示股票第i天的价格。在每一天，你可能会决定购买和/或出售股票。你在任何时候最多只能持有一股股票。你也可以购买它，然后在同一天出售返回 你能获得的最大利润 

// 1.确定三种状态：天数、允许交易的最大次数、当前持有状态(0为未持有,1为持有)
// 2.确定选择：买入、卖出、不操作
// 3.定义dp[i][j][0]：表示当前为第i天，现在手上没有股票，当天截止最多进行了j次交易，此时能获得的最大利润
//   定义dp[i][j][1]：表示当前为第i天，现在手上有股票，当天截止最多进行了j次交易，此时能获得的最大利润
//   最后结果要求得dp[n-1][K][0]:到最后一天，手上没有股票了，当天截止最多进行了K次交易，此时能获得的最大利润
//   注意不是n，因为在最后一天进行操作没有意义,并且dp[n-1][K][1]肯定小于dp[n-1][K][0],因为最后一天还持有股票未卖,肯定利润小
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
//      dp[-1][...][0]=0: i=-1表示还未开始,所以利润为0
//      dp[-1][...][1]=0: i=-1表示还未开始,不可能持有股票,所以利润为-infinity
//      dp[...][0][1]=0: j=0表示不允许交易,所以利润为0
//      dp[...][0][1]=0: j=0表示不允许交易,不可能持有股票,所以利润为-infinity

public class maxProfitII {
    // 套用框架,K=正无穷,所以状态转移方程为:
    //     dp[i][k][0]=max(dp[i-1][k][0],dp[i-1][k][1]+prices[i])
    //     dp[i][k][1]=max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i])
    //                =max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i]) // k为无穷,所以减去1后还是无穷
    //     因为k值始终不变,所以和k值无关,所以可以将三维dp改为二维dp:
    //     dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i])
    //     dp[i][1]=max(dp[i-1][1],dp[i-1][0]-prices[i])
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // dp[i][0]表示第i天截止时,手上没有股票时的利润
        // dp[i][1]表示第i天截止时,手上有股票时的利润
        int[][] dp = new int[n][2];

        // base case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);
        }

        return dp[n-1][0];
    }
}
