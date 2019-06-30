package lt_400_499;

import java.util.HashMap;
import java.util.Map;

/**
 * [416] Partition Equal Subset Sum
 *
 * Solution1: DP (0-1背包)
 * Solution2: DFS+HashMap
 */
public class LC_416 {
    private int getSum(int[] nums) {
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        return sum;
    }

    /**
     * Solution1 DP
     * 二维DP
     * Actually, this is a 0/1 knapsack problem, for each number, we can pick it or not.
     * Let us assume dp[i][j] means whether the specific sum j can be gotten from the first i numbers.
     * If we can pick such a series of numbers from 0-i whose sum is j, dp[i][j] is true, otherwise it is false
     *
     * For each number, if we don't pick it, dp[i][j] = dp[i-1][j], which means if the first i-1 elements has made it to j, dp[i][j] would also make it to j (we can just ignore nums[i]).
     * If we pick nums[i]. dp[i][j] = dp[i-1][j-nums[i]], which represents that j is composed of the current value nums[i] and the remaining composed of other previous numbers.
     * Thus, the transition function is dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
     *
     * dp[i][j]表示前i各元素选择是否能够等于j
     * 分为选择nums[i]和不选择nums[i]
     *
     * dp[i][j] = dp[i-1][j-nums[i]] || dp[i-1][j];
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = getSum(nums);
        int len = nums.length;
        if (len <= 0 || sum % 2 == 1) return false;
        int target = sum/2;
        // dp[i][j]表示前i各元素选择是否能够等于j
        // dp[i][j] = dp[i][j-nums[i]] || dp[i-1][j];
        boolean [][]dp = new boolean[len][target+1];
        for (int i = 0; i < len; ++i) {
            dp[i][0] = true;
        }
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        for (int i =1; i < len; ++i) {
            for (int j = 1; j <= target; ++j) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i-1][j-nums[i]] || dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[len-1][target];
    }

    /**
     *  Solution1 DP
     *  二维变一维DP
     *
     * 根据上边的要求，降低dp的space空间
     * beats 50.77 %
     * @param nums
     * @return
     */
    public boolean canPartition_1(int[] nums) {
        int sum = getSum(nums);
        int len = nums.length;
        if (len <= 1 || sum % 2 == 1) return false;
        int target = sum/2;
        // dp[i][j]表示前i各元素选择是否能够等于j
        //dp[i][j] = dp[i][j-nums[i]] || dp[i-1][j];
        // -> 1d -> dp[j] = dp[j-nums[i]] || dp[j]
        boolean []dp = new boolean[target+1];
        dp[0] = true;
        dp[nums[0]] = true;
        for (int i =1; i < len; ++i) {
            for (int j = target; j >= nums[i]; --j) {
                dp[j] = dp[j - nums[i]] || dp[j];
            }
        }
        return dp[target];
    }

    /**
     * Solution2
     * DFS + HashMap
     * Your runtime beats 5.02 % of java submissions
     * @param nums
     * @return
     */
    public boolean canPartition1(int[] nums) {
        int sum = getSum(nums);
        int len = nums.length;
        if (len <= 0 || sum % 2 == 1) return false;
        int target = sum/2;
        return dfs(nums, 0, target, new HashMap<>());
    }
    // 每个位置有选和不选两种选择
    private boolean dfs(int[] nums, int idx, int target, Map<String, Boolean> map) {
        if (target == 0) return true;
        if (target < 0 || idx >= nums.length) return false;
        String key = idx + "_" + target;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (dfs(nums, idx+1, target, map) || dfs(nums, idx+1, target-nums[idx], map)) {
            return true;
        }
        map.put(key, false);
        return false;
    }

    public static void main(String ...arts) {
        int[] nums = {3,3,3,4,5};
        LC_416 lc_416 = new LC_416();
        System.out.println(lc_416.canPartition(nums));
    }
}
