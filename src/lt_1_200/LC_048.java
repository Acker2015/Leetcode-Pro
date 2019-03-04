package lt_1_200;

import java.util.Arrays;

public class LC_048 {
	/**
     * 1. 先对角线交换 
     * 2. 左右交换
     * 
     * 或者 先左右 在对角
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 1) return;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        int j = 0, k = n - 1;
        while (j < k) {
            for (int i = 0; i < n; ++i) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][k];
                matrix[i][k] = tmp;
            }
            j++;
            k--;
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
