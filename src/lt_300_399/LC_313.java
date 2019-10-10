package lt_300_399;

// [313] Super Ugly Number
public class LC_313 {
    /**
     * 解法同 ugly-numberII
     * ugly[0]=1，ugly由 primes中的数乘积得到
     * 下一次肯定在 ugly[0] * primes[k]中选择
     *
     * 所以记录每个primes在ugly中的乘积位置，每次找到最小的作为新的丑数，并将该prime对应的丑数索引加一
     *
     * 这里对于重复数的处理比如 2*7=14, 7*2=14，这里14只会被算一次， 所以在代码中是先找到最小值minUgly, 然后将结果为最小值的idx索引全都自增一遍
     * 这样就能保证重复数字都出现一次
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] idxs = new int[primes.length];
        int[] uglys = new int[n];
        uglys[0] = 1;
        for (int i = 1; i < n; ++i) {
            int minUgly = primes[0] * uglys[idxs[0]];
            for (int k = 1; k < primes.length; ++k) {
                minUgly = Math.min(minUgly, primes[k]*uglys[idxs[k]]);
            }
            uglys[i] = minUgly;
            for (int k = 0; k < primes.length; ++k) {
                if (primes[k] * uglys[idxs[k]] == minUgly) {
                    idxs[k] += 1;
                }
            }
        }
        return uglys[n-1];
    }
}
