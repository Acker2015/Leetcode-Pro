package basic_data_structure.DP;

/**
 * 将数组分割成两部分，使得两部分和最接近
 *
 * 背包问题 - DP
 * 求得array的和sum，问题转化为：在array中选取若干个元素，使得这些元素的和<=sum/2，且是最接近sum/2的元素集合。
 * dp[i][j] 表示在前i个元素中挑选元素，使得它们的和最接近j
 *
 * dp[i][j] = Max{dp[i-1][j], dp[i-1][j-arr[i]]+arr[i]}
 *
 * https://blog.csdn.net/BrilliantEagle/article/details/39860145
 *
 */
public class DivideArrayWithTwoCommon {
}
