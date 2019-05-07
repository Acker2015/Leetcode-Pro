package lt_200_299;

/**
 * [292] Nim Game
 */
public class LC_292 {
    /**
     * solution1
     * Math
     * https://leetcode.com/problems/nim-game/discuss/73760/One-line-O(1)-solution-and-explanation
     * @param n
     * @return
     */
    public boolean canWinNim1(int n) {
        return n%4!=0;
    }

    /**
     * dp[i] = !(dp[i-1] && dp[i-2] && dp[i-3])
     * A拿几个石头，得看拿完之后能不能让B是失败状态
     * 比如拿1个石头可以让B直接失败
     * dp[i] = !dp[i-1] || !dp[i-2] || !dp[i-3]
     * 那么dp[i] = !dp[i-1] = true
     */
    public boolean canWinNim(int n) {
        boolean[] dp = {true, true, true};
        if (n <= 3) return true;
        if (n >= 134882061) return n%4!=0;
        for (int i = 4; i <= n; ++i) {
            dp[i%3] = !(dp[0] && dp[1] && dp[2]);
        }
        return dp[n%3];
    }
}
