package dp;

public class dp_03 {
    // dp[i] means num of ways. dp[i] = dp[i-1]+dp[i-2]
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int pre1 = 2, pre2 = 1, cur = 0;
        int i = 3;
        while (i++ <= n) {
            cur = pre1+pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }
}
