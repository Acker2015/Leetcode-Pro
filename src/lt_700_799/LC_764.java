package lt_700_799;


import java.util.HashSet;
import java.util.Set;

/**
 * [764] Largest Plus Sign
 * 一句话以每一个slice坐标值为1的点，上下左右方向能到达的最长距离的最小值为order-k
 * 直接使用四个数组来记录每个位置上下左右的最长连续1距离即可
 * 最后结果为四个方向距离的最小值
 */
public class LC_764 {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        if (N <= 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int[] mine: mines) {
            set.add(mine[0]*N + mine[1]);
        }
        // two-dp dp1 means top-left to bottom-right, dp2 means bottom-right to top-left
        int[][] dpTop = new int[N][N], dpLeft = new int[N][N];
        int[][] dpBottom = new int[N][N], dpRight = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (set.contains(i*N+j)) {
                    dpTop[i][j] = 0;
                    dpLeft[i][j] = 0;
                } else {
                    dpTop[i][j] = (i > 0 ? dpTop[i-1][j] : 0) + 1;
                    dpLeft[i][j] = (j > 0 ? dpLeft[i][j-1] : 0) + 1;
                }
            }
        }
        for (int i = N-1; i >= 0; --i) {
            for (int j = N-1; j >= 0; --j) {
                if (set.contains(i*N+j)) {
                    dpBottom[i][j] = 0;
                    dpRight[i][j] = 0;
                } else {
                    dpBottom[i][j] = (i < N-1 ? dpBottom[i+1][j] : 0) + 1;
                    dpRight[i][j] = (j < N-1 ? dpRight[i][j+1] : 0) + 1;
                }
            }
        }
        int plusSign = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                int min1 = Math.min(dpTop[i][j], dpLeft[i][j]);
                int min2 = Math.min(dpBottom[i][j], dpRight[i][j]);
                plusSign = Math.max(plusSign, Math.min(min1, min2));
            }
        }
        return plusSign;
    }
    // 5\n[[3,0],[3,3]]
}
