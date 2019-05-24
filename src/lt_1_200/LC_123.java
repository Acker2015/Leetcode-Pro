package lt_1_200;

/**
 *
 * dp[k][j] means the max profit up until prices[j] with at most k transaction.
 * dp[k][j] 表示在j个交易日进行至多k次交易能够获得的最大收益
 *
 * dp[k][j] = Math.max{ dp[k][j-1], Math.max{dp[k-1][i] + prices[j]-prices[i]} }  with i in [0, j]
 *          = Math.max{ dp[k][j-1], prices[j] + Math.max{dp[k-1][i]-prices[i]} }
 *
 *          这里(dp[k-1][i]-prices[i])的最大值可以再第二轮循环中顺便求出直接使用
 *
 *
 */
public class LC_123 {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        if (prices.length <= 1) {
            return maxProfit;
        }
        int transactionNum = 2, len = prices.length;
        int[][] dp = new int[transactionNum+1][len];
        // when k=0, dp[0][j] = 0, with 0 transaction.
        // when j = 0, dp[k][0] = 0, no transaction days.
        for (int k = 1; k <= transactionNum; ++k) {
            int ans = dp[k-1][0] - prices[0];
            for (int j = 1; j < len; ++j) {
                dp[k][j] = Math.max(dp[k][j-1], prices[j] + ans);
                ans = Math.max(ans, dp[k-1][j] - prices[j]);
                // 记录最大收益
                maxProfit = Math.max(maxProfit, dp[k][j]);
            }
        }
        return maxProfit;
    }




    // 任意交易的最大收益
    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; ++i) {
            profit += Math.max(0, prices[i]-prices[i-1]);
        }
        return profit;
    }
    /**
     * 减少空间复杂度
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int maxProfit = 0;
        if (prices.length <= 1 || k < 1) {
            return maxProfit;
        }
        // 如果交易次数大于等于交易天数的一半，则说明可以任意交易
        if (k >= prices.length/2) {
            return quickSolve(prices);
        }
        int[] dp = new int[prices.length];
        for (int trans = 1; trans <= k; ++ trans) {
            int maxSub = dp[0] - prices[0];
            for (int j = 1; j < prices.length; ++j) {
                int tmp = Math.max(maxSub, dp[j]-prices[j]);
                dp[j] = Math.max(dp[j-1], prices[j] + maxSub);
                maxSub = tmp;
                maxProfit = Math.max(maxProfit, dp[j]);
            }
        }
        return maxProfit;
    }

    public static void main(String ...atgs) {

        //System.out.println(prices.length);
    }
}
