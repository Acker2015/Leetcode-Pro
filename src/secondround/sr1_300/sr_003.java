package secondround.sr1_300;


import java.util.HashMap;
import java.util.Map;

public class sr_003 {
    /**
     * sliding window
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        if (s.length() <= 1) return s.length();
        char[] chs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int i = 0, j = 0, cntRepeat = 0, maxLen = 1;
        while (j < chs.length) {
            map.put(chs[j], map.getOrDefault(chs[j], 0) + 1);
            if (map.get(chs[j]) == 2) {
                cntRepeat++;
            }
            j++;
            while (cntRepeat > 0) {
                map.put(chs[i], map.get(chs[i])-1);
                if (map.get(chs[i]) == 1) {
                    cntRepeat--;
                }
                i++;
            }
            maxLen = Math.max(maxLen, j-i);
        }
        return maxLen;
    }

    /**
     * hashMap - 直接记录节点上一次出现的位置
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length()<=1) return s.length();
        char[] chs = s.toCharArray();
        Map<Character, Integer> lastPositionMap = new HashMap<>();
        int i = 0, j = 0, maxLen = 0;
        while (j < chs.length) {
            if (lastPositionMap.containsKey(chs[j]) && lastPositionMap.get(chs[j]) >= i) {
                i = lastPositionMap.get(chs[j])+1;
            } else {
                lastPositionMap.put(chs[j], j);
                j++;
                maxLen = Math.max(maxLen, j-i);
            }
        }
        return maxLen;
    }
}
