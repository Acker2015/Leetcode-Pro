package lt_1100_1199;

/**
 * [1147] Longest Chunked Palindrome Decomposition
 *
 * 贪心正确性证明
 * from Lee
 * https://leetcode.com/problems/longest-chunked-palindrome-decomposition/discuss/350560/JavaC%2B%2BPython-Easy-Greedy-with-Prove
 *
 * DP 或者 贪心
 */
public class LC_1147 {

    /**
     * solution1
     * greedy - recursive
     *
     * 出现首尾子串相同的时候就立即匹配
     * @param text
     * @return
     */
    public int longestDecomposition1(String text) {
        return helper(text, 0, text.length()-1);
    }
    public int helper(String text, int left, int right) {
        if (left > right) return 0;
        int len = right - left + 1;
        for (int i = left; i < left+len/2; ++i) {
            int subLen = i - left + 1;
            if (text.charAt(left) == text.charAt(right-subLen+1)) {
                if (text.substring(left, i+1).equals(text.substring(right-subLen+1, right+1))) {
                    return helper(text, i+1, right-subLen) + 2;
                }
            }
        }
        return 1;
    }

    /**
     * solution2
     * greedy - iterator
     *
     * @param text
     * @return
     */
    public int longestDecomposition2(String text) {
        int len = text.length();
        // lastI, lastJ记录上次匹配的位置
        int i = 0, j = len-1, lastI = 0, lastJ = len-1;
        int num = 0;
        while (i < len/2) {
            if (text.charAt(lastI) == text.charAt(j)) {
                if (text.substring(lastI, i+1).equals(text.substring(j, lastJ+1))) {
                    num+=2;
                    lastI = i+1;
                    lastJ = j-1;
                }
            }
            i++;j--;
        }
        // 如果还存在字符没有匹配，说明独立成为中心子串，个数增1
        if (lastI <= lastJ) {
            num += 1;
        }
        return num;

    }

    /**
     * solution3
     * DP
     * 循环O(N^2), 取子串O(N)
     * 效率 O(N^3)
     *
     * DP dp[i] means [0,i] and [len-i-1, len-1] has completed match
     * dp[i] = Math.max(dp[i], dp[k]+2), where dp[k]>0||k==0
     * 为方便表示 索引提高一位
     */
    public int longestDecomposition(String text) {
        int len = text.length();
        if (len <= 1) return len;
        int[] dp = new int[len/2+1];
        int max = 0;
        for (int j = 0; j < len/2; ++j) {
            for (int i = 0; i <= j; ++i) {
                //
                if (text.charAt(i) == text.charAt(len-j-1) && (i == 0 || dp[i] > 0)) {
                    if (text.substring(i, j+1).equals(text.substring(len-j-1, len-i))) {
                        dp[j+1] = Math.max(dp[j+1], dp[i]+2);
                        max = Math.max(max, dp[j+1]);
                    }
                }
            }
        }
        if (len%2==0 && dp[len/2]!=0) {
            return dp[len/2];
        } else {
            return max + 1;
        }
    }
}
