package secondround.sr1_300;

public class sr_032 {
    /**
     * dp
     * dp[i] means longest substring of valid parentheses ending with index i.
     *
     * 1. s[i]='(' -> dp[i]=0
     * 2. s[i]=')'
     *      2.1 s[i-1]='(' -> dp[i] = dp[i-2]+2
     *      2.2 s[i-1]=')' && s[i-1-dp[i-1]]='(' -> dp[i]=2+dp[i-1]+dp[i-2-dp[i-1]]
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int len = s.length();
        char[] chs = s.toCharArray();
        int[] dp = new int[len];
        int maxLen = 0;
        for (int i = 1; i < len; ++i) {
            if (chs[i] == ')') {
                if (chs[i-1] == '(') {
                    dp[i] = 2 + (i-2>=0 ? dp[i-2] : 0);
                } else if (i-1-dp[i-1]>=0 && chs[i-1-dp[i-1]]=='('){
                    dp[i] = 2 + dp[i-1] + (i-2-dp[i-1]>=0 ? dp[i-1-dp[i-1]]:0);
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }
}
