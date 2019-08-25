package lt_1_200;

import java.util.HashMap;
import java.util.Map;

/**
 * [37] Sudoku Solver - backtracking
 * 回溯
 */
public class LC_037 {
    private Map<Integer, Integer> rowMap;
    private Map<Integer, Integer> columnMap;
    private Map<Integer, Integer> chunkMap;

    private int numberOfchunk(int i, int j) {
        int row = i / 3;
        int column = j / 3;
        return row * 3 + column;
    }
    private void init(char[][] board) {
        for (int row = 0; row < 9; row++){
            for (int column =0 ; column < 9; ++column) {
                if (board[row][column]=='.') continue;
                int chunk= numberOfchunk(row, column);
                int cellNum = board[row][column]-'0';
                rowMap.put(row, rowMap.getOrDefault(row, 0)|(1<<cellNum));
                columnMap.put(column, columnMap.getOrDefault(column,0)|(1<<cellNum));
                chunkMap.put(chunk, chunkMap.getOrDefault(chunk, 0)|(1<<cellNum));
            }
        }
    }
    /**
     * 新加入的digit是否匹配
     */
    private boolean match(int i, int j, int digit) {
        boolean rowExt = (rowMap.getOrDefault(i, 0)&(1<<digit)) > 0;
        boolean columnExt = (columnMap.getOrDefault(j, 0)&(1<<digit)) > 0;
        int chunk = numberOfchunk(i, j);
        boolean chunkExt = (chunkMap.getOrDefault(chunk, 0)&(1<<digit)) > 0;
        return !(rowExt || columnExt || chunkExt);
    }

    /**
     * map的设置与恢复
     * @param i
     * @param j
     * @param digit
     * @param cancel
     */
    private void resetMap(int i, int j, int digit, boolean cancel) {
        int chunk = numberOfchunk(i, j);
        int rowVal = rowMap.getOrDefault(i, 0);
        int columnVal = columnMap.getOrDefault(j, 0);
        int chunkVal = chunkMap.getOrDefault(chunk, 0);
        if (cancel) {
            rowMap.put(i, rowVal^(1<<digit));
            columnMap.put(j, columnVal^(1<<digit));
            chunkMap.put(chunk, chunkVal^(1<<digit));
        } else {
            rowMap.put(i, rowVal|(1<<digit));
            columnMap.put(j, columnVal|(1<<digit));
            chunkMap.put(chunk, chunkVal|(1<<digit));
        }
    }
    /**
     * 回溯
     * @param board
     * @param loc
     * @return
     */
    private boolean backtracking(char[][] board, int loc) {
        int i = loc/9, j = loc%9;
        if (i >= 9) return true;
        if (board[i][j] != '.') return backtracking(board, loc+1);
        for (int d = 1; d < 10; ++d) {
            if (!match(i, j, d)) continue;
            board[i][j] = (char)(d+'0');
            resetMap(i, j, d, false);
            if (backtracking(board, loc+1)) {
                return true;
            }
            resetMap(i, j, d, true);
            board[i][j] = '.';
        }
        return false;
    }
    public void solveSudoku(char[][] board) {
        this.rowMap = new HashMap<>();
        this.columnMap = new HashMap<>();
        this.chunkMap = new HashMap<>();
        init(board);
        backtracking(board, 0);
    }

    public static void main(String ...args) {

    }
}
