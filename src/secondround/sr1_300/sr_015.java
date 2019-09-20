package secondround.sr1_300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sr_015 {
    /**
     * O(n^2)
     * sort + twoPointers
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> retList = new ArrayList<>();
        if (nums.length < 3) {
            return retList;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            if (i+2 >= nums.length) break;
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int j = i+1, k = nums.length-1;
            while (j < k) {
                int subsum = nums[j]+nums[k];
                if (subsum > -nums[i]) {
                    k--;
                } else if (subsum < -nums[i]) {
                    j++;
                } else {
                    retList.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                    while (j < k && nums[j]==nums[j-1]) j++;
                    while (j < k && nums[k]==nums[k+1]) k--;
                }
            }
        }
        return retList;
    }
}
