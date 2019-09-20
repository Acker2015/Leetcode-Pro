package secondround.sr1_300;

/**
 * 使用位操作进行处理，不断增大divisor
 */
public class sr_029 {
    /**
     * @lc app=leetcode id=29 lang=java
     *
     * [29] Divide Two Integers
     */
    class Solution {
        public int divide(int dividend, int divisor) {
            if (dividend == 0) return 0;
            boolean flag = (dividend>0&&divisor>0) || (dividend<0&&divisor<0);
            long a = Math.abs((long)dividend);
            long b = Math.abs((long)divisor);
            long ans = b;

            int cnt = 1;
            long ret = 0;
            while (a > 0) {
                if (a >= b) {
                    a -= b;
                    ret += cnt;
                    // increase b
                    if (a > b) {
                        b <<= 1;
                        cnt *= 2;
                    }
                } else{
                    if (b <= ans) {
                        break;
                    }
                    while (a < b && b > ans) {
                        b >>= 1;
                        cnt = cnt / 2;
                    }
                }
            }
            ret = flag ? ret : -ret;
            if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            return (int)ret;
        }
    }
}
