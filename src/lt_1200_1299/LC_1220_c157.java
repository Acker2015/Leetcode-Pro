package lt_1200_1299;

import java.util.Arrays;

/**
 * 1220. Count Vowels Permutation
 *
 * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
 Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')

 Each vowel 'a' may only be followed by an 'e'.                     (a后边只能跟着e)
 Each vowel 'e' may only be followed by an 'a' or an 'i'.           (e后边只能跟着a, i)
 Each vowel 'i' may not be followed by another 'i'.                 (i后边不能跟着i)
 Each vowel 'o' may only be followed by an 'i' or a 'u'.            (o后边只能跟着i或者u)
 Each vowel 'u' may only be followed by an 'a'.                     (u后边只能跟着a)
 Since the answer may be too large, return it modulo 10^9 + 7.      (结果对10^9+7取余)

 1. a可以在e,i,u后边
 2. e可以再a,i后边
 3. i可以再e,o后边
 4. o可以再i后边
 5. u可以再i,o后边


 Example 1:

 Input: n = 1
 Output: 5
 Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
 Example 2:

 Input: n = 2
 Output: 10
 Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
 Example 3:

 Input: n = 5
 Output: 68


 Constraints:

 1 <= n <= 2 * 10^4
 */
public class LC_1220_c157 {
    private char[] viwels = {'a', 'e', 'i', 'o', 'u'};
    private final static int MOD = 1000000007;

    /**
     *
     * DP
     1. a可以在e,i,u后边
     2. e可以再a,i后边
     3. i可以再e,o后边
     4. o可以再i后边
     5. u可以再i,o后边
     * @param n
     * @return
     */
    public int countVowelPermutation(int n) {
        if (n == 0) return 0;
        if (n == 1) return 5;
        int[] pre = new int[128];
        int[] current = new int[128];
        for (char c: viwels) {
            pre[c] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            current['a'] = ((pre['e']+pre['i'])%MOD+pre['u'])%MOD;
            current['e'] = (pre['a']+pre['i'])%MOD;
            current['i'] = (pre['e']+pre['o'])%MOD;
            current['o'] = pre['i'];
            current['u'] = (pre['i']+pre['o'])%MOD;
            int[] tmp = pre;
            pre = current;
            current = tmp;
            Arrays.fill(current, 0);
        }
        int ans = 0;
        for (char c: viwels) {
            ans = (ans + pre[c])%MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        LC_1220_c157 solution = new LC_1220_c157();
        System.out.println(solution.countVowelPermutation(144));
    }
}
