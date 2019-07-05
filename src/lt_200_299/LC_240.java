package lt_200_299;

public class LC_240 {
	/**
     * solution1
	 * 对每一行进行二分查找
	 * 注意剪枝
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length <= 0 || matrix[0].length <= 0) return false;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < matrix.length; ++i) {
        		// 剪枝
            if (matrix[i][0] > target) break;
            if (matrix[i][n-1] < target) continue;
            int left = 0, right = n-1;
            while (left <= right) {
                int mid = left + (right-left)/2;
                if (matrix[i][mid] == target) return true;
                if (matrix[i][mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return false;
    }

    /**
     * solution2
     * 核心思想
     * 1.若列的开头大于target，那么x应该位于该列的左边
     * 2.若行的末端小于target，那么x应该位于该行的下边
     *
     * 从右上角matrix[row][col]开始，这属于最后一列，第一行
     * matrix[row][col]>target, 命中上述1，col--
     * matrix[row][col]<target, 命中上述2，row++
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix.length <= 0 || matrix[0].length <= 0) return false;
	    int row = 0, col = matrix[0].length;
	    while (row < matrix.length && col >= 0) {
	        if (matrix[row][col] == target) {
	            return true;
            } else if (matrix[row][col] > target) {
	            col--;
            } else {
	            row++;
            }
        }
        return false;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
