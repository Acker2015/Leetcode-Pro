package lt_1_200;


public class LC_063 {
    /**
     * dp[i][j]
     * if obstacleGrid[i][j]=1, dp[i][j] = 1;
     * else dp[i][j] = dp[i-1][j] + dp[i][j+1]
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m <= 0) return 0;
        int n = obstacleGrid[0].length;
        if (n <= 0) return 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            if (obstacleGrid[0][j] == 1) {
                break;
            }
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }


    /**
     * DP
     * 空间复杂度
     * 二维变一维
     *
     * @param grid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        if (m <= 0) return 0;
        int n = grid[0].length;
        if (n <= 0) return 0;
        int[] dp = new int[n];
        for (int j = 0; j < n; ++j) {
            if (grid[0][j] == 1) break;
            dp[j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    dp[j] = dp[j] + (j > 0 ? dp[j-1] : 0);
                } else {
                    dp[j] = 0;
                }
            }
        }
        return dp[n-1];
    }


}
