package lt_1_200;

public class LC_045 {
	/**
     * Greedy贪心
     * [2,3,1,1,4] -> 2
     * [2,3,1] -> 1
     * 其实位置为0
     * 迭代i=0开始：
     *      1. 判断从i位置是否可以结束，如果可以结束，那么jump次数+1，结束
     *      2. 否则，寻找下一个位置maxIndex，maxIndex能够在i可以跳到的位置里，是可以再次跳的最远的
     *          k in (i, i+nums[i]], 位置maxIndex为max(k+nums[k])
     */
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        int i = 0, jumpNum = 0;
        while (i < nums.length - 1) {
        		jumpNum++;
        		// 1.如果这里可以直接跳到结尾就直接结束
        		// 2.如果跳不到结果，下一个位置，这个位置能跳的更远
        		if (i + nums[i] >= nums.length-1) {
        			break;
        		}
            int maxIndex = i+1;
            for (int k = i + 1; k < nums.length && k <= i+nums[i]; ++k) {
	            	// 不能跳到最后找到下一次可以跳到最大的地方
                if (k + nums[k] >= maxIndex + nums[maxIndex]) {
                    maxIndex = k;
                }
            }
            i = maxIndex;
        }
        return jumpNum;
    }
	public static void main(String[] args) {
		int[] nums = {2,3,1};
		LC_045 lc_045 = new LC_045();
		System.out.println(lc_045.jump(nums));

	}

}
