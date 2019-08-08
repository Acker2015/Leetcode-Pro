package lt_500_599;

/**
 * [509] Fibonacci Number
 */
public class LC_509 {
    public int fib(int N) {
        if (N <= 1) return N;
        int a = 0, b = 1, ans;
        for (int i = 2; i <= N; ++i) {
            ans = a + b;
            a = b;
            b = ans;
        }
        return b;
    }
}
