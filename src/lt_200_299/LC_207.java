package lt_200_299;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [207] Course Schedule
 */
public class LC_207 {
    private boolean dfs(boolean[] visited, ArrayList[] graph, int c) {
        if (visited[c]) {
            return false;
        }
        visited[c] = true;
        for (Object nc: graph[c]) {
            if (!dfs(visited, graph, (int)nc)) {
                return false;
            }
        }
        visited[c] = false;
        return true;

    }
    /**
     * DFS
     */
    public boolean canFinish_DFS(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < prerequisites.length; ++i) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];
            graph[from].add(to);
        }
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            if (!dfs(visited, graph, i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * BFS + 拓扑排序
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            graph[i] = new ArrayList();
        }
        // 倒序存储
        for (int i = 0; i < prerequisites.length; ++i) {
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
            degree[prerequisites[i][1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count += size;
            while (size-- > 0) {
                int c = queue.poll();
                for (int i = 0; i < graph[c].size(); ++i) {
                    int nc = (int)graph[c].get(i);
                    degree[nc]--;
                    if (degree[nc] == 0) {

                        queue.offer(nc);
                    }
                }
            }
        }
        return count==numCourses;
    }
}
