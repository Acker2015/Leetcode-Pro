package basic_data_structure.tree;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 使用优先队列来实现
 */
public class Haffman {
    public int haffman(List<Integer> list) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(list);
        int wpl = 0;
        while (priorityQueue.size() > 1) {
            int x = priorityQueue.poll();
            int y = priorityQueue.poll();
            wpl += (x+y);
            priorityQueue.offer(x+y);
        }
        return wpl;
    }

    public static void main(String[] args) {
        Integer[] arr = {2,6,1,3,2};
        Haffman haffman = new Haffman();
        System.out.println(haffman.haffman(Arrays.asList(arr)));
    }
}
