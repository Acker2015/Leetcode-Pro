package lt_1_200;

public class LC_053 {
	/**
     * dynamic programming
     * dp[i] means the max sum for the sub array [0, i], and the max sum must contains nums[i]
     * so, infer dp[i] = max{dp[i-1]+nums[i], nums[i]}
     */
    public int maxSubArray(int[] nums) {
        if (nums.length <= 0) return 0;
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            nums[i] = Math.max(nums[i], nums[i-1]+nums[i]);
            maxSum = Math.max(maxSum, nums[i]);
        }
        return maxSum;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
