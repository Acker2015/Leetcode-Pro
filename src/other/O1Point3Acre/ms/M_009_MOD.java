package other.O1Point3Acre.ms;

/**
 * 给两个正int数，a和b，然后求a%b,不能用乘法和除法。
 */
public class M_009_MOD {
    private static int multipleTwo(int b) {
        return (b<<1)<b ? b:(b<<1);
    }
    /**
     * 通过位操作扩大b来加快取余操作
     * @param a
     * @param b
     * @return
     */
    public static int mod(int a, int b) {
        System.out.println("a%b=" + (a%b));
        if (a == 0 || b == 1 || a == b) {
            return 0;
        }
        int ans = b;
        while (a >= ans) {
            if (a == b) {
                return 0;
            } else if (a > b) {
                a -= b;
                // 假设a是个大数，b*2
                b = multipleTwo(b); // 防止越界
            } else {
                // a < b, 缩小b，b/2
                b >>= 1;
            }
        }
        return a;
    }

    public static void main(String ...args) {
        System.out.println(M_009_MOD.mod(23273,5));
    }
}
