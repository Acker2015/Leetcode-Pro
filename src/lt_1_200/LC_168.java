package lt_1_200;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/24
 */
public class LC_168 {
    /**
     * 解法一：
     * 注意需要将索引从1开始，置为从0开始
     * @param n
     * @return
     */
    public String convertToTitle1(int n) {
        if (n <= 0) return "";
        StringBuilder sbd = new StringBuilder();
        while (n > 0) {
            n--;
            sbd.insert(0, (char)(n%26+'A'));
            n = n/26;
        }
        return sbd.toString();
    }

    /**
     * 解法二：
     * 递归
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        if (n <= 0) return "";
        return convertToTitle(--n/26) + (char)(n%26+'A');
    }

    public static void main(String ...args) {
        LC_168 lc_168 = new LC_168();
        System.out.println(lc_168.convertToTitle(701));
    }
}
