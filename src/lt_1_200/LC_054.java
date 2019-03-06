package lt_1_200;

import java.util.ArrayList;
import java.util.List;
/*
 * @lc app=leetcode id=54 lang=java
 *
 * [54] Spiral Matrix
 *
 * https://leetcode.com/problems/spiral-matrix/description/
 *
 * algorithms
 * Medium (29.63%)
 * Total Accepted:    211.2K
 * Total Submissions: 712.2K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 4, 5, 6 ],
 * ⁠[ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * 
 * 
 * Example 2:
 * 
 * Input:
 * [
 * ⁠ [1, 2, 3, 4],
 * ⁠ [5, 6, 7, 8],
 * ⁠ [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 */
public class LC_054 {
	/**
     * 每一次结束一个外圈的螺旋, 循环次数为Math.min(m, n)/2向上取整
     * 1 2 3
     * 4 5 6
     * 7 8 9
     * 循环两次
     * 1. 从1开始，输出外圈螺旋。 1, 2, 3, 6, 9, 8, 7, 4
     * 2. 从5开始，输出第二圈螺旋，5
     * 
     * 注意点： 对于每一圈，当只有一行或者一列的时候要注意不要重复数据元素
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> retList = new ArrayList<>();
        int m = matrix.length;
        if (m <= 0) return retList;
        int n = matrix[0].length;
        if (n <= 0) return retList;
        int min = Math.min(m, n);
        int num = min%2==0 ? min/2:min/2+1, i = 0;
        while (i < num) {
            int row = i, col = i;
            while (col < n-i) retList.add(matrix[row][col++]);
            col--;
            row++;
            while (row < m-i) retList.add(matrix[row++][col]);
            row--;
            col--;
            // row > i 防止只有一行的时候重复添加
            while (row > i && col >= i) retList.add(matrix[row][col--]);
            col++;
            row--;
            // col < n-i-1防止只有一列的之后重复添加
            while (col < n-i-1 && row > i) retList.add(matrix[row--][col]);
            i++;
        }
        return retList;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = {{1, 2, 3 , 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
		LC_054 solution = new LC_054();
        System.out.println(solution.spiralOrder(matrix));

	}

}
