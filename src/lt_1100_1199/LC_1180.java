package lt_1100_1199;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 加锁题
 * 1181
 * Before and After Puzzle
 * 在一个句子数组phrases中寸照before and after组合，phrases[i]和phrases[j], i!=j, 其中phrases[i]的最后一个单词跟phrases[j]的第一个单词相同
 *
 * example1:
 * input: phrases = ["writing code", "code rocks", "good", "good boy"]
 * output: ["writing code rocks", "good boy"]
 *
 * example2:
 * input: ["a", "b", "a"]
 * output: ["a"]
 */
public class LC_1180 {
    private String concat(String s1, String s2, String same) {
        return s1.substring(0, s1.length()-same.length()) + s2;
    }
    private String getStartWord(String s) {
        int i = 0;
        while (i < s.length() && s.charAt(i) != ' ') {
            i++;
        }
        return s.substring(0, i);
    }
    private String getEndWord(String s) {
        int j = s.length();
        while (j >= 0 && s.charAt(j) != ' ') {
            j--;
        }
        return s.substring(j+1);
    }

    List<String> beforeAndAfterPuzzle(String[] phrases) {
        Map<String, List<String>> beginMap = new HashMap<>();
        Map<String, List<String>> endMap = new HashMap<>();
        for (int i = 0; i < phrases.length; ++i) {
            String w1 = getStartWord(phrases[i]);
            String w2 = getEndWord(phrases[i]);
            beginMap.computeIfAbsent(w1, k->new ArrayList<>()).add(phrases[i]);
            endMap.computeIfAbsent(w2, k->new ArrayList<>()).add(phrases[i]);
        }
        List<String> ret = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry: endMap.entrySet()) {
            if (beginMap.containsKey(entry.getKey())) {
                for (String end: entry.getValue()) {
                    for (String start: beginMap.get(entry.getKey())) {
                        ret.add(concat(end, start, entry.getKey()));
                    }
                }
            }
        }
        return ret;
    }

}
