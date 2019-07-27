package lt_800_899;

/**
 * [803] Bricks Falling When Hit
 * 这个题目很棒啊！
 * DFS
 *
 * 看到此题的一开始直白的想法就是 每次erasure之后，都BFS or DFS一遍把drop brick的个数记录下来，但是复杂度太高 O(K*M*N)
 *
 * 这里有一种更加高效的解法
 * 每一次erase一个位置，将未连接的brick丢下的这个过程可以反过来考虑
 * reverse之后就相当于把所有hit的位置都erase之后，倒序一次放回去的过程，放回去之后重新连接的砖头个数就是erase的时候drop的个数
 *
 * 所以这里我们先准备一次性把所有的hits位置都erase，那么我们就需要继续其中的状态，方便恢复
 * 1. 未erase的位置保持原样，如果erase的位置值为1，那么置为0，如果erase的位置的值为0，那么置为-1 （方便区分erase之前位置的值）
 * 2. 全部擦除之后，dfs一遍将能够fix而不是drop的节点值置为2
 * 3. 开始倒序将擦除的节点放回去,+1即可。然后看此位置的上下左右有没有位置的值为2 （为2说明not drop）
 *    如果有，那么过dfs将连接的1置为2 （此时所有连接的1就是擦掉时能够掉下的brick）
 */
public class LC_803 {
    private int[][] d = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    private int m, n;
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int len = hits.length;
        int[] drop = new int[len];
        if ((m=grid.length)<=0 || (n=grid[0].length)<=0) {
            return drop;
        }
        // if origin value is 0, the value of hit position would be set to be -1, if origin value is 1, the value of hit position would be set to be 1;
        for (int[] hit: hits) {
            grid[hit[0]][hit[1]] -= 1;
        }
        // set connected brick as 2
        for (int j = 0; j < n; ++j) {
            dfs(grid, 0, j);
        }
        for (int i = hits.length-1; i >= 0; i--) {
            int x = hits[i][0], y = hits[i][1];
            grid[x][y] += 1;
            if (grid[x][y] == 1 && canConnected(grid, x, y)) {
                drop[i] = dfs(grid, x, y) - 1;
            }
        }
        return drop;
    }
    private int dfs(int[][] grid, int x, int y) {
        if (x<0 || y<0 || x>=m || y>=n || grid[x][y]!=1) return 0;
        grid[x][y] = 2;
        int ans = 1;
        for (int[] di: d) {
            ans += dfs(grid, x+di[0], y+di[1]);
        }
        return ans;
    }
    private boolean canConnected(int[][] grid, int x, int y) {
        if (x==0) return true;
        for (int[] di: d) {
            int nx = x + di[0];
            int ny = y + di[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny]==2) {
                return true;
            }
        }
        return false;
    }
}
