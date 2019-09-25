package basic_data_structure.DP;

import java.util.Arrays;

/**
 * 将数组分割成两部分，使得两部分和最接近
 *
 * 背包问题 - DP
 * 求得array的和sum，问题转化为：在array中选取若干个元素，使得这些元素的和<=sum/2，且是最接近sum/2的元素集合。
 * dp[i][j] 表示在前i个元素中挑选元素，使得它们的和最接近j (即在和小于j的情况下的最大值 - 价值)
 *
 * dp[i][j] = Max{dp[i-1][j], dp[i-1][j-arr[i]]+arr[i]} - 挑或者不挑arr[i]
 *
 * init:
 * dp[i][0] = 0
 * https://blog.csdn.net/BrilliantEagle/article/details/39860145
 *
 */
public class DP_001_DivideArrayWithTwoCommon {
    public int getMaxDiff(int[] nums) {
        int len = nums.length;
        int sum = Arrays.stream(nums).sum();
        int[][] dp = new int[len][sum/2+1];
        for (int i = sum/2; i >= 0; --i) {
            if (i >= nums[0]) {
                dp[0][i] = nums[0];
            } else {
                break;
            }
        }
        for (int i = 1; i < nums.length; ++i) {
            for (int j = 1; j <= sum/2; ++j) {
                dp[i][j] = dp[i-1][j];
                if (j >= nums[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-nums[i]] + nums[i]);
                }
            }
        }
        return dp[len-1][sum/2];
    }

    public static void main(String ...args) {
        int[] nums = new int[]{1, 0, 1, 7, 2, 4};
        System.out.println(new DP_001_DivideArrayWithTwoCommon().getMaxDiff(nums));
    }

}
