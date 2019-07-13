package lt_700_799;

/**
 * [714] Best Time to Buy and Sell Stock with Transaction Fee
 * 这里的transaction fee统一产生一次，并且在卖出时进行支付
 * 1. Greedy解法
 * 2. DP解法（更加通用）
 */
public class LC_714 {
    /**
     * solution1
     * 贪心
     *
     * 在交易不产生交易税费的时候是怎么求解最大收益的呢？无数次交易，只要买卖有收益，就选择交易
     * 而本题中交易会产生费用，这时候就需要考虑每次交易是不是要进行了？
     *
     * 解题思路 -> 贪心
     * 记录买点在prices[0]处，然后从idx=1处开始遍历
     * 1. 如果prices[idx]<=lastBuy，那么需要做的就是更新买点 lastBuy=prices[idx]，索引自增
     * 2. 否则，如果交易不能产生正向收益，那么索引自增
     * 3. 否则表示可以产生正常收益，那么这时候就需要考虑如何产生最大的正向收益
     *      3.1 如果后续连续索引处价格prices[idx+1]比当前prices[idx]价格高，那么等下一天再考虑卖出，这样可以省掉一次交易费用
     *      3.2 否则表示后续有一段连续空间是比prices[idx]小的，那么就需要再次判断prices[idx]-lastBuy这次交易是否发生
     *          值得购买的条件是在prices[i]后边连续区间值都不大于price[i]的区间内，出现一个prices[j], 是的prices[j]-prices[i]>fee, 就表示此次交易值得购买
     * 举个栗子
     * [1,3,2,8,7,4,9]， fee=2
     * 遍历到8的时候可以知道，此时的lastBuy为索引0处的价格为1，可以卖出价格为8。
     * 是否卖出要看后续区间里的3.2满不满足
     *      后续连续区间值都不大于price[i]的区间内出现4，使得8-4=4大于费用，所以此次卖出，更新lastBuy为4，profit=8-1-fee=5
     * 然后遇到了9，遍历结束。最后卖出此时profit = 5 + (9-4-fee) = 8
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit1(int[] prices, int fee) {
        int lastBuy = prices[0];
        int profit = 0;
        int i = 1;
        while (i < prices.length) {
            if (prices[i] <= lastBuy) {
                lastBuy = prices[i++];
                continue;
            }
            if (prices[i] - lastBuy <= fee) {
                i++;
                continue;
            }
            if (i < prices.length-1 && prices[i+1] >= prices[i]) {
                i++;
                continue;
            }
            // 判断本次[lastBuy, prices[i]]是否值得购买
            // 值得购买的条件是在prices[i]后边连续区间值都不大于price[i]的区间内，出现一个prices[j], 是的prices[j]-prices[i]>fee, 就表示此次交易值得购买
            int j = i+1;
            while (j<prices.length && prices[j] <= prices[i] && prices[i]-prices[j]<=fee) j++;
            if (j == prices.length || prices[i]-prices[j] > fee){
                profit += prices[i] - lastBuy - fee;
                lastBuy = Integer.MAX_VALUE;
                i = j;
            } else {
                i = j;
            }
            /*
            1. 继续往后找到更大的
            2. 继续往后找可以完成本次交易的，并更新lastBuy
            3.
            */
        }
        return profit;
    }

    /**
     * 2. DP解法
     * 对于交易费用，这里统一在卖出时候进行结算
     *
     * 对于第I天，最大收益都会从两个状态中产生
     * 1. buy status
     * buy[i]表示在购买状态下产生的max profit,并且上次购买位置k<=i。另外可以选择在i+1处卖出
     * 2. sell status
     * sell[i]表示在卖出状态下产生的max profit,并且上次卖出位置k<=i。另外可以选择在i+1处买入
     * 所以在第0天的位置
     * buy[0] = -prices[0]
     * sell[0] = 0
     *
     * for buy[i], the transition expression is
     * buy[i] = Max{buy[i-1], sell[i-1] - prices[i]} 这个式子表示买入价格会一直会更新为最低的
     * sell[i] = Max{sell[i-1], buy[i-1] + prices[i] - fee}
     * 那么最后的返回结果就是卖出状态下的max profit = sell[days-1]
     *
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108871/2-solutions-2-states-DP-solutions-clear-explanation!
     */
    public int maxProfit(int[] prices, int fee) {
        int days = prices.length;
        if (days <= 1) return 0;
        int[] buy = new int[days], sell = new int[days];
        buy[0] = -prices[0];
        for (int i = 1; i < days; ++i) {
            // 此时如果buy[i-1]之前有购买的话，prices[i]价格更低的话，就可以更新买入价格。所以取最大值即可
            buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i] - fee);
        }
        return sell[days-1];
    }





    public static void main(String ...ags) {
        int[] num = new int[]{1,3,2,8,4,9};
        LC_714 lc_714 = new LC_714();
        System.out.println(lc_714.maxProfit(num,2));
    }
    // [1,3,2,8,4,9] \n 2
}
