package lt_1_200;

public class LC_073 {
	/**
	 * in-place
	 * 
     * use first row and column to record certain row or column should be set zero or not.
     * 
     * give two variable to record the first row with zero or not and first column with zero or not
     * 使用第一行记录此行是否应该被置0
     * 使用第一列记录此列是否应该被置0
     * 
     * 但是应该注意第一行和第一列本身是否应该被置0，使用两个boolean标志位分别来记录
     */
    public void setZeroes(int[][] matrix) {
        if (matrix.length <= 0 || matrix[0].length <= 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        // 
        boolean rowFlag = false, columnFlag = false;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    if (i == 0) rowFlag = true;
                    if (j == 0) columnFlag = true;
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // set first row
        if (rowFlag) {
            for (int j = 0; j < n; ++j) {
                matrix[0][j] = 0;
            }
        }
        // set first column
        if (columnFlag) {
            for (int i = 0; i < m; ++i) {
                matrix[i][0] = 0;
            }
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
