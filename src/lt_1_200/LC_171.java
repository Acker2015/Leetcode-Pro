package lt_1_200;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/27
 */
public class LC_171 {
    public static class Solution1 {
        /**
         * Excel Sheet Column Number
         *
         * 迭代
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
    }

    public static class Solution2 {
        /**
         * 递归
         */
        public int titleToNumber(String s) {
            if (s.length() <= 0) return 0;
            int len = s.length();
            return (s.charAt(len-1)-'A'+1) + titleToNumber(s.substring(0, len-1))*26;

        }
    }

    public static void main(String ...args) {
        LC_171 lc_171 = new LC_171();
        System.out.println(new LC_171.Solution1().titleToNumber("ZY"));
    }
}
