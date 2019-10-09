package lt_1200_1299;

/**
 *
 * 1218. Longest Arithmetic Subsequence of Given Difference
 *
 * Given an integer array arr and an integer difference,
 * return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
 *
 * Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [7,5,3,1].
 *
 * 约束：
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i], difference <= 10^4
 *
 * Let dp[i] be the maximum length of a subsequence of the given difference whose last element is i.
 */
public class LC_1218_c157 {
    /**
     * DP
     *
     * dp[k]表示固定difference之后，subsequence的最大长度
     *
     * dp[k] = dp[k-difference] + 1
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence(int[] arr, int difference) {
        if (arr.length <= 0) return 0;
        int len = 100000;
        int[] dp = new int[len+1];
        int maxLen = 1;
        for (int i = 0; i < arr.length; ++i) {
            int moveValue = arr[i] + 10000;
            int last = moveValue - difference;
            dp[moveValue] = Math.max(dp[moveValue], last < 0 ? 1 : dp[last]+1);
            maxLen = Math.max(dp[moveValue], maxLen);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {1,5,7,8,5,3,4,2,1};
        LC_1218_c157 lc_1218_c157 = new LC_1218_c157();
        System.out.println(lc_1218_c157.longestSubsequence(arr, -2));

        System.out.println(lc_1218_c157.longestSubsequence(new int[]{1,2,3,4,5,7,8}, 1));
    }
}
