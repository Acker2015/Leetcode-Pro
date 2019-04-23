package lt_600_699;

/**
 * [674] Longest Continuous Increasing Subsequence
 * 最大连续上升子序列 (索引连续)
 */
public class LC_674 {
    /**
     * [674] Longest Continuous Increasing Subsequence
     * dynamic programming
     *
     * dp[i] means the max length of continuous increasing subsequence with the ending num nums[i]
     * dp[i] = dp[i-1]+1 (nums[i-1] < nums[i])
     * dp[i] = 1 (nums[i-1] >= nums[i])
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length <= 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxContinuousQ = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i-1]) {
                dp[i] = dp[i-1]+1;
                maxContinuousQ = Math.max(maxContinuousQ, dp[i]);
            } else {
                dp[i] = 1;
            }
        }
        return maxContinuousQ;
    }
}
