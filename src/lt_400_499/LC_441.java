package lt_400_499;

/**
 * [441] Arranging Coins
 */
public class LC_441 {
    /**
     * binary-search
     * 找第一个不满足条件的k
     * 返回k-1
     *
     * 特殊处理n=1的情况
     * 注意需要使用double来存储->k(k+1)/2
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        if (n == 1) return 1;
        int l = 1, r = n;
        while (l < r) {
            int m = l + (r-l)/2;
            double num = ((double)m)*(m+1)/2;
            if (num <= n) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l-1;
    }
}
