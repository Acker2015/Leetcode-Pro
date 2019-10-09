package lt_200_299;

/**
 * [283] Move Zeroes
 */
public class LC_283 {
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public void moveZeroes(int[] nums) {
        int i = 0, idx = 0;
        while (i < nums.length) {
            if (nums[i] != 0) {
                swap(nums, i, idx++);
            }
            i++;
        }
    }
}
