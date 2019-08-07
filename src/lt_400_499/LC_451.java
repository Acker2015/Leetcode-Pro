package lt_400_499;

import java.util.*;

/**
 * [451] Sort Characters By Frequency
 * solution1: HashMap + bucketSort
 * solution2: HashMap + PriorityQueue
 */
public class LC_451 {
    class Solution {
        /**
         * Solution1
         * HashMap + bucketSort
         * @param s
         * @return
         */
        public String frequencySort(String s) {
            char[] chs = s.toCharArray();
            Map<Character, Integer> map = new HashMap<>();
            for (char c: chs) {
                map.put(c, map.getOrDefault(c, 0)+1);
            }
            List<Character>[] bucket = new List[chs.length+1];
            for (Map.Entry<Character, Integer> entry: map.entrySet()) {
                int fre = entry.getValue();
                if (bucket[fre] == null) {
                    bucket[fre] = new ArrayList<>();
                }
                bucket[fre].add(entry.getKey());
            }
            StringBuilder builder = new StringBuilder();
            for (int i = bucket.length-1; i >= 0; --i) {
                if (bucket[i] != null) {
                    for (char c: bucket[i]) {
                        for (int j = 0; j < i; ++j) {
                            builder.append(c);
                        }
                    }
                }
            }
            return builder.toString();
        }

        /**
         * Solution2
         * 优先队列
         * HashMap + PriorityQueue
         * O(nlogm)
         */
        public String frequencySort1(String s) {
            PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue()-a.getValue());
            char[] chs = s.toCharArray();
            Map<Character, Integer> map = new HashMap<>();
            for (char c: chs) {
                map.put(c, map.getOrDefault(c, 0)+1);
            }
            pq.addAll(map.entrySet());
            StringBuilder builder = new StringBuilder();
            while (!pq.isEmpty()) {
                Map.Entry<Character, Integer> entry = pq.poll();
                for (int i = 0; i < entry.getValue(); ++i) {
                    builder.append(entry.getKey());
                }
            }
            return builder.toString();
        }
    }
}
