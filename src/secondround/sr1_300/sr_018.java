package secondround.sr1_300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sr_018 {
    /**
     * @lc app=leetcode id=18 lang=java
     *
     * [18] 4Sum
     *
     * two-pointers
     */
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> list = new ArrayList<>();
            Arrays.sort(nums);
            if (nums.length < 4) return list;
            for (int i = 0; i < nums.length-3; ++i) {
                if (i > 0 && nums[i] == nums[i-1]) continue;
                for (int j = i+1; j < nums.length-2; ++j) {
                    if (j > i+1 && nums[j] == nums[j-1]) continue;
                    int m = j+1, n = nums.length-1;
                    while (m < n) {
                        int ans = nums[i] + nums[j] + nums[m] + nums[n];
                        if (ans > target) {
                            n--;
                        } else if (ans < target) {
                            m++;
                        } else {
                            list.add(Arrays.asList(nums[i], nums[j], nums[m++], nums[n--]));
                            while (m < n && nums[m]==nums[m-1]) m++;
                            while (m < n && nums[n]==nums[n+1]) n--;
                        }
                    }
                }
            }
            return list;
        }
    }
}
