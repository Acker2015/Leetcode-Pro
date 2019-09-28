package lt_1_200;

/**
 * [154] Find Minimum in Rotated Sorted Array II
 * 二分
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * The array may contain duplicates.
 * 剑指offer 第11题
 */
public class LC_154 {

    public int findMin(int[] nums) {
        int l = 0, r = nums.length-1;
        while (l < r) {
            // 防止有序数组-特殊case
            if (nums[l] < nums[r]) {
                return nums[l];
            }
            // 只有两个元素的时候
            if (l+1 == r) {
                return Math.min(nums[l], nums[r]);
            }
            int mid = l + (r-l) /2;
            // 直接将左右可能会影响判断的情况掐掉
            if (nums[mid] == nums[l]) {
                l++;
            } else if (nums[mid] == nums[r]) {
                r--;
            } else {
                // [l, mid] 升序
                if (nums[l] <= nums[mid]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
        }
        return nums[l];
    }
}
