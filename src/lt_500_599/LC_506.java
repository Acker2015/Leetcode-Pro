package lt_500_599;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [506] Relative Ranks
 */
public class LC_506 {
    /**
     * sort
     */
    class Solution {
        private String[] medals = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        public String[] findRelativeRanks(int[] nums) {
            int[] copy = Arrays.copyOf(nums, nums.length);
            Arrays.sort(copy);
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; ++i) {
                map.put(nums[i], i);
            }
            String[] ret = new String[nums.length];
            int rank = 0;
            for (int i = copy.length-1; i >= 0; --i) {
                ret[map.get(copy[i])] = rank < 3 ? medals[rank]:String.valueOf(rank+1);
                rank++;
            }
            return ret;
        }
    }
}
