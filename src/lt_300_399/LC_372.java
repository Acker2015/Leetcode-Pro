package lt_300_399;

/**
 * [372] Super Pow
 */
public class LC_372 {
    private static final int MOD = 1337;
    // 快速幂
    private int power(int a, int k) {
        if (k == 0) return 1;
        if (a == 1 || k == 1) return a;
        int ans = power(a, k/2);
        ans = (ans%MOD)*(ans%MOD)%MOD;
        return k%2==0 ? ans: ans*(a%MOD)%MOD;
    }

    private int f(int a, int[] b, int end) {
        if (end < 0) return 1;
        int lastDigit = b[end];
        return (power(f(a, b, end-1), 10)%MOD) * (power(a, lastDigit) % MOD) % MOD;
    }
    // 2147483647\n[2,0,0]
    /**
     * formula: (a*b)%mod = (a%mod)*(b%mod)%mod
     *
     * f(a,b) means a^b
     * f(a, 123456) = f(a, 123450) * f(a, 6)
     *              = f(f(a,12345),10) * f(a, 6)
     * f(a, 123456) = (f(a, 123450)%mod)*(f(a,6)%mod)%mod
     */
    public int superPow(int a, int[] b) {
        return f(a, b, b.length-1);
    }
}
