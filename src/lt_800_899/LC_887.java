package lt_800_899;

/**
 * [887] Super Egg Drop
 * 扔鸡蛋问题
 *
 * 1. solution1 DP O(nlogk)
 * 2. solution2 DP O(klogn)
 */
public class LC_887 {
    /**
     * dp[i][j]表示前i层，使用j个蛋的最小尝试次数
     * dp[0][j] = 0;
     * dp[i][0] = 0;
     * dp[i][1] = i;
     *
     * dp[N][K] = Math.min{ max(dp[i-1][K-1], dp[N-i][K]) + 1 }  (i>=1 && i<=N)
     * 1. 选择在第i层扔鸡蛋，如果碎了。那么需要在前i-1楼层使用k-1个egg继续尝试
     * 2. 选择在第i层扔鸡蛋，如果没碎。那么需要在后边N-i层使用k个egg继续尝试
     * 选择的每个楼层，尝试次数为 max(dp[i-1][K-1], dp[N-i][K]) + 1
     * 对于不同的i (i >= 1 && i <= N)
     * 最小次数就等于( max(dp[i-1][K-1], dp[N-i][K]) + 1 )在不同的i处的最小值
     *
     *
     * Notice that for the same K when N goes up, dp[K][N] goes up.
     * Then for int left = helper(K - 1, i - 1, memo); int right = helper(K, N - i, memo); when i goes up, left goes up and right goes down.
     * We can use Binary Search here to get the minimum Math.max(left, right) + 1, when left and right are as close as possible.
     * We come to this O(KNlogN) solution:
     */

    private int cal(int n, int k, int[][] mem) {
        if (n == 0 || k == 0) return 0;
        if (k == 1) return n;
        if (mem[n][k] > 0) {
            return mem[n][k];
        }
        int lower = 1, upper = n, mid, ans = n;
        while (lower <= upper) {
            mid = lower + (upper-lower)/2;
            int left = cal(mid-1, k-1, mem);
            int right = cal(n-mid, k, mem);
            ans = Math.min(ans, Math.max(left, right) + 1);
            if (left == right) {
                break;
            } else if (left < right) {
                lower = mid + 1;
            } else {
                upper = mid - 1;
            }
        }
        mem[n][k] = ans;
        return ans;
    }
    public int superEggDrop1(int K, int N) {
        int[][] mem = new int[N+1][K+1];
        return cal(N, K, mem);
    }

    /**
     * solution2
     * 另外一种比较高效的DP思路
     * dp[m][k] means m moves and k eggs can check the maximum floor
     * dp[m][k] = dp[m-1][k-1] + dp[m-1][k] + 1
     *
     * initial state
     * m = 0, dp[0][k] = 0
     * k = 0, dp[m][0] = 0
     *
     * https://leetcode.com/problems/super-egg-drop/discuss/158974/C%2B%2BJavaPython-2D-and-1D-DP-O(KlogN)
     */
    public int superEggDrop2(int K, int N) {
        int[][] dp = new int[N+1][K+1];
        int m = 0;
        while (dp[m][K] < N) {
            m++;
            for (int i = 1; i <= K; ++i) {
                dp[m][i] = dp[m-1][i-1] + dp[m-1][i] + 1;
            }
        }
        return m;
    }
    // 2D -> 1d
    public int superEggDrop(int K, int N) {
        int[] dp = new int[K+1];
        int m = 0;
        while (dp[K] < N) {
            m++;
            for (int i = K; i >= 1; --i) {
                dp[i] = dp[i-1] + dp[i] + 1;
            }
        }
        return m;
    }
}
