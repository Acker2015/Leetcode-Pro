package lt_1200_1299;

/**
 * 1254. Number of Closed Islands
 *
 * DFS找出小岛的个数
 *
 * 小岛行程的条件是被水域(1)包裹住，也就是说小岛中的0不会与边界外的点相连
 *
 */
public class LC_1254_c162 {
    private int[] dx = {1,-1,0,0};
    private int[] dy = {0,0,1,-1};
    private int m, n;
    private boolean dfs(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= m || y >= n) {
            return false;
        }
        if (grid[x][y] != 0) return true;
        grid[x][y] = -1;    // 将遍历过的水域置为其他值
        boolean ret = true;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            ret &= dfs(grid, nx, ny);
        }
        return ret;
    }
    public int closedIsland(int[][] grid) {
        m = grid.length;
        if (m <= 0) return 0;
        n = grid[0].length;
        if (n <= 0) return 0;
        int ret = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ret += dfs(grid, i, j) ? 1 : 0;
                }
            }
        }
        return ret;
    }
}
