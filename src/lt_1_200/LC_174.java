package lt_1_200;

/**
 * [174] Dungeon Game
 */
public class LC_174 {

    int m, n;
    /**
     * dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1])
     *
     * if dp[i][j] > 0, then dp[i][j] = 0, means always healthy from (i, j) to the right-down ending.
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        if ((m=dungeon.length) <= 0 || (n=dungeon[0].length) <= 0) {
            return 1;
        }
        Integer[][] mem = new Integer[m][n];
        int neg = search(dungeon, 0, 0, mem);
        return Math.abs(neg)+1;
    }

    private int search(int[][] dungeon, int x, int y, Integer[][] mem) {
        if (mem[x][y] != null) {
            return mem[x][y];
        }
        int right = Integer.MIN_VALUE, down = Integer.MIN_VALUE;
        if (x < m - 1) {
            down = search(dungeon, x + 1, y, mem);
        }
        if (y < n - 1) {
            right = search(dungeon, x, y + 1, mem);
        }
        int ans;
        if (right == Integer.MIN_VALUE && down == Integer.MIN_VALUE) {
            ans = dungeon[x][y];    // reach the position of princess
        } else {
            ans = Math.max(down, right) + dungeon[x][y];    // get the optimal sub result.
        }
        // if ans is bigger than 0, it always means healthy from this position to the right-down ending.
        // so just set it 0
        if (ans > 0) {
            ans = 0;
        }
        mem[x][y] = ans;
        return ans;
    }
}
