package basic_data_structure.math;

/**
 * 计算C(n, m)
 *
 * 利用递推公式
 *
 * C(n, m) = C(n-1, m) + C(n-1, m-1)
 */

public class Cnm {
    /**
     * O(n^2)
     * @param n
     * @param m
     * @param mem
     * @return
     */
    long C(int n, int m, long[][] mem) {
        if (m == 0 || m == n) return 1;
        if (mem[n][m] > 0) {
            return mem[n][m];
        }
        return mem[n-1][m] + mem[n-1][m-1];
    }



}
