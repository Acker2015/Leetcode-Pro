package lt_400_499;

/**
 * leetcode 498
 * https://leetcode.com/problems/diagonal-traverse/submissions/
 * 以左上方向为例(row - 1, column + 1)，边界情况包括
 * 1  2  3  4  5
 * 6  7  8  9  10
 * 11 12 13 14 15
 * 16 17 18 19 20
 * 21 22 23 24 25
 * 边界类型1 -> 位置3
 * 边界类型2 -> 位置5
 * 边界类型3 -> 位置15
 * 以上三种边界处理对应 topLeft=true的三块处理逻辑
 * @author Acker
 *
 */
public class Leetcode_498 {
	public int[] findDiagonalOrder(int[][] matrix) {
		if (matrix.length <= 0) return new int[] {};
        int m = matrix.length, n = matrix[0].length, row = 0, column = 0 ;
        int[] ret = new int[m * n];
        boolean topLeft = true;
        for (int index = 0; index < ret.length; ++index) {
        		ret[index] = matrix[row][column];
        		if (topLeft) {
        			if (row - 1 < 0 || column + 1 >= n) {
        				if (row - 1 < 0 && column + 1 >= n) {
        					row += 1;
        				} else if (row - 1 < 0) {
        					column += 1;
        				} else {
        					row += 1;
        				}
        				topLeft = !topLeft;
        			} else {
        				column += 1;
        				row -= 1;
        			}
        		} else {
        			if (row + 1 >= m || column - 1 < 0) {
        				if (row + 1 >= m && column - 1 < 0) {
        					column += 1;
        				} else if (column - 1 < 0) {
        					row += 1;
        				} else {
        					column += 1;
        				}
        				topLeft = !topLeft;
        			} else {
        				column -= 1;
        				row += 1;
        			}
        		}
        }
		return ret;
    }
}
