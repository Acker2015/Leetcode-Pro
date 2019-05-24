package lt_1_200;

/**
 * [188] Best Time to Buy and Sell Stock IV
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * link to 123
 */
public class LC_188 {
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
     * @param transactionNum
     * @param prices
     * @return
     */
    public int maxProfit(int transactionNum, int[] prices) {
        int len = prices.length;
        if (len <= 1 || transactionNum < 1) {
            return 0;
        }
        // 如果交易次数大于等于交易天数的一半，则说明可以任意交易
        if (transactionNum >= prices.length/2) {
            return quickSolve(prices);
        }
        int[][] dp = new int[transactionNum+1][len];
        // when k=0, dp[0][j] = 0, with 0 transaction.
        // when j = 0, dp[k][0] = 0, no transaction days.
        for (int k = 1; k <= transactionNum; ++k) {
            int ans = dp[k-1][0] - prices[0];
            for (int j = 1; j < len; ++j) {
                dp[k][j] = Math.max(dp[k][j-1], prices[j] + ans);
                ans = Math.max(ans, dp[k-1][j] - prices[j]);
            }
        }
        return dp[transactionNum][len-1];
    }
}
