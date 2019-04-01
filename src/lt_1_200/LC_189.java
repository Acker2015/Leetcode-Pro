package lt_1_200;

public class LC_189 {
    public void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int ans = nums[left];
            nums[left] = nums[right];
            nums[right] = ans;
            left++;
            right--;
        }
    }

    /**
     *
     * [189] Rotate Array
     *
     * time O(n), space O(1)
     * 先翻转左边部分，再翻转右边部分，然后再整个翻转
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if (len <= 1) return;
        k = k % len;
        reverse(nums, 0, len-k-1);
        reverse(nums, len-k, len - 1);
        reverse(nums, 0, len-1);
    }
}
