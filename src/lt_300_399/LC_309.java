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
    public int maxProfit(int[] prices) {
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
}
