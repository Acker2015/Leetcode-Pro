package basic_data_structure.graph;


import java.util.ArrayList;

public class Bellman {
    private class Node {
        int v;
        int dis; // 边权
    }
    private static final int MAXV = 1000;
    private static final int INF = 0x3fffffff;

    private ArrayList[] adj; // 邻接表
    int n;      // n为顶点数
    int[] d;    // 起点到达各点的最短路径长度

    /**
     * time: O(VE)
     * @param s
     * @return
     */
    public boolean bellman(int s) {
        for (int i = 0; i < n; ++i) {
            d[i] = INF;
        }
        d[s] = 0;
        for (int i = 0; i < n - 1; ++i) { // 执行n-1轮操作
            for (int u = 0; u < n; ++u) {
                for (Object obj_v: adj[u]) {
                    Node node = (Node) obj_v;
                    int v = node.v;
                    if (d[u] + node.dis < d[v]) {
                        d[v] = d[u] + node.dis; // 松弛操作
                    }
                }
            }
        }
        // 以下代码用来判断负环 (有负环存在，永远都能够找到最小距离)
        for (int u = 0; u < n; ++u) {
            for (Object obj_v: adj[u]) {
                Node node = (Node) obj_v;
                if (d[u] + node.dis < d[node.v]) {
                    return false;
                }
            }
        }
        return true;
    }
}
