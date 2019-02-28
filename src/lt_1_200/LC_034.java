package lt_1_200;

public class LC_034 {
	public int binarySearch(int[] nums, int target, boolean posL) {
	    int l=0, r=nums.length-1;
	    while(l<r) {
	        int mid = ((r-l)>>1)+l;
	        if (nums[mid]>target) {
	            r = mid-1;
	        } else if (nums[mid]<target) {
	            l = mid+1;
	        } else {
	            if (posL) {
	                r = mid;
	            } else {
	                l = nums[r]==target? mid+1:mid;
	                r = nums[r]==target?r:r-1;
	            }
	        }
	    }
	    return nums[l]==target ? l:-1;
	}
	/**
	 * solution1
	 * 两次二分，分别查找左边界和右边界
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] searchRange(int[] nums, int target) {
	    int[] ret = new int[2];
	    ret[0]=binarySearch(nums, target, true);
	    ret[1]=binarySearch(nums, target, false);
	    return ret;    
	}
	
	/**
	 * solution2
     * 区间二分
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] ret = {-1, -1};
        if (nums.length <= 0) {
            return ret;
        }
        int left = 0, right = nums.length-1;
        while(nums[left] < nums[right]) {
            int mid = left + (right-left)/2;
            if (nums[mid] > target) {
                right=mid-1;
            } else if (nums[mid] < target) {
                left=mid+1;
            } else {
            	// important, 如果mid处的值等于target，那么选择移动left或者right
                if (nums[left] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        if (nums[left]==target) {
            ret[0]=left;
            ret[1]=right;
        }
        return ret;
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LC_034 lc_034 = new LC_034();
		int[] nums = {5,7,7,8,8,10};
		int[] ret = lc_034.searchRange(nums, 11);
		for (int item: ret) {
			System.out.println(item);
		}
	}

}
