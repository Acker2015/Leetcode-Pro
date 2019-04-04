package lt_200_299;


public class LC_204 {
    /**
     * [204] Count Primes
     *
     * 使用素数表的解法
     * O(nloglogn)
     */
    public int countPrimes(int n) {
        // flags[i] = false, mean i is a prime number
        boolean[] flags = new boolean[n];
        int num = 0;
        int upper = (int)Math.sqrt(n);
        for (int i = 2; i < n; ++i) {
            if (!flags[i]) {
                num++;
                if (i > upper) continue;
                for (int k = i*i; k < n; k+=i) {
                    flags[k]=true;
                }
            }
        }
        return num;
    }

    public int countPrimes1(int n) {
        // flags[i] = false, mean i is a prime number
        boolean[] flags = new boolean[n];
        int num = 0;
        for (int i = 2; i < n; ++i) {
            if (!flags[i]) {
                num++;
            }
            long max = ((long)i) * i;
            for (int k=i+i; k<n && k<= max; k+=i) {
                flags[k]=true;
            }
        }
        return num;
    }

    public static void main(String ...args) {
        LC_204 lc_204 = new LC_204();
        int n =499979;
        System.out.println(lc_204.countPrimes(n));
    }
}
