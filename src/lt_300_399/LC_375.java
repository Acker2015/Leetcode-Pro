package lt_300_399;

/**
 * [375] Guess Number Higher or Lower II
 * DP
 * 可以通过注释进一步的理解题意
 */
public class LC_375 {
    /**
     * minimize the max cost
     *
     * 1. for one number, like 1, best strategy is 0$
     * 2. for two number, like 3,4, best strategy is 3$, which can be understood in this way: you have two way to guess
     *      3, 4 ---------> $3
     *      5, 6 ---------> $5
     * 3. for three number, the best strategy is guess the middle number first, and (worst case is) if wrong, you get charged that middle number money,
     *    and then you immediately know what target number is by using "lower" or "higher" response, so in summary:
     *    range ---------> best strategy cost
     *      3, 4, 5 ---------> $4
     *      7, 8, 9 ---------> $8
     * so if guess the number i, the max cost should be f(i) = i + {cost(s, i-1), cost(i+1,e)}
     * so just make the f(i) is minmum when i belongs to [start, end] -> [1, n]
     * def: f(i) = i + max{DP(start, i-1), DP(i+1, end)}
     *         DP(start, end) = min{f(start), f(start+1), ..., f(end)}
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        if (n <= 1) return 0;
        int[][] mem = new int[n+1][n+1];
        return DP(mem, 1, n);
    }
    private int DP(int[][] mem, int start, int end) {
        if (start >= end) return 0;
        if (mem[start][end] > 0) return mem[start][end];
        int res = Integer.MAX_VALUE;
        for (int i = start; i <= end; ++i) {
            int tmp = i + Math.max(DP(mem, start, i-1), DP(mem, i+1, end));
            res = Math.min(res, tmp);
        }
        mem[start][end] = res;
        return res;
    }
}
