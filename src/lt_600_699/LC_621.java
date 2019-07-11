package lt_600_699;

import java.util.*;

/**
 * [621] Task Scheduler
 */
public class LC_621 {
    /**
     * p:
     * 1. 出现频率高的task同时会伴随着更多的idle interval
     * 2. 从频率高的task保证每个执行流间隙为n，多余的使用idle填充
     *
     * map + priorityQueue + greedy
     * 优先队列目的是每次寻找频率出现最高的task，这其中就是贪心的思想，因为频率最高的任务之间才会出现更多的idle，所以优先安排高频task
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char task: tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> priorityQueue = new PriorityQueue<>(
                (a, b)-> !a.getValue().equals(b.getValue()) ? b.getValue()-a.getValue() : a.getKey().compareTo(b.getKey()));
        priorityQueue.addAll(map.entrySet());
        int intervalCnt = tasks.length;
        while (!priorityQueue.isEmpty()) {
            int k = n + 1;
            List<Map.Entry<Character, Integer>> list = new ArrayList<>();
            while (!priorityQueue.isEmpty() && k > 0) {
                Map.Entry<Character, Integer> entry = priorityQueue.poll();
                entry.setValue(entry.getValue()-1);
                if (entry.getValue() > 0) {
                    list.add(entry);
                }
                k--;
            }
            priorityQueue.addAll(list);
            // 如果后续没有任务那么idle自然就不用加了
            if (!priorityQueue.isEmpty()) {
                intervalCnt += k;
            }
        }
        return intervalCnt;
    }
    public static void main(String ...args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println(new LC_621().leastInterval(tasks, 0));
    }
}
