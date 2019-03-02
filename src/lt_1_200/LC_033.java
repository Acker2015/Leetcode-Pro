package lt_1_200;

public class LC_033 {
	/**
     * binary search
     * l, m, r 
     * 1.如果nums[m]==target,直接返回
     * 2.如果nums[l]<=nums[m]. 说明l->m为有序的, 
     *              2.1 如果target在[l,m]之间，那么就在[l,m)继续二分查找
     *              2.2 否则在(m, r]之间查找
     * 3.如果nums[l]>nums[m],说明m->r为有序的
     *              3.1 如果target在(m, r]之间，那么就在(m, r]之间继续查找
     *              3.2 否则在[l, m)之间查找
     * 最后判断二分查找结果是否为target
     */
	public int search(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while (l < r) {
            int m = l + (r-l)/2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[l]<=nums[m]) {
                if (target>=nums[l] && target<nums[m]) {
                    r=m-1;
                } else {
                    l=m+1;
                }
            } else {
                if (target>nums[m] && target<=nums[r]) {
                    l=m+1;
                } else {
                    r=m-1;
                }
            }
        }
        return nums[l]==target?l:-1;
    }
	public static void main(String[] args) {
		int[] nums = {4,5,6,7,0,1,2};
		LC_033 lc_033 = new LC_033();
		System.out.println(lc_033.search(nums, 3));

	}
}
