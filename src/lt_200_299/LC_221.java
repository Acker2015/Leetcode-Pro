package lt_200_299;

/**
 * [221] Maximal Square
 */
public class LC_221 {
    /**
     * dp[i][j] means the max len of the Square
     * 1. dp[i][j] = 0 -> dp[i][j] = 0
     * 2. dp[i][j] = 1 -> dp[i][j] = min{dp[i-1][j-1], dp[i-1][j], dp[i][j-1]} + 1
     *
     * return max{dp[i][j]}^2
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int len1, len2, maxLen = 0;
        if ((len1=matrix.length) <= 0 || (len2=matrix[0].length) <= 0) return 0;
        int[][] dp = new int[len1][len2];
        for (int j = 0; j < len2; ++j) {
            if (matrix[0][j] == '1') {
                maxLen = 1;
                dp[0][j] = 1;
            }
        }
        for (int i = 0; i < len1; ++i) {
            if (matrix[i][0] == '1') {
                maxLen = 1;
                dp[i][0]=1;
            }
        }
        for (int i = 1; i < len1; ++i) {
            for (int j = 1; j < len2; ++j) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen*maxLen;
    }
}
