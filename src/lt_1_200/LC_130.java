package lt_1_200;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * Example:

 X X X X
 X O O X
 X X O X
 X O X X
 After running your function, the board should be:

 X X X X
 X X X X
 X X X X
 X O X X
 Explanation:

 Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'.
 Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class LC_130 {
    private static char O = 'O';
    private static char X = 'X';
    private static char T = 'T';
    private int[] horArr = {-1, 1, 0, 0};
    private int[] verArr = {0, 0, -1, 1};
    private int len1 = 0, len2 = 0;

    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || y < 0 || x >= len1 || y >= len2) return;
        if (board[x][y]!=O) return;
        board[x][y] = T;
        for (int i = 0; i < 4; ++i) {
            dfs(board, x+horArr[i], y+verArr[i]);
        }
    }
    private void reset(char[][] board, char from, char to) {
        for (int i = 0; i < len1; ++i) {
            for (int j = 0; j < len2; ++j) {
                if (board[i][j] == from) {
                    board[i][j] = to;
                }
            }
        }
    }

    /**
     * 思路很简单
     * 1. 将board四周的可联通的O全部置为T,那么board剩下的O全部都是regions surrounded by X
     * 2. 遍历一遍将所有的O置为X
     * 3. 还原非region的O，遍历一遍将所有的T变为O
     * @param board
     */
    public void solve(char[][] board) {
        len1 = board.length;
        if (len1 <= 1) return;
        len2 = board[0].length;
        if (len2 <= 1) return;
        for (int i = 0; i < len1; ++i) {
            if (board[i][0] == O) {
                dfs(board, i, 0);
            }
            if (board[i][len2-1] == O) {
                dfs(board, i, len2-1);
            }
        }
        for (int j = 0; j < len2; ++j) {
            if (board[0][j] == O) {
                dfs(board, 0, j);
            }
            if (board[len1-1][j] == O) {
                dfs(board, len1-1, j);
            }
        }
        reset(board, O, X);
        reset(board, T, O);
    }


    public static void main(String ...args) {
        char[][] board = {
                {O,O,O,O,X,X},
                {O,O,O,O,O,O},
                {O,X,O,X,O,O},
                {O,X,O,O,X,O},
                {O,X,O,X,O,O},
                {O,X,O,O,O,O}};
        LC_130 lc_130 = new LC_130();
        lc_130.solve(board);
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
