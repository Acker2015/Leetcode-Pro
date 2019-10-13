package lt_300_399;

import java.util.HashMap;
import java.util.Map;

/**
 * DFS + memory
 *
 * [329] Longest Increasing Path in a Matrix
 *
 The idea is simple and intuitive:
 1. For each cell, try it's left, right, up and down for smaller number.
 2. If it's smaller, means we are on the right track and we should keep going. If larger, stop and return.
 3. Treat each cell as a start cell. Calculate and memorize the longest distance for this cell,
 so we don't need to calculate it again in the future.

 time: O(mn)
 space: O(mn)
 */
public class LC_329 {
    private int m, n;
    private int[] d1 = {1,-1,0,0};
    private int[] d2 = {0,0,1,-1};
    private int dfs(int[][] matrix, int i, int j, int[][] mem) {
        if (mem[i][j] > 0) return mem[i][j];
        int ans = 1;
        for (int k = 0; k < 4; ++k) {
            int ni = d1[k]+i;
            int nj = d2[k]+j;
            if (ni < 0 || nj < 0 || ni >= m || nj >= n || matrix[ni][nj]>=matrix[i][j]) continue;
            ans = Math.max(ans, dfs(matrix, ni, nj, mem) + 1);
        }
        mem[i][j] = ans;
        return ans;
    }
    public int longestIncreasingPath(int[][] matrix) {
        if ((m=matrix.length) <= 0 || (n=matrix[0].length) <= 0) {
            return 0;
        }
        int ret = 1;
        int[][] mem = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ret = Math.max(ret, dfs(matrix, i, j, mem));
            }
        }
        return ret;
    }
}
