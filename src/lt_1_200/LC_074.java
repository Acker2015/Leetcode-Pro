package lt_1_200;

public class LC_074 {
	/**
	 * 将二维数组想象成一个一维有序的数组， 然后进行二分即可
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length <= 0) return false;
        int m = matrix.length, n = matrix[0].length;
        System.out.println("m="+m+", n="+n);
        int left = 0, right = m*n-1;
        while(left <= right) {
            int mid = left + (right-left)/2;
            System.out.printf("mid=%d, row=%d, column=%d\n", mid, mid/n, mid%n);
            if (matrix[mid/n][mid%n]==target) {
                return true;
            }
            if (matrix[mid/n][mid%n] < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return false;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LC_074 lc_074 = new LC_074();
		int [][] nums = {{1,1}};
		System.out.println(lc_074.searchMatrix(nums, 1));
	}

}
