package lt_200_299;

/**
 * [268] Missing Number
 */
public class LC_268 {

    /**
     * solution1
     * bit manipulation
     * @param nums
     * @return
     */
    public int missingNumber1(int[] nums) {
        int len = nums.length;
        int ans = len;
        for (int i = 0; i < len; ++i) {
            ans ^= i;
            ans ^= nums[i];
        }
        return ans;
    }
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
