package lt_300_399;

import java.util.*;

/**
 * [332] Reconstruct Itinerary
 * 欧拉路径，具体看印象笔记关于欧拉回路的介绍
 *
 * solution1 - 递归
 * solution2 - 迭代
 */
public class LC_332 {

    /**
     * solution1
     * 递归
     */
    static class Solution1 {
        private Map<String, PriorityQueue<String>> targets = new HashMap<>();
        private List<String> route = new LinkedList<>();

        private void visit(String airport) {
            while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
                visit(targets.get(airport).poll());
            route.add(0, airport);
        }
        /**
         * solution1 - 递归
         * 欧拉回路 - 后序遍历
         *
         * Hierholzer 算法
         */
        public List<String> findItinerary(List<List<String>> tickets) {
            for (List<String> ticket : tickets)
                targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>())
                        .add(ticket.get(1));
            visit("JFK");
            return route;
        }

    }
    /**
     * solution2 - 迭代
     */
    static class Solution2 {
        // stack
        public List<String> findItinerary2(List<List<String>> tickets) {
            Map<String, PriorityQueue<String>> adj = new HashMap<>();
            List<String> retList = new LinkedList<>();
            for (List<String> ticket: tickets) {
                if (!adj.containsKey(ticket.get(0))) {
                    adj.put(ticket.get(0), new PriorityQueue<>());
                }
                adj.get(ticket.get(0)).offer(ticket.get(1));
            }
            Stack<String> stack = new Stack<>();
            stack.push("JFK");
            while (!stack.isEmpty()) {
                String depart = stack.peek();
                PriorityQueue<String> arrivals = adj.get(depart);
                if (arrivals!=null && !arrivals.isEmpty()) {
                    stack.push(arrivals.poll());
                } else {
                    retList.add(0, stack.pop());
                }
            }
            return retList;
        }
    }
}
