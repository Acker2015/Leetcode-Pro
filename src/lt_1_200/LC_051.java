package lt_1_200;

import java.util.ArrayList;
import java.util.List;

/**
 * [51] N-Queens
 */
public class LC_051 {
    // 根据board打印八皇后
    private List<String> printNQ(int[] board, int n) {
        List<String> list = new ArrayList<>();
        for (int row = 0; row < n; ++row) {
            int col = board[row];
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n; ++i) {
                if (i != col) {
                    builder.append('.');
                } else {
                    builder.append('Q');
                }
            }
            list.add(builder.toString());
        }
        return list;
    }
    /**
     * board[i]=j表示i行j列为皇后存放位置
     * 由于在solve中是一行一行寻找皇后，所以这里只需要判断与其他皇后不同列和不同对角线即可
     */
    private boolean check(int[] board, int row, int col) {
        for (int i = 0; i < row; ++i) {
            if (board[i]==col) return false;
            if (row-i == Math.abs(board[i]-col)) return false;
        }
        return true;
    }
    private void solve(int[] board, int row, int n, List<List<String>> NQList) {
        if (row == n) {
            NQList.add(printNQ(board, n));
            return;
        }
        for (int col = 0; col < n; ++col) {
            if (check(board, row, col)) {
                board[row] = col;
                solve(board, row+1, n, NQList);
            }
        }
    }

    /**
     * backtracking
     * 一行行的填充，每行填充一个皇后，在check的时候只需要检测同列以及45度角线没有其他皇后即可
     */
    public List<List<String>> solveNQueens(int n) {
        int[] board = new int[n];
        List<List<String>> NQList = new ArrayList<>();
        solve(board, 0, n, NQList);
        return NQList;
    }
}
