package lt_600_699;

import java.util.Arrays;

/**
 * [698] Partition to K Equal Sum Subsets
 * DFS
 */
public class LC_698 {
    /**
     * Solution1
     * 分割成k分，那么就一份一份填满即可
     * divide into k subsets, so just to fill each subset, for each num in nums, it would be put into a subset of k.
     * just dfs
     */
    public static class Solution1 {
        private boolean dfs(int[] nums, int[] subsets, int index, int sideLen) {
            if (index < 0) {
                for (int subSum: subsets) {
                    if (subSum != sideLen) {
                        return false;
                    }
                }
                return true;
            }
            for (int i = 0; i < subsets.length; ++i) {
                if (subsets[i] + nums[index] > sideLen) continue;
                subsets[i] += nums[index];
                if (dfs(nums, subsets, index-1, sideLen)) {
                    return true;
                }
                subsets[i] -= nums[index];
            }
            return false;
        }
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int len = nums.length;
            int sum = 0;
            for (int num: nums) sum += num;
            if (k > len || sum%k != 0) return false;
            Arrays.sort(nums);
            return dfs(nums, new int[k], len-1, sum/k);
        }
    }

    /**
     * Solution2
     * 对nums中的每一个元素，逐个挑选进入一个subset
     * each loop, choose num to reach to target from nums
     * 效率较高
     */
    public static class Solution2 {
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int len = nums.length;
            int sum = 0;
            for (int num: nums) sum += num;
            if (sum%k != 0 || sum <= 0) return false;
            return choose(nums, new boolean[len], sum/k, k, 0, 0);
        }

        private boolean choose(int[] nums, boolean[] vis, int target, int k, int index, int preSum) {
            if (preSum == target) {
                return choose(nums, vis, target, k-1, 0, 0);
            }
            if (k <= 0) {
                return true;
            }
            for (int i = index; i < nums.length; ++i) {
                if (preSum+nums[i] > target || vis[i]) continue;
                vis[i] = true;
                if (choose(nums, vis, target, k, i+1, preSum+nums[i])) {
                    return true;
                }
                vis[i] = false;
            }
            return false;
        }

    }




    public static void main(String ...args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        LC_698 lc_698 = new LC_698();
        System.out.println(new LC_698.Solution2().canPartitionKSubsets(nums, 4));
    }
}
