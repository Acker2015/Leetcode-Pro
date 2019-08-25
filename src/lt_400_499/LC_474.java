package lt_400_499;

/**
 * [474] Ones and Zeroes
 * 类似0-1背包问题的解法
 *
 * In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
 * For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
 * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 *
 * Note:
 * 1. The given numbers of 0s and 1s will both not exceed 100
 * 2. The size of given string array won't exceed 600.
 */
public class LC_474 {
    private int[] getBitNums(String str) {
        int[] cnt = new int[2];
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '0') {
                cnt[0]++;
            } else if (str.charAt(i) == '1') {
                cnt[1]++;
            }
        }
        return cnt;
    }
    /**
     * dp[i][j][k] means the maximum number of strings with previous i string in strs, m zeros and n ones.
     *
     * if strs[i] has x zeroes and y ones, we can choose strs[i] or not.
     * dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i-1][j-x][k-y])
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        if (len <= 0) return 0;
        int[][][] dp = new int[len+1][m+1][n+1];
        for (int i = 0; i < len; ++i) {
            int[] cnt = getBitNums(strs[i]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= cnt[0] && k >= cnt[1]) {
                        dp[i+1][j][k] = Math.max(dp[i][j][k], dp[i][j-cnt[0]][k-cnt[1]]+1);
                    } else {
                        dp[i+1][j][k] = dp[i][j][k];
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    /**
     * 优化空间解法
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        int len = strs.length;
        if (len <= 0) return 0;
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < len; ++i) {
            int[] cnt = getBitNums(strs[i]);
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if (j >= cnt[0] && k >= cnt[1]) {
                        dp[j][k] = Math.max(dp[j][k], dp[j-cnt[0]][k-cnt[1]]+1);
                    } else {
                        dp[j][k] = dp[j][k];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String ...args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        LC_474 lc_474 = new LC_474();
        System.out.println(lc_474.findMaxForm(strs, m, n));
        System.out.println(lc_474.findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
    }
}
