package lt_200_299;

/**
 * [213] House Robber II
 *
 * 与198. House Robber相似
 */
public class LC_213 {
    /**
     *
     *
     * @param nums
     * @param from
     * @param to
     * @return
     */
    private int rob(int[] nums, int from, int to) {
        int prev2 = 0, prev1 = 0;
        for (int i = from; i <= to; ++i) {
            int tmp = Math.max(prev2+nums[i], prev1);
            prev2 = prev1;
            prev1 = tmp;
        }
        return prev1;
    }
    /**
     * think about the first num, have two choices
     * a) select the first num
     * b) don't select the first num
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len <= 0) return 0;
        if (nums.length <= 1) return nums[0];
        int ans1 = rob(nums, 0, len - 2);
        int ans2 = rob(nums, 1, len - 1);
        return Math.max(ans1, ans2);
    }

    public static void main(String ...args) {
        LC_213 lc_213 = new LC_213();
        int[] nums = {2,1,1,1};
        System.out.println(lc_213.rob(nums));
    }
}
