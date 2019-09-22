package secondround.sr1_300;

// 贪心
public class sr_055 {
    /**
     * greedy
     * if current position is index i
     * you can move from i to (i + nums[i])
     * so just find a postion j in [i+1, i + nums[i]]
     * the postion j would just to farthest postion
     * 如果现在的索引位置为i, 那么从i最远可以跳到i+nums[i]的位置，
     * 所以贪心算法，找到的下一个位置j只需要满足在[i+1, i+nums[i]]之间，并且可以跳到的索引位置最远即可
     */
    public boolean canJump1(int[] nums) {
        if (nums.length <= 1) return true;
        int nowI = 0;
        while (nowI < nums.length - 1) {
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

    public boolean canJump(int[] nums) {
        if (nums.length <= 1) return true;  // already at the last index of array
        int start = 0;
        while (start < nums.length-1) {
            if (nums[start] == 0) {
                // can not jump to next position
                return false;
            }
            // select a best position
            int candidate = start + 1;
            int furtherPos = candidate+nums[candidate];
            for (int i = start+1; i < nums.length && i <= start+nums[start]; ++i) {
                int nextPos = i + nums[i];
                if (nextPos >= nums.length-1) return true;
                if (nextPos >= furtherPos) {
                    candidate = i;
                    furtherPos = nextPos;
                }
            }
            start = candidate;
        }
        return true;
    }
}
