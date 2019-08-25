package lt_400_499;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [473] Matchsticks to Square
 * DFS
 * Solution1: DFS-回溯 + memorization
 * Solution2: DFS尝试将每个索引处的值加到对应的边长上(排序，优先选择大边，这样如果有异常情况会优先退出)
 */
public class LC_473 {
    public static class Solution1 {
        Map<Integer, Boolean> map;
        /**
         * dfs
         * @param nums      nums输入数组
         * @param vis       访问标志
         * @param left      未使用的num个数
         * @param pre       当前的边长
         * @param sideLen   边长
         * @return
         */
        public boolean dfs(int[] nums, boolean[] vis, int left, int pre, int sideLen, int key) {
            if (left <= 0) {
                return pre == 0;
            }
            if (map.containsKey(key)) {
                return map.get(key);
            }
            for (int i = 0; i < nums.length; ++i) {
                if (pre + nums[i] > sideLen) break; // 有序的，后续肯定全部不满足
                if (vis[i]) continue;
                vis[i] = true;
                if (dfs(nums, vis, left-1, (pre+nums[i])%sideLen, sideLen, key|(1<<i))) {
                    return true;
                }
                map.put(key|(1<<i), false);
                vis[i] = false;
            }
            return false;
        }

        /**
         * Depth-First-Search
         * 1. The length sum of the given matchsticks is in the range of 0 to 10^9.
         * 2. The length of the given matchstick array will not exceed 15.
         * @param nums
         * @return
         */
        public boolean makesquare(int[] nums) {
            int len = nums.length;
            if (len < 4) return false;
            Arrays.sort(nums);
            int sum = 0;
            for (int num: nums) {
                sum += num;
            }
            if (sum%4 != 0 || sum/4 <= 0 || nums[len-1] > sum/4) return false;
            boolean[] vis = new boolean[len];
            map = new HashMap<>();
            return dfs(nums, vis, len, 0, sum/4, 0);
        }
    }

    public static class Solution2 {
        /**
         * 每次选择对于nums中的index索引的num，都放到四个边长中的其中一个，这样不同对nums中每一个数进行回溯
         * 复杂度更低
         * @param nums
         * @param sums
         * @param index
         * @param target
         * @return
         */
        private boolean dfs(int[] nums, int[] sums, int index, int target) {
            if (index < 0) {
                if (sums[0]==target && sums[1]==target && sums[2]==target) {
                    return true;
                }
                return false;
            }
            for (int i = 0; i < 4; ++i) {
                if (sums[i] + nums[index] > target) continue;
                sums[i] += nums[index];
                if (dfs(nums, sums, index-1, target)) {
                    return true;
                }
                sums[i] -= nums[index];
            }
            return false;
        }

        /**
         * DFS
         * why sort?
         *  Sorting the input array DESC will make the DFS process run much faster.
         *  Reason behind this is we always try to put the next matchstick in the first subset.
         *  If there is no solution, trying a longer matchstick first will get to negative conclusion earlier.
         * @param nums
         * @return
         */
        public boolean makesquare(int[] nums) {
            int len = nums.length;
            if (len < 4) return false;
            int sum = 0;
            for (int num: nums) sum += num;
            Arrays.sort(nums);
            if (sum % 4 != 0 || sum/4 <= 0 || sum/4 > nums[len-1]) return false;
            return dfs(nums, new int[4], len-1, sum/4);
        }
    }
    public static void main(String ...args) {
        Solution2 solution = new Solution2();
        int[] nums = {1,1,2,2,2};
        System.out.println(solution.makesquare(nums));
    }
}
