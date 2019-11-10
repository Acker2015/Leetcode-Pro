package lt_1200_1299;

/**
 * 1248. Count Number of Nice Subarrays
 *
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
 * Return the number of nice sub-arrays.
 *
 *
 * Example 1:
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 *
 *
 * Example 2:
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 *
 *
 * Example 3:
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16

-----------------------------------------------------------------------------------------------------------------------
 Constraints:

 1 <= nums.length <= 50000
 1 <= nums[i] <= 10^5
 1 <= k <= nums.length
 */
public class LC_1248_c161 {
    /**
     * sliding window
     *
     * 记录window开始和结束的偶数数量，那么当前区间的子数组的个数就是左右的乘积
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        if (k <= 0) return 0;
        int ret = 0;
        int count = 0;
        int i = 0, j = 0;
        while (j < nums.length) {
            if (nums[j++] % 2 == 1) {
                count++;
            }
            if (count == k) {
                int left = 1, right = 1;
                while (i < j && nums[i]%2==0) {
                    i++;
                    left++;
                }
                while (j < nums.length && nums[j]%2 == 0) {
                    j++;
                    right++;
                }
                i++;
                count--;
                ret += left*right;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = {2,2,2,1,2,2,1,2,2,2};             // k = 2 -> ret = 16
        int[] nums2 = {1,0,0,1,0,1,0,0,0,0,0,1,1,1};    // k = 4 -> ret = 6
        LC_1248_c161 solution = new LC_1248_c161();
        System.out.println(solution.numberOfSubarrays(nums2, 4));
    }

    /**
     [1,0,0,1,0,1,0,0,0,0,0,1,1,1]
     4
     */
}
