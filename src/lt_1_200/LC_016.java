package lt_1_200;

import java.util.Arrays;
import java.util.Collections;

/**
 * 解法类似three sum
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. 
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.

	Example:
	Given array nums = [-1, 2, 1, -4], and target = 1.
	The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * @author Acker
 *
 */
public class LC_016 {
	public int threeSumClosest(int[] nums, int target) {
		if (nums.length < 3)  return 0;
	    Arrays.sort(nums);
	    int result = nums[0]+nums[1]+nums[2];
	    for (int i = 0; i < nums.length-2; ++i) {
	        int left = i+1;
	        int right = nums.length-1;
	        while (left < right) {
	            int sum = nums[i]+nums[left]+nums[right];
	            if (sum == target) {
	                return sum;
	            } else if (sum < target) {
	                left++;
	            } else {
	                right--;
	            }
	            if (Math.abs(result-target) > Math.abs(sum-target)) {
	                result = sum;
	            }
	        }
	    }
	    return result;
	}
	public static void main(String[] args) {
		int[] nums= {-1, 2, 1, -4};
		LC_016 lc_016 = new LC_016();
		System.out.println(lc_016.threeSumClosest(nums, 1));
		
	}

}
