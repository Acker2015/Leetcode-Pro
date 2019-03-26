package lt_1_200;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/27
 */
public class LC_096 {
    /**
     * f(n) mean the num of unique BST
     * f(0) = 1
     * f(1) = 1
     * f(2) = f(0)*f(1) + f(1)*f(0) = 2
     * f(3) = f(0)*f(2) + f(1)*f(1) + f(2)*f(0) = 5
     * f(4) = f(0)*f(3) + f(1)*f(2) + f(2)*f(1) + f(3)*f(0) = 14
     * f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n == 0 || n == 1) return 1;
        int[] dp = new int[n+1];
        dp[0]=dp[1]=1;
        for (int i = 2; i < n+1; ++i) {
            int ans = 0;
            for (int j = 0; j < i; ++j) {
                ans += dp[j]*dp[i-1-j];
            }
            dp[i] = ans;
        }
        return dp[n];
    }
    public static void main(String ...args) {
        LC_096 lc_096 = new LC_096();
        System.out.println(lc_096.numTrees(4));
    }
}
