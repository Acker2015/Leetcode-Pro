package lt_1_200;

/**
 * [44] Wildcard Matching
 */
public class LC_044 {
    /**
     * solution1
     * DP time O(m*n) space O(m*n)
     *
     * DP[i][j] 表示s中前i个和p中前j个是否可以匹配
     * 如果 p[j-1]=='?' || s[i-1]==p[j-1] => dp[i][j] = dp[i-1][j-1] (字符匹配)
     * 如果 p[j-1]=='*'
     *          1. *不匹配字符 dp[i][j] = dp[i][j-1]
     *          2. *匹配一个字符 dp[i][j] = dp[i-1][j-1]
     *          3. *匹配多个字符 dp[i][j] = dp[i-1][j]
     *
     * 初始状态
     * dp[0][0] = true(未开始匹配)
     * dp[i][0] = false (i >= 1), pattern为空，s不为空，匹配失败
     * dp[0][j] = dp[0][j-1] && p[j-1]=='*'
     */
    public boolean isMatch1(String s, String p) {
        int len1 = s.length(), len2 = p.length();
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        for (int j=1; j <= len2; ++j) {
            if (p.charAt(j-1)=='*') {
                dp[0][j] = dp[0][j-1];
            } else {
                break;
            }
        }
        for (int i = 1; i <= len1; ++i) {
            for (int j = 1; j <= len2; ++j) {
                char sc = s.charAt(i-1), pc = p.charAt(j-1);
                if (sc == pc || pc == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (pc == '*') {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j-1] || dp[i-1][j];
                }
            }
        }
        return dp[len1][len2];
    }


    /**
     * greedy
     * O(m+n)
     * 使用双指针i, j分别表示s,p中的匹配位置
     * 1. 如果匹配，那么i, j后移(i++, j++)
     * 2. 如果j位置为'*', 那么记录'*'出现的位置，以及此时i的位置
     *          此时默认*匹配0个元素，将j后移进行后续的正常比较
     *          如果出现不匹配的情况，那么找到上一个'*'的位置，使得其匹配的字符个数+1
     * exp:
     * s: baab
     * p: *ab
     *
     * 1. i=0, j=0, 遇到'*'，记录*的位置idx=0, 默认匹配0个字符，so j++
     * 2. i=0, j=1  此时i和j不匹配，找到上一个*的位置，增加其匹配的元素个数
     *              i = 1, j = 1
     *
     * 3. i=1, j=1  元素匹配， i++; j++;
     * 4. i=2, j=2  此时i和j不匹配，找到上一个*的位置，增加其匹配的元素个数
     *              i = 2, j = 1
     * 5. i=2, j=1  元素匹配， i++; j++
     * 6. i=3, j=2  元素匹配， i++; j++
     * 7. 结束
     *
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int idx = -1, match = -1;
        int i = 0, j = 0;
        while (i < s.length()) {
            // s[i] can match p[j]
            if (j < p.length() && (p.charAt(j)=='?' || s.charAt(i) == p.charAt(j))) {
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                idx = j;
                match = i; // 记录s中与*匹配的起始位置
                j++;
            } else if (idx >= 0) { // 都不匹配，回退到上一个'*'的状态, 使得*多匹配一个字符，在重新后续匹配
                j = idx + 1; // pattern回到上一个*的第一个匹配位置
                match++; // 上一个'*'多匹配s中的一个字符
                i = match;
            } else {
                return false;
            }
        }
        while (j < p.length() && p.charAt(j)=='*') j++;
        return j == p.length();
    }
}
