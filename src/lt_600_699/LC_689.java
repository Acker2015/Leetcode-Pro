package lt_600_699;


import java.util.Arrays;

/**
 * [689] Maximum Sum of 3 Non-Overlapping Subarrays
 *
 * DP的思想
 * 枚举中间subArray可选的合法位置
 * 使用两个DP数组帮助快速找到左边subArray和右边subArray
 * 1. previousRecord[i]记录[0, i]的 SUM和最大 的长度为k的subArray的索引
 * 2. backRecord[i]记录的是从i到结尾位置 SUM和最大 的长度为k的subArray的索引
 */
public class LC_689 {
    /**
     * The question asks for three non-overlapping intervals with maximum sum of all 3 intervals.
     * If the middle interval is [i, i+k-1], where k <= i <= n-2k, the left interval has to be in subrange [0, i-1], and the right interval is from subrange [i+k, n-1].
     * 枚举中间的subArray位置i -> [k, n-k)
     * 然后找到左边最大subArray位置和右边最大subArray位置就可以了
     * 可以使用前缀数组来帮助两边subArray位置的查找
     *
     * previousRecord[i]记录的是从0-i位置subArray的最大和的索引
     * backRecord[i]记录的是从i到结尾位置subArray的最大和的索引
     * O(n)
     *
     * 注意其中索引位置的变化
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i-1];
        }
        int[] segment = new int[len - k + 1];
        for (int i = 0; i <= len - k; ++i) {
            segment[i] = nums[i + k - 1] - (i > 0 ? nums[i-1] : 0);
        }
        // dp辅助数组previousRecord，previousRecord[i]表示0-i最大的子数组和的索引位置
        int[] previousRecord = new int[len - k + 1];
        previousRecord[0] = 0;
        for (int i = 1; i < previousRecord.length; ++i) {
            int preMaxIdx = previousRecord[i-1];
            if (segment[i] > segment[preMaxIdx]) {
                previousRecord[i] = i;
            } else {
                previousRecord[i] = preMaxIdx;
            }
        }
        // dp辅助数组backRecord，backRecord[i]表示i到尾部最大的子数组和的索引位置
        int[] backRecord = new int[len-k+1];
        backRecord[len-k] = len-k;
        for (int i = len-k-1; i >= 0; --i) {
            int backMaxIdx = backRecord[i+1];
            if (segment[i] >= segment[backMaxIdx]) {
                backRecord[i] = i;
            } else {
                backRecord[i] = backMaxIdx;
            }
        }
        int n = len - k + 1;
        /*
        n=len-k+1
        1. 中间集合遍历区间为 i -> [k, n-k)
        2. 左边集合遍历区间为 [0, i-k]
        3. 右边集合遍历区间为 [i+k, 边界)
         */
        int maxSum = 0;
        int[] ans = new int[3];
        for (int i = k; i < n-k; ++i) {
            // middle subarray -> [i, n-k)
            int li = previousRecord[i-k];
            int ri = backRecord[i+k];
            if (segment[i] + segment[li] + segment[ri] > maxSum) {
                maxSum = segment[i] + segment[li] + segment[ri];
                ans[0] = li; ans[1] = i; ans[2] = ri;
            }
        }
        return ans;
    }

    public static void main(String ...args) {
        //int[] nums = {1,5,10,2,1,6,2,8,3,10,2,3,1};
        //int[] nums = {1,2,1,2,6,7,5,1};
        int[] nums = {7,13,20,19,19,2,10,1,1,19};
        Arrays.stream(new LC_689().maxSumOfThreeSubarrays(nums, 3)).forEach(System.out::println);
    }
}
