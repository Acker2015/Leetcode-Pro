package lt_300_399;

import java.util.HashMap;
import java.util.Map;

/**
 * DFS + memory
 *
 * [329] Longest Increasing Path in a Matrix
 */
public class LC_329 {
    private int m, n;
    int[] d1 = {1,-1,0,0};
    int[] d2 = {0,0,1,-1};
    private int dfs(int[][] matrix, int i, int j, Map<Integer, Integer> map) {
        if (map.containsKey(n*i+j)) {
            return map.get(n*i+j);
        }
        int ans = 1;
        for (int k = 0; k < 4; ++k) {
            int ni = d1[k]+i;
            int nj = d2[k]+j;
            if (ni < 0 || nj < 0 || ni >= m || nj >= n || matrix[ni][nj]>=matrix[i][j]) continue;
            ans = Math.max(ans, dfs(matrix, ni, nj, map) + 1);
        }
        map.put(n*i+j, ans);
        return ans;
    }
    public int longestIncreasingPath(int[][] matrix) {
        if ((m=matrix.length) <= 0 || (n=matrix[0].length) <= 0) {
            return 0;
        }
        int ret = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ret = Math.max(ret, dfs(matrix, i, j, map));
            }
        }
        return ret;
    }
}
