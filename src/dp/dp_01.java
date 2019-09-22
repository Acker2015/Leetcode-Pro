package dp;

/**
 * [53] Maximum Subarray
 *
 * easy
 *
 * 以固定索引为结束点
 */
public class dp_01 {
    /**
     * dynamic programming
     * dp[i] means the max sum for the sub array [0, i], and the max sum must contains nums[i]
     * so, infer
     *
     * dp[i] = max{dp[i-1]+nums[i], nums[i]}
     */
    public int maxSubArray(int[] nums) {
        if (nums.length <= 0) return 0;
        int[] dp = new int[nums.length];
        int maxSum = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            maxSum = Math.max(dp[i], maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new dp_01().maxSubArray(arr));
    }
}
