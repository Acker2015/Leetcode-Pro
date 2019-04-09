package lt_200_299;

/**
 * [231] Power of Two
 */
public class LC_231 {
    private long pow2(int n) {
        if (n == 0) return 1;
        if (n == 1) return 2;
        long ans = pow2(n/2);
        return n%2==0 ? ans*ans:2*ans*ans;
    }

    /**
     * solution1 二分
     * n=8   -> log2(n)=3 sqrt(n)=2.82
     * n=16  -> log2(n)=4 sqrt(n)=4
     * n=32  -> log2(n)=5 sqrt(n)=5.6
     * 再往后 log2(n)<sqrt(n) 这个可以帮助设置右界
     * @param n
     * @return
     */
    public boolean isPowerOfTwo1(int n) {
        int l = 0, r = 1 + (int)(Math.log(n)/Math.log(2));
        while (l <= r) {
            int mid = l+(r-l)/2;
            long ans = pow2(mid);
            if (ans == n) {
                return true;
            }
            if (ans < n) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return false;
    }

    /**
     * solution2
     * bit操作
     * 2的次幂 显然二进制只有1个位是1 -> n&(n-1)==0即可判断
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n&(n-1))==0;
    }
    public static void main(String ...args) {
        LC_231 lc_231 = new LC_231();
        System.out.println(lc_231.isPowerOfTwo(16384));
    }
}
