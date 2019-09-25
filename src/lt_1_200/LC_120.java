package lt_1_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [120] Triangle
 *
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class LC_120 {

    public static class Solution1 {
        /**
         * DP
         * down to top
         * @param triangle
         * @return
         */
        public int minimumTotal(List<List<Integer>> triangle) {
            int size = triangle.size();
            if (size == 1) {
                return triangle.get(0).get(0);
            }
            int[] dp = new int[size];
            for (int i = 0; i < size; ++i) {
                dp[i] = triangle.get(size-1).get(i);
            }
            for (int i = size-2; i >= 0; --i) {
                int len = triangle.get(i).size();
                for (int j = 0; j < len; ++j) {
                    dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
                }
            }
            return dp[0];
        }
    }


    /**
     * DP
     * 记忆化搜索
     * memorization search
     *
     * top-down
     */
    public static class Solution2 {
        private int dfs(List<List<Integer>> triangle, int row, int column, Integer[][] mem) {
            if (row >= triangle.size()) {
                return 0;
            }
            if (mem[row][column] != null) {
                return mem[row][column];
            }
            int minSubPathSum = triangle.get(row).get(column) + Math.min(dfs(triangle, row+1, column, mem), dfs(triangle, row+1, column+1, mem));
            mem[row][column] = minSubPathSum;
            return minSubPathSum;
        }
        public int minimumTotal(List<List<Integer>> triangle) {
            int size = triangle.size();
            if (size == 1) {
                return triangle.get(0).get(0);
            }
            Integer[][] mem = new Integer[size][size];
            return dfs(triangle, 0, 0, mem);
        }
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));

        System.out.println(solution2.minimumTotal(triangle));
    }
}
