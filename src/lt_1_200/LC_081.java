package lt_1_200;

public class LC_081 {
	/**
	 * [81] Search in Rotated Sorted Array II
	 *
	 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
     * 此种旋转的问题重点就是确定target在哪个部分
     * 比较让人头疼和容易混淆的地方就是当mid处的值跟left或者right处的值相同的时候，这时候不知道如何设置left,right了
     * 其实很简单，遇到nums[left]或者nums[right]等于nums[mid]的时候，将边缘移动一步进入下次迭代
     * 
     *  然后确定
     *  1. left -> mid之间是升序, target在[left, mid]之间，那么right=mid-1，否则left=mid+1
     *  2. mid -> right之间是升序, target在[mid, right]之间，那么left=mid+1,否则right=mid-1
     */
	public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length-1, mid;
        while (left <= right) {
            mid = left + (right-left)/2;
            if (nums[mid] == target) return true;
            if (nums[mid] == nums[left]) {
                left++;
            } else if (nums[mid] == nums[right]) {
                right--;
            } else {
                // 如果[left,mid]是升序的
                if (nums[left] < nums[mid]) {
                    if (target >= nums[target] && target < nums[mid]) {
                        right = mid-1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    // 否则[mid, right]是升序的
                    if (target > nums[mid] && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid- 1 ;
                    }
                }
            }
        }
        return false;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {2,5,6,0,0,1,2};
		LC_081 lc_081 = new LC_081();
		System.out.println(lc_081.search(nums, 3));
	}
}
