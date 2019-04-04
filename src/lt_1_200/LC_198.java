package lt_1_200;

/**
 * 198. House Robber
 *
 * A robber has 2 options: a) rob current house i; b) don't rob current house.
 * If an option "a" is selected it means she can't rob previous i-1 house but can safely proceed to the one before previous i-2 and gets all cumulative loot that follows.
 * If an option "b" is selected the robber gets all the possible loot from robbery of i-1 and all the following buildings.
 * So it boils down to calculating what is more profitable:
 *
 * robbery of current house + loot from houses before the previous
 * loot from the previous house robbery and any loot captured before that
 *
 * rob(i) = Math.max( rob(i - 2) + currentHouseValue, rob(i - 1) )
 *
 * https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
 */
public class LC_198 {
    /**
     * rob[i] = max{rob[i-1], rob[i-2] + nums[i]}
     *
     * recursive
     * 递归过程中会出现重复计算，使用mem记录中间结果
     */
    private int[] mem;
    private int rob(int[] nums, int i) {
        if (i < 0) return 0;
        if (mem[i] >= 0) return mem[i];
        mem[i] = Math.max(rob(nums, i-1), rob(nums, i-2)+nums[i]);
        return mem[i];

    }
    public int rob(int[] nums) {
        mem = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            mem[i] = -1;
        }
        return rob(nums, nums.length-1);
    }
    /**
     * 只需要用两个数记录前边两个位置的最大偷盗钱财即可，
     * 循环过程中不断更新
     */
    public int rob1(int[] nums) {
        int len = nums.length;
        if (len <= 0) return 0;
        int prev1 = 0, prev2 = 0;
        for (int i = 0; i < len; ++i) {
            int ans = Math.max(prev1+nums[i], prev2);
            prev1 = prev2;
            prev2 = ans;
        }
        return prev2;
    }
}
