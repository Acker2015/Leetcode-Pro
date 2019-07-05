package lt_400_499;

/**
 * [463] Island Perimeter
 * solution1: DFS
 * solution2: math (high-efficiency)
 */
public class LC_463 {
    int[] xd = {1,-1,0,0};
    int[] yd = {0,0,-1,1};
    int len1, len2;
    private int dfs(int[][] grid, int x, int y) {
        grid[x][y] = -1;
        int pm = 0;
        for (int i = 0; i < 4; ++i) {
            int nx = x + xd[i];
            int ny = y + yd[i];
            if (nx < 0 || ny < 0 || nx >= len1 || ny >= len2 || grid[nx][ny] == 0) {
                pm++;
                continue;
            }
            if (grid[nx][ny] < 0) {
                continue;
            }
            pm += dfs(grid, nx, ny);
        }
        return pm;
    }

    /**
     * DFS
     * @param grid
     * @return
     */
    public int islandPerimeter1(int[][] grid) {
        if ((len1=grid.length) <= 0 || (len2=grid[0].length) <= 0) return 0;
        for (int i = 0; i < len1; ++i) {
            for (int j = 0; j < len2; ++j) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    /**
     * math
     * 4*island - 2 *neighbour
     * 防止邻居重复计算，只计算右边和下边的邻居
     * 这样最后的结果就是4*island - 2 *neighbour
     */
    public int islandPerimeter(int[][] grid) {
        int island = 0, neighbour = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1) {
                    island++;
                    if (j+1 < grid[0].length && grid[i][j+1] == 1) neighbour++;
                    if (i+1 < grid.length && grid[i+1][j] == 1) neighbour++;
                }
            }
        }
        return 4*island-2*neighbour;
    }
}
