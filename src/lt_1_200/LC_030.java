package lt_1_200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [30] Substring with Concatenation of All Words
 * 滑动窗口的原理
 * 对每个单词起始点进行枚举
 *
 */
public class LC_030 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        int len1 = s.length(), len2 = words.length, wl = 0;
        if (len2 == 0 || len1 < len2 * (wl = words[0].length())) {
            return list;
        }
        Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
        for (String word: words) {
            map.put(word, map.getOrDefault(word, 0)+1);
        }
        String str = "", tmp = "";
        // 从开头的每个位置开始，后边直接取wl长度的单词，对string进行分割
        for (int i = 0; i < wl; ++i) {
            int count = 0;
            int start = i, end = i;
            while (end+wl <= len1) {
                str = s.substring(end, end+wl);
                end += wl;
                if (map.containsKey(str)) {
                    curMap.put(str, curMap.getOrDefault(str, 0)+1);
                    if (curMap.get(str) <= map.get(str)) {
                        count++;
                    }
                    while (curMap.get(str) > map.get(str)) {
                        tmp = s.substring(start, start + wl);
                        curMap.put(tmp, curMap.get(tmp)-1);
                        if (curMap.get(tmp) < map.get(tmp)) {
                            count--;
                        }
                        start += wl;
                    }
                    if (count == len2) {
                        list.add(start);
                        tmp = s.substring(start, start + wl);
                        curMap.put(tmp, curMap.get(tmp)-1);
                        count--;
                        start += wl;
                    }
                } else {
                    curMap.clear();
                    count = 0;
                    start = end;
                }
            }
            curMap.clear();
        }
        return list;
    }
}
