package lt_300_399;

import java.util.List;

/**
 *
 nums1 = [3, 4, 6, 5]
 nums2 = [9, 1, 2, 5, 8, 3]
 k = 5
 Output:
 [9, 8, 6, 5, 3]
 */
public class LC_321 {
    /**
     * https://leetcode.com/problems/create-maximum-number/discuss/77287/C%2B%2B-16ms-FASTEST-beats-97.
     * To create max number of length k from two arrays, you need to create max number of length i from array one and max number of length k-i from array two,
        then combine them together. After trying all possible i, you will get the max number created from two arrays.
     * Optimization
     * 1. Suppose nums1 = [3, 4, 6, 5], nums2 = [9, 1, 2, 5, 8, 3],
     *    the maximum number you can create from nums1 is [6, 5] with length 2.
     *    For nums2, it's [9, 8, 3] with length 3.
     *    Merging the two sequence, we have [9, 8, 6, 5, 3], which is the max number we can create from two arrays without length constraint.
     *    If the required length k<=5, we can simply trim the result to required length from front. For instance, if k=3, then [9, 8, 6] is the result.
     *
     * 2. Suppose we need to create max number with length 2 from num = [4, 5, 3, 2, 1, 6, 0, 8]. The simple way is to use a stack,
     *    first we push 4 and have stack [4], then comes 5 > 4, we pop 4 and push 5, stack becomes [5], 3 < 5, we push 3, stack becomes [5, 3].
     *    Now we have the required length 2, but we need to keep going through the array in case a larger number comes,
     *    2 < 3, we discard it instead of pushing it because the stack already grows to required size 2. 1 < 3, we discard it.
     *    6 > 3, we pop 3, since 6 > 5 and there are still elements left, we can continue to pop 5 and push 6, the stack becomes [6], since 0 < 6, we push 0, the stack becomes [6, 0], the stack grows to required length again.
     *    Since 8 > 0, we pop 0, although 8 > 6, we can't continue to pop 6 since there is only one number, which is 8, left, if we pop 6 and push 8, we can't get to length 2, so we push 8 directly, the stack becomes [6, 8].
     *
     * 3. In the basic idea, we mentioned trying all possible length i. If we create max number for different i from scratch each time, that would be a waste of time.
     *    Suppose num = [4, 9, 3, 2, 1, 8, 7, 6], we need to create max number with length from 1 to 8.
     *    For i==8, result is the original array.
     *    For i==7, we need to drop 1 number from array, since 9 > 4, we drop 4, the result is [9, 3, 2, 1, 8, 7, 6].
     *    For i==6, we need to drop 1 more number, 3 < 9, skip, 2 < 3, skip, 1 < 2, skip, 8 > 1, we drop 1, the result is [9, 3, 2, 8, 7, 6].
     *    For i==5, we need to drop 1 more, but this time, we needn't check from beginning, during last scan, we already know [9, 3, 2] is monotonically non-increasing, so we check 8 directly, since 8 > 2, we drop 2, the result is [9, 3, 8, 7, 6].
     *    For i==4, we start with 8, 8 > 3, we drop 3, the result is [9, 8, 7, 6].
     *    For i==3, we start with 8, 8 < 9, skip, 7 < 8, skip, 6 < 7, skip, by now, we've got maximum number we can create from num without length constraint. So from now on, we can drop a number from the end each time. The result is [9, 8, 7],
     *    For i==2, we drop last number 7 and have [9, 8].
     *    For i==1, we drop last number 8 and have [9].
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        return null;
    }

    /**
     * Solution2
     * DP 时间复杂度太高
     * O(len1*len2*k)
     * dp[k][i][j] 表示在nums1前i个和nums2前j个元素选择k个元素能够得到的最大值 (为了兼容0，这里的i,j正常从1开始)
     * dp[k][i][j] = Max{dp[k][i-1][j], dp[k][i][j-1], dp[k-1][i-1][j]+nums[i], dp[k-1][i][j-1]+nums[j]}
     *
     * 时间f复杂度太高，TLE
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber2(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        String[][][] dp = new String[k+1][len1+1][len2+1];
        for (int i = 0; i <= len1; ++i) {
            for (int j = 0; j <= len2; ++j) {
                for (int p = 0; p <= k; ++p) {
                    dp[p][i][j] = "";
                }
            }
        }
        String ret = "";
        for (int p = 1; p <= k; ++p) {
            for (int i = 0; i <= len1; ++i) {
                for (int j = 0; j <= len2; ++j) {
                    int ans = i+j;
                    if (ans < p) continue;
                    String start = i >= 1 ? dp[p-1][i-1][j]+nums1[i-1] : dp[p-1][i][j-1];
                    String cmp;
                    if (i >= 1) {
                        cmp = dp[p-1][i-1][j]+nums1[i-1];
                        start = start.compareTo(cmp) < 0 ? cmp : start;
                        if (ans > p) {
                            start = start.compareTo(dp[p][i-1][j]) < 0 ? dp[p][i-1][j] : start;
                        }
                    }
                    if (j >= 1) {
                        cmp = dp[p-1][i][j-1]+nums2[j-1];
                        start = start.compareTo(cmp) < 0 ? cmp : start;
                        if (ans > p) {
                            start = start.compareTo(dp[p][i][j-1]) < 0 ? dp[p][i][j-1] : start;
                        }
                    }

                    dp[p][i][j] = start;
                    if (p == k) {
                        ret = ret.compareTo(dp[p][i][j]) < 0 ? dp[p][i][j] : ret;
                    }
                }
            }
        }
        System.out.println(ret);
        char[] chrs = ret.toCharArray();
        int[] retArr = new int[k];
        for (int i = 0; i < k; ++i) {
            retArr[i] = chrs[i]-'0';
        }
        return retArr;
    }
    public static void main(String ...args) {
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        LC_321 lc_321 = new LC_321();
        lc_321.maxNumber(nums1, nums2, 5);
    }
}
