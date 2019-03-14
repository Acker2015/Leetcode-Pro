package lt_1_200;

public class LC_080 {
	/**
	 * Remove Duplicates from Sorted Array II
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
        if (nums.length < 2) return nums.length;
        int ans = 2;
        for (int i = 2; i < nums.length; ++i) {
            if (nums[i] == nums[ans-2]) continue;
            nums[ans++] = nums[i];
        }
        return ans;
    }
	public static void main(String[] args) {
		int[] nums = {1,1,1,2,2,3};
		LC_080 lc_080 = new LC_080();
		int len = lc_080.removeDuplicates(nums);
		for (int i = 0; i < len; ++i) {
			System.out.println(nums[i] + " ");
		}

	}

}
