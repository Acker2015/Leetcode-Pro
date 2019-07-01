package lt_400_499;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * [494] Target Sum
 * solution1: BFS
 * solution2: DFS
 * solution3: DP(转化为subset)
 */
public class LC_494 {
    /**
     * Solution1
     * DP? hashMap
     * 每一轮的数字都会有两个操作
     *      1. 执行加号
     *      2. 执行减号
     * 所以一次遍历nums,每一轮都与前边的结果做以上两个操作，并记录表达式结果以及对应的组合个数
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays0(int[] nums, int S) {
        if (nums.length <= 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 1);
        // 防止nums[0]和-nums[0]相同
        map.put(-nums[0], map.getOrDefault(-nums[0], 0)+1);
        for (int i = 1; i < nums.length; ++i) {
            Map<Integer, Integer> ans = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                int key = entry.getKey()+nums[i];
                // ans.getOrDefault(key, 0) 已经构成key的组合数，entry.getValue()为本轮+nums[i]之后的组合数
                ans.put(key, ans.getOrDefault(key, 0) + entry.getValue());
                key = entry.getKey()-nums[i];
                ans.put(key, ans.getOrDefault(key, 0) + entry.getValue());
            }
            map = ans;
        }
        return map.getOrDefault(S, 0);
    }

    public int findTargetSumWays(int[] nums, int S) {
        if (nums.length <= 0) return 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int cnt = 0;
        for (int i = 0; i < nums.length; ++i) {
            int size = queue.size();
            while (size-- > 0) {
                int pk = queue.poll();
                queue.add(pk+nums[i]);
                queue.add(pk-nums[i]);
                if (i == nums.length-1) {
                    if (pk+nums[i] == S) cnt++;
                    if (pk-nums[i] == S) cnt++;
                }
            }
        }
        return cnt;
    }


    /**
     * Solution2
     * DFS
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays1(int[] nums, int S) {
        return findWays(nums, 0, S);
    }
    private int findWays(int[] nums, int idx, int S) {
        if (idx == nums.length) {
            if (S == 0) {
                return 1;
            }
            return 0;
        }
        int res = 0;
        res += findWays(nums, idx+1, S + nums[idx]);
        res += findWays(nums, idx+1, S - nums[idx]);
        return res;
    }

    /**
     * solution3
     * DP
     * 由于可以对每一个数决定是使用+或者-，那么问题可以等价于找到一个子集合全部使用+，剩下的集合使用-
     * 问题转化为subset的问题，找到正数子集合P，那么剩下的数就自动为负数子集合N
     * 那么根据什么来找到这个正数集合呢？
     *
     *                 SUM(P)-SUM(N)=target
     * SUM(P)+SUM(N) + SUM(P)-SUM(N)=target + SUM(P)+SUM(N)
     * 2*SUM(P) = target + sum
     * SUM(P) = (target+sum)/2
     * 所以找到的正数子集合的和为 sum(p), 并且target+sum为偶数
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for (int num: nums) sum+=num;
        return sum<S||(sum+S)%2>0 ? 0 : findWays_1d(nums, (sum+S)/2);
    }
    // DP 0-1背包
    // dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
    // 如果nums[0]=0的话，那么这里涉及到选与不选两种,所以dp[0]会等于2
    private int findWays(int[] nums, int target) {
        int len = nums.length;
        if (len <= 0) return 0;
        int[][] dp = new int[len][target+1];
        for (int i = 0; i < len; ++i) {
            dp[i][0] = 1;
        }
        if (nums[0] <= target) {
            dp[0][nums[0]] = 1; // 如果nums[0]=0的话，那么这里涉及到选与不选两种,所以dp[0]会等于2
        }
        for (int i = 1; i < len; ++i) {
            for (int j = 1; j <= target; ++j) {
                if (j >= nums[i]) {
                    dp[i][j] += dp[i-1][j] + dp[i-1][j-nums[i]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[len-1][target];
    }

    // 上边的状态转移，可以优化空间 dp[j] = dp[j] + dp[j-nums[i]]
    private int findWays_1d(int[] nums, int target) {
        int len = nums.length;
        if (len <= 0) return 0;
        int[] dp = new int[target+1];
        dp[0] = 1;
        if (nums[0] <= target) {
            dp[nums[0]] += 1; // 如果nums[0]=0的话，那么这里涉及到选与不选两种,所以dp[0]会等于2
        }
        for (int i = 1; i < len; ++i) {
            for (int j = target; j >= nums[i]; --j) {
                dp[j] += dp[j-nums[i]];
            }
        }
        return dp[target];
    }




    public static void main(String ...args) {
        int[] nums = {1, 1, 1, 1, 1};
        LC_494 lc_494 = new LC_494();
        System.out.println(lc_494.findTargetSumWays2(nums, 3));
    }
}
