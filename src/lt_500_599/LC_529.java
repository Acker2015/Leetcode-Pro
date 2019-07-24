package lt_500_599;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [529] Minesweeper
 */
public class LC_529 {
    private int m,n;
    private int[][] loc = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1, 0},{1, 1}};
    private int getMineNumAbroud(char[][] board, int x, int y) {
        int mineNum = 0;
        for (int i = 0; i < loc.length; ++i) {
            int nx = x+loc[i][0];
            int ny = y+loc[i][1];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            if (board[nx][ny]=='M' || board[nx][ny]=='X') {
                mineNum++;
            }
        }
        return mineNum;
    }

    /**
     * BFS解法
     * @param board
     * @param click
     * @return
     */
    public char[][] updateBoard_BFS(char[][] board, int[] click) {
        if ((m=board.length) <= 0 || (n=board[0].length) <= 0) return board;
        if (click[0] < 0 || click[0] >= m || click[1] < 0 || click[1] >= n) return board;
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        boolean[] vis = new boolean[m*n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(click);
        vis[click[0]*n+click[1]] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0) {
                int[] cur = queue.poll();
                int mineNum = getMineNumAbroud(board, cur[0], cur[1]);
                if (mineNum > 0) {
                    board[cur[0]][cur[1]] = (char)(mineNum+'0');
                } else {
                    board[cur[0]][cur[1]] = 'B';
                    for (int i = 0; i < loc.length; ++i) {
                        int nx = cur[0]+loc[i][0];
                        int ny = cur[1]+loc[i][1];
                        if (nx < 0 || ny < 0 || nx >= m || ny >= n || vis[nx*n+ny] || board[nx][ny]!='E') continue;
                        queue.offer(new int[]{nx, ny});
                        vis[nx*n+ny] = true;
                    }
                }
            }
        }
        return board;
    }

    /**
     * DFS解法
     * @param board
     * @param x
     * @param y
     * @param vis
     */
    private void dfs(char[][] board, int x, int y, boolean[] vis) {
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return;
        }
        int mineNum = getMineNumAbroud(board, x, y);
        if (mineNum > 0) {
            board[x][y] = (char)(mineNum+'0');
            return;
        }

        board[x][y] = 'B';
        for (int i = 0; i < loc.length; ++i) {
            int nx = x + loc[i][0];
            int ny = y + loc[i][1];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            if (vis[nx*n+ny] || board[nx][ny] != 'E') continue;
            vis[nx*n+ny] = true;
            dfs(board, nx, ny, vis);
        }
    }
    public char[][] updateBoard_DFS(char[][] board, int[] click) {
        if ((m=board.length) <= 0 || (n=board[0].length) <= 0) return board;
        if (click[0] < 0 || click[0] >= m || click[1] < 0 || click[1] >= n) return board;
        boolean[] vis = new boolean[m*n];
        vis[click[0]*n+click[1]] = true;
        dfs(board, click[0], click[1], vis);
        return board;
    }
}
