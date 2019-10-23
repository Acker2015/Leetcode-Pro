package other.O1Point3Acre.ms;

/**
 * 类似leetcode-516
 *
 * 给定一个字符串s，你可以从中删除一些字符，使得剩下的串是一个回文串。如何删除才能使得回文串最长呢？
 *
 * solution1
 * 反转字符串，两个字符串的最长公共子序列就是回文串的最大长度
 * s与rs
 * dp[i][j] 表示s中前i个元素以及rs中前j个元素的最长公共子序列长度
 * s[i] = rs[j] -> dp[i][j] = dp[i-1][j-1] +1
 * s[i] != rs[j] -> dp[i][j] = max{dp[i][j-1], dp[i-1][j]}
 *
 *
 * solution2
 * dp[i][j] 表示[i,j]构成回文串需要删除最少字符的个数
 * s[i] = [j] 问题转化为dp[i+1][j-1] -> dp[i][j] = dp[i+1][j-1]
 * s[i] != s[j] 问题转换为删除s[i]或者删除s[j], 那么 dp[i][j] = min{dp[i+1][j], dp[i][j-1]}+1
 */
public class M_004_DP {
    /**
     * solution1
     * 翻转之后求最大公共子序列
     * @param s
     * @return
     */
    public static int lenToCreatePlalindromeWithLeastDelete1(String s) {
        int len = s.length();
        if (len <= 1) {
            return len;
        }
        String rs = new StringBuilder(s).reverse().toString(); // 反转字符串
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < len; ++j) {
                if (s.charAt(i) == rs.charAt(j)) {
                    dp[i][j] = (i==0 || j==0) ? 1 : dp[i-1][j-1] + 1;
                } else {
                    int ans = 0;
                    if (i != 0) {
                        ans = Math.max(dp[i-1][j], ans);
                    }
                    if (j != 0) {
                        ans = Math.max(dp[i][j-1], ans);
                    }
                    dp[i][j] = ans;
                }
            }
        }
        return dp[len-1][len-1];
    }


    /**
     * solution2
     * dp[i][j] 表示[i,j]构成回文串需要删除最少字符的个数
     * s[i] = [j] 问题转化为dp[i+1][j-1] -> dp[i][j] = dp[i+1][j-1]
     * s[i] != s[j] 问题转换为删除s[i]或者删除s[j], 那么 dp[i][j] = min{dp[i+1][j], dp[i][j-1]}+1
     * @param s
     * @return
     */
    public static int lenToCreatePlalindromeWithLeastDelete2(String s) {
        int len = s.length();
        if (len <= 1) {
            return len;
        }
        int[][] dp = new int[len][len];
        for (int gap = 2; gap <= len; ++gap) {
            for (int k = 0; k+gap<=len; ++k) {
                int i = k, j = k+gap-1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1])+1;
                }
            }
        }
        return len - dp[0][len-1];
    }


    public static void main(String ...args) {
        System.out.println(M_004_DP.lenToCreatePlalindromeWithLeastDelete1("abaacaa"));
    }
}
