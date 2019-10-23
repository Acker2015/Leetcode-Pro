package lt_1_200;

public class LC_072 {
    /**
     * 假设word1(0,i)表示word1从0到i位的子串
     dp[i][j] 表示word1(0,i)变化到word2(0,j)所需要的最小的步数，
     能够执行的操作有
        1. 不执行操作
        2. add操作
        3. delete操作
        4. change操作

     ＝》不执行操作的条件是 word1[i] = word2[j] => dp[i][j] = dp[i-1][j-1]
     ＝》那么如果word1[i] != word2[j], 那么dp[i][j]将会是执行增删改操作的最小值
     1. 改操作 dp[i][j] = dp[i-1][j-1]+1, 即将word1的第i位改成word2[j]
     2. 增操作 dp[i][j] = dp[i][j-1] +1, 说明：dp[i][j-1]为word1变成word2的操作次数，也就是两者经过操作之后长度一致了，现在需要给word2添加一位之后，由于word1下标不变，所以add操作就很正常了
     3. 删操作 dp[i][j] = dp[i-1][j] + 1
     所以 dp[i][j] = min(dp[i-1][j-1], dp[i][j-1], dp[i-1][j])+1;就是word1[i]!=word2[j]时的状态转移方程
     -> dp[i][j] = dp[i-1][j-1]（word1[i] == word2[j]）
     -> dp[i][j] = min(dp[i-1][j-1], dp[i][j-1], dp[i-1][j])+1 ( word1[i] != word2[j] )

     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        if (len1 == 0) return len2;
        if (len2 == 0) return len1;
        char[] chs1 = word1.toCharArray();
        char[] chs2 = word2.toCharArray();

        int[][] dp = new int[len1][len2];
        boolean flag = false;
        // init first row -> dp[0][j]
        for (int j = 0; j < len2; ++j) {
            flag = flag || chs1[0]==chs2[j];
            if (flag) {
                dp[0][j] = j;
            } else {
                dp[0][j] = j+1;
            }
        }
        flag = false;
        // init first column -> dp[i][0]
        for (int i = 0; i < len1; ++i) {
            flag = flag || chs1[i]==chs2[0];
            if (flag) {
                dp[i][0] = i;
            } else {
                dp[i][0] = i + 1;
            }
        }
        for (int i = 1; i < len1; ++i) {
            for (int j = 1; j < len2; ++j) {
                if (chs1[i] == chs2[j]) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // change/update: dp[i][j] = dp[i-1][j-1]
                    // insert: dp[i][j] = dp[i][j-1]
                    // delete: dp[i][j] = dp[i-1][j]
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                }
            }
        }
        return dp[len1-1][len2-1];
    }
}
