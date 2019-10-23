package lt_1_200;

import java.util.Arrays;
import java.util.Stack;

public class LC_085 {
    public static class Solution1 {
        /**
         * 单调队列
         */
        private int getMaxArea(int[] heights) {
            Stack<Integer> stack = new Stack<>();
            int maxArea = 0;
            int i = 0;
            while (i < heights.length) {
                if (stack.isEmpty() || stack.peek() <= heights[i]) {
                    stack.push(heights[i++]);
                } else {
                    int num = 1;
                    while (!stack.isEmpty() && stack.peek() > heights[i]) {
                        int top = stack.pop();
                        num++;
                        maxArea = Math.max(maxArea, (num-1)*top);
                    }
                    while (num-- > 0) {
                        stack.push(heights[i]);
                    }
                    i++;
                }
            }
            int num = 0;
            while (!stack.isEmpty()) {
                int top = stack.pop();
                num++;
                maxArea = Math.max(maxArea, num*top);
            }
            return maxArea;
        }

        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length;
            if (m <= 0) return 0;
            int n = matrix[0].length;
            if (n <= 0) return 0;
            int[] heights = new int[n];
            int maxArea = 0;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j]=='1') {
                        heights[j] += 1;
                    } else {
                        heights[j] = 0;
                    }
                }
                maxArea = Math.max(maxArea, getMaxArea(heights));
            }
            return maxArea;
        }
    }

    /**
     * DP
     * https://leetcode.com/problems/maximal-rectangle/discuss/29054/Share-my-DP-solution
     *
     * The DP solution proceeds row by row, starting from the first row.
     * Let the maximal rectangle area at row i and column j be computed by [right(i,j) - left(i,j)]*height(i,j).
     *
     * 对于列索引j处的高度为height[j], 那么left[j]记录的就是左边高度大于等于height[j]的左边界，right[j]记录的就是右边高度大于等于height[j]的右边界
     */
    public static class Solution2 {
        public int maximalRectangle(char[][] matrix) {
            int m, n;
            if ((m=matrix.length) <= 0 || (n=matrix[0].length) <= 0) {
                return 0;
            }
            int maxArea = 0;
            int[] heights = new int[n];
            int[] left = new int[n], right = new int[n];
            Arrays.fill(right, n);
            for (int i = 0; i < m; ++i) {
                // update heights
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j]=='1') {
                        heights[j] = heights[j] + 1;
                    } else {
                        heights[j] = 0;
                    }
                }
                // update left
                int leftEdge = 0;
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j]=='1') {
                        // 这里取max是关键
                        left[j]=Math.max(left[j],leftEdge); // left记录当前索引j处高度能够对应的左边最大的连续1
                    } else {
                        left[j]=0;
                        leftEdge = j+1;
                    }
                }
                int rightEdge = n;
                for (int j = n-1; j >= 0; j--) {
                    if (matrix[i][j] == '1') {
                        // 这里取min是关键
                        right[j] = Math.min(right[j],rightEdge);    // right记录当前索引j处高度能够对应的右边的最小的连续1
                    } else {
                        right[j] = n;
                        rightEdge = j;
                    }
                }
                for (int j = 0; j < n; ++j) {
                    maxArea = Math.max(maxArea, (right[j]-left[j])*heights[j]);
                }
            }
            return maxArea;
        }
    }

    public static void main(String[] args) {
        char[][] chs = {{'1','0'}};
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}};
        System.out.println(new LC_085.Solution2().maximalRectangle(matrix));
    }
}
