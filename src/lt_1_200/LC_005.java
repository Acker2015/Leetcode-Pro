package lt_1_200;

/**
 * [5] Longest Palindromic Substring
 */
public class LC_005 {
    private char[] chs;
    public int maxPalindrome(int l, int r) {
        while (l >= 0 && r < chs.length && chs[l] == chs[r]) {
            l--;r++;
        }
        return r-l-1;
    }

    /**
     * solution1
     * O(N^2)
     * 对遍历的每一个位置都当成回文串的中间位置，two pointer两边扩展来获取最大的回文串
     * 1. i为中心，长度为奇数
     * 2. i和(i+1)为中心，长度为偶数
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len <= 1) return s;
        chs = s.toCharArray();
        int left = 0, right = 0;
        for (int i = 0; i < len; ++i) {
            int l1 = maxPalindrome(i,i);
            int l2 = maxPalindrome(i,i+1);
            int maxLen = l1 < l2 ? l2 : l1;
            if (maxLen > right-left+1) {
                left = i - (maxLen-1)/2;
                right = i + maxLen/2;
            }
        }
        return s.substring(left, right+1);
    }

    /**
     * dp[i][j] 可以通过dp[i+1][j-1]来判断
     * 1. s[i] != s[j]  => dp[i][j] = 0;
     *
     * 2. s[i] == s[j]  => 2.1 如果i,j相邻(i+1==j),那么dp[i][j]=2
     *                  => 2.2 否则需要判断dp[i+1][j-1]是否为回文子串
     *                          => 2.2.1 dp[i+1][j-1]是回文子串，那么dp[i][j]=dp[i+1][j-1]+2
     *                          => 2.2.2 dp[i+1][j-1]不是回文子串, 那么dp[i][j]=0
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        int len = s.length();
        if (len <= 1) return s;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; ++i) {
            dp[i][i] = 1;
        }
        char[] chs = s.toCharArray();
        int begin = 0, maxLen = 1;
        // dp[i][j] -> dp[i+1][j-1]
        for (int i=len-1; i>=0; --i) {
            for (int j=i+1; j<len; ++j) {
                if (chs[i] == chs[j]) {
                    // 考虑两个情况，1就是i,j相邻的时候，2就是不相邻就是要dp[i+1][j-1]是否为Palindrome
                    if (i+1 == j) {
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i+1][j-1]>0 ? dp[i+1][j-1]+2 : 0;
                    }
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        begin = i;
                    }
                }
            }
        }
        return s.substring(begin, begin+maxLen);
    }

    public static void main(String ...args) {
        LC_005 lc_005 = new LC_005();
        System.out.println(lc_005.longestPalindrome("babad"));
    }
}
