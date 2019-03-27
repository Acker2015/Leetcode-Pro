package lt_1_200;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/27
 */
public class LC_172 {

    /**
     * 计算n!中因子p的个数
     *
     * n/p + n/(p^2) + n/(p^3) ...
     * @param n
     * @param p
     * @return
     */
    private int cal(int n, int p) {
        if (n < p) return 0;
        return n/p + cal(n/p, p);
    }
    public int trailingZeroes(int n) {
        return cal(n, 5);
    }

    public static void main(String ...args) {
        LC_172 lc_172 = new LC_172();
        System.out.println(lc_172.trailingZeroes(26));
    }

}
