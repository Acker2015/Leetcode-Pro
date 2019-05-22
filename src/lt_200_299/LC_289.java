package lt_200_299;

/**
 * [289] Game of Life
 * Array
 */
public class LC_289 {
    int[] xd = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] yd = {-1, 0, 1, 1, -1, -1, 0, 1};

    int len1, len2;
    private int getAliveNeighbors(int[][] board, int x, int y) {
        int aliveNum = 0;
        for (int i = 0; i < xd.length; ++i) {
            int nx = x + xd[i];
            int ny = y + yd[i];
            if (nx < 0 || nx >= len1 || ny < 0 || ny >= len2) continue;
            if ((board[nx][ny]&1)==1) {
                aliveNum++;
            }
        }
        return aliveNum;
    }
    private int getNextGeneration(int cur, int aliveNeighbors) {
        if (cur == 1) {
            return (aliveNeighbors < 2 || aliveNeighbors > 3) ? 0 : 1;
        } else {
            return aliveNeighbors == 3 ? 1 : 0;
        }
    }
    /**
     * 1. alive 少于两个活着的邻居 -> dead
     * 2. alive 2or3个活着的邻居 -> live
     * 3. alive 多余三个活着的邻居 -> dead
     * 4. dead  拥有三个活着的邻居 -> live
     */
    public void gameOfLife(int[][] board) {
        if ((len1=board.length)<= 0 || (len2=board[0].length)<=0) return;
        for (int i = 0; i < len1; ++i) {
            for (int j = 0; j < len2; ++j) {
                int aliveNeighbors = getAliveNeighbors(board, i, j);
                int nextGen = getNextGeneration(board[i][j]&1, aliveNeighbors);
                board[i][j] = board[i][j]|(nextGen<<1);
            }
        }
        for (int i = 0; i < len1; ++i) {
            for (int j = 0; j < len2; ++j) {
                board[i][j] >>= 1;
            }
        }
    }
}
