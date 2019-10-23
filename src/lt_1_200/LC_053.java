package lt_1_200;

public class LC_053 {
    public static class Solution1 {
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
    }

    /**
     * two pointers
     * 滑动窗口
     * 记录窗口和sum，只要sum小于0， 那么就整体进行窗口移动
     */
    public static class Solution2 {
        public int maxSubArray(int[] nums) {
            if (nums.length <= 0) return 0;
            int i = 0, j = 0, sum = 0, largestSum = Integer.MIN_VALUE;
            while (j < nums.length) {
                sum += nums[j++];
                if (sum > largestSum) {
                    largestSum = sum;
                }
                if (sum <= 0) {
                    i = j;
                    sum = 0;
                }
            }
            return largestSum;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
