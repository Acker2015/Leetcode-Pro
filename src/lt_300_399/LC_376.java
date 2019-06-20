package lt_300_399;

/**
 * [376] Wiggle Subsequence
 */
public class LC_376 {
    /**
     * 贪心
     * greedy
     *
     * 遍历过程中观察下一次摇摆顺序，此时值为last, (按照开始摇摆升序和降序分别遍历一次)
     * 1. 下一次摇摆为降序，那么需要找到一个比last更小的值。
     *      需要跳过后续上升值(比last大，遇到更大的值就替换更新last)
     * 2. 下一次摇摆为升序，那么需要找到一个比last更大的值。
     *      需要跳过后续下降值(比last小，遇到更小的值就换位更新last)
     * 3. 找到复合条件的下一次摇摆值，更新last和len
     * 4. 摇摆方向取反
     *
     * time: O(n)
     */
    public int trace(int[] nums, boolean asc) {
        int len = 1, i = 1, last = nums[0];
        while (i < nums.length) {
            int j = i;
            // asc
            while (asc && j < nums.length && nums[j] <= last) {
                last = nums[j++];
            }
            // desc, find lower val
            while (!asc && j < nums.length && nums[j] >= last) {
                last = nums[j++];
            }
            if (j < nums.length) {
                len++;
                last = nums[j];
            }
            i = j + 1;
            asc = !asc;
        }
        return len;
    }
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) return nums.length;
        return Math.max(trace(nums, true), trace(nums, false));
    }
}
