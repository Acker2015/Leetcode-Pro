package lt_1_200;

public class LC_027 {
	/**
     * set two index of start and end
     * traverse the array from begin to end, 
     1. if nums[begin] equals to val, just swap the val of nums[begin] and nums[end], and the put end--
     2. if nums[begin] does not equals to val, just put beign++ to find next val
     */
    public int removeElement(int[] nums, int val) {
        if (nums.length<=0) return 0;
        int begin = 0, end = nums.length-1;
        while(begin<end) {
            if (nums[begin] == val) {
                nums[begin]=nums[end--];
            } else {
                begin++;
            }
        }
        return nums[begin]==val ? begin:begin+1;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
