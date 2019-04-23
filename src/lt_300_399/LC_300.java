package lt_300_399;

/**
 * 300. Longest Increasing Subsequence
 */
public class LC_300 {

    // 找第一个大于等于val的位置
    private int binarySearch(int[] nums, int left, int right, int val) {
        int l = left, r = right;
        while (l < r) {
            int m = l + (r-l)/2;
            if (nums[m] == val) {
                return m;
            } else if (nums[m] < val) {
                l = m+1;
            } else {
                r = m;
            }
        }
        return l;
    }

    /**
     * DP + 二分
     * 通过DP来记录之前的最长递增数列即可
     * 当遍历到新节点val，二分查找第一个大于等于val的节点位置idx
     * 如果idx < len_of_dp, 那么将val替换到idx位置
     * 如果idx = len_of_dp, 说明val比dp现存的任一值都要大，那么val插入到尾部，len_of_dp增1
     *
     * ex: [10,9,2,5,3,7,101,18]
     * dp-> [10]
     * dp-> [9]
     * dp-> [2]
     * dp-> [2, 5]
     * dp-> [2, 3]
     * dp-> [2, 3, 7]
     * dp-> [2, 3, 7, 101]
     * dp-> [2, 3, 7, 18]
     * length of LIS equals with len of the final dp;
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int len = 1;
        for (int i = 1; i < nums.length; ++i) {
            int idx = binarySearch(nums, 0, len, nums[i]);
            nums[idx] = nums[i];
            if (idx == len) {
                len++;
            }
        }
        return len;
    }


    /**
     * solution 直接DP
     * dp[i]表示以i结尾的最长上升子序列的长度
     * O(n^2) DP
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        if (nums.length <= 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int lenOfLIS = 1;
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    lenOfLIS = Math.max(dp[i], lenOfLIS);
                }
            }
        }
        return lenOfLIS;
    }
}
