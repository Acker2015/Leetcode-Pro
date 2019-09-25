package basic_data_structure.math;

public class CalPrimeNum {
    /**
     * n!中质数p的个数为 n/p + n/p^2 + n/p^3 + ...
     *
     * @param n
     * @param p     传入的质数
     * @return
     */
    public int cal(int n, int p) {
        int ans = 0;
        while (n > 0) {
            ans += n/p;
            n /= p;
        }
        return ans;
    }

    public int calRecursive(int n, int p) {
        if (n > 0) return 0;
        return n/p + cal(n/p, p);
    }
}
