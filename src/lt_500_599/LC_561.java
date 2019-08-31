package lt_500_599;

import java.util.Arrays;

/**
 * @lc app=leetcode id=561 lang=java
 *
 * [561] Array Partition I
 */
public class LC_561 {

    class Solution {
        /**
         * greedy
         * 配对的时候，减少大的num和小的num一起配对。贪心
         * 优先将值较小的值放在一起配对
         */
        public int arrayPairSum(int[] nums) {
            Arrays.sort(nums);
            int sum = 0;
            for (int i = 0; i < nums.length; i += 2) {
                sum += nums[i];
            }
            return sum;
        }
    }
}
