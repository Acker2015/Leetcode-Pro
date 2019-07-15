package basic_data_structure.DP;

import java.util.Arrays;

/**
 * 有一个无序，元素个数为2N的正整数数组，要求：如何能把这个数组分割为元素个数为N的两个数组，并使者两个子数组的和最接近？
 *
 * 这个题目与之前不一样的点就是找到的子数组的长度必须是N，之前的问题是任意长度。这里使用DP来解决问题
 * dp[i][j] 表示是否可以找到i个数，使得它们的和是v（注意这里的是i个数，而不是代表数组索引）
 *
 * dp[i][j] = dp[i-1][j-nums[k]]   {k in [0, 2*N) && j >= nums[k]}
 *
 * 同时注意这里对数组中的每一个k，在计算dp[i][j]中的i计数的时候，需要从高到低进行遍历
 *
 * 最后结果是如果DP[N][sum]有为true的sum的最大值
 *
 */
public class DP_002_DivideArray02 {
    public int divide(int[] nums) {
        int len = nums.length;
        int N = len / 2;
        int sum = Arrays.stream(nums).sum();
        boolean[][] dp = new boolean[N+1][sum/2+1];
        dp[0][0] = true;

        for (int k = 0; k < len; ++k) {
            // 从高到低遍历，防止nums[i]在比较小的i和比较大的i中被多次用到
            // 比如 i=2的时候，得到dp[2][5]=dp[1][5-nums[k]]=true, 此时dp[2][5]=true, 使用到了nums[k]
            // 当i=3的时候，dp[3][nj] = dp[2][nj-nums[k]], 如果nj-nums[k]=5, 那么说明nums[k]被重复选择
            // 所以这里选择i从高到低遍历，避免重复选择
            for (int i = Math.min(N, k+1); i >= 1 ; --i) {
                for (int j = 1; j <= sum/2; ++j) {
                    if (nums[k] <= j && dp[i-1][j-nums[k]]) {
                        dp[i][j] = true;
                    }
                }
            }
        }
        for (int s = sum/2; s >= 0; --s) {
            if (dp[N][s]) {
                return s;
            }
        }
        return 0;
    }
}
