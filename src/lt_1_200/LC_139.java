package lt_1_200;


import java.util.*;

/**
 * [139] Word Break
 */
public class LC_139 {

    public boolean wordBreak1(String s, List<String> wordDict) {
        int len = s.length();
        if (s.length() <= 0) return true;
        HashSet<String> hashSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[len];
        for (int i = 0; i < len; ++i) {
            for (int j = -1; j < i; ++j) {
                if ((j<0 || dp[j]) && hashSet.contains(s.substring(j+1, i+1))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len-1];
    }

    /**
     * 进一步优化版本
     * 第二次遍历判断dp[i]是否为true的时候，根据word的最大最小长度来限定遍历范围
     * dp[i] 表示从0->i是否能够被wordDict中的word完美分割
     * dp[i] = dp[j] && s[j+1, i] in wordDict
     *
     * 第二轮遍历不要从0-j遍历，根据wordDict中word的长度区间来降低复杂度
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        int len = s.length();
        if (s.length() <= 0) return true;
        HashSet<String> hashSet = new HashSet<>(wordDict);
        int maxLen = Integer.MIN_VALUE, minLen = Integer.MAX_VALUE;
        for (String word: wordDict) {
            int ansLen = word.length();
            if (ansLen > maxLen) maxLen = ansLen;
            if (ansLen < minLen) minLen = ansLen;
        }
        boolean[] dp = new boolean[len];
        for (int i = 0; i < len; ++i) {
            // j+1 = i+1 => j=i-maxLen
            // j+1 = i+1 => j+minLen<=i
            for (int j = Math.max(-1, i-maxLen); j + minLen <= i; ++j) {
                if ((j<0 || dp[j]) && hashSet.contains(s.substring(j+1, i+1))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len-1];
    }






    // dp[i] = dp[j] && s[j+1, i] in wordDict
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String word: wordDict) {
            set.add(word);
        }
        int maxLen = Integer.MIN_VALUE, minLen = Integer.MAX_VALUE;
        for (String word: wordDict) {
            int ansLen = word.length();
            if (ansLen > maxLen) maxLen = ansLen;
            if (ansLen < minLen) minLen = ansLen;
        }
        int len = s.length();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        for (int i = 1; i <= len; ++i) {
            for (int j = Math.max(i-maxLen, 0); j+minLen <= i; ++j) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[len];
    }


    public static void main(String ...ats){
        LC_139 lc_139 = new LC_139();
        String s = "leetcode";
        String[] words = {"leet", "code"};
        System.out.println(lc_139.wordBreak(s, Arrays.asList(words)));
    }
}
