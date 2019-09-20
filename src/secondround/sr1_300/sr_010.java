package secondround.sr1_300;

public class sr_010 {
    /**
     * DP
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        boolean[][] dp = new boolean[slen+1][plen+1];
        dp[0][0] = true;
        // s is not empty, p is empty. dp[i][0] = false, it is the default value.
        // s is empty, p should be match "";
        for (int j = 2; j <= plen; ++j) {
            if (p.charAt(j-1)=='*' && dp[0][j-2]) {
                dp[0][j] = true;
            }
        }
        for (int i = 1; i <= slen; ++i) {
            for (int j = 1; j <= plen; ++j) {
                char sc = s.charAt(i-1);
                char pc = p.charAt(j-1);
                if (sc == pc || pc == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (pc == '*') {
                    // don't match, dp[i][j-2]
                    // math one time, dp[i-1][j-2]
                    // math more than one time, dp[i-1][j]
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j];
                }
            }
        }
        return dp[slen][plen];
    }

    // [1,8,6,2,5,4,8,3,7]
}
