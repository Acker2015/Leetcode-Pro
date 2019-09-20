package basic_data_structure.graph;

import java.util.ArrayList;

/**
 * Dijkstra解决单元最短路径问题
 *
 * 这里用邻接矩阵实现（邻接表原理类似）
 */
public class Dijkstra {
    private class Node {
        int v;
        int dis; // 边权
    }
    private static final int MAXV = 1000;
    private static final int INF = 0x3fffffff;

    private static int n;  // 图顶点数
    private static ArrayList[] adj;  // 邻接表
    private static int[] d;     // 起点到达各个点的最短路径长度
    private static int[] pre;   // 记录最短路径的前驱节点
    private static boolean[] vis;   // 访问标志

    Dijkstra(int v) {
        n = v;
        adj = new ArrayList[n];
        d = new int[n];
        pre = new int[n];
        vis = new boolean[n];
    }

    /**
     * 根据前驱路径数组pre，以及给出的起点终点来输出路径
     * @param s
     * @param v
     */
    private void DFS_pre(int s, int v) {
        if (s == v) {
            System.out.println(s);
            return;
        }
        DFS_pre(s, pre[v]);
        System.out.println(v);
    }

    /**
     * O(v^2+E)
     * 运行结束，d[i]表示的就是 s->i的最短路径
     * @param s
     */
    private void dij(int s) {
        // init
        for (int i = 0; i < MAXV; ++i) {
            d[i] = INF;
            pre[i] = i;
        }
        d[s] = 0;
        // loop n times
        for (int i = 0; i < n; ++i) {
            // u使得d[u]在未访问节点中保持最小，即为下个月选取的节点
            int u = -1;
            int min_d = INF;
            for (int k = 0; k < n; ++k) {
                if (!vis[k] && d[k]!=INF && d[k] < min_d) {
                    min_d = d[k];
                    u = k;
                }
            }
            if (u == -1) break; // end, no link together
            vis[u] = true;

            for (Object obj_v: adj[u]) {
                Node vNode = (Node) obj_v;
                if (!vis[vNode.v] && d[u] + vNode.dis < d[vNode.v]) {
                    // 如果v未访问 && 以u为中介点可以使得d[v]最优
                    d[vNode.v] = d[u] + vNode.dis;
                    // 更新前驱
                    pre[vNode.v] = u;
                }
            }
        }
    }


    /**
     * 单源最短路径
     * 以及到每个节点的最短路径条数
     * @param s
     */
    private void dij_path_num(int s) {
        int[] pathNum = new int[n];
        // init
        for (int i = 0; i < MAXV; ++i) {
            d[i] = INF;
            pre[i] = i;
        }
        d[s] = 0;
        pathNum[s] = 1;
        // loop n times
        for (int i = 0; i < n; ++i) {
            // u使得d[u]在未访问节点中保持最小，即为下个要选择的节点
            int u = -1;
            int min_d = INF;
            for (int k = 0; k < n; ++k) {
                if (!vis[k] && d[k]!=INF && d[k] < min_d) {
                    min_d = d[k];
                    u = k;
                }
            }
            if (u == -1) break; // end, no link together
            vis[u] = true;

            for (Object obj_v: adj[u]) {
                Node vNode = (Node) obj_v;
                if (vis[vNode.v]) continue;
                if (d[u] + vNode.dis < d[vNode.v]) {
                    // 最短路径更新的时候，路径条数num也需要同步更新
                    d[vNode.v] = d[u] + vNode.dis;
                    pathNum[vNode.v] = pathNum[u];
                } else if (d[u] + vNode.dis == d[vNode.v]) {
                    // 最短距离相同的时候，累加num
                    pathNum[vNode.v]+=pathNum[u];
                }

//                if (!vis[vNode.v] && d[u] + vNode.dis < d[vNode.v]) {
//                    // 如果v未访问 && 以u为中介点可以使得d[v]最优
//                    d[vNode.v] = d[u] + vNode.dis;
//                    // 更新前驱
//                    pre[vNode.v] = u;
//                }
            }
        }
    }
}
