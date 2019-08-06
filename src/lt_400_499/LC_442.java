package lt_400_499;

import java.util.ArrayList;
import java.util.List;

/**
 * [442] Find All Duplicates in an Array
 *
 * 两种解法
 */
public class LC_442 {
    class Solution {
        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        /**
         * 解答一：
         *
         * using swap, make the nums[i] back to right location.
         * index idx of nums[i] is nums[i]-1, if nums[idx]=nums[i], it means nums[i] is one of the duplicate number.
         *
         * let nums[i] back to origin position i.
         */
        public List<Integer> findDuplicates1(int[] nums) {
            List<Integer> list = new ArrayList<>();
            int i = 0;
            while (i < nums.length) {
                if (nums[i] < 1 || nums[i]-1 == i) {
                    i++;
                } else {
                    int fitIdx;
                    while (nums[i]-1 != i && nums[i] >= 1) {
                        fitIdx = nums[i]-1;
                        // deplicate nums[i]-1
                        if (nums[fitIdx] == nums[i]) {
                            list.add(nums[i]);
                            nums[i] = -1;
                            break;
                        } else {
                            swap(nums, i, fitIdx);
                        }
                    }
                }
            }
            return list;
        }

        /**
         * Solution2
         * 更简单，更brainstorm的解法
         * index = Math.abs(nums[i])-1;
         * 直接将对应index处的值置为负数，如果发现index处的值为负数，那么说明已经出现
         */
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; ++i) {
                int idx = Math.abs(nums[i])-1;
                if (nums[idx] < 0) {
                    list.add(idx+1);
                } else {
                    nums[idx] = -nums[idx];
                }
            }
            return list;
        }
    }
}
