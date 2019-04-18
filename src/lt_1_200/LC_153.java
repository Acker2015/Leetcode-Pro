package lt_1_200;

public class LC_153 {
    /**
     * [153] Find Minimum in Rotated Sorted Array
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int l=0, r=nums.length-1;
        while (l<r) {
            int mid = l+(r-l)/2;
            if (l == mid) {
                return Math.min(nums[l], nums[r]);
            }
            if (nums[mid] > nums[r]) {
                l = mid+1;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }

    public static void main(String ...args) {
        int[] nums = {3,4,5,1,2};
        LC_153 lc_153 = new LC_153();
        System.out.println(lc_153.findMin(nums));
    }
}
