package other.O1Point3Acre.ms;

/**
 * 最少插入的字符个数，使原字符串变成回文串
 * dp[i][j]表示[i,j]之间表示的字符串变成回文串需要插入的最小字符数
 * s[i]=s[j] -> dp[i][j] = dp[i+1][j-1] (问题转化为[i+1, j-1]需要的插入的字符数)
 * s[i]!=s[j] -> dp[i][j] = min{dp[i+1][j], dp[i][j-1]}+1， 需要插入一个字符与dp[i]匹配，或者与dp[j]匹配
 */
public class M_003 {
    public static int createPlalindromeWithLeastInsert(String s) {
        if (s.length() <= 1) return 0;
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[][] dp = new int[len][len];
        for (int gap = 2; gap <= len; ++gap) {
            for (int l = 0; l + gap <= len; ++l) {
                int r = l+gap-1;
                if (chs[l] == chs[r]) {
                    dp[l][r] = dp[l+1][r-1];
                } else {
                    dp[l][r] = Math.min(dp[l+1][r], dp[l][r-1])+1;
                }
            }
        }
        return dp[0][len-1];
    }

    public static void main(String ...args) {
        System.out.println(M_003.createPlalindromeWithLeastInsert("ab"));
    }
}
