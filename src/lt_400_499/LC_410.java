package lt_400_499;

/**
 * [410] Split Array Largest Sum
 */
public class LC_410 {
    /**
     * DP
     * time O(m*n*n) n为数组长度
     *
     * 从2段-m段来动态的看待这个问题
     * 比如前i个元素被分成2段，可以由前一个分成一段的subarr来作为中间状态推导
     * 例如 [0,...k, ...i]
     *      0-i被分成两段可以由{0-k 一段}和{k-i 一段}组成
     *      0-i被分成三段可以由{0-k 两段}和{k-i 一段}组成
     *      ...
     *      0-i被分成m段可以由{0-k m-1段}和{k-i 一段}组成
     *
     * dp[i][j] 表示0-i个元素，分成j段的每一个分段和最大值的最小值
     * 在0-i中寻找一个点k作为中间状态dp[k][j-1]，表示0-k被分成j-1段，所以此时最大值表示为 Max{dp[k][j-1], sum(k+1,i)}
     * minmax
     * dp[i][j] = min{dp[i][j], max(dp[k][j-1], sum(k+1,i))}
     */
    public int splitArray(int[] nums, int m) {
        // presum
        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i-1];
        }
        int sum = nums[nums.length-1];
        if (m == 1) return sum;
        int[][] dp = new int[nums.length][m+1];
        // 初始化状态，默认1段，取其前缀合即可
        for (int i = 0; i < nums.length; ++i) {
            dp[i][1] = nums[i];
        }
        // dp[i][j] = Max{dp[k][j-1], sum(k+1, i)}
        for (int j = 2; j <= m; ++j) {
            // i=j-1, 可以保证0-i的元素可以被分成j段
            for (int i = j-1; i < nums.length; ++i) {
                dp[i][j] = Integer.MAX_VALUE;
                // k=j-2保证0-k的元素可以被分成j-1段
                for (int k = j-2; k < i; ++k) {
                    // 取最大值里的最小值
                    int ans = Math.max(dp[k][j-1], nums[i] - nums[k]);
                    dp[i][j] = Math.min(dp[i][j], ans);
                }
            }
        }
        return dp[nums.length-1][m];
    }
}
