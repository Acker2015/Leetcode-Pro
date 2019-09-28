package lt_1_200;

/**
 * [152] Maximum Product Subarray
 *
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * DP
 */
public class LC_152 {
    /**
     * 由于可能出现负数乘负数得到最大值的情况，所以这里同时记录最大最小值
     * dp1[i] 表示包含i的最小的product
     * dp2[i] 表示包含i的最大的product
     * 由于只记录前一个状态，这里完全可以使用两个变量来代替
     */
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int preMax = nums[0];
        int preMin = nums[0];
        int maxVal = nums[0];
        for (int i = 1; i < len; ++i) {
            int c1 = preMax*nums[i];
            int c2 = preMin*nums[i];
            preMax = Math.max(nums[i], Math.max(c1, c2));
            preMin = Math.min(nums[i], Math.min(c1, c2));
            maxVal = Math.max(maxVal, preMax);
        }
        return maxVal;
    }
}
