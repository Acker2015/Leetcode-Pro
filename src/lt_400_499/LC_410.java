package lt_400_499;

import java.util.Arrays;

/**
 * [410] Split Array Largest Sum
 * solution1 DP
 * solution2 binary-search 想不出来呀-但是有觉得不是很难
 */
public class LC_410 {
    /**
     * DP
     * time O(m*n*n) n为数组长度
     *
     * 从2段-m段来动态的看待这个问题
     * 比如前i个元素被分成2段，可以由前一个分成一段的subarr来作为中间状态推导
     * 例如 [0,...k, ...i]
     *      0-i被分成两段可以由{0-k 一段}和{k-i 一段}组成
     *      0-i被分成三段可以由{0-k 两段}和{k-i 一段}组成
     *      ...
     *      0-i被分成m段可以由{0-k m-1段}和{k-i 一段}组成
     *
     * dp[i][j] 表示0-i个元素，分成j段的每一个分段和最大值的最小值
     * 在0-i中寻找一个点k作为中间状态dp[k][j-1]，表示0-k被分成j-1段，所以此时最大值表示为 Max{dp[k][j-1], sum(k+1,i)}
     * minmax
     * dp[i][j] = min{dp[i][j], max(dp[k][j-1], sum(k+1,i))}
     */
    public int splitArray1(int[] nums, int m) {
        // presum
        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i-1];
        }
        int sum = nums[nums.length-1];
        if (m == 1) return sum;
        int[][] dp = new int[nums.length][m+1];
        // 初始化状态，默认1段，取其前缀合即可
        for (int i = 0; i < nums.length; ++i) {
            dp[i][1] = nums[i];
        }
        // dp[i][j] = Max{dp[k][j-1], sum(k+1, i)}
        for (int j = 2; j <= m; ++j) {
            // i=j-1, 可以保证0-i的元素可以被分成j段
            for (int i = j-1; i < nums.length; ++i) {
                dp[i][j] = Integer.MAX_VALUE;
                // k=j-2保证0-k的元素可以被分成j-1段
                for (int k = j-2; k < i; ++k) {
                    // 取最大值里的最小值
                    int ans = Math.max(dp[k][j-1], nums[i] - nums[k]);
                    dp[i][j] = Math.min(dp[i][j], ans);
                }
            }
        }
        return dp[nums.length-1][m];
    }

    /**
     * solution2
     * binary-search
     * 由题意可知，被分割的子数组的最大值在 [max(nums), sum(nums)], 因为子数组至少包含一个元素
     * 那么题意可以转化为在[max(nums), sum(nums)]中找到一个最小的值，满足nums可以被分割为m段
     * 所以可以二分最大子数组的和subSum，根据subSum可以将nums分成k段
     * 1. k > m, 说明subSum过小
     * 2. k < m, 说明subSum过大
     * 3. k==m, 说明此时subSum符合题意，但是不一定是最小的subSum
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        if (nums.length <= 0) return -1;
        long left = Long.MIN_VALUE, right = 0;
        for (int num : nums) {
            right += num;
            left = Math.max(left, num);
        }
        // long aim = right;
        while (left < right) {
            long preSum = left + (right-left)/2;
            int k = splitByPreSum(nums, preSum);
            if (k > m) {
                left = preSum + 1;
            } else if (k < m) {
                right = preSum-1;
            } else {
                //aim = Math.min(aim, preSum);
                right = preSum;
            }
        }
        return (int)left;
    }
    private int splitByPreSum(int[] nums, long preSum) {
        int cnt = 1;
        long tempSum = 0;
        for (int num: nums) {
            if (tempSum + num <= preSum) {
                tempSum += num;
            } else {
                cnt++;
                tempSum = num;
            }
        }
        return cnt;
    }

    public static void main(String ...args) {
        int[] nums = {7,2,5,10,8};
        System.out.println(new LC_410().splitArray(nums,2));
    }
}
