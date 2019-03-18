package lt_1_200;

public class LC_091 {
	/**
     * dp
     * dp[i] 表示s[0,i]范围可以多少种编码方式
     * 由于每个编码范围为1-26，所以最多有两位
     * 如果与前一位字符连一起结果大于26， 那么dp[i]=dp[i-1]
     * 否则 dp[i] = dp[i-1] + dp[i-2]
     */
    public int numDecodings(String s) {
        int len = s.length();
        if (len <= 0 || s.startsWith("0")) return 0;
        if (len == 1) return 1;

        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; ++i) {
            int ans = 0;
            // 本位如果为0， 不能独立编码，只能考虑跟前一位搭配
            if (s.charAt(i)!='0') {
                ans = dp[i-1];
            }
            int tmp = Integer.valueOf(s.substring(i-1, i+1));
            // 前一个字符不能为0，make sure s[i-1]!='0'
            if (tmp <= 26 && tmp >= 10) {
                ans += (i==1 ? 1 : dp[i-2]);
            }
            if (ans == 0) return 0;
            dp[i] = ans;
        }
        return dp[len-1];
    }
	public static void main(String[] args) {
		String string = "1001";
		LC_091 lc_091 = new LC_091();
		System.out.println(lc_091.numDecodings(string));

	}

}
