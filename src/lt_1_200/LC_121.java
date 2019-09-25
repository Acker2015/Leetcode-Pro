package lt_1_200;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction
 * (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 */
public class LC_121 {
    /**
     * [121] Best Time to Buy and Sell Stock
     * 贪心
     * 从头开始遍历，记录买入点
     * 如果遇到价格更高的，那么更新卖出最高利润
     * 如果遇到价格更低的，那么就是更新买入价格，降低成本
     */
    public int maxProfit(int[] prices) {
        int maxFrofit = 0;
        if (prices.length <= 1) return maxFrofit;
        int buyPrice = prices[0];
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i]>buyPrice) {
                maxFrofit = Math.max(maxFrofit, prices[i]-buyPrice);
            } else {
                buyPrice = prices[i];
            }
        }
        return maxFrofit;
    }
}
