package lt_300_399;

import java.util.*;

/**
 * [347] Top K Frequent Elements
 */
public class LC_347 {
    /**
     * 优先队列
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent1(int[] nums, int k) {
        List<Integer> retList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>((a, b) -> b.getValue()-a.getValue());
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            priorityQueue.offer(entry);
        }
        while (k-- > 0) {
            retList.add(priorityQueue.poll().getKey());
        }
        return retList;
    }

    /**
     * TreeMap 解法
     * O(nlogn)
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> retList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            treeMap.putIfAbsent(entry.getValue(), new ArrayList<>());
            treeMap.get(entry.getValue()).add(entry.getKey());
        }
        while (retList.size() < k) {
            Map.Entry<Integer, List<Integer>> entry = treeMap.pollLastEntry();
            retList.addAll(entry.getValue());
        }
        return retList;
    }

    /**
     * 桶排序
     * O(n)
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent3(int[] nums, int k) {
        List<Integer> retList = new ArrayList<>();
        List<Integer>[] bucket = new List[nums.length+1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (bucket[entry.getValue()] == null) {
                bucket[entry.getValue()] = new ArrayList<>();
            }
            bucket[entry.getValue()].add(entry.getKey());
        }
        for (int i = bucket.length-1; i >= 0 && k > 0; --i) {
            if (bucket[i] == null) continue;
            for (Integer aim: bucket[i]) {
                retList.add(aim);
                k--;
                if (k <= 0) {
                    break;
                }
            }
        }
        return retList;
    }
    public static void main(String ...args) {
        int[] nums = {1,1,1,2,2,3};
        LC_347 lc_347 = new LC_347();
        lc_347.topKFrequent(nums, 2).forEach(System.out::println);
    }
}
