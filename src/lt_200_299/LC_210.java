package lt_200_299;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Course Schedule II
 * solution1 BFS + 拓扑排序
 * solution2 DFS
 */
public class LC_210 {
    /**
     * Solution1
     * BFS + 拓扑排序
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];       // 入度数组
        for (int i = 0; i < numCourses; ++i) {
            graph[i] = new ArrayList();
        }
        for (int i = 0; i < prerequisites.length; ++i) {
            indegree[prerequisites[i][0]]++;
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; ++i) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] order = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int u = queue.poll();
                order[idx++] = u;
                for (Object obj: graph[u]) {
                    int v = (int)obj;
                    indegree[v]--;
                    if (indegree[v] == 0) {
                        queue.offer(v);
                    }
                }
            }
        }
        return idx==numCourses ? order : new int[]{};
    }

    private void dfs(boolean[] vis, int[] indegree, ArrayList[] graph, int cur, List<Integer> orderList) {
        // 如果已经访问过 或者 入度不为0
        if (vis[cur] || indegree[cur] > 0) {
            return;
        }
        vis[cur] = true;
        orderList.add(cur);
        for (Object next: graph[cur]) {
            indegree[(int)next]--;
            dfs(vis, indegree, graph, (int)next, orderList);
        }
    }

    /**
     * solution2
     * 通过邻接表+入度来进行DFS
     * 1. 对图中的每个联通分量进行DFS，遍历到的节点入度减一
     * 2. 遇到未访问过&&入度为0的节点，就可以记录，否则不能继续遍历下去，因为此节点还有未访问的前驱节点存在
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            graph[i] = new ArrayList();
        }
        for (int i = 0; i < prerequisites.length; ++i) {
            indegree[prerequisites[i][0]]++;
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        boolean[] vis = new boolean[numCourses];
        List<Integer> orderList = new ArrayList<>();
        for (int i = 0; i < graph.length; ++i) {
            // 入度为0并且未访问过，需要遍历 (每个联通分量)
            if (indegree[i] == 0 && !vis[i]) {
                dfs(vis, indegree, graph, i, orderList);
            }
        }
        if (orderList.size() != numCourses) {
            return new int[0];
        }
        int[] order = new int[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            order[i] = orderList.get(i);
        }
        return order;
    }


    public static void main(String ...args) {
        int courseNum = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        LC_210 lc_210 = new LC_210();
       lc_210.findOrderDFS(courseNum, prerequisites);
    }
}
