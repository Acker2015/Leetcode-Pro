package basic_data_structure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * shortest path faster algorithm
 */
public class SPFA {
    private class Node {
        int v;
        int dis; // 边权
    }
    private static final int MAXV = 1000;
    private static final int INF = 0x3fffffff;

    private ArrayList[] adj; // 邻接表
    int n;      // n为顶点数
    int[] d;    // 起点到达各点的最短路径长度
    int[] num;  // num数组用来记录节点入队列次数，辅助判断负环
    boolean[] inq;  // 顶点是否在队列中

    /**
     * time O(KE) -> k一般情况下不超过2
     * 如果存在负环，时间复杂度下降到O(VE)
     *
     * 根据Bellman算法，只有当某个顶点u的d[u]改变(变得更优)，从它出发的边的邻接点v的d[v]的值才有可能被改变
     * 这里借助队列，只考虑每一轮距离更新最优的顶点，进行下轮更新的备选中间节点
     * @param s
     * @return
     */
    private boolean spfa(int s) {
        for (int i = 0; i < n; ++i) {
            d[i] = INF;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        d[s] = 0;
        inq[s] = true;
        num[s] = 1;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            inq[u] = false;         // 设置u不在队列中
            // 遍历所有临接边
            for (Object obj_v: adj[u]) {
                Node node = (Node) obj_v;
                int v = node.v;
                // 松弛操作
                if (d[u] + node.dis < d[v]) {
                    d[v] = d[u] + node.dis;
                    if (!inq[v]) {
                        inq[v] = true;  // 标注已入队
                        queue.offer(v); // v顶点入队
                        num[v]++;       // v的入队次数+1
                        if (num[v] >= n) {
                            return false;   // 有可达负环
                        }
                    }
                }
            }
        }
        return true;
    }
}
