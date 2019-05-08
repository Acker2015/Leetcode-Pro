package lt_200_299;

/**
 * [201] Bitwise AND of Numbers Range
 */
public class LC_201 {
    /**
     * Solution1
     * brute solution 暴力
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd1(int m, int n) {
        int val = m;
        for (int i = m+1; i <= n; ++i) {
            val &= i;
        }
        return val;
    }
    /**
     * Solution2
     * 计算m与n之间的差距diff
     * 如果k位bit能够表示diff，那么m&n结果的低k位肯定为0
     *
     * 使用mask = 0x7fffffff ^ ((1<<k)-1)
     * 返回结果m&n&mask
     */
    public int rangeBitwiseAnd2(int m, int n) {
        int diff = n-m+1;
        int diffBitLen = (int)Math.ceil(Math.log(diff)/Math.log(2)); // 计算k
        // 构造mask的时候，需要注意偏移31位时候的特殊处理
        int mask = 0;
        if (diffBitLen < 31) {
            mask =0x7fffffff ^ ((1<<diffBitLen)-1);
        }
        return m&n&mask;
    }
    /**
     * Solution3
     *
     * The idea is very simple:
      1. last bit of (odd number & even number) is 0.
      2. when m != n, There is at least an odd number and an even number, so the last bit position result is 0.
      3. Move m and n rigth a position.
     Keep doing step 1,2,3 until m equal to n, use a factor to record the iteration time.
     */
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) return 0;
        int flag = 1;
        while (m!=n) {
            m>>=1;
            n>>=1;
            flag <<= 1;
        }
        return flag*m; // 相当于将m往左移
    }
}
