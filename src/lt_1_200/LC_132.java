package lt_1_200;

/**
 * [132] Palindrome Partitioning II
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.

 Example:

 Input: "aab"
 Output: 1
 Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class LC_132 {

    /**
     * dp[i]表示到索引i的最小分割次数
     * 注意如果i已经到达了最后，那么最后一次是不计算分割的
     * 但是这里我们统一将最后一次记上，最后节点将最后一次的分割减掉即可(因为这种情况下末尾肯定会有一刀)
     *
     * 如果 [0,i]是回文串，那么 dp[i] = 1
     * 否则中间找到一个点j, 使得(j, i]是回文串， 此时 dp[i] = dp[j]+1
     *
     * 这里我们使用 dp[len+1], dp[i]表示[0, i)切分为各个回文串所需要的最小次数
     *
     * 这里借助一个二维数据来存储历史遍历过的回文串。对于每次遇到需要判断s.subtring(i, j)是否是回文串的时候
     * s.substring(i+1, j-1)是否为回文串在上一轮都已经计算过，所以可以直接使用
     *
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        int len = s.length();
        if (len <= 1) return 0;
        // 协助保存历史回文信息
        // 遍历到i的时候， 之前的substr[j, i-1]是否为回文串都已经判断过
        boolean[][] isPal = new boolean[len][len];
        int[] dp = new int[len+1];
        for (int i = 1; i <= len; ++i) {
            dp[i] = len;
            isPal[i-1][i-1] = true;
        }
        for (int i = 1; i <= len; ++i) {
            for (int j = 0; j < i; ++j) {
                // substring [j, i-1]
                if ((s.charAt(j)==s.charAt(i-1) && (j+1>=i-2 || isPal[j+1][i-2]))) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                    isPal[j][i-1] = true;
                }
            }
        }
        return dp[len]-1;
    }

    public static void main(String[] args) {
        String s = "adacaacad";
        System.out.println(new LC_132().minCut(s));
    }
}
