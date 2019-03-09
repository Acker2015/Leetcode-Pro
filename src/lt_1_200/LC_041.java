package lt_1_200;

public class LC_041 {
	/**
     * 假设数据里全都是大于0的自然数，那么怎么找到第一个没出现的最小正数呢
     * 
     * 遍历一遍
     * 将nums[i]索引处的值置为负数，就能表示nums[i]出现过
     * 
     * 如果nums[i]=3, 那么将索引为3处的nums数组中的值置为负数，
     * 那么就可以通过索引对应处的值的正负来判断该索引代表的正数是否存在
     * 
     * 如果[2, 3, 1, 6], 假设索引从1开始
     * 遍历2， 将索引2的值置为负数，数组变为[2, -3, 1, 6]
     * 遍历-3, 取绝对值，将索引3处的值置为负数，数组变为[2, -3, -1, 6]
     * 遍历-1, 取绝对值，将索引1处的值置为负数，数组变为[-2, -3, -1, 6]
     * 遍历6, 超出数据索引范围，跳过不考虑
     * 所以遍历一遍数组遇到的第一个正数的索引就是最小未出现的正数
     * 
     * 至于怎么把数组里的值处理成全部为大于0的正整数呢？
     * 只需要将小于0的数全部替换为超过数据索引边界的任一值就可以
     */
    public int firstMissingPositive(int[] nums) {
        if (nums.length <= 0) return 1;
        // 第一次遍历，将所有小于等于0的数全部置为nums.length+1
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] <= 0){
                nums[i] = nums.length + 1;
            }
        }
        // 第二次遍历，将nums[i]值代表的索引处的值用负数替代
        for (int i = 0; i < nums.length; ++i) {
            int idx = Math.abs(nums[i]);
            if (idx <= nums.length) {
                nums[idx-1] = -1*Math.abs(nums[idx-1]);
            }
        }
        // 第三次遍历，找出结果
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) return i+1;
        }
        return nums.length + 1;
    }
	public static void main(String[] args) {
		int[] nums = {1};
		LC_041 lc_041 = new LC_041();
		System.out.println(lc_041.firstMissingPositive(nums));

	}

}
