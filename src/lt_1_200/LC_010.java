package lt_1_200;

/**
 * [10] Regular Expression Matching
 *
 * solution1: 回溯,DFS
 * solution2: DP time O(m*n)
 *
 */
public class LC_010 {
    private int sLen, pLen;
    private String s, p;
    /**
     * 遇到四种状态
     * 1. lower letter
     * 2. lower letter + *
     * 3. .
     * 4. .*
     * sIndex, pIndex记录遍历索引
     */
    private boolean match(int sIndex, int pIndex) {
        if (sIndex >= sLen && pIndex >= pLen) return true;
        // pattern中出现可以自销的情况如"a*", 那么可以跳过
        if (sIndex == sLen && pIndex+2 <= pLen && p.charAt(pIndex+1) == '*'){
            return match(sIndex, pIndex+2);
        }
        if (sIndex >= sLen || pIndex >= pLen) return false;
        char sc = s.charAt(sIndex), pc = p.charAt(pIndex);
        if (pc == '.') {
            if (pIndex + 1 < pLen && p.charAt(pIndex+1) == '*') {
                return match(sIndex+1, pIndex) || match(sIndex, pIndex+2);
            } else {
                return match(sIndex+1, pIndex+1);
            }
        } else {
            if (pIndex+1 < pLen && p.charAt(pIndex+1) == '*') {
                if (sc != pc) {
                    return match(sIndex, pIndex+2);
                } else {
                    return match(sIndex+1, pIndex) || match(sIndex, pIndex+2);
                }
            } else {
                return sc == pc && match(sIndex+1, pIndex+1);
            }
        }
    }
    /**
     * solution1
     * 根据遇到的四种状态进行回溯
     * backtracking
     */
    public boolean isMatch1(String s, String p) {
        this.sLen = s.length();
        this.pLen = p.length();
        this.s = s;
        this.p = p;
        return match(0, 0);
    }

    /**
     * solution2 DP
     * DP[i][j] 表示s中前i个和p中前j个是否可以匹配
     *
     * 1. s[i-1]==p[j-1] || p[j-1] == '.' => dp[i][j] = dp[i-1][j-1]
     * 2. p[j-1]=='*'
     *      2.1 s[i-1] != p[j-2] && p[j-2] != '.', 此时这个*匹配不到s中的元素， dp[i][j] = dp[i][j-2]
     *      2.2 else 此时可以匹配s[i-1],这之后有三种选择咯
     *              one: 不匹配 dp[i][j] = dp[i][j-2]
     *              two: 仅匹配一次 dp[i][j] = dp[i-1][j-2]
     *              three: 匹配多次 dp[i][j] = dp[i-1][j]
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null) return false;

        int m = s.length();
        int n = p.length();

        // M[i][j] represents if the 1st i characters in s can match the 1st j characters in p
        boolean[][] M = new boolean[m + 1][n + 1];

        // initialization:
        // 1. M[0][0] = true, since empty string matches empty pattern
        M[0][0] = true;

        // 2. M[i][0] = false(which is default value of the boolean array) since empty pattern cannot match non-empty string
        // 3. M[0][j]: what pattern matches empty string ""? It should be #*#*#*#*..., or (#*)* if allow me to represent regex using regex :P,
        // and for this case we need to check manually:
        // as we can see, the length of pattern should be even && the character at the even position should be *,
        // thus for odd length, M[0][j] = false which is default. So we can just skip the odd position, i.e. j starts from 2, the interval of j is also 2.
        // and notice that the length of repeat sub-pattern #* is only 2, we can just make use of M[0][j - 2] rather than scanning j length each time
        // for checking if it matches #*#*#*#*.
        for (int j = 2; j < n + 1; j += 2) {
            if (p.charAt(j - 1) == '*' && M[0][j - 2]) {
                M[0][j] = true;
            }
        }

        // Induction rule is very similar to edit distance, where we also consider from the end. And it is based on what character in the pattern we meet.
        // 1. if p.charAt(j) == s.charAt(i), M[i][j] = M[i - 1][j - 1]
        //    ######a(i)
        //    ####a(j)
        // 2. if p.charAt(j) == '.', M[i][j] = M[i - 1][j - 1]
        // 	  #######a(i)
        //    ####.(j)
        // 3. if p.charAt(j) == '*':
        //    1. if p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i), then b* is counted as empty. M[i][j] = M[i][j - 2]
        //       #####a(i)
        //       ####b*(j)
        //    2.if p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i):
        //       ######a(i)
        //       ####.*(j)
        //
        // 	  	 #####a(i)
        //    	 ###a*(j)
        //      2.1 if p.charAt(j - 1) is counted as empty, then M[i][j] = M[i][j - 2]
        //      2.2 if counted as one, then M[i][j] = M[i - 1][j - 2]
        //      2.3 if counted as multiple, then M[i][j] = M[i - 1][j]

        // recap:
        // M[i][j] = M[i - 1][j - 1]
        // M[i][j] = M[i - 1][j - 1]
        // M[i][j] = M[i][j - 2]
        // M[i][j] = M[i][j - 2]
        // M[i][j] = M[i - 1][j - 2]
        // M[i][j] = M[i - 1][j]
        // Observation: from above, we can see to get M[i][j], we need to know previous elements in M, i.e. we need to compute them first.
        // which determines i goes from 1 to m - 1, j goes from 1 to n + 1

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if (curS == curP || curP == '.') {
                    M[i][j] = M[i - 1][j - 1];
                } else if (curP == '*') {
                    char preCurP = p.charAt(j - 2);
                    if (preCurP != '.' && preCurP != curS) {
                        M[i][j] = M[i][j - 2];
                    } else {
                        /**
                         * 此时可以选择
                         * 1. 不匹配 M[i][j-2]
                         * 2. 仅匹配一次 M[i-1][j-2]
                         * 3. 匹配多次 M[i-1][j]
                         */
                        M[i][j] = (M[i][j - 2] || M[i - 1][j - 2] || M[i - 1][j]);
                    }
                }
            }
        }

        return M[m][n];
    }
}
