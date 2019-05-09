package lt_200_299;

/**
 * [209] Minimum Size Subarray Sum
 */
public class LC_209 {
    /**
     * Solution1
     * two pointers
     */
    public int minSubArrayLen1(int s, int[] nums) {
        int[] preSum = new int[nums.length+1];
        preSum[0] = 0;
        for (int i = 0; i < nums.length; ++i) {
            preSum[i+1] = preSum[i] + nums[i];
        }
        int i = 0, j = 1, minSub = Integer.MAX_VALUE;
        while (j < preSum.length) {
            if (preSum[j] - preSum[i] >= s) {
                minSub = Math.min(minSub, j - i);
                i++;
            }else {
                j++;
            }
        }
        return minSub==Integer.MAX_VALUE ? 0 : minSub;
    }

    /**
     * Solution2
     * binary-search
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length <= 0) return 0;
        int[] preSum = new int[nums.length+1];
        preSum[0] = 0;
        for (int i = 0; i < nums.length; ++i) {
            preSum[i+1] = preSum[i] + nums[i];
        }
        int minSub = Integer.MAX_VALUE;
        for (int i = 0; i < preSum.length; ++i) {
            int ans = binarySearch(s, preSum, i);
            if (ans > 0) {
                minSub = Math.min(minSub, ans-i);
            }
        }
        return Integer.MAX_VALUE==minSub ? 0 : minSub;
    }
    private int binarySearch(int s, int[] preSum, int i) {
        int l = i, r = preSum.length;
        while (l < r) {
            int mid = l + (r-l)/2;
            if (preSum[mid] - preSum[i] < s) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r < preSum.length ? r : -1;
    }
}
