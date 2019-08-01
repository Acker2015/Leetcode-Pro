package lt_700_799;

import java.util.Arrays;

/**
 * [719] Find K-th Smallest Pair Distance
 */
public class LC_719 {
    /*
     * @lc app=leetcode id=719 lang=java
     *
     * [719] Find K-th Smallest Pair Distance
     */
    class Solution {
        /**
         * 二分, 枚举距离
         * 对数组排序，最大距离为首尾元素的差值，最小距离为0，然后对距离进行二分
         * 计算小于等于距离的pair对数
         * 如果对数<k, 说明当前距离偏小
         * 如果对数>=k, 说明目标距离小于等于当前距离（可能会出现结果距离对数较多, 1,2,2,2,2,2）
         */
        public int smallestDistancePair(int[] nums, int k) {
            int len = nums.length;
            Arrays.sort(nums);
            int ld = 0, rd = nums[len-1]-nums[0];
            while (ld < rd) {
                int mid = ld + (rd - ld) / 2;
                // 计算距离小于等于mid的pair num
                int numOfDistance = 0;
                int start = 0;
                for (int i = 0; i < len; ++i) {
                    while (start < len && nums[start]-nums[i] <= mid) {
                        start++;
                    }
                    numOfDistance += start-i-1;
                }
                if (numOfDistance < k) {
                    ld = mid + 1;
                } else {
                    rd = mid;
                }
            }
            return ld;
        }
    }
}
