package lt_800_899;

/**
 * 801-Minimum Swaps To Make Sequences Increasing
 */
public class LC_801 {

    /**
     * DP problem
     * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/119879/C%2B%2BJavaPython-Easy-Understood-DP-Solution
     * two helper array.
     *
     * swap[n] means the minimum swaps to make the A[i] and B[i] sequences increasing for 0 <= i <= n in condition that we swap at i
     * not_swap[n] is the same with A[n] and B[n] that we do not swap at i
     *
     *
     * it's a logic problem, I can't think out this solution by myself, but I can understand your solution.
     * I would try to explain it.
     * 1. A[i - 1] < A[i] && B[i - 1] < B[i]. In this case, if we want to keep A and B increasing before the index i, can only have two choices.
     *      -> 1.1 don't swap at (i-1) and don't swap at i, we can get not_swap[i] = not_swap[i-1]
     *      -> 1.2 swap at (i-1) and swap at i, we can get swap[i] = swap[i-1]+1
     *      if swap at (i-1) and do not swap at i, we can not guarantee A and B increasing.
     * 2. A[i-1] < B[i] && B[i-1] < A[i]. In this case, if we want to keep A and B increasing before the index i, can only have two choices.
     *      -> 2.1 swap at (i-1) and do not swap at i, we can get notswap[i] = Math.min(swap[i-1], notswap[i] )
     *      -> 2.2 do not swap at (i-1) and swap at i, we can get swap[i]=Math.min(notswap[i-1]+1, swap[i])
     */
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[] swap = new int[n];
        int[] no_swap = new int[n];
        swap[0] = 1;
        for (int i = 1; i < n; ++i) {
            swap[i] = n;
            no_swap[i] = n;
            /**
             * A[i-1]<A[i] && B[i-1]<B[i]
             * 1. swap at i and i-1
             * 2. do not swap at i and i-1
             */
            if (A[i-1]<A[i] && B[i-1]<B[i]) {
                // swap
                swap[i] = swap[i-1] + 1;
                no_swap[i] = no_swap[i-1];
            }
            /**
             * A[i-1]<B[i] && B[i-1]<A[i]
             * 1. swap at i-1 and do not swap at i
             * 2. do not swap at i-1 and swap at i
             */
            if (A[i-1]<B[i] && B[i-1]<A[i]) {
                // swap at i-1 and do not swap at i
                no_swap[i] = Math.min(no_swap[i], swap[i-1]);
                // do not swap at i-1 and swap at i
                swap[i] = Math.min(swap[i], no_swap[i-1]+1);
            }
            // else whether swap or do not swap, it can't guarantee both A and B increasing.
        }
        return Math.min(swap[n-1], no_swap[n-1]);
    }
    public static void main(String ...args) {
        int[] A = {1,3,5,4};
        int[] B = {1,2,3,7};
        System.out.println(new LC_801().minSwap(A, B));
    }
}
