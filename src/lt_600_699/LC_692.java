package lt_600_699;


import java.util.*;

/**
 * [692] Top K Frequent Words
 */
public class LC_692 {

    class Solution {
        /**
         * Solution1
         * HashMap + PriorityQueue 常规
         * O(nlogk)
         * @param words
         * @param k
         * @return
         */
        public List<String> topKFrequent1(String[] words, int k) {
            PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) ->
                    a.getValue().equals(b.getValue()) ? b.getKey().compareTo(a.getKey()):a.getValue()-b.getValue());

            Map<String, Integer> map = new HashMap<>();
            for (String word: words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
            for (Map.Entry<String, Integer> entry: map.entrySet()) {
                pq.offer(entry);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
            k = Math.max(k, pq.size());
            List<String> list = new ArrayList<>();
            while (k-- > 0) {
                list.add(0,pq.poll().getKey());
            }
            return list;
        }

        /**
         * Solution2
         *
         * HashMap + bucket-sort + TreeMap
         * using bucketsort to sort the word by frequecy.
         * for same frequecy, using treeset to implement alphabetical order.
         *
         * certainly, you can use Trie the make the words alphabetical with same frequency
         */
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> map = new HashMap<>();
            for (String word: words) {
                map.put(word, map.getOrDefault(word, 0)+1);
            }
            TreeSet<String>[] bucket = new TreeSet[words.length+1];
            for (Map.Entry<String, Integer> entry: map.entrySet()) {
                if (bucket[entry.getValue()] == null) {
                    bucket[entry.getValue()] = new TreeSet<>();
                }
                bucket[entry.getValue()].add(entry.getKey());
            }
            List<String> retList = new ArrayList<>();
            for (int i = bucket.length-1; i>=0; --i) {
                if (bucket[i] != null) {
                    for (String word: bucket[i]) {
                        if (k-- <= 0) break;
                        retList.add(word);
                    }
                }
                if (k <= 0) break;
            }
            return retList;
        }
    }
}
