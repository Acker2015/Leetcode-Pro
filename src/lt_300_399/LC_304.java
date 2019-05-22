package lt_300_399;

/**
 * [304-Range Sum Query 2D-Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/description/)
 */
public class LC_304 {
    private int len1, len2;
    private int[][] preSum;
    public LC_304(int[][] matrix) {
        if ((len1 = matrix.length) <= 0 || (len2 = matrix[0].length) <= 0) {
            return;
        }
        preSum = new int[len1][len2];
        preSum[0][0] = matrix[0][0];
        for (int i = 1; i < len1; ++i) {
            preSum[i][0] = matrix[i][0] + preSum[i-1][0];
        }
        for (int j = 1; j < len2; ++j) {
            preSum[0][j] = matrix[0][j] + preSum[0][j-1];
        }
        for (int i = 1; i < len1; ++i) {
            for (int j = 1; j < len2; ++j) {
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + matrix[i][j];
            }
        }
    }
    private int getSum(int row, int col) {
        if (row < 0 || col < 0) return 0;
        return preSum[row][col];
    }
    /**
     * preSum[row2][col2] - preSum[][]
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getSum(row2, col2) - getSum(row2, col1-1) - getSum(row1-1, col2) + getSum(row1-1, col1-1);
    }
}