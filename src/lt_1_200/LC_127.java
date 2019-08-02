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
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
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
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visitedMap.put(beginWord, true);
        int len = 1;
        while (queue.size() > 0) {
            int size = queue.size();
            for (int k = 0; k < size; ++k) {
                String tmpWord = queue.poll();
                for (int i = 0; i < wordLen; ++i) {
                    String pattern = tmpWord.substring(0,i) + "_" + tmpWord.substring(i+1,wordLen);
                    List<String> patternList = wordPatternListMap.getOrDefault(pattern, new LinkedList<>());
                    for (String word: patternList) {
                        if (word.equals(endWord)) {
                            return len+1;
                        }
                        if (!visitedMap.containsKey(word)) {
                            queue.offer(word);
                            visitedMap.put(word, true);
                        }
                    }
                }
            }
            len++;
        }
        return 0;
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
        Map<String, List<String>> patternListMap = new HashMap<>();
        for (String word: wordList) {
            for (int i = 0; i < wordLen; ++i) {
                String pattern = word.substring(0, i) + "_" + word.substring(i+1, wordLen);
                patternListMap.computeIfAbsent(pattern, k->new LinkedList<>()).add(word);
            }
        }
        // 记录已访问word，防止死循环
        Map<String, Boolean> visitedMap = new HashMap<>();
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        // two queue
        beginSet.add(beginWord);
        endSet.add(endWord);
        visitedMap.put(beginWord, true);
        visitedMap.put(endWord, true);
        int len = 1;
        while (beginSet.size() > 0 && endSet.size() > 0) {
            if (endSet.size() < beginSet.size()) {
                Set<String> swapSet = beginSet;
                beginSet = endSet;
                endSet = swapSet;
            }
            Set<String> tmp = new HashSet<>();
            for (String word: beginSet) {
                for (int i = 0; i < wordLen; ++i) {
                    String pattern = word.substring(0, i) + "_" + word.substring(i + 1, wordLen);
                    List<String> patternList = patternListMap.getOrDefault(pattern, new LinkedList<>());
                    for (String w : patternList) {
                        if (endSet.contains(w)) {
                            return len + 1;
                        }
                        if (!visitedMap.containsKey(w)) {
                            visitedMap.put(w, true);
                            tmp.add(w);
                        }
                    }

                }
            }
            beginSet = tmp;
            len++;
        }
        return 0;
    }


    public static void main(String ...agrs) {
        LC_127 lc_127 = new LC_127();
        System.out.println(lc_127.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
    }
}



























