package lt_1_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC_140 {
	Map<String, List<String>> map = new HashMap<>();
    /**
     * DFS
     * use map to record the result for substring s.
     * reserve the middle result would save more time and reduce some repeated calculation.
     * 
     * DFS记忆化搜索，如果剩下的子串已经有搜索结果那么就可以直接使用
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (map.containsKey(s)) return map.get(s);
        List<String> list = new ArrayList<>();
        if (s.length() == 0) {
            list.add("");
            return list;
        }
        for (String word: wordDict) {
            if (s.startsWith(word)) {
            		// 找到s.substring(word.length())的搜索结果
                List<String> subList = wordBreak(s.substring(word.length()), wordDict);
                for (String sub: subList) {
                    list.add(word+(sub.equals("")?"":" ")+sub);
                }
            }
        }
        map.put(s, list);
        return list;
    }
	public static void main(String[] args) {
		String[] words = {"cat", "cats", "and", "sand", "dog"};
		String str = "catsanddog";
		LC_140 lc_140 = new LC_140();
		System.out.println(lc_140.wordBreak(str, Arrays.asList(words)));

	}

}
