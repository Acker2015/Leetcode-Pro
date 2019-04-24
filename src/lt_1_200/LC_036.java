package lt_1_200;


import java.util.HashMap;
import java.util.Map;

/**
 * [36] Valid Sudoku
 * hashMap + bit
 * 对于1-9直接使用对应位置的bit位来存储，这样只需要一个int就可以代替原来的9个int
 */
public class LC_036 {
    private int numberOfchunk(int i, int j) {
        int row = i / 3;
        int column = j / 3;
        return row * 3 + column;
    }
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Integer> rowMap = new HashMap<>();
        Map<Integer, Integer> columnMap = new HashMap<>();
        Map<Integer, Integer> chunkMap = new HashMap<>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == '.') continue;
                int chunkNumber = numberOfchunk(i, j);
                int ans = 1 << (board[i][j]-'0');
                int rowFlag = rowMap.getOrDefault(i, 0);
                int columnFlag = columnMap.getOrDefault(j, 0);
                int chunkFlag = chunkMap.getOrDefault(chunkNumber, 0);

                if ((rowFlag&ans) > 0 || (columnFlag&ans) > 0 || (chunkFlag&ans) >0) {
                    return false;
                }
                rowMap.put(i, rowFlag|ans);
                columnMap.put(j, columnFlag|ans);
                chunkMap.put(chunkNumber, chunkFlag|ans);
            }
        }
        return true;
    }

    public static void main(String ...args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        LC_036 lc_036 = new LC_036();
        lc_036.isValidSudoku(board);
    }
}
