package lt_400_499;

/**
 * [405] Convert a Number to Hexadecimal
 */
public class LC_405 {

    /**
     * 直接使用无符号右移>>>, 无论正数还是负数，高位通通补0
     */
    static class Solution1 {
        private static char[] charMap = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        public String toHex(int num) {
            StringBuilder builder = new StringBuilder();
            while (num != 0) {
                builder.insert(0, charMap[num&15]);
                num >>>= 4;
            }
            return builder.toString();
        }
    }

    /**
     * 正常取反、加一的套路
     */
    static class Solution2 {
        private char toH(int x) {
            if (x < 10) {
                return (char)(x+'0');
            } else {
                return (char)((x-10)+'a');
            }
        }
        /**
         * 正数按照正常求法
         * 负数注意两点 (取反加一，最高位变为符号位)
         *      1. 取反，使用carry变量
         *      2. 最高位变为符号位
         */
        public String toHex(int num) {
            if (num == 0) return "0";
            if (num == Integer.MIN_VALUE) return "80000000";
            StringBuilder builder = new StringBuilder();
            boolean flag = num < 0;
            int carry = 1, idx = 0;
            num = Math.abs(num);
            while (num!=0) {
                int ans = num%16;
                if (flag) {
                    ans = 15 - ans + carry;
                    carry = ans/16;
                    ans %= 16;
                    if (idx == 7) {
                        ans += 8;   // 符号位 1000
                    }
                }
                builder.insert(0, toH(ans));
                num /= 16;
                idx++;
            }
            while (flag && builder.length() < 8) {
                builder.insert(0, 'f');
            }
            return builder.toString();
        }
    }
}
