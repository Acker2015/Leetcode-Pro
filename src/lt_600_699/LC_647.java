package lt_600_699;

/**
 * [647] Palindromic Substrings
 */
public class LC_647 {
    /**
     * Solution1
     * DP
     * dp[i][j] 表示i-j是否可以构成回文串
     * dp[i][j] = s[i]==s[j] && dp[i+1][j-1]
     * init:
     *      dp[i][i]=true
     * @param s
     * @return
     */
    public int countSubstrings1(String s) {
        // dp[i][j]
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; ++i) {
            dp[i][i] = true;
        }
        char[] chs = s.toCharArray();
        int sum = len;
        for (int k = 2; k <= len; ++k) {
            for (int i = 0; i+k<=len; ++i) {
                int j = i+k-1;
                dp[i][j] = chs[i] == chs[j] && (k <= 2 || dp[i+1][j-1]);
                if (dp[i][j]) sum++;
            }
        }
        return sum;
    }
    /**
     * Solution2
     *
     * String
     * 判断Palindromic可以使用左右均速向外expand的解法，遍历中心点即可
     */
    public int countSubstrings(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int cnt = 0;
        if (len <= 1) return len;
        for (int i = 0; i < len; ++i) {
            cnt += expandPalindromic(chs, i, i); // odd
            cnt += expandPalindromic(chs, i, i+1); // even
        }
        return cnt;
    }
    private int expandPalindromic(char[] chs, int i, int j) {
        int cnt = 0;
        while (i >= 0 && j < chs.length && chs[i]==chs[j]) {
            i--;
            j++;
            cnt++;
        }
        return cnt;
    }
}
