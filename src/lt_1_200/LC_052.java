package lt_1_200;


/**
 * [52] N-Queens II
 * backtracking
 */
public class LC_052 {
    /**
     * 直接使用boolean数组
     */
    static class Solution2 {
        boolean[] column;
        boolean[] diagonalPos;
        boolean[] diagonalNeg;
        int count;
        private void backtracking(int row, int n) {
            if (row >= n) {
                count+=1;
                return;
            }
            for (int col = 0; col < n; ++col) {
                int d1 = col + row;
                int d2 = col - row + n;
                if (column[col] || diagonalPos[d1] || diagonalNeg[d2]) continue;
                column[col] = true;
                diagonalPos[d1] = true;
                diagonalNeg[d2] = true;
                backtracking(row+1, n);
                column[col] = false;
                diagonalPos[d1] = false;
                diagonalNeg[d2] = false;
            }
        }
        public int totalNQueens(int n) {
            column = new boolean[n];
            diagonalPos = new boolean[2*n];
            diagonalNeg = new boolean[2*n];
            count = 0;
            backtracking(0, n);
            return count;
        }
    }
    public static void main(String ...args) {
        System.out.println(new Solution2().totalNQueens(8));
    }
}
