package basic_data_structure.graph;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Traverse_BFS {
    private static final int MAXV = 1000;
    private static final int INF = 0x3fffffff;

    /**
     * 邻接矩阵版本
     */
    private int[][] G = new int[MAXV][MAXV];
    private boolean[] vis = new boolean[MAXV];
    private void BFS_1(int u) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(u);
        vis[u] = true;
        while (!queue.isEmpty()) {
            int tu = queue.poll();
            for (int i = 0; i < MAXV; ++i) {
                if (G[tu][i] != INF && !vis[i]) {
                    vis[i] = true;
                    queue.offer(i);
                }
            }
        }
    }
    public void BFS_1_trave() {
        for (int u = 0; u < MAXV; ++u) {
            if (!vis[u]) {
                BFS_1(u);
            }
        }
    }


    /**
     * 邻接表版本
     */
    private ArrayList[] adj = new ArrayList[MAXV];
    private void BFS_2(int u) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        vis[u] = true;
        while (!queue.isEmpty()) {
            int tu = queue.poll();
            for (Object obj_v: adj[tu]) {
                int v = (int) obj_v;
                if (!vis[v]) {
                    vis[v] = true;
                    queue.offer(v);
                }
            }
        }
    }
    public void BFS_2_trave() {
        for (int u = 0; u < MAXV; ++u) {
            if (!vis[u]) {
                BFS_2(u);
            }
        }
    }



}
