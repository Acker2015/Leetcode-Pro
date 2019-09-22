package basic_data_structure.graph.minimum_spanning_tree;

import java.util.ArrayList;

/**
 * 算法类似Dijkstra算法 - 贪心的思路
 *
 */
public class Prim {
    private static final int MAXV = 1000;
    private static final int INF = 0x03fffffff;
    private class Node {
        int v;
        int dis; // 边权
    }
    /**
     * 邻接矩阵版本
     */
    public static class Solution1 {
        int n;  // n为顶点个数
        int[][] G = new int[MAXV][MAXV];    // G为邻接矩阵
        int[] d = new int[MAXV];    // 顶点与最小生成树集合S的最小距离
        boolean[] vis = new boolean[MAXV];  // 标记数组, vis[v]=true表示v已经被访问
        Solution1(int n) {
            this.n = n;
        }
        /**
         * 默认0号为初始点
         * @return
         */
        int prim() {
            for (int i = 0; i < n; ++i) {
                d[i] = INF;
            }
            d[0] = 0;
            vis[0] = true;
            int ans = 0;    // 记录最小生成树的边权之和
            for (int i = 0; i < n; ++i) {
                int u = -1, MIN = INF;
                for (int j = 0; j < n; ++j) {
                    if (!vis[j] && d[j] < MIN) {
                        MIN = d[j];
                        u = j;
                    }
                }
                // 找不到
                if (u == -1) return -1;
                vis[u] = true;
                ans += d[u];
                for (int v = 0; v < G[u].length; ++v) {
                    // 这里由于记录的是v距离已选择集合S的最近距离，u已经加入集合S，所以可通过判断G[u][v]边长来判断是否更新
                    if (!vis[v] && G[u][v] != INF && G[u][v] < d[v]) {
                        d[v] = G[u][v];
                    }
                }
            }
            return ans;
        }
    }

    /**
     * 邻接表版本
     */
    public static class Solution2 {
        int n;
        ArrayList[] adj = new ArrayList[MAXV]; // 邻接表
        int[] d = new int[MAXV];    // 顶点与最小生成树集合S的最小距离
        boolean[] vis = new boolean[MAXV];  // 标记数组, vis[v]=true表示v已经被访问
        Solution2(int n) {
            this.n = n;
        }
        int prim() {
            for (int i = 0; i < n; ++i) {
                d[i] = INF;
            }
            d[0] = 0;
            vis[0] = true;
            int ans = 0;    // 记录最小生成树的边权之和
            for (int i = 0; i < n; ++i) {
                int u = -1, MIN = INF;
                for (int j = 0; j < n; ++j) {
                    if (!vis[j] && d[j] < MIN) {
                        MIN = d[j];
                        u = j;
                    }
                }
                // 找不到
                if (u == -1) return -1;
                vis[u] = true;
                ans += d[u];
                for (Object obj: adj[u]) {
                    Node node = (Node)obj;
                    // 这里由于记录的是v距离已选择集合S的最近距离，u已经加入集合S，所以可通过判断G[u][v]边长来判断是否更新
                    if (!vis[node.v] && node.dis < d[node.v]) {
                        d[node.v] = node.dis;
                    }
                }
            }
            return ans;
        }
    }
}
