package lt_1_200;

public class LC_075 {
	/**
	 * 因为只有三种颜色0，1，2
	 * 所以开头肯定是放0，结尾肯定是放2，剩下的中间部分就是放置1了
	 * two pointers
	 * @param nums
	 */
	public void sortColors(int[] nums) {
        if (nums.length <= 1) return;
        int n = nums.length;
        int left = 0, right = n - 1, i = 0;
        while (i <= right) {
            if (nums[i]==0) {
                nums[left++] = 0;
                i++;
            } else if (nums[i] == 2) {
                nums[i] = nums[right];
                nums[right--] = 2;
            } else {
                i++;
            }
        }
        while (left <= right) nums[left++]=1;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
