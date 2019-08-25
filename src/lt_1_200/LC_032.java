package lt_1_200;

public class LC_032 {
    /*
     * @lc app=leetcode id=32 lang=java
     *
     * [32] Longest Valid Parentheses
     */
    class Solution {
        /**
         * DP
         *
         * dp[i] means longest valid parentheses ending with index i.
         * dp[i]表示索引i结尾的最长valid parentheses的长度
         * so
         * 1. s[i]='(', dp[i] = 0
         * 2. s[i]=')'
         *      2.1 s[i-1]='(', dp[i] = dp[i-2]+2
         *      2.2 s[i-1]=')' && s[i-1-dp[i-1]]='(', then dp[i]=dp[i-1]+2+dp[i-dp[i-1]-2]
         *          其中dp[i-dp[i-1]-2]是s[i]和s[i-1-dp[i-1]]配对之后，前一个索引处的最长匹配
         */
        public int longestValidParentheses(String s) {
            int len = s.length();
            if (len <= 1) return 0;
            int[] dp = new int[len];
            char[] chs = s.toCharArray();
            int maxLen = 0;
            for (int i = 1; i < len; ++i) {
                if (chs[i]==')') {
                    if (chs[i-1]=='(') {
                        dp[i] = 2 + (i-2>=0 ? dp[i-2] : 0);
                    } else if (i-1-dp[i-1]>=0 && chs[i-1-dp[i-1]]=='(') {
                        dp[i] = 2 + dp[i-1] + (i-dp[i-1]-2>=0 ? dp[i-dp[i-1]-2]:0);
                    }
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
            return maxLen;
        }
    }
}
