package secondround.sr1_300;

public class sr_005 {
    private int palindrome(char[] chs, int l, int r) {
        while (l >= 0 && r < chs.length && chs[l] == chs[r]) {
            l--;r++;
        }
        return r-l-1;
    }
    public String longestPalindrome1(String s) {
        char[] chs = s.toCharArray();
        if(chs.length <= 1) return s;
        int maxLen = 1, idx = 0;
        for (int i = 1; i < chs.length; ++i) {
            int len1 = palindrome(chs, i-1, i); // 偶数
            int len2 = palindrome(chs, i-1, i+1);
            if (len1 > maxLen) {
                maxLen = len1;
                idx = i - maxLen/2;
            }
            if (len2 > maxLen) {
                maxLen = len2;
                idx = i - maxLen/2;
            }
        }
        return String.valueOf(chs, idx, maxLen);
    }

    // DP
    public String longestPalindrome(String s) {
        char[] chs = s.toCharArray();
        if (chs.length <= 0) return s;
        int len = chs.length;
        int[][] dp = new int[len][len];
        int maxLen = 1, idx = 0;
        for (int i = 0; i < len; ++i) {
            dp[i][i] = 1;
            if (i + 1 < len && chs[i] == chs[i+1]) {
                dp[i][i+1]=2;
                maxLen = 2;
                idx = i;
            }
        }
        for (int l = 3; l <= len; ++l) {
            for (int i = 0; i + l <= len; i++) {
                int j = i + l - 1;
                if (chs[i] == chs[j] && dp[i+1][j-1] > 0) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    idx = i;
                }
            }
        }
        return String.valueOf(chs, idx, maxLen);
    }


    public static void main(String ...args) {
        String str = "cbbd";
        System.out.println(new sr_005().longestPalindrome("ccc"));
    }
}
