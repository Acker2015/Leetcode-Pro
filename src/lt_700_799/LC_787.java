package lt_700_799;


import java.util.*;

/**
 * [787] Cheapest Flights Within K Stops
 * solution1: BFS
 * solution2: Dijkstra's Algorithm, BFS
 */
public class LC_787 {
    /**
     * Solution1
     *
     * BFS 最多走K步，记录每一步的花费（原path，跟随入栈）
     * 外部维护一个目的地的最小花费
     *
     * 4\n[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]\n0\n3\n1
     * 有一个坑的地方就是， 不能只是用cost来记录花费，而应该在入队列的时候直接记住此时的花费
     * 比如上述输入
     * 这里是因为出队列有多种可能会更改cost[2],
     * 如果cost[2]在1出队的时候被更新为更小的值2（0->1->2），
     * 那么后边5出队选择3的时候就会使用新的cost[2]=2来更新cost[2], 而正确的应该是使用本路径cost[2]=5来更新。
     * 这里错误的在中间阶段将path从0->2->3 更改为了 0->1->2->3
     */
    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int K) {
        if (src >= n || dst >= n || K >= n) return -1;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: flights) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(edge);
        }
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        Queue<int[]> queue = new LinkedList<>();
        cost[src] = 0;
        queue.offer(new int[]{src, 0});
        int depth = 0;
        while (!queue.isEmpty() && depth <= K) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                if (graph.containsKey(cur[0])) {
                    for (int[] edge: graph.get(cur[0])) {
                        int next = edge[1];
                        // 剪枝，只去更近的路径
                        if (cost[next] > cur[1] + edge[2]) {
                            cost[next] = cur[1] + edge[2];
                            queue.offer(new int[]{next, cost[next]});
                        }
                    }
                }
            }
            depth++;
        }
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }

    /**
     * Dijkstra's Algorithm + Priority Queue
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (src >= n || dst >= n || K >= n) return -1;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: flights) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(edge);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1]-b[1]);
        // node - cost - stop
        queue.offer(new int[]{src, 0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == dst) {
                return cur[1];
            }
            if (cur[2] <= K && graph.containsKey(cur[0])) {
                for (int[] edge: graph.get(cur[0])) {
                    queue.offer(new int[]{edge[1], cur[1]+edge[2], cur[2]+1});
                }
            }
        }
        return -1;
    }
}
