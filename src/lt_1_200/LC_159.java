package lt_1_200;

import java.util.HashMap;
import java.util.Map;

/**
 * lc-159
 * Longest Substring with At Most Two Distinct Characters
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 */
public class LC_159 {
    /**
     * 滑动窗口 slide window
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int start = 0, end = 0, count = 0, maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {
            char cur = s.charAt(end++);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            if (map.get(cur) == 1) {
                count++;
            }
            while (count > 2) {
                char prev = s.charAt(start++);
                map.put(prev, map.get(prev)-1);
                if (map.get(prev) == 0) {
                    count--;
                }
            }
            maxLen = Math.max(maxLen, end - start);
        }
        return maxLen;
    }

    public static void main(String ...args) {
        String str = "AABBCCCAB";
        System.out.println(new LC_159().lengthOfLongestSubstringTwoDistinct(str));
    }
}
