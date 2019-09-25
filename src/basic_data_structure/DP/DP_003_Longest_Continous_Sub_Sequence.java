package basic_data_structure.DP;

/**
 * 求解数组的最大连续子序列的和
 */
public class DP_003_Longest_Continous_Sub_Sequence {

    /**
     * DP
     * dp[i]表示以dp[i]结束的最大连续子序列的和
     *
     * dp[i] = Max{arr[i], dp[i-1]+arr[i]}
     * @param arr
     * @return
     */
    public int LCSS(int[] arr) {
        int len = arr.length;
        int[] dp = new int[len];
        dp[0] = arr[0];
        int maxSum = dp[0];
        for (int i = 1; i < arr.length; ++i) {
            dp[i] = Math.max(arr[i], dp[i-1]+arr[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }


    /**
     * two-pointers
     *
     * let sum variable is the sum of [i, j)
     * so when sum<=0 or arr[i] <= 0, i would go to next position.
     * @param arr
     * @return
     */
    public int twoPointers(int[] arr) {
        int i = 0, j = 0;
        int sum = 0, maxSum = arr[0];
        while (j < arr.length) {
            sum += arr[j++];
            while (i < j && (arr[i] <= 0 || sum <= 0)) {
                sum -= arr[i++];
            }
            if (i < j) {
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 11,-4,13,-5,-2};
        int[] arr1 = {-2, 11, 1, -13, 14, -5, -2};
        DP_003_Longest_Continous_Sub_Sequence solution = new DP_003_Longest_Continous_Sub_Sequence();

        System.out.println(solution.LCSS(arr1));
        System.out.println(solution.twoPointers(arr1));
    }
}
