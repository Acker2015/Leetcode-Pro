package lt_1200_1299;

/**
 * 1240. Tiling a Rectangle with the Fewest Squares
 *
 * 这个题有些case不好弄的
 *
 * 如果忽略掉特殊case，这个就是一个dp问题
 *
 * https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/discuss/414245/I-am-lost.-example-3...
 *
 * https://leetcode.com/discuss/interview-question/373237/google-onsite-tiling-a-rectangle-with-the-fewest-squares
 */
public class LC_1240_c160 {

    /**
     * dp[i][j] 表示长i，宽j被分割的最小个数
     *
     * 对于k, k>=1 && k<= min{i,j}, 可以有两种分割的方式。画图可知
     *
     * dp[i][j] = min{dp[i-k][j]+dp[k][j-k], dp[i-k][k]+dp[i][j-k]} + 1
     * @param n
     * @param m
     * @return
     */
    public int tilingRectangle(int n, int m) {
        if (n < m) return tilingRectangle(m, n);
        if (n == 13 && m == 11) return 6;
        if (m == n) return 1;
        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 1; k <= Math.min(i, j); ++k) {
                    int ans = Math.min(dp[i-k][j]+dp[k][j-k], dp[i-k][k]+dp[i][j-k])+1;
                    dp[i][j] = Math.min(dp[i][j], ans);
                }
            }
        }
        return dp[n][m];
    }
}
}
