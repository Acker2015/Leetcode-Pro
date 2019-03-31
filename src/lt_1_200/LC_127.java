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
    /**
     * 解法1
     * 单纯的BFS（木有任何套路）
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
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

    private int helperBFS (Queue<String> queue,
                            Map<String, Integer> aimVisitedMap,
                            Map<String, Integer> otherVisitedMap,
                            Map<String, List<String>> wordPatternListMap) {
        String word = queue.poll();
        int wordLen = word.length();
        for (int i = 0; i < wordLen; ++i) {
            String pattern = word.substring(0, i) + "_" + word.substring(i+1, wordLen);
            List<String> childList = wordPatternListMap.getOrDefault(pattern, new ArrayList<>());
            for (String w: childList) {
                if (otherVisitedMap.containsKey(w)) {
                    return aimVisitedMap.get(word) + otherVisitedMap.get(w);
                }
                if (!aimVisitedMap.containsKey(w)) {
                    aimVisitedMap.put(w, aimVisitedMap.get(word)+1);
                    queue.offer(w);
                }
            }
        }
        return -1;
    }

    /**
     * 方法二:
     * 双向BFS，一定程度上可以减小时间和空间复杂度
     * bidictional BFS
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.size() <=0 || !wordList.contains(endWord)) return 0;
        int wordLen = beginWord.length();
        Map<String, List<String>> wordPatternListMap = new HashMap<>();
        wordList.forEach(word -> {
            for (int i = 0; i < wordLen; ++i) {
                String pattern = word.substring(0, i) + "_" + word.substring(i+1, wordLen);
                wordPatternListMap.computeIfAbsent(pattern, k->new ArrayList<>()).add(word);
            }
        });
        Queue<String> beginQueue = new LinkedList<>();
        Queue<String> endQueue = new LinkedList<>();
        Map<String, Integer> beginVisitedMap = new HashMap<>();
        Map<String, Integer> endVisitedMap = new HashMap<>();
        beginQueue.offer(beginWord);
        beginVisitedMap.put(beginWord, 1);
        endQueue.offer(endWord);
        endVisitedMap.put(endWord, 1);
        while (beginQueue.size() > 0 && endQueue.size() > 0) {
            boolean useBegin = beginQueue.size() <= endQueue.size();
            Queue<String> tmpQueue = useBegin ? beginQueue:endQueue;
            Map<String, Integer> tmpMap = useBegin ? beginVisitedMap : endVisitedMap;
            Map<String, Integer> otherMap = useBegin ? endVisitedMap : beginVisitedMap;
            String tmpWord = tmpQueue.poll();
            for (int i = 0; i < wordLen; ++i) {
                String pattern = tmpWord.substring(0, i) + "_" + tmpWord.substring(i+1, wordLen);
                List<String> tmpList = wordPatternListMap.getOrDefault(pattern, new ArrayList<>());
                for (String word: tmpList) {
                    if (otherMap.containsKey(word)) {
                        return tmpMap.get(tmpWord) + otherMap.get(word);
                    }
                    if (!tmpMap.containsKey(word)) {
                        tmpMap.put(word, tmpMap.get(tmpWord)+1);
                        tmpQueue.offer(word);
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



























