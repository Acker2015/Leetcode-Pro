package lt_500_599;

/**
 * [516] Longest Palindromic Subsequence
 */
public class LC_516 {
    static class Solution1 {
        /**
         * 翻转之后就是LCS
         * 最长公共子序列
         */
        public int longestPalindromeSubseq(String s) {
            int len = s.length();
            if (len <= 1) return len;
            String rs = new StringBuilder().append(s).reverse().toString();
            // LCS
            int[][] dp = new int[len][len];
            char[] chs1 = s.toCharArray();
            char[] chs2 = rs.toCharArray();
            for (int i = 0; i < len; ++i) {
                for (int j = 0; j < len; ++j) {
                    if (chs1[i]==chs2[j]) {
                        dp[i][j] = (i<=0 || j <= 0) ? 1: dp[i-1][j-1]+1;
                    } else {
                        int ans = 0;
                        if (i > 0) {
                            ans = Math.max(ans, dp[i-1][j]);
                        }
                        if (j > 0) {
                            ans = Math.max(ans, dp[i][j-1]);
                        }
                        dp[i][j] = ans;
                    }
                }
            }
            return dp[len-1][len-1];
        }
    }




    /**
     * dp[i][j] 表示[i,j]子序列中回文串最大长度
     * s[i]=s[j] -> dp[i][j] = dp[i+1][j-1]+2
     * s[i]!=s[j] -> dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
     *
     * O(N^2)
     */
    public int longestPalindromeSubseq(String s) {
        if (s.length() <= 1) {
            return 1;
        }
        char[] chs = s.toCharArray();
        int[][] dp = new int[chs.length][chs.length];
        for (int i = 0; i < chs.length; ++i) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= chs.length; ++len) {
            for (int i = 0; i+len-1 < chs.length; ++i) {
                int j = i + len-1;
                if (chs[i] == chs[j]) {
                    dp[i][j] = 2 + (j-1>i ? dp[i+1][j-1] : 0);
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][chs.length-1];
    }

    public static void main(String ...args) {
        LC_516 lc_516 = new LC_516();
        System.out.println(lc_516.longestPalindromeSubseq("babbb"));
    }
}
