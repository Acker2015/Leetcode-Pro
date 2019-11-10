package lt_1200_1299;

/**
 *
 * 1252. Cells with Odd Values in a Matrix
 */
public class LC_1252_c162 {
    public class Solution1 {
        /**
         * solution1 - 常规解法
         *
         * 使用int二维数组计数，最后找出奇数个数即可
         *
         * time O(m*n)
         * space O(m*n)
         *
         * @param n
         * @param m
         * @param indices
         * @return
         */
        public int oddCells(int n, int m, int[][] indices) {
            if (n <= 0 || m <= 0) return 0;
            int[][] matrix = new int[n][m];
            for (int k = 0 ; k < indices.length; ++k) {
                int[] in = indices[k];
                for (int j = 0; j < m; ++j) {
                    matrix[in[0]][j] += 1;
                }
                for (int i = 0; i < n; ++i) {
                    matrix[i][in[1]] += 1;
                }
            }
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (matrix[i][j] % 2 == 1) {
                        ans++;
                    }
                }
            }
            return ans;
        }
    }

    /**
     * solution2 - 降低空间复杂度
     *
     * 这里因为只需要统计奇数cell的个数，那么可以使用true来表示奇数，false表示偶数
     *
     *
     *
     */
    public class Solution2 {
        public int oddCells(int n, int m, int[][] indices) {
            boolean[] r = new boolean[n];   // 记录某行的自增次数
            boolean[] c = new boolean[m];   // 记录某列的自增次数
            for (int[] in: indices) {
                r[in[0]] ^= true;       // if row in[0] appears odd times, it will correspond to true.
                c[in[1]] ^= true;       // if col in[1] appears odd times, it will correspond to true
            }
            int ret = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    ret += r[i]^c[j] ? 1 : 0; // only cell (i, j) with odd times count of row + column would get odd values.
                }
            }
            return ret;
        }
    }
}
