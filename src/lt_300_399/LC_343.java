package lt_300_399;

//[343] Integer Break
public class LC_343 {
    /**
     * solution1
     * DP
     * 计算小于n的break或者不break的最大product
     *
     * dp[i] = dp[j]*dp[i-j] (j >= 0 && j <= i)
     *
     * dp[n] = dp[i]*dp[n-i] (i >= 1)
     *
     * time: O(n^2)
     */
    public int integerBreak1(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            if (i < n) {
                dp[i] = i;
            }
            for (int j = 1; j <= i/2; ++j) {
                if (i == n && j == 0) continue;
                dp[i] = Math.max(dp[i], dp[j]*dp[i-j]);
            }
        }
        return dp[n];
    }
    /**
     * solution2:
     * Math
     *
     * The first thing we should consider is : What is the max product if we break a number N into two factors?
     * I use a function to express this product: f=x(N-x)
     * When x=N/2, we get the maximum of this function.
     * However, factors should be integers. Thus the maximum is (N/2)*(N/2) when N is even or (N-1)/2 *(N+1)/2 when N is odd.
     * When the maximum of f is larger than N, we should do the break.
         1. (N/2)*(N/2)>=N, then N>=4
         2. (N-1)/2 *(N+1)/2>=N, then N>=5
     * These two expressions mean that factors should be less than 4, otherwise we can do the break and get a better product.
       The factors in last result should be 1, 2 or 3. Obviously, 1 should be abandoned.
       Thus, the factors of the perfect product should be 2 or 3.
     * The reason why we should use 3 as many as possible is
     * For 6, 3 * 3>2 * 2 * 2. Thus, the optimal product should contain no more than three 2.
     *
     * https://leetcode.com/problems/integer-break/discuss/80689/A-simple-explanation-of-the-math-part-and-a-O(n)-solution
     *
     * time O(n)
     */
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        int ret = 1;
        while (n > 4) {
            ret *= 3;
            n -= 3;
        }
        ret *= n;
        return ret;
    }
}
