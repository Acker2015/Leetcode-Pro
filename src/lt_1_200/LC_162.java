package lt_1_200;

/**
 *
 * [162] Find Peak Element
 *
 * 数据分布有三种情况
 * 1. 单调增
 * 2. 单调减
 * 3. peek在中间
 *
 */
public class LC_162 {
    /**
     * 线性查找
     * 从头开始遍历
     * 满足nums[i] > nums[i+1]的i即为peek
     * @param nums
     * @return
     */
    public int findPeakElement1(int[] nums) {
        for (int i = 0; i < nums.length-1; ++i) {
            if (nums[i] > nums[i+1]) {
                return i;
            }
        }
        return nums.length-1;
    }
    /**
     * 二分
     *
     * 根据条件nums[mid] 与 nums[mid+1]关系来决定二分区间
     * if (nums[mid] < nums[mid+1])
     *      -> l = mid+1
     * else
     *      -> r = mid
     *
     */
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length-1;
        while (l < r) {
            int mid = l + (r-l)/2;
            if (nums[mid] < nums[mid+1]) {
                l = mid+1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
