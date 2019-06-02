package basic_data_structure.graph;

import java.util.ArrayList;

/**
 * 两个表示方法
 * 1. 邻接矩阵
 * 2. 邻接表
 */
public class Traverse_DFS {
    private static final int MAXV = 1000;
    private static final int INF = 0x3fffffff;

    // 邻接矩阵版本
    private int[][] G = new int[MAXV][MAXV];    // 图G的邻接矩阵表示
    private boolean[] vis = new boolean[MAXV];

    private void DFS_1(int u, int depth) {
        vis[u] = true;
        for (int v = 0; v < MAXV; ++v) {
            if (G[u][v] != INF && !vis[v]) {
                DFS_1(v, depth+1);
            }
        }
    }
    void DFS_1_trave() {
        // 访问所有的联通量
        for (int u = 0; u < MAXV; ++u) {
            if (!vis[u]) {
                DFS_1(u, 1);
            }
        }
    }


    // 邻接表版本
    private ArrayList[] adj = new ArrayList[MAXV]; // 图G的邻接表表示
    private void DFS_2(int u, int depth) {
        vis[u] = true;
        for (Object obj_v: adj[u]) {
            int v = (int) obj_v;
            if (!vis[v]) {
                DFS_2(v, depth+1);
            }
        }
    }
    public void DFS_2_trave() {
        // 访问所有的联通量
        for (int u = 0; u < MAXV; ++u) {
            if (!vis[u]) {
                DFS_2(u, 1);
            }
        }
    }

}
