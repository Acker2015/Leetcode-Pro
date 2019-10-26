package lt_500_599;

import java.util.Arrays;

/**
 * 581. Shortest Unsorted Continuous Subarray
 *
 * Array
 *
 * 1. 新数组排序之后跟原数组比较，左右两端第一个值不一样的地方就是左右索引
 *    time O(nlogn)
 * 2. 左右端点分别寻找
 *      考虑左边(左->右)，一直遍历记录最大值max，如果新遍历到的数num比之前的最大值max小，说明这个点可以作为右端点
 *      考虑右边(右->左)，一直遍历记录最小值min，如果新遍历到的数num比之前的最小值min大，说明这个点可以作为左端点
 *    time O(n)
 */
public class LC_581 {
    public class Solution1 {
        // 排序之后字段值起始跟原数组不同的区间就是最小区间
        public int findUnsortedSubarray(int[] nums) {
            int[] temp = nums.clone();
            Arrays.sort(temp);
            int left = -1, right = -1;
            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] != temp[i]) {
                    left = i;
                    break;
                }
            }
            for (int i = nums.length-1; i >= 0; --i) {
                if (nums[i] != temp[i]) {
                    right = i;
                    break;
                }
            }
            return left==-1 ? 0: right-left+1;
        }
    }

    public static class Solution2 {
        /**
         * 这个解法也太妙了
         *
         * 考虑左边(左->右)，一直遍历记录最大值max，如果新遍历到的数num比之前的最大值max小，说明这个点可以作为右端点
         * 考虑右边(右->左)，一直遍历记录最小值min，如果新遍历到的数num比之前的最小值min大，说明这个点可以作为左端点
         * @param nums
         * @return
         */
        public int findUnsortedSubarray(int[] nums) {
            int n = nums.length;
            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, left = -1, right = -1;
            for (int i = 0; i < nums.length; ++i) {
                max = Math.max(nums[i], max);
                min = Math.min(nums[n-1-i], min);
                if (nums[i] < max) {
                    right = i;
                }
                if (nums[n-1-i] > min) {
                    left = n-1-i;
                }
            }
            return right-left+1;
        }
    }
}
