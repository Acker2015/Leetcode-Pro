package lt_200_299;

public class LC_240 {
	/**
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
