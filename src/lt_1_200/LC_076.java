package lt_1_200;

import java.util.HashMap;
import java.util.Map;

/**
 * [76] Minimum Window Substring
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 */
public class LC_076 {
    /**
     * slide window 通用解法
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
     */
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";
        Map<Character, Integer> map = new HashMap<>();
        for (char c: t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) +1);
        }
        int count = map.size();
        int begin = 0, end = 0;
        int head = 0, len = s.length()+1;
        while (end < s.length()) {
            char c = s.charAt(end++);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)-1);
                if (map.get(c)==0) {
                    count--;
                }
            }
            while(count==0) {
                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc)+1);
                    if (map.get(tempc) > 0) {
                        count++;
                    }
                }
                if (end-begin < len) {
                    len = end-begin;
                    head = begin;
                }
                begin++;
            }
        }
        if (len > s.length()) return "";
        return s.substring(head, head+len);
    }
}
