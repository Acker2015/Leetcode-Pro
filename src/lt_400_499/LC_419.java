package lt_400_499;

/**
 * [419] Battleships in a Board
 */
public class LC_419 {
    /**
     * 遇到'X',如果top adjacent或者left adjacent是'X',则说明是一个连接的battleships，不会用于计数
     */
    private static final Character BS = 'X';
    public int countBattleships(char[][] board) {
        int cnt = 0;
        int m = board.length, n;
        if (m <= 0 || (n = board[0].length) <= 0) return cnt;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j]==BS) {
                    // 这里此题并不需要，但是可以用来判断输入的board是否合法, 思路就是判断水平和垂直都有连接的时候就是不合法的
                    boolean horFlag = (j > 0 && board[i][j-1]==BS) || (j+1 < n && board[i][j+1]==BS);
                    boolean verFlag = (i > 0 && board[i-1][j]==BS) || (i+1 < m && board[i+1][j]==BS);
                    if (horFlag && verFlag) {
                        return -1;
                    }
                    // 只要检查上方或者左边是不是battleship就能判断这个位置是不是新的起点
                    boolean topFlag = i > 0 && board[i-1][j]==BS;
                    boolean leftFlag = j > 0 && board[i][j-1]==BS;
                    if (!topFlag && !leftFlag) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String ...args) {
        char[][] board = {{'X','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
        LC_419 lc_419 = new LC_419();
        System.out.println(lc_419.countBattleships(board));
    }
}
