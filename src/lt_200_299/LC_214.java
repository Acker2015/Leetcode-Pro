package lt_200_299;

/**
 * [214] Shortest Palindrome
 * 此题相当于在s中寻找最长的Palindrome子串，并且此子串是从0开始
 *
 */
public class LC_214 {
    /**
     * solution1
     * 使用二维dp求出来从0开始的最长回文子串
     *
     * 这种解法空间超了
     * out-of-memory
     */
    static class Solution {
        public String shortestPalindrome(String s) {
            int len = s.length();
            if (len <= 1) return s;
            boolean[][] dp = new boolean[len][len];
            char[] chs = s.toCharArray();
            for (int i = 0; i < len; ++i) {
                dp[i][i] = true;
                if (i < len-1 && chs[i]==chs[i+1]) {
                    dp[i][i+1] = true;
                }
            }
            for (int l = 3; l <= len; ++l) {
                for (int i = 0; i <= len-l; ++i) {
                    int j = i+l-1;
                    if (chs[i] == chs[j]) {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
            }
            int maxIdx = 0;
            for (int i = 1; i < len; ++i) {
                if (dp[0][i]) {
                    maxIdx = i;
                }
            }
            StringBuilder builder = new StringBuilder();
            for (int i = len-1; i > maxIdx; --i) {
                builder.append(chs[i]);
            }
            return builder.toString() + s;
        }
    }

    /**
     * Solution2
     *
     * O(N^2) 找到从0开始的最长回文子串
     */
    public static class Solution2 {
        public String shortestPalindrome(String s) {
            int len = s.length();
            if (len <= 1) return s;
            char[] chs = s.toCharArray();
            StringBuilder builder = new StringBuilder();
            int pIdx = 0;
            for (int j = len-1; j >= 0; --j) {
                if (chs[0] == chs[j]) {
                    int b = 1, e = j-1;
                    // 匹配就继续往中间靠
                    while (b < e && chs[b] == chs[e]) {
                        b++;
                        e--;
                    }
                    // 如果是回文子串那么直接跳出
                    if (b >= e) {
                        pIdx = j;
                        break;
                    }
                }
            }
            for (int i = len-1; i > pIdx; --i) {
                builder.append(chs[i]);
            }
            return builder.toString()+s;
        }
    }

    /**
     * 解法类似solution2, 找最长子串的方式略有差异
     */
    public class Solution3 {
        public String shortestPalindrome2(String s) {
            int len = s.length();
            if (len <= 1) return s;
            char[] chs = s.toCharArray();
            int i = 0, j = len-1, end = len-1;
            while (i < j) {
                if (chs[i] == chs[j]) {
                    i++;
                    j--;
                } else {
                    i = 0;
                    end--;
                    j = end;
                }
            }
            return new StringBuilder().append(s.substring(end+1)).reverse().append(s).toString();
        }
    }

    public class Solution4 {
        /**
         * KMP
         * 待补充
         * https://leetcode.com/problems/shortest-palindrome/discuss/60113/Clean-KMP-solution-with-super-detailed-explanation
         */
    }

    public static void main(String[] args) {
        String s = "aacecaaa";
        LC_214 lc_214 = new LC_214();
        Solution2 solution = new Solution2();
        System.out.println(solution.shortestPalindrome(s));
    }
}
