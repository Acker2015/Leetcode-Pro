package lt_200_299;

/**
 * [268] Missing Number
 */
public class LC_268 {

    public static class Solution1 {
        /**
         * solution1
         * bit manipulation
         * @param nums
         * @return
         */
        public int missingNumber(int[] nums) {
            int len = nums.length;
            int ans = len;
            for (int i = 0; i < len; ++i) {
                ans ^= i;
                ans ^= nums[i];
            }
            return ans;
        }
    }

    public static class Solution2 {
        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        /**
         * solution2
         * 数组中元素归位
         * nums[i] 放到nums[i]的索引上
         * 即 i == nums[i]
         *
         * 数据遍历，对于遍历到的索引i, 如果nums[i]!=i, 那么将nums[i]放到改索引处
         *
         * 举例
         * 3 0 2 4
         * i = 0, nums[i] = 3
         * 那么将4与3交换， 4 0 2 3，此时3回到正确位置上
         */
        public int missingNumber(int[] nums) {
            int len = nums.length;
            for (int i = 0; i < len; ++i) {
                while (nums[i]!=i && nums[i] < len) {
                    swap(nums, i, nums[i]);
                }
            }
            for (int i = 0; i < len; ++i) {
                if (i!=nums[i]) return i;
            }
            return len;
        }
    }


    public static class Solution3 {
        /**
         * 将对应索引处的值设置为负数
         *
         * 为了防止0的干扰，起始将所有元素值都增1
         * @param nums
         * @return
         */
        public int missingNumber(int[] nums) {
            for (int i = 0; i < nums.length; ++i) {
                nums[i] += 1;
            }
            for (int i = 0; i < nums.length; ++i) {
                int val = Math.abs(nums[i]);
                if (val <= nums.length) {
                    nums[val-1] = -Math.abs(nums[val-1]);
                }
            }
            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] > 0) {
                    return i;
                }
            }
            return nums.length;
        }
    }
}
