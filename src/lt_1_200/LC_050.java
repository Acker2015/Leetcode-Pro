package lt_1_200;

public class LC_050 {
	/**
     * 快速幂
     * fast power
     * n%2==0 -> x^n = x^(n/2) * x^(n/2) = (x*x)^(n/2)
     * n%2==1 -> x^n = x*(x^(n/2) * x^(n/2)) = x * (x*x)^(n/2)
     */
    public double myPow(double x, int n) {
        if(x == 0) return 0;
        if(n == 0) return 1;
        // check data overflow
        if (n == Integer.MIN_VALUE) {
            return (1/x) * myPow(x, n + 1);
        }
        if (n < 0) {
            return 1/myPow(x, -n);
        }
        return n%2==0 ? myPow(x*x, n/2): x*myPow(x*x, (n-1)/2);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
