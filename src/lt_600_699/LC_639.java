package lt_600_699;


/**
 * [639] Decode Ways II
 *
 * DP - hard
 */
public class LC_639 {
    private static int MOD = 1000000007;
    /**
     * DP思路类似 LC91:decode-way
     * dp[i] 表示s[0,i]范围可以多少种编码方式
     * 由于每个编码范围为1-26，所以最多有两位
     * 取1位的时候利用到dp[i-1], 取两位的时候用到dp[i-2]
     * dp[i] = dp[i-1]*num{s[i]} + dp[i-2]*num{s[i-1]s[i]}
     *
     * num{s[i]}表示s[i]对应的decode个数
     * num{s[i-1]s[i]}表示s.substring(i-1.i+1)对应的decode个数
     */
    public int numDecodings(String s) {
        int len = s.length();
        if (len <= 0 || s.charAt(0) == '0') {
            return 0;
        }
        char[] chs = s.toCharArray();
        long[] dp = new long[len];
        dp[0] = chs[0]=='*' ? 9 : 1;

        for (int i = 1; i < len; ++i) {
            long ans = 0;
            char c = s.charAt(i);
            // dp[i-1]*chs[i] -> 将chs[i]当成单独一位来decode
            if (c != '0') {
                ans += dp[i-1]*(c == '*' ? 9 : 1);
            }
            // dp[i-2]*chs{i-1,i} -> 将chs{i-1,i}当成两位来decode
            ans += (i>1?dp[i-2]:1) * numOfTwo(chs[i], chs[i-1]);
            dp[i] = ans % MOD;
        }
        return (int)dp[len-1];
    }

    /**
     * 只考虑两位
     * 1. 如果两位都是**，那么一共有15种可能，11->19  21-26
     * 2. 如果两位都不是**，那么如果两位组成数字在[10,26]则有1中可能，否则为0
     * 3. 如果第一位为*，第二位为数字n。
     *          3.1 当n小于等于6的时候，*可以取1和2，此时返回2
     *          3.1 当n大于6的时候，*只能取1，此时返回1
     * 4. 如果第一位为数字n，第二位为*
     *          4.1 如果n是1，那么*可以取1-9，此时返回9
     *          4.2 如果n是2，那么*可以取1-6，此时返回6
     *          4.3 否则，*没有值可取，此时返回0
     * "**", "n*", "*n"
     * @return
     */
    private int numOfTwo(char cur, char prev) {
        if (cur=='*' && prev=='*') {
            return 9+6;
        } else if (cur!='*' && prev!='*') {
            int val = (prev-'0')*10 + (cur-'0');
            return val>=10&&val<=26 ? 1 : 0;
        } else {
            // *n
            if (prev=='*') {
                return cur <= '6' ? 2 : 1;
            } else { // n*
                switch (prev) {
                    case '1':
                        return 9;
                    case '2':
                        return 6;
                    default:
                        return 0;
                }
            }
        }
    }

    public static void main(String ...args) {
        System.out.println(new LC_639().numDecodings("1**"));
    }
}
