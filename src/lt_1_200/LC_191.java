package lt_1_200;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/4/2
 */
public class LC_191 {
    /**
     *  [191] Number of 1 Bits
     * @param n
     * @return
     */
    // you need to treat n as an unsigned value
    public int hammingWeight1(int n) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            ans += (n&1);
            n = n>>>1;
        }
        return ans;
    }

    /**
     * n & (n-1)能够将n的最后一个bit位为1的bit置为0
     *
     */
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            ans++;
            n = n&(n-1);
        }
        return ans;
    }
}
