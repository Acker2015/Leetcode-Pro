package lt_1_200;

public class LC_136 {
	 /**
     * 只有一个数的个数为奇数，由于两个相同的数的异或结果为0
     * 所以所有的数异或，最终的值就是single number
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int val: nums) {
            ans ^= val;
        }
        return ans;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
