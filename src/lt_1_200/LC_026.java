package lt_1_200;

/**
 * Remove Duplicates from Sorted Array
 * modify in-place
 * @author Acker
 *
 */
public class LC_026 {
	public int removeDuplicates(int[] nums) {
        int len = nums.length > 0 ? 1 : 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] != nums[i-1]) {
                nums[len++] = nums[i];
            }
        }
        return len;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
