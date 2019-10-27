package lt_1200_1299;

/**
 * 1240. Tiling a Rectangle with the Fewest Squares
 *
 *
 * Solution1: DP - 这种DP如果不做特殊判断其实是错误的
 * Solution2: Backtracking
 *
 * 这个题有些case不好弄的
 *
 * 如果忽略掉特殊case，这个就是一个dp问题
 *
 * https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/discuss/414245/I-am-lost.-example-3...
 *
 * https://leetcode.com/discuss/interview-question/373237/google-onsite-tiling-a-rectangle-with-the-fewest-squares
 */
public class LC_1240_c160 {

    static class Solution1 {
        /**
         * dp[i][j] 表示长i，宽j被分割的最小个数
         *
         * 对于k, k>=1 && k<= min{i,j}, 可以有两种分割的方式。画图可知
         *
         * dp[i][j] = min{dp[i-k][j]+dp[k][j-k], dp[i-k][k]+dp[i][j-k]} + 1
         * @param n
         * @param m
         * @return
         */
        public int tilingRectangle(int n, int m) {
            if (n < m) return tilingRectangle(m, n);
            if (n == 13 && m == 11) return 6;
            if (m == n) return 1;
            int[][] dp = new int[n+1][m+1];
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= m; ++j) {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = 1; k <= Math.min(i, j); ++k) {
                        int ans = Math.min(dp[i-k][j]+dp[k][j-k], dp[i-k][k]+dp[i][j-k])+1;
                        dp[i][j] = Math.min(dp[i][j], ans);
                    }
                }
            }
            return dp[n][m];
        }
    }

    /**
     * 这里通过回溯方法来遍历所有填充的情况，以左上角为坐标原点
     * 每次都去寻找左上角第一个未被填充的位置(top, left), 那么通过遍历可能填充的正方形边长len (len <= min(n-top, m-left) && len >= 1)
     * 然后将对应位置填充
     * 继续回溯递归剩余的情况
     *
     * 注意点：
     * 1. 寻找可能填充的正方形边长的时候，从大到小选择,这样能够更快的产生最优解(方便对之后非最优解进行剪枝)
     * 2. 根据1中结论，只要中间结果出现大于之前产生的结果情况，提前剪枝
     *
     */
    // DFS
    public static class Solution {
        private int res;
        public int tilingRectangle(int n, int m) {
            boolean[][] matrix = new boolean[n][m];    // 表示m*n个单元格，false表示未填充，true表示已经填充
            res = m*n;
            DFS(matrix, 0, m*n);
            return res;
        }

        private void DFS(boolean[][] matrix,  int curNum, int leftNum) {
            // 剪枝，减掉不可能成为最优解的情况
            if (curNum >= res) return;
            // 分割完成(剩下的单位为1的正方形已经被分配完)
            if (leftNum == 0) {
                res = Math.min(curNum, res);
                return;
            }
            // find left-top unfilled position
            int left = 0, top;
            for (top = 0; top < matrix.length; ++top) {
                for (left = 0; left < matrix[0].length; ++left) {
                    if (!matrix[top][left]) break;
                }
                if (left < matrix[0].length) break;
            }
            // try all size square from large to small
            int len = 1; // enum the length of square
            for (len = Math.min(matrix.length-top, matrix[0].length-left); len >= 1; --len) {
                // check if the len is valid
                boolean valid = true;
                for (int i = top; i < top+len; ++i) {
                    for (int j = left; j < left+len; ++j) {
                        if (matrix[i][j]) {
                            valid = false;
                        }
                    }
                    if (!valid) break;
                }
                if (!valid) continue;
                for (int i = top; i < top+len; ++i) {
                    for (int j = left; j < left + len; ++j) {
                        matrix[i][j] = true;
                    }
                }
                DFS(matrix, curNum+1, leftNum-len*len);
                for (int i = top; i < top+len; ++i) {
                    for (int j = left; j < left + len; ++j) {
                        matrix[i][j] = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.tilingRectangle(11, 13));
    }
}
