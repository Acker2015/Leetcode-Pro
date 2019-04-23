package lt_600_699;


public class LC_673 {
    /**
     * [673] Number of Longest Increasing Subsequence
     *
     * 每个位置都记录两组数据
     *
     * 1. dp -> LCI的长度
     * 2. ans -> 该长度有多少种组成
     *
     * dp[i]表示索引i处最大上升子序列的长度
     * dp[i] = dp[j]+1 (0<=j<i, nums[j] < nums[i])
     * dp[i] = 1 (0<=j<i, nums[j] >= nums[i])
     *
     * ans[i]表示索引i处的最大上升子序列长度有多少种组成，显然可以根据ans[j]表示
     * 如果dp[i]更新为更大的dp[j]+1, 则ans[i]=ans[j]
     * 如果遇到dp[i]==dp[j]+1，则说明组成增加， ans[i]+=ans[j]
     *
     * 结果就是dp中最大长度的组成个数之和
     */
    public int findNumberOfLIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int[] dp = new int[nums.length];
        int[] ans = new int[nums.length];
        dp[0] = ans[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = ans[i] = 1;
            int tmp = 0;
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        maxLen = Math.max(maxLen, dp[i]);
                        tmp = ans[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        tmp += ans[j];
                    }
                }
            }
            ans[i] = Math.max(ans[i], tmp);
        }
        // 结果就是dp中最大长度的组成个数之和
        int sum = 0;
        for (int i = 0; i < dp.length; ++i) {
            if (dp[i] == maxLen) {
                sum += ans[i];
            }
        }
        return sum;
    }

    public static void main(String ...args) {
        int[] nums = {1,2, 3, 5, 4};
        LC_673 lc_673 = new LC_673();
        System.out.println(lc_673.findNumberOfLIS(nums));
    }
}
