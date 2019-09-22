package lt_1_200;

/**
 * DP
 * [115] Distinct Subsequences
 */
public class LC_115 {
    /**
     * dp[i][j] means the num of subsequence of s[0,i] equals t[0,j]
     *
     * s[i]!=t[j] -> dp[i][j] = dp[i-1][j]
     * s[i]==t[j] -> dp[i][j] = dp[i-1][j-1]+dp[i-1][j]   (分为子串以i为结尾dp[i-1][j-1], 以及不以i结尾dp[i][j-1])

     */
    public int numDistinct(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        if (len1 == 0 || len2 == 0) return 0;
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();

        int[][] dp = new int[len1][len2];
        // init dp[i][0]
        int num = 0;
        for (int i = 0; i < len1; ++i) {
            if (chs[i]==cht[0]) num++;
            dp[i][0] = num;
        }
        for (int i= 1; i < len1; ++i) {
            for (int j = 1; j < len2; ++j) {
                if (chs[i] != cht[j]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                }
            }
        }
        return dp[len1-1][len2-1];
    }
}
