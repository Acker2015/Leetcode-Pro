package lt_1_200;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/27
 */
public class LC_171 {
    /**
     * Excel Sheet Column Number
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        int num = 0;
        int p = 1;
        for (int i = s.length()-1; i >= 0; --i) {
            num += (s.charAt(i)-'A'+1) * p;
            p *= 26;
        }
        return num;
    }

    public static void main(String ...args) {
        LC_171 lc_171 = new LC_171();
        System.out.println(lc_171.titleToNumber("ZY"));
    }
}
