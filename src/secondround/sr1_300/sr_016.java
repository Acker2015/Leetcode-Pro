package secondround.sr1_300;

import java.util.Arrays;

/**
 * Array
 * sort + two pointers
 */
public class sr_016 {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) return -1;
        Arrays.sort(nums);
        int sum = nums[0]+nums[1]+nums[2];
        for (int i = 0; i < nums.length-2; ++i) {
            int j = i+1, k = nums.length-1;
            while (j < k) {
                int ans = nums[i]+nums[j]+nums[k];
                if (Math.abs(target-ans) < Math.abs(target-sum)) {
                    sum = ans;
                }
                if (ans == target) {
                    return target;
                } else if (ans < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return sum;
    }
}
