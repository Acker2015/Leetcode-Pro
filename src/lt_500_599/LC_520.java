package lt_500_599;

public class LC_520 {
    /**
     * @lc app=leetcode id=520 lang=java
     *
     * [520] Detect Capital
     */
    class Solution {
        public boolean detectCapitalUse(String word) {
            if (word.length() <= 1) return true;
            char[] chs = word.toCharArray();
            if (Character.isUpperCase(chs[1]) && Character.isLowerCase(chs[0])) {
                return false;
            }
            boolean isUpper = Character.isUpperCase(chs[1]);
            for (int i = 2; i < chs.length; ++i) {
                if (Character.isUpperCase(chs[i]) != isUpper) {
                    return false;
                }
            }
            return true;
        }
    }
}
