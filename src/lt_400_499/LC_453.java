package lt_400_499;

// [453] Minimum Moves to Equal Array Elements
public class LC_453 {
    /**
     * explain1:
     *
     * let's define sum as the sum of all the numbers
     * before any moves; minNum as the min number int the list;
     * n is the length of the list;
     * After, say m moves, we get all the numbers as x, and we will get the following equation
     * sum + m * (n - 1) = x * n, and actually, x = minNum + m
     *
     * This part may be a little confusing, let me explain a little again. it comes from two observations:
     * the minum number will always be minum until it reachs the final number, because every move, other numbers (besides the max) will be increamented too;
     * from above, we can get, the minum number will be incremented in every move. So, if the final number is x, it would be minNum + moves;
     * and finally, we will get sum - minNum * n = m
     * This is just a math calculation.
     *
     * explain2
     * 更妙的解释 from Stefan
     *
     * 对n-1个元素执行+1操作相当于对一个元素执行减一操作，所以可以使用这个操作来等价替换，执行m次操作使得所有的数都变成原数组的最小值即可
     * sum - m = n * minVal
     *
     * Incrementing all but one is equivalent to decrementing that one. So let's do that instead. How many single-element decrements to make all equal?
     * No point to decrementing below the current minimum, so how many single-element decrements to make all equal to the current minimum?
     * Just take the difference from what we currently have (the sum) to what we want (n times the minimum).
     */
    public int minMoves(int[] nums) {
        if (nums.length <= 1) return 0;
        int sum = 0, minVal = Integer.MAX_VALUE;
        for (int num: nums) {
            sum += num;
            minVal = Math.min(minVal, num);
        }
        return sum - nums.length * minVal;
        //return Arrays.stream(nums).sum() - nums.length * Arrays.stream(nums).min().orElse(0);
    }
}
