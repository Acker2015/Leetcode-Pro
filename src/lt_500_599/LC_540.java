package lt_500_599;

/**
 * [540] Single Element in a Sorted Array
 * binary-search
 */
public class LC_540 {
    /**
     * 如果全部出现两次，那么每个数的起始位置一定是偶数0，2，4，6，8
     * 二分位置 - (mid设置为偶数索引位置)
     * 如果起始位是奇数，那么single Number在左边
     * 否则在右边
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length-1, mid;
        while (left < right) {
            mid = left + (right-left)/2;
            if (mid % 2 == 1) {
                mid--;
            }
            if (nums[mid] != nums[mid+1]) {
                right = mid;
            } else {
                left = mid+2;
            }
        }
        return nums[left];
    }
}
