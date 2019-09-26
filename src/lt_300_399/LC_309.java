package lt_300_399;

/**
 * DP
 *
 * DP -> https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75927/Share-my-thinking-process
 * DP(状态转移) -> https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75928/Share-my-DP-solution-(By-State-Machine-Thinking)
 */
public class LC_309 {
    /**
     * buy[i] = max{buy[i-1], sell[i-2]-price[i]}  买入需要在卖出时隔一天
     * sell[i] = max{sell[i-1], buy[i-1]+price[i]}
     */
    public int maxProfit1(int[] prices) {
        if (prices.length <= 1) return 0;
        int preSell = 0, sell = 0, buy = -prices[0];
        for (int price: prices) {
            int ans = buy;
            buy = Math.max(buy, preSell-price);
            preSell = sell;
            sell = Math.max(sell, ans + price);
        }
        return sell;
    }


    public int maxProfit(int[] profits) {
        int len = profits.length;
        if (len <= 0) return 0;
        int[] buy = new int[len], sell = new int[len];
        buy[0] = -profits[0];
        for (int i = 1; i < len; ++i) {
            buy[i] = Math.max(buy[i-1], (i>=2 ? sell[i-2]:0)-profits[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1]+profits[i]);
        }
        return sell[len-1];
    }
}
