package lt_1_200;

public class LC_035 {
	/*
	 * @lc app=leetcode id=35 lang=java
	 *
	 * [35] Search Insert Position
	 *
	 * https://leetcode.com/problems/search-insert-position/description/
	 *
	 * algorithms
	 * Easy (40.44%)
	 * Total Accepted:    361.4K
	 * Total Submissions: 893.6K
	 * Testcase Example:  '[1,3,5,6]\n5'
	 *
	 * Given a sorted array and a target value, return the index if the target is
	 * found. If not, return the index where it would be if it were inserted in
	 * order.
	 * 
	 * You may assume no duplicates in the array.
	 * 
	 * Example 1:
	 * 
	 * 
	 * Input: [1,3,5,6], 5
	 * Output: 2
	 * 
	 * 
	 * Example 2:
	 * 
	 * 
	 * Input: [1,3,5,6], 2
	 * Output: 1
	 * 
	 * 
	 * Example 3:
	 * 
	 * 
	 * Input: [1,3,5,6], 7
	 * Output: 4
	 * 
	 * 
	 * Example 4:
	 * 
	 * 
	 * Input: [1,3,5,6], 0
	 * Output: 0
	 * 
	 * 
	 */
	
    // find the position p, nums[p] >= target
    /**
     * [1,3,5,6], 7
     * 
     */
    public int searchInsert(int[] nums, int target) {
        if (nums.length <= 0) return 0;
        int l=0, r=nums.length-1;
        while(l<r) {
            int mid = l+(r-l)/2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return nums[l] < target ? l+1: l;
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
