package lt_400_499;

public class LC_424 {
    /*
     * @lc app=leetcode id=424 lang=java
     *
     * [424] Longest Repeating Character Replacement
     * 窗口里除了出现次数最多的那个字符，其他字符的个数不能超过k，构造滑动窗口的方法来解题
     */
    class Solution {
        // slide window - O(n)
        public int characterReplacement(String s, int k) {
            int maxLen = 0;
            int i = 0, j = 0, maxCnt = 0;   // maxCnt记录窗口单字符最长长度
            int[] mem = new int[26];
            while (j < s.length()) {
                char c = s.charAt(j++);
                mem[c-'A']++;
                if (mem[c-'A'] > maxCnt) {
                    maxCnt = mem[c-'A'];
                }
                while (j - i - maxCnt > k) {
                    mem[s.charAt(i++) - 'A']--;
                    // 这里为什么不必要更新maxCnt, 因为记录的是最大窗口长度，窗口中的单字符最长长度只有增加了才会使得窗口长度增加
                    // and we only grow the window when the count of new char exceeds the historical max char of single char.
                    // so we do not need the update the maxCnt in time, just to get the longer window.

                    // maxCnt = 0;
                    // for (int t = 0; t < 24; ++t) {
                    //     maxCnt = Math.max(maxCnt, mem[t]);
                    // }
                }
                maxLen = Math.max(maxLen, j-i);
            }
            return maxLen;
        }
    }
}
