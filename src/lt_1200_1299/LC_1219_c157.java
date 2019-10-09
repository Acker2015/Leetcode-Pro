package lt_1200_1299;

/**
 * 1219. Path with Maximum Gold
 *
 * In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 * Return the maximum amount of gold you can collect under the conditions:
 *
 * 1. Every time you are located in a cell you will collect all the gold in that cell.
 * 2. From your position you can walk one step to the left, right, up or down.
 * 3. You can't visit the same cell more than once.
 * 4. Never visit a cell with 0 gold.
 You can start and stop collecting gold from any position in the grid that has some gold.


 Example 1:

 Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 Output: 24
 Explanation:
 [[0,6,0],
 [5,8,7],
 [0,9,0]]
 Path to get the maximum gold, 9 -> 8 -> 7.
 Example 2:

 Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 Output: 28
 Explanation:
 [[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
 Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.


 Constraints:

 1 <= grid.length, grid[i].length <= 15
 0 <= grid[i][j] <= 100
 There are at most 25 cells containing gold.
 */
public class LC_1219_c157 {
    private int m, n;

    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    private int dfs(int[][] grid, int x, int y) {
        int tmp = grid[x][y], ans = 0;
        grid[x][y] = -1;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] <= 0) {
                continue;
            }
            ans = Math.max(ans, dfs(grid, nx, ny));
        }
        grid[x][y] = tmp;
        return ans + tmp;
    }

    /**
     * 直接暴力回溯
     * @param grid
     * @return
     */
    public int getMaximumGold(int[][] grid) {
        if ((m=grid.length) <= 0 || (n=grid[0].length) <= 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    ans = Math.max(ans, dfs(grid, i, j));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,6,0},{5,8,7},{0,9,0}};
        LC_1219_c157 solution = new LC_1219_c157();
        System.out.println(solution.getMaximumGold(grid));


        int[][] grid2 = {{1,0,7},{2,0,6},{3,4,5},{0,3,0},{9,0,20}};
        System.out.println(solution.getMaximumGold(grid2));
    }
}
