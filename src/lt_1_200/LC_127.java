package lt_1_200;


import javafx.util.Pair;

import java.util.*;

/**
 *
 * Example 1:
 * [127] Word Ladder
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" ->
 * "dog" -> "cog",
 * return its length 5.
 */
public class LC_127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() <=0 || !wordList.contains(endWord)) return 0;
        int wordLen = beginWord.length();
        Map<String, List<String>> wordPatternListMap = new HashMap<>();
        wordList.forEach(word -> {
            for (int i = 0; i < wordLen; ++i) {
                String pattern = word.substring(0, i) + "_" + word.substring(i+1, wordLen);
                wordPatternListMap.computeIfAbsent(pattern, k->new LinkedList<>()).add(word);
            }
        });
        Map<String, Boolean> visitedMap = new HashMap<>();
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(beginWord, 1));
        visitedMap.put(beginWord, true);
        while (queue.size() > 0) {
            Pair<String, Integer> pair = queue.poll();
            String tmpWord = pair.getKey();
            for (int i = 0; i < wordLen; ++i) {
                String pattern = tmpWord.substring(0,i) + "_" + tmpWord.substring(i+1,wordLen);
                List<String> patternList = wordPatternListMap.getOrDefault(pattern, new LinkedList<>());
                for (String word: patternList) {
                    if (word.equals(endWord)) {
                        return pair.getValue()+1;
                    }
                    if (!visitedMap.containsKey(word)) {
                        queue.offer(new Pair<>(word, pair.getValue()+1));
                        visitedMap.put(word, true);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String ...agrs) {
        LC_127 lc_127 = new LC_127();
        System.out.println(lc_127.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
    }
}



























