package lt_200_299;

import javafx.util.Pair;

import java.util.*;

/**
 [Leetcode] 281. Zigzag Iterator 加锁题

 Given two 1d vectors, implement an iterator to return their elements alternately.
 For example, given two 1d vectors:

 v1 = [1, 2]
 v2 = [3, 4, 5, 6]
 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

 Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

 Clarification for the follow up question - Update (2015-09-18):
 The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

 [1,2,3]
 [4,5,6,7]
 [8,9]
 It should return [1,4,8,2,5,9,3,6,7]

 */
public class LC_281 {
    private List<List<Integer>> matrix;
    private Queue<Pair<Integer, Integer>> queue;

    public LC_281(List<List<Integer>> list) {
        this.matrix = list;
        queue = new LinkedList<>();
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).size() > 0) {
                queue.offer(new Pair<>(i, 0));
            }
        }
    }

    public int next() {
        Pair<Integer, Integer> pair = queue.poll();
        int i = pair.getKey();
        int j = pair.getValue();
        int size = this.matrix.get(pair.getKey()).size();
        if (j + 1 < size) {
            queue.offer(new Pair<>(i, j+1));
        }
        return this.matrix.get(i).get(j);
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public static void main(String ...args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1,2, 3));
        list.add(Arrays.asList(4,5,6,7));
        list.add(Arrays.asList(8,9));
        LC_281 lc_281 = new LC_281(list);
        while (lc_281.hasNext()) {
            System.out.print(lc_281.next() + ", ");
        }
    }
}
