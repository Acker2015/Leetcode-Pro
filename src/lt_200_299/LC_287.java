package lt_200_299;

public class LC_287 {
    public int findDuplicate(int[] nums) {
	    int fast = 0, slow = 0;
        while (nums[fast] < nums.length && nums[slow] < nums.length) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) break;
        }
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
	public static void main(String[] args) {
		int[] nums = {3,1,3,4,2};
		LC_287 lc_287 = new LC_287();
		System.out.println(lc_287.findDuplicate(nums));
		// TODO Auto-generated method stub

	}

}
