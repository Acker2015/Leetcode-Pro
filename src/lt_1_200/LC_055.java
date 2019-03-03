package lt_1_200;

public class LC_055 {
	/**
     * greedy
     * if current position is index i
     * you can move from i to (i + nums[i])
     * so just find a postion j in [i+1, i + nums[i]]
     * the postion j would just to farthest postion
     * 如果现在的索引位置为i, 那么从i最远可以跳到i+nums[i]的位置，
     * 所以贪心算法，找到的下一个位置j只需要满足在[i+1, i+nums[i]]之间，并且可以跳到的索引位置最远即可
     */
    public boolean canJump(int[] nums) {
        if (nums.length <= 1) return true;
        int nowI = 0;
        while (nowI < nums.length - 1) {
        		System.out.println("jump index: "+nowI);
            if (nums[nowI] == 0) return false;
            int maxIndex = nowI+1;
            for (int i = nowI+1; i < nums.length && i <= nowI+nums[nowI]; ++i) {
                if (i + nums[i] > maxIndex + nums[maxIndex]) {
                    maxIndex = i;
                }
            }
            nowI = maxIndex;
        }
        return true;
    }
    
    public static void main(String ...args) {
    		int[] nums = {3,2,1,0,4};
    		LC_055 lc_055 = new LC_055();
    		System.out.println(lc_055.canJump(nums));
    }
}
