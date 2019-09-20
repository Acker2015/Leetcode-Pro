package secondround.sr1_300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 滑动窗口
 *
 * 滑动窗口解法，枚举单词起始点即可
 */
public class sr_030 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        int sLen = s.length(), wLen = words.length, wl;
        if (wLen <= 0 || sLen < wLen*(wl = words[0].length())) {
            return list;
        }
        Map<String, Integer> curMap = new HashMap<>(), recordMap = new HashMap<>();
        for (String word: words) {
            recordMap.put(word, recordMap.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < wl; ++i) {
            int start = i, end = i, cnt = 0;
            while (end+wl <= sLen && start+wl*wLen<=sLen) {
                String word = s.substring(end, end+wl);
                end += wl;
                if (recordMap.containsKey(word)) {
                    curMap.put(word, curMap.getOrDefault(word, 0)+1);
                    if (curMap.get(word) <= recordMap.get(word)) {
                        cnt++;
                    }
                    while (curMap.get(word) > recordMap.get(word)) {
                        String tmp = s.substring(start, start+wl);
                        curMap.put(tmp, curMap.get(tmp)-1);
                        if (curMap.get(tmp) < recordMap.get(tmp)) {
                            cnt--;
                        }
                        start += wl;
                    }
                    if (cnt == words.length) {
                        list.add(start);
                        String tmp = s.substring(start, start+wl);
                        start += wl;
                        curMap.put(tmp, curMap.get(tmp)-1);
                        cnt--;
                    }
                } else {
                    start = end;
                    curMap.clear();
                    cnt = 0;
                }
            }
            curMap.clear();
        }
        return list;
    }

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        StringBuilder builder = new StringBuilder();
        new sr_030().findSubstring(s, words).forEach(System.out::println);
    }
}
