package lt_1_200;

/**
 * [122] Best Time to Buy and Sell Stock II
 */
public class LC_122 {
    /**
     * 不限制次数的买卖，只有有盈利净进行交易
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; ++i) {
            profit += Math.max(0, prices[i]-prices[i-1]);
        }
        return profit;
    }
}
