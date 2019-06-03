package lt_300_399;

import java.util.Arrays;

/**
 * [322] Coin Change
 */
public class LC_322 {
    /**
     * DP问题
     * dp[k] means min coin change when amount = k
     * dp[k] = min{dp[k-coin[i]]} + 1, where coin[i] is any val in coins
     *
     * https://www.zhihu.com/question/23995189/answer/613096905?utm_source=wechat_session&utm_medium=social&utm_oi=64857319669760
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        Arrays.sort(coins);
        int[] dp = new int[amount+1];
        for (int k = 1; k <= amount; ++k) {
            int minVal = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; ++i) {
                if (k < coins[i]) break;
                if (dp[k-coins[i]] >= 0){
                    minVal = Math.min(dp[k-coins[i]]+1, minVal);
                }
            }
            dp[k] = minVal==Integer.MAX_VALUE?-1:minVal;
        }
        return dp[amount];
    }
}
