package lt_400_499;

/**
 * [457] Circular Array Loop
 */
public class LC_457 {
    private boolean sameDirection(int[] nums, int x, int y) {
        return nums[x] * nums[y] > 0;
    }
    private int nextIdx(int[] nums, int x) {
        int len = nums.length;
        return ((x+nums[x])%len+len)%len;
    }

    /**
     * find cycle的问题，可以使用快慢指针的问题, 对于失败路径上的节点，设置为0，自然失败。后续遍历直接跳过
     * Just think it as finding a loop in Linked List, except that loops with only 1 element do not count.
     * Use a slow and fast pointer, slow pointer moves 1 step a time while fast pointer moves 2 steps a time.
     * If there is a loop (fast == slow), we return true, else if we meet element with different directions, then the search fail, we set all elements along the way to 0.
     * Because 0 is fail for sure so when later search meet 0 we know the search will fail.
     *
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                continue;
            }
            // j means the slow pointer, k means the fast pointer. if j meets k, you get the circle.
            int j = i, k = nextIdx(nums, j);
            while (sameDirection(nums, i, k) && sameDirection(nums, i, nextIdx(nums, k))) {
                if (j == k) {
                    // the cycle has only 1 element
                    if (j == nextIdx(nums, j)) {
                        break;
                    }
                    return true;
                }
                j = nextIdx(nums, j);   // slow pointer, one step each loop
                k = nextIdx(nums, nextIdx(nums, k));    // fast pointer, two step each loop
            }
            // make the visited path to zero, keep same direction.
            j = i;
            int val = nums[j];
            while (nums[j] * val > 0) {
                int next = nextIdx(nums, j);
                nums[j] = 0;
                j = next;
            }
        }
        return false;
    }
}
