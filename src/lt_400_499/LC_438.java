package lt_400_499;

import java.util.ArrayList;
import java.util.List;

/**
 * [438] Find All Anagrams in a String
 */
public class LC_438 {
    public boolean judge(int[] sMap, int[] pMap) {
        for (int i = 0; i < 26; ++i) {
            if (sMap[i] != pMap[i]) {
                return false;
            }
        }
        return true;
    }
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if (sLen <= 0 || pLen <= 0 || pLen > sLen) return list;

        int[] sMap = new int[26];
        int[] pMap = new int[26];
        for (int i = 0; i < pLen; ++i) {
            pMap[p.charAt(i)-'a']++;
        }
        for (int i = 0; i < sLen; ++i) {
            int idx = s.charAt(i)-'a';
            sMap[idx]++;
            if (i >= pLen) {
                sMap[s.charAt(i-pLen)-'a']--;
            }

            if (i >= pLen-1 && sMap[idx] == pMap[idx] && judge(sMap, pMap)) {
                list.add(i-pLen+1);
            }
        }
        return list;
    }
}
