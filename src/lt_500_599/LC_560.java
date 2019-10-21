package lt_500_599;


import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 */
public class LC_560 {
    // brute-force O(n^2)
    static class Solution1 {
        // brute
        public int subarraySum(int[] nums, int k) {
            int len = nums.length;
            int[] preSum = new int[len+1];
            for (int i = 0; i < len; ++i) {
                preSum[i+1] = preSum[i]+nums[i];
            }
            int ans = 0;
            for (int i = 1; i <= len; ++i) {
                for (int j = 0; j < i; ++j) {
                    if (preSum[i]-preSum[j] == k) {
                        ans+=1;
                    }
                }
            }
            return ans;
        }
    }

    /**
     * solution2
     *
     * use HashMap to record the num of same pre-sum.
     * so the method is like two-sum problem.
     */
    static class Solution2 {
        public int subarraySum(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            int sum = 0;
            int ans = 0;
            for (int num: nums) {
                sum += num;
                // sum[i]-sum[j]=k
                ans += map.getOrDefault(sum-k, 0);
                map.put(sum, map.getOrDefault(sum, 0)+1);
            }
            return ans;
        }
    }
}
