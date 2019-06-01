package lt_800_899;

import java.util.Arrays;
import java.util.List;

/**
 * [877] Stone Game
 */
public class LC_877 {
    /**
     * Alex is first to pick pile.
     piles.length is even, and this lead to an interesting fact:
     Alex can always pick odd piles or always pick even piles!

     For example,
     If Alex wants to pick even indexed piles piles[0], piles[2], ....., piles[n-2],
     he picks first piles[0], then Lee can pick either piles[1] or piles[n - 1].
     Every turn, Alex can always pick even indexed piles and Lee can only pick odd indexed piles.

     In the description, we know that sum(piles) is odd.
     If sum(piles[even]) > sum(piles[odd]), Alex just picks all evens and wins.
     If sum(piles[even]) < sum(piles[odd]), Alex just picks all odds and wins.

     So, Alex always defeats Lee in this game.
     *
     */
    public boolean stoneGame1(int[] piles) {
        return true;
    }

    /**
     * dp[i][j] is the max number of stones that Alex can get more than Lee's stones.
     * dp[i][j]表示拿到的石头数，alex拿到就增加，Lee拿到就减去
     */
    public boolean stoneGame2(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len];
        boolean alexTurn = !(len%2==0); // 当d为1的时候，只剩一堆石子。len为奇数被alex拿到，为偶数就被Lee拿到
        for (int d = 1; d <= len; ++d) {
            for (int i = 0; i <= len-d; ++i) {
                int j = i + d - 1;
                if (i == j) {
                    // len为偶数的时候，最后一个会被Lee拿到。为奇数的话，最后一个会被Alex拿到
                    dp[i][j] = alexTurn ? piles[i] : -piles[i];
                    continue;
                }
                if (alexTurn) {
                    dp[i][j] = Math.max(dp[i+1][j]+piles[i], dp[i][j-1]+piles[j]);
                } else {
                    dp[i][j] = Math.min(dp[i+1][j]-piles[i], dp[i][j-1]-piles[j]);
                }
            }
            alexTurn = !alexTurn;
        }
        return dp[0][len-1] > 0;
    }

    /**
     * 算法同上
     * dp空间二维变一维，降低空间复杂度
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int len = piles.length;
        int[] dp = new int[len];
        boolean alexTurn = !(len%2==0);
        for (int d = 1; d <= len; ++d) {
            for (int j = len-1; j - d + 1>= 0; --j) {
                int i = j - d + 1;
                if (i == j) {
                    dp[j] = alexTurn ? piles[i] : -piles[i];
                    continue;
                }
                if (alexTurn) {
                    dp[j] = Math.max(dp[j]+piles[i], dp[j-1]+piles[j]);
                } else {
                    dp[j] = Math.min(dp[j]-piles[i], dp[j-1]-piles[j]);
                }
            }
            alexTurn = !alexTurn;
        }
        return dp[len-1] > 0;
    }
}
