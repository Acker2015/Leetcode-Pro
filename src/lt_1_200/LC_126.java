package lt_1_200;


import java.util.*;

/**
 * [126] Word Ladder II
 * 解法类似 LC——127
 *
 * 这里为了求出路径，有两个点需要说明一下
 * 1. 使用preMap记录每个word的前驱单词列表
 * 2. 在求下一层的时候，需要统一入队(访问置访问标记影响后边路径, 比如 aa->ac, ab->ac 需要将这两个路径记录为 ac: {aa, ab})
 */
public class LC_126 {
    private Map<String, List<String>> getWordMap(List<String> wordList) {
        Map<String, List<String>> wordMap = new HashMap<>();
        for (String word: wordList) {
            for (int i = 0; i < word.length(); ++i) {
                String symbol = word.substring(0, i) + "_" + word.substring(i+1);
                wordMap.computeIfAbsent(symbol, k->new ArrayList<>()).add(word);
            }
        }
        return wordMap;
    }

    private List<List<String>> backtracking(Map<String, List<String>> preMap, String beginWord, String endWord) {
        List<List<String>> retList = new ArrayList<>();
        if (beginWord.equals(endWord)) {
            List<String> list = new ArrayList<>();
            list.add(endWord);
            retList.add(list);
            return retList;
        }
        for (String pre : preMap.get(endWord)) {
            List<List<String>> pathList = backtracking(preMap, beginWord, pre);
            for (List<String> list: pathList) {
                list.add(endWord);
                retList.add(list);
            }
        }
        return retList;
    }


    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> retList = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return retList;
        }
        Map<String, List<String>> wordMap = getWordMap(wordList);
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, List<String>> preMap = new HashMap<>();
        // add onto queue
        queue.offer(beginWord);
        set.add(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> ans = new HashSet<>();
            boolean isExist = false;
            while (size-- > 0) {
                String word = queue.poll();
                for (int i =0; i < word.length(); ++i) {
                    String tmp = word.substring(0,i)+"_"+word.substring(i+1);
                    if (!wordMap.containsKey(tmp)) continue;
                    for (String next : wordMap.get(tmp)) {
                        if (set.contains(next)) continue;
                        if (next.equals(endWord)) {
                            isExist = true;
                        }
                        // 记录前驱
                        preMap.computeIfAbsent(next, k->new ArrayList<>()).add(word);
                        // 这里先不进队列，后边一起入队列(防止入队列，置访问标记之后，后续其他前驱别跳过)
                        ans.add(next);
                    }
                }
            }
            // 如果存在，那么直接回溯求出全部路径
            if (isExist) {
                return backtracking(preMap, beginWord, endWord);
            }
            // 统一入队
            // in-queue and set the visited flag
            for (String next: ans) {
                set.add(next);
                queue.offer(next);
            }
        }



        return retList;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("hot","dot","dog","lot","log","cog");
        LC_126 lc_126 = new LC_126();
        List<List<String>> retList = lc_126.findLadders("hit", "cog", words);
        retList.forEach(list -> {
            for (String str: list) {
                System.out.print(str+"->");
            }
            System.out.println();
        });
    }
}
