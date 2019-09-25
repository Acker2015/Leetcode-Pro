package lt_1_200;

/**
 *
 * [123] Best Time to Buy and Sell Stock III
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 * dp[k][j] means the max profit up until prices[j] with at most k transaction.
 * dp[k][j] 表示在j个交易日进行至多k次交易能够获得的最大收益
 *
 * 1. jump out the days j, no transaction at days j, dp[k][j] = dp[k][j-1]
 * 2. carry on transaction at days j, so just find last transaction day jj,
 *      make (dp[k-1][jj]+prices[j]-prices[jj]) maximum.
 *
 * dp[k][j] = Math.max{ dp[k][j-1], Math.max{dp[k-1][i] + prices[j]-prices[i]} }  with i in [0, j-1]
 *          = Math.max{ dp[k][j-1], prices[j] + Math.max{dp[k-1][i]-prices[i]} }
 *
 *          这里(dp[k-1][i]-prices[i])的最大值可以再第二轮循环中顺便求出直接使用
 *
 *
 */
public class LC_123 {

    /**
     * 贪心-找一个中间的交易日分成两段，就会变成问题1
     */
    public static class Solution1 {
        private int maxProfitOfOneTransaction(int[] prices, int l, int r) {
            if (r <= l) {
                return 0;
            }
            int maxProfit = 0;
            int buyPrice = prices[l];
            for (int i = l+1; i <= r; ++i) {
                if (prices[i] > buyPrice) {
                    maxProfit = Math.max(maxProfit, prices[i] - buyPrice);
                } else {
                    buyPrice = prices[i];
                }
            }
            return maxProfit;
        }
        /**
         * O(N^2) 两边贪心
         */
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            int len = prices.length;
            for (int i = 0; i < len; ++i) {
                int ans = maxProfitOfOneTransaction(prices, 0, i) + maxProfitOfOneTransaction(prices, i, len-1);
                maxProfit = Math.max(maxProfit, ans);
            }
            return maxProfit;
        }
    }

    /**
     * DP
     * 解法看class注释
     */
    public static class Solution2 {
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
    }



    public class Solution3 {
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
    }

    public static void main(String ...atgs) {

        //System.out.println(prices.length);
    }
}
